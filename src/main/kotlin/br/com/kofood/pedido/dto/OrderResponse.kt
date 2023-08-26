package br.com.kofood.pedido.dto

import br.com.kofood.pedido.model.Status
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class OrderResponse(
    val id: Long,
    @JsonProperty("data_hora")
    val dateTime: LocalDateTime,
    @JsonProperty("itens")
    val items: List<OrderItemDto> = emptyList(),
    val status: Status

)
