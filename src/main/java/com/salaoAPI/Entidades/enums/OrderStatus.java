package com.salaoAPI.Entidades.enums;

public enum OrderStatus {
	AGUARDANDO_FILA(1),
	ATENDIMENTO(2),
	FINALIZADO(3),
	CANCELADO(4);

	
	private int code;
	
	private OrderStatus(int code) {
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code)  {
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo Invalido do Status de pedido");
	}
}
