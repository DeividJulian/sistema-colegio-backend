package com.colegio.controller;

import com.colegio.dto.*;
import com.colegio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO req) {
        AuthResponseDTO resp = usuarioService.login(req.getUsuario(), req.getContrasena());
        if (resp == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"Credenciales incorrectas\"}");
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenDTO req) {
        String nuevo = usuarioService.renovarToken(req.getRefreshToken());
        if (nuevo == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"Refresh token inválido\"}");
        return ResponseEntity.ok(new AuthResponseDTO(nuevo, req.getRefreshToken(), ""));
    }
}
