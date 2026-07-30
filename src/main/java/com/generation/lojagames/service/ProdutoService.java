package com.generation.lojagames.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> getAll() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> getById(Long id) {
		return produtoRepository.findById(id);
	}

	public List<Produto> getByNome(String nome) {
		return produtoRepository.findAllByNomeContainingIgnoreCase(nome);
	}

	public List<Produto> getByPrecoMaiorQue(BigDecimal preco) {
		return produtoRepository.findAllByPrecoGreaterThanOrderByPreco(preco);
	}

	public List<Produto> getByPrecoMenorQue(BigDecimal preco) {
		return produtoRepository.findAllByPrecoLessThanOrderByPrecoDesc(preco);
	}

	// mesma lógica do professor: só salva se a categoria informada existir
	public Optional<Produto> post(Produto produto) {
		produto.setId(null);
		return categoriaRepository.findById(produto.getCategoria().getId())
				.map(resposta -> produtoRepository.save(produto));
	}

	public Optional<Produto> put(Produto produto) {
		if (produtoRepository.existsById(produto.getId())) {
			return categoriaRepository.findById(produto.getCategoria().getId())
					.map(resposta -> produtoRepository.save(produto));
		}
		return Optional.empty();
	}

	public boolean delete(Long id) {
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return true;
				})
				.orElse(false);
	}
}
