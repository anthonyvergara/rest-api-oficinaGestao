package com.api.oficina.security.aspect;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.api.oficina.model.Usuario;
import com.api.oficina.modelEnum.Permission;
import com.api.oficina.modelEnum.Recurso;
import com.api.oficina.modelEnum.Role;
import com.api.oficina.security.annotation.RequiresPermission;

@Aspect
@Component
public class PermissionAspect {

    @Before("@annotation(requiresPermission)")
    public void checkPermission(JoinPoint joinPoint, RequiresPermission requiresPermission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Usuário não autenticado");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof Usuario)) {
            throw new AccessDeniedException("Usuário inválido");
        }

        Usuario usuario = (Usuario) principal;

        // ADMIN e DONO tem acesso total - não precisa validar permissões
        if (usuario.getRole() == Role.ADMIN || usuario.getRole() == Role.DONO) {
            return; // Libera acesso
        }

        // Para FUNCIONARIO, valida permissões por recurso
        Permission[] requiredPermissions = requiresPermission.value();
        Recurso recurso = requiresPermission.recurso();

        // Extrai as permissões do usuário para o recurso específico
        Set<Permission> userPermissionsForRecurso = usuario.getRecursoPermissoes()
                .stream()
                .filter(rp -> rp.getRecurso() == recurso)
                .map(rp -> rp.getPermission())
                .collect(Collectors.toSet());

        // Verifica se o usuário tem pelo menos uma das permissões requeridas para o recurso
        boolean hasPermission = Arrays.stream(requiredPermissions)
                .anyMatch(userPermissionsForRecurso::contains);

        if (!hasPermission) {
            throw new AccessDeniedException(
                String.format("Usuário não possui permissão para realizar esta ação no recurso %s. Permissões necessárias: %s",
                    recurso.name(),
                    Arrays.toString(requiredPermissions))
            );
        }
    }
}
