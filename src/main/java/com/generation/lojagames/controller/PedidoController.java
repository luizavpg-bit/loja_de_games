package com.generation.lojagames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames.model.Pedido;
import com.generation.lojagames.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> getAll() {
		return ResponseEntity.ok(pedidoService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getById(@PathVariable Long id) {
		return pedidoService.getById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Pedido> postPedido(@Valid @RequestBody Pedido pedido, Authentication authentication) {
		return pedidoService.post(pedido, authentication.getName())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePedido(@PathVariable Long id) {
		return pedidoService.delete(id)
				? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
				: ResponseEntity.notFound().build();
	}
}
