package com.api.oficina.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.oficina.infrastructure.repository.UsuarioRecursoPermissaoRepository;
import com.api.oficina.infrastructure.repository.UsuarioRepository;
import com.api.oficina.model.Endereco;
import com.api.oficina.model.Telefone;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.oficina.dto.LoginRequest;
import com.api.oficina.dto.LoginResponse;
import com.api.oficina.dto.RecursoPermissaoDTO;
import com.api.oficina.dto.UsuarioDTO;
import com.api.oficina.infrastructure.repository.OficinaRepository;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.Usuario;
import com.api.oficina.model.UsuarioRecursoPermissao;
import com.api.oficina.modelEnum.Permission;
import com.api.oficina.modelEnum.Recurso;
import com.api.oficina.security.util.JwtUtil;
import com.api.oficina.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private UsuarioRepository usuarioRepository;
    private UsuarioRecursoPermissaoRepository usuarioRecursoPermissaoRepository;
    private OficinaRepository oficinaRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           UsuarioRecursoPermissaoRepository usuarioRecursoPermissaoRepository,
                           OficinaRepository oficinaRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil,
                           AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioRecursoPermissaoRepository = usuarioRecursoPermissaoRepository;
        this.oficinaRepository = oficinaRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", usuario.getRole().name());
        claims.put("userId", usuario.getId());

        String token = jwtUtil.generateToken(usuario, claims);

        // Agrupa permissões por recurso
        List<RecursoPermissaoDTO> recursoPermissoes = agruparPermissoesPorRecurso(usuario);

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setType("Bearer");
        response.setId(usuario.getId());
        response.setUsername(usuario.getUsername());
        response.setEmail(usuario.getEmail());
        response.setNome(usuario.getNome());
        response.setRole(usuario.getRole());
        response.setRecursoPermissoes(recursoPermissoes);
        response.setOficinaId(usuario.getOficina() != null ? usuario.getOficina().getId() : null);

        return response;
    }

    @Override
    @Transactional
    public UsuarioDTO registrar(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByUsername(usuarioDTO.getUsername())) {
            throw new RuntimeException("Username já existe");
        }

        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new RuntimeException("Email já existe");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setRole(usuarioDTO.getRole());
        usuario.setAtivo(true);
        usuario.setCriadoEm(LocalDateTime.now());
        usuario.setAtualizadoEm(LocalDateTime.now());

        if (usuarioDTO.getOficinaId() != null) {
            Oficina oficina = oficinaRepository.findById(usuarioDTO.getOficinaId())
                .orElseThrow(() -> new RuntimeException("Oficina não encontrada"));
            usuario.setOficina(oficina);
        }

        usuario = usuarioRepository.save(usuario);

        // Processar telefones
        if (usuarioDTO.getTelefones() != null && !usuarioDTO.getTelefones().isEmpty()) {
            List<Telefone> telefones = new ArrayList<>();
            for (Telefone telefoneDTO : usuarioDTO.getTelefones()) {
                Telefone telefone = new Telefone();
                telefone.setTipo(telefoneDTO.getTipo());
                telefone.setCountry(telefoneDTO.getCountry());
                telefone.setDdd(telefoneDTO.getDdd());
                telefone.setNumero(telefoneDTO.getNumero());
                telefone.setPessoa(usuario);
                telefones.add(telefone);
            }
            usuario.setTelefone(telefones);
        }

        // Processar endereços
        if (usuarioDTO.getEnderecos() != null && !usuarioDTO.getEnderecos().isEmpty()) {
            List<Endereco> enderecos = new ArrayList<>();
            for (Endereco enderecoDTO : usuarioDTO.getEnderecos()) {
                Endereco endereco = new Endereco();
                endereco.setRua(enderecoDTO.getRua());
                endereco.setNumero(enderecoDTO.getNumero());
                endereco.setPostcode(enderecoDTO.getPostcode());
                endereco.setCidade(enderecoDTO.getCidade());
                endereco.setPessoa(usuario);
                enderecos.add(endereco);
            }
            usuario.setEndereco(enderecos);
        }

        // Sistema de permissões por recurso
        if (usuarioDTO.getRecursoPermissoes() != null && !usuarioDTO.getRecursoPermissoes().isEmpty()) {
            for (RecursoPermissaoDTO recursoPermissaoDTO : usuarioDTO.getRecursoPermissoes()) {
                Recurso recurso = recursoPermissaoDTO.getRecurso();
                for (Permission permission : recursoPermissaoDTO.getPermissoes()) {
                    UsuarioRecursoPermissao urp = new UsuarioRecursoPermissao();
                    urp.setUsuario(usuario);
                    urp.setRecurso(recurso);
                    urp.setPermission(permission);
                    usuarioRecursoPermissaoRepository.save(urp);
                }
            }
        }

        return convertToDTO(usuarioRepository.findById(usuario.getId()).orElseThrow());
    }

    @Override
    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuarioDTO.getNome() != null) {
            usuario.setNome(usuarioDTO.getNome());
        }

        if (usuarioDTO.getEmail() != null && !usuarioDTO.getEmail().equals(usuario.getEmail())) {
            if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
                throw new RuntimeException("Email já existe");
            }
            usuario.setEmail(usuarioDTO.getEmail());
        }

        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }

        if (usuarioDTO.getRole() != null) {
            usuario.setRole(usuarioDTO.getRole());
        }

        if (usuarioDTO.getAtivo() != null) {
            usuario.setAtivo(usuarioDTO.getAtivo());
        }

        if (usuarioDTO.getOficinaId() != null) {
            Oficina oficina = oficinaRepository.findById(usuarioDTO.getOficinaId())
                .orElseThrow(() -> new RuntimeException("Oficina não encontrada"));
            usuario.setOficina(oficina);
        }

        // Atualizar telefones
        if (usuarioDTO.getTelefones() != null) {
            usuario.getTelefone().clear();
            for (Telefone telefoneDTO : usuarioDTO.getTelefones()) {
                Telefone telefone = new Telefone();
                if (telefoneDTO.getId_telefone() != null) {
                    telefone.setId_telefone(telefoneDTO.getId_telefone());
                }
                telefone.setTipo(telefoneDTO.getTipo());
                telefone.setCountry(telefoneDTO.getCountry());
                telefone.setDdd(telefoneDTO.getDdd());
                telefone.setNumero(telefoneDTO.getNumero());
                telefone.setPessoa(usuario);
                usuario.getTelefone().add(telefone);
            }
        }

        // Atualizar endereços
        if (usuarioDTO.getEnderecos() != null) {
            usuario.getEndereco().clear();
            for (Endereco enderecoDTO : usuarioDTO.getEnderecos()) {
                Endereco endereco = new Endereco();
                if (enderecoDTO.getId_endereco() != null) {
                    endereco.setId_endereco(enderecoDTO.getId_endereco());
                }
                endereco.setRua(enderecoDTO.getRua());
                endereco.setNumero(enderecoDTO.getNumero());
                endereco.setPostcode(enderecoDTO.getPostcode());
                endereco.setCidade(enderecoDTO.getCidade());
                endereco.setPessoa(usuario);
                usuario.getEndereco().add(endereco);
            }
        }

        // Atualiza permissões por recurso
        if (usuarioDTO.getRecursoPermissoes() != null) {
            usuario.getRecursoPermissoes().clear();
            for (RecursoPermissaoDTO recursoPermissaoDTO : usuarioDTO.getRecursoPermissoes()) {
                Recurso recurso = recursoPermissaoDTO.getRecurso();
                for (Permission permission : recursoPermissaoDTO.getPermissoes()) {
                    UsuarioRecursoPermissao urp = new UsuarioRecursoPermissao();
                    urp.setUsuario(usuario);
                    urp.setRecurso(recurso);
                    urp.setPermission(permission);
                    usuario.getRecursoPermissoes().add(urp);
                }
            }
        }

        usuario.setAtualizadoEm(LocalDateTime.now());
        usuario = usuarioRepository.save(usuario);

        return convertToDTO(usuario);
    }

    @Override
    @Transactional
    public void deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return convertToDTO(usuario);
    }

    @Override
    public UsuarioDTO buscarPorUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return convertToDTO(usuario);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        // Agrupa permissões por recurso
        List<RecursoPermissaoDTO> recursoPermissoes = agruparPermissoesPorRecurso(usuario);

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setUsername(usuario.getUsername());
        dto.setRole(usuario.getRole());
        dto.setRecursoPermissoes(recursoPermissoes);
        dto.setOficinaId(usuario.getOficina() != null ? usuario.getOficina().getId() : null);
        dto.setAtivo(usuario.getAtivo());

        // Converter telefones
        if (usuario.getTelefone() != null && !usuario.getTelefone().isEmpty()) {
            List<Telefone> telefonesDTO = usuario.getTelefone().stream()
                .map(telefone -> {
                    Telefone telefoneDTO = new Telefone();
                    telefoneDTO.setId_telefone(telefone.getId_telefone());
                    telefoneDTO.setTipo(telefone.getTipo());
                    telefoneDTO.setCountry(telefone.getCountry());
                    telefoneDTO.setDdd(telefone.getDdd());
                    telefoneDTO.setNumero(telefone.getNumero());
                    return telefoneDTO;
                })
                .collect(Collectors.toList());
            dto.setTelefones(telefonesDTO);
        }

        // Converter endereços
        if (usuario.getEndereco() != null && !usuario.getEndereco().isEmpty()) {
            List<Endereco> enderecosDTO = usuario.getEndereco().stream()
                .map(endereco -> {
                    Endereco enderecoDTO = new Endereco();
                    enderecoDTO.setId_endereco(endereco.getId_endereco());
                    enderecoDTO.setRua(endereco.getRua());
                    enderecoDTO.setNumero(endereco.getNumero());
                    enderecoDTO.setPostcode(endereco.getPostcode());
                    enderecoDTO.setCidade(endereco.getCidade());
                    return enderecoDTO;
                })
                .collect(Collectors.toList());
            dto.setEnderecos(enderecosDTO);
        }

        return dto;
    }

    /**
     * Agrupa as permissões do usuário por recurso
     */
    private List<RecursoPermissaoDTO> agruparPermissoesPorRecurso(Usuario usuario) {
        Map<Recurso, Set<Permission>> recursosMap = usuario.getRecursoPermissoes().stream()
            .collect(Collectors.groupingBy(
                UsuarioRecursoPermissao::getRecurso,
                Collectors.mapping(UsuarioRecursoPermissao::getPermission, Collectors.toSet())
            ));

        List<RecursoPermissaoDTO> result = new ArrayList<>();
        for (Map.Entry<Recurso, Set<Permission>> entry : recursosMap.entrySet()) {
            result.add(new RecursoPermissaoDTO(entry.getKey(), entry.getValue()));
        }

        return result;
    }
}
