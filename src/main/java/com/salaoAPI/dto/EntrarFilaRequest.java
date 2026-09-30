package com.salaoAPI.dto;

import jakarta.validation.constraints.NotBlank;

public record EntrarFilaRequest(
		@NotBlank(message = "Nome e Obrigatorio")
		String nome
		) {}
