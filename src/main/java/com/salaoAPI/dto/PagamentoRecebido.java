package com.salaoAPI.dto;

import com.salaoAPI.Entidades.enums.StatusRecebimento;

import jakarta.validation.constraints.NotNull;

public class PagamentoRecebido {

		@NotNull(message = "O Metodo De Pagamento e Obrigatorio (1-Dinheiro , 2 -Cartao , 3- Pix)")
		private StatusRecebimento statusRecebimento;

		public StatusRecebimento getStatusRecebimento() {
			return statusRecebimento;
		}

		public void setStatusRecebimento(StatusRecebimento statusRecebimento) {
			this.statusRecebimento = statusRecebimento;
		}
		
		
} 
