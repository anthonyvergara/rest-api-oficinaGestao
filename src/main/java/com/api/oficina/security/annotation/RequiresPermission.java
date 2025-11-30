package com.api.oficina.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.api.oficina.modelEnum.Permission;
import com.api.oficina.modelEnum.Recurso;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {

    /**
     * Permissões necessárias (CONSULTAR, INSERIR, EDITAR, DELETAR)
     */
    Permission[] value();

    /**
     * Recurso que está sendo acessado (USUARIO, ORDEM_SERVICO, PAGAMENTO, BOOKING)
     * Se não especificado, valida apenas a permissão genérica (comportamento antigo)
     */
    Recurso recurso() default Recurso.USUARIO;
}
