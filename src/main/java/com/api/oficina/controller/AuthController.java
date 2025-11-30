package com.api.oficina.controller;

import com.api.oficina.modelEnum.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.oficina.dto.LoginRequest;
import com.api.oficina.dto.LoginResponse;
import com.api.oficina.dto.UsuarioDTO;
import com.api.oficina.modelEnum.Permission;
import com.api.oficina.security.annotation.RequiresPermission;
import com.api.oficina.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/oficina/api/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação e gerenciamento de usuários")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Autentica um usuário e retorna um token JWT")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registrar")
    //@RequiresPermission(value = {Permission.INSERIR}, recurso = Recurso.USUARIO)
    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário no sistema")
    public ResponseEntity<UsuarioDTO> registrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO response = authService.registrar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/usuarios/{id}")
    @RequiresPermission({Permission.EDITAR})
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO response = authService.atualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/usuarios/{id}")
    @RequiresPermission({Permission.DELETAR})
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        authService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/{id}")
    @RequiresPermission({Permission.CONSULTAR})
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        UsuarioDTO response = authService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuarios/username/{username}")
    @RequiresPermission({Permission.CONSULTAR})
    @Operation(summary = "Buscar usuário por username", description = "Retorna os dados de um usuário pelo username")
    public ResponseEntity<UsuarioDTO> buscarPorUsername(@PathVariable String username) {
        UsuarioDTO response = authService.buscarPorUsername(username);
        return ResponseEntity.ok(response);
    }
}

