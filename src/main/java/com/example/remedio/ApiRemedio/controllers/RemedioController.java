package com.example.remedio.ApiRemedio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.remedio.ApiRemedio.remedios.DadosAtualizarRemedio;
import com.example.remedio.ApiRemedio.remedios.DadosCadastroRemedio;
import com.example.remedio.ApiRemedio.remedios.DadosListagemRemerio;
import com.example.remedio.ApiRemedio.remedios.Remedio;
import com.example.remedio.ApiRemedio.remedios.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

	@Autowired
	private RemedioRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroRemedio dados) {
		repository.save(new Remedio(dados));
	}

	@GetMapping
	public List<DadosListagemRemerio> listar() {
		return repository.findAllByAtivoTrue().stream().map(DadosListagemRemerio::new).toList();
	}

	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@DeleteMapping("inativar/{id}")
	@Transactional
	public void inativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();
	}

}
