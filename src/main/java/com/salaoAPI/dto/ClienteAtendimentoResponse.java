package com.salaoAPI.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ClienteAtendimentoResponse(long id,String nome,
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss",timezone = "America/Sao_Paulo")
	Instant inicioAtendimento){}
