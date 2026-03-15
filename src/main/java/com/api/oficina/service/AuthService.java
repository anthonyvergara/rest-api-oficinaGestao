package com.api.oficina.service;

import com.api.oficina.dto.LoginRequest;
import com.api.oficina.dto.LoginResponse;
import com.api.oficina.dto.UsuarioDTO;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    UsuarioDTO registrar(UsuarioDTO usuarioDTO);

    UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    void deletarUsuario(Long id);

    UsuarioDTO buscarPorId(Long id);

    UsuarioDTO buscarPorUsername(String username);
}

