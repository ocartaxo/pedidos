package br.com.kofood.pedidos.dto

data class OrderRequest(
    val items: List<OrderItemDto>
)
