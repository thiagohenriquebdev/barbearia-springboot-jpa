package com.salaoAPI.entidades.enums;

public enum StatusAtendimento {
	AGUARDANDO_FILA(1),
	ATENDIMENTO(2),
	FINALIZADO(3),
	CANCELADO(4);

	
	private int code;
	
	private StatusAtendimento(int code) {
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static StatusAtendimento valueOf(int code)  {
		for (StatusAtendimento value : StatusAtendimento.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo Invalido do Status de pedido");
	}
}
