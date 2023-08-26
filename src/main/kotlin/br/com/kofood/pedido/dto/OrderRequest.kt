package br.com.kofood.pedido.dto

data class OrderRequest(
    val items: List<OrderItemDto>
)
