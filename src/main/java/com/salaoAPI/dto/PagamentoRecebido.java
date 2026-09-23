package com.salaoAPI.dto;

import com.salaoAPI.entidades.enums.FormaPagamento;

import jakarta.validation.constraints.NotNull;

public class PagamentoRecebido {

		@NotNull(message = "O Metodo De Pagamento e Obrigatorio (1-Dinheiro , 2 -Cartao , 3- Pix)")
		private FormaPagamento formaPagamento;

		public FormaPagamento getStatusRecebimento() {
			return formaPagamento;
		}

		public void setStatusRecebimento(FormaPagamento formaPagamento) {
			this.formaPagamento = formaPagamento;
		}
		
		
} 
