package com.generation.lojagames.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.lojagames.model.Pedido;
import com.generation.lojagames.repository.PedidoRepository;
import com.generation.lojagames.repository.ProdutoRepository;
import com.generation.lojagames.repository.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	public Optional<Pedido> getById(Long id) {
		return pedidoRepository.findById(id);
	}

	public Optional<Pedido> post(Pedido pedido, String emailUsuarioLogado) {

		return usuarioRepository.findByEmail(emailUsuarioLogado)
				.flatMap(usuario -> produtoRepository.findById(pedido.getProduto().getId())
						.map(produto -> {
							pedido.setId(null);
							pedido.setUsuario(usuario);
							pedido.setProduto(produto);
							pedido.setValorTotal(produto.getPreco().multiply(BigDecimal.valueOf(pedido.getQuantidade())));
							return pedidoRepository.save(pedido);
						}));
	}

	public boolean delete(Long id) {
		return pedidoRepository.findById(id)
				.map(resposta -> {
					pedidoRepository.deleteById(id);
					return true;
				})
				.orElse(false);
	}
}
