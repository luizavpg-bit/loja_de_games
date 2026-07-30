package com.generation.lojagames.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.model.UsuarioLogin;
import com.generation.lojagames.repository.UsuarioRepository;
import com.generation.lojagames.security.JWTUtil;

@Service
public class UsuarioService {

	private static final int IDADE_MINIMA = 18;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JWTUtil jwtUtil;

	public Usuario cadastrarUsuario(Usuario usuario) {

		if (usuario.getIdade() == null || usuario.getIdade() < IDADE_MINIMA) {
			throw new IllegalArgumentException("Cadastro permitido apenas para maiores de 18 anos");
		}

		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Este e-mail já está cadastrado!");
		}

		usuario.setId(null);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

		return usuarioRepository.save(usuario);
	}

	public Optional<UsuarioLogin> autenticar(UsuarioLogin usuarioLogin) {

		var credentials = new UsernamePasswordAuthenticationToken(
				usuarioLogin.getEmail(), usuarioLogin.getSenha());

		Authentication authentication = authenticationManager.authenticate(credentials);

		if (!authentication.isAuthenticated()) {
			return Optional.empty();
		}

		Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.getEmail());

		if (usuario.isEmpty()) {
			return Optional.empty();
		}

		String token = jwtUtil.generateToken(usuario.get().getEmail());

		UsuarioLogin resposta = new UsuarioLogin();
		resposta.setId(usuario.get().getId());
		resposta.setNome(usuario.get().getNome());
		resposta.setEmail(usuario.get().getEmail());
		resposta.setToken("Bearer " + token);

		return Optional.of(resposta);
	}
}
