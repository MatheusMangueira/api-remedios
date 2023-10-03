package com.example.remedio.ApiRemedio.remedios;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Remedio")
@Entity(name = "remedio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Remedio {

	public Remedio(DadosCadastroRemedio dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.lote = dados.lote();
		this.laboratorio = dados.laboratorio();
		this.quantidade = dados.quantidade();
		this.validade = dados.validade();
		this.via = dados.via();
		this.laboratorio = dados.laboratorio();

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	Via via;
	@Enumerated(EnumType.STRING)
	Laboratorio laboratorio;

	private String nome;
	private String lote;
	private int quantidade;
	private LocalDate validade;

	private Boolean ativo;

	public void atualizarInformacoes(@Valid DadosAtualizarRemedio dados) {
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (dados.via() != null) {
			this.via = dados.via();
		}
		if (dados.laboratorio() != null) {
			this.laboratorio = dados.laboratorio();
		}

	}

	public void inativar() {
		this.ativo = false;

	}

	public void reativar() {
		this.ativo = true;

	}

}
