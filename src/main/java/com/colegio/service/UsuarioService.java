package com.colegio.service;

import com.colegio.dto.AuthResponseDTO;
import com.colegio.model.Usuario;
import com.colegio.repository.UsuarioRepository;
import com.colegio.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired private UsuarioRepository repo;
    @Autowired private JwtUtil jwtUtil;

    public AuthResponseDTO login(String usuario, String contrasena) {
        Optional<Usuario> opt = repo.findByUsuario(usuario);
        if (opt.isEmpty()) return null;
        Usuario u = opt.get();
        if (!u.getContrasena().equals(contrasena)) return null;
        if (u.getEstado() != Usuario.Estado.ACTIVO) return null;
        return new AuthResponseDTO(
            jwtUtil.generarToken(u.getUsuario(), u.getRol().name()),
            jwtUtil.generarRefreshToken(u.getUsuario()),
            u.getRol().name()
        );
    }

    public String renovarToken(String refreshToken) {
        if (!jwtUtil.esRefreshValido(refreshToken)) return null;
        String username = jwtUtil.getUsuario(refreshToken);
        return repo.findByUsuario(username)
            .map(u -> jwtUtil.generarToken(u.getUsuario(), u.getRol().name()))
            .orElse(null);
    }
}
