package com.salaoAPI.dto;

import com.salaoAPI.entidades.enums.FormaPagamento;

import jakarta.validation.constraints.NotNull;

public record PagamentoRecebido(
	    @NotNull(message = "A forma de pagamento é obrigatória (DINHEIRO, CARTAO ou PIX)")
	    FormaPagamento formaPagamento
	) {
	}
