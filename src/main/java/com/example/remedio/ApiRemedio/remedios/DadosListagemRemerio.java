package com.example.remedio.ApiRemedio.remedios;

import java.time.LocalDate;

public record DadosListagemRemerio(
		Long id,
		String nome, 
		Via via, 
		String lote, 
		Laboratorio laboratorio, 
		LocalDate validade) {

	public DadosListagemRemerio(Remedio remedio) {
		this(
		remedio.getId(),
		remedio.getNome(), 
		remedio.getVia(),
		remedio.getLote(),
		remedio.getLaboratorio(), 
		remedio.getValidade()
		);
	}

}
