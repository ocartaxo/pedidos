package br.com.kofood.pedidos.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderItemDto(
    val id: Long,
    @JsonProperty("quantidade")
    val quantity: Int,
    @JsonProperty("descricao")
    val description: String
)
