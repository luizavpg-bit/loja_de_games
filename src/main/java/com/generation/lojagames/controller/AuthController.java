package com.generation.lojagames.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.model.UsuarioLogin;
import com.generation.lojagames.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario) {
		try {
			Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
			novoUsuario.setSenha(null); // nunca devolve a senha (nem o hash) na resposta
			return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
		}
	}

	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> login(@RequestBody UsuarioLogin usuarioLogin) {
		return usuarioService.autenticar(usuarioLogin)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
}
