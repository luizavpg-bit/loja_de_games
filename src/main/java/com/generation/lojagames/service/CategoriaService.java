package com.generation.lojagames.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.lojagames.model.Categoria;
import com.generation.lojagames.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> listarTodas() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> buscarPorId(Long id) {
		return categoriaRepository.findById(id);
	}

	public List<Categoria> buscarPorTipo(String tipo) {
		return categoriaRepository.findAllByTipoContainingIgnoreCase(tipo);
	}

	public Categoria criar(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Optional<Categoria> atualizar(Categoria categoria) {
		return categoriaRepository.findById(categoria.getId())
				.map(existente -> categoriaRepository.save(categoria));
	}

	public boolean deletar(Long id) {
		return categoriaRepository.findById(id)
				.map(existente -> {
					categoriaRepository.deleteById(id);
					return true;
				})
				.orElse(false);
	}
}
