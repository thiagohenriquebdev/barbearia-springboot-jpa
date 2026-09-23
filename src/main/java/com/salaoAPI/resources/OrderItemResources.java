package com.salaoAPI.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salaoAPI.entidades.OrderItem;
import com.salaoAPI.services.OrderItemService;

@RestController
@RequestMapping("/atendimentos/{atendimentoId}/itens")
public class OrderItemResources {

	@Autowired
	private OrderItemService orderItemService;

	@PostMapping
	public ResponseEntity<OrderItem> registra(
			@PathVariable Long atendimentoId,
			@RequestParam Long produtoId,
			@RequestParam Integer quantidade) {
		OrderItem item = orderItemService.registraConsumo(atendimentoId, produtoId, quantidade);
		return ResponseEntity.ok(item);
	}

	@DeleteMapping
	public ResponseEntity<Void> cancelar(
			@PathVariable Long atendimentoId,
			@RequestParam Long produtoId,
			@RequestParam Integer quantidade) {
		orderItemService.cancelar(atendimentoId, produtoId, quantidade);
		return ResponseEntity.noContent().build();
	}
}
