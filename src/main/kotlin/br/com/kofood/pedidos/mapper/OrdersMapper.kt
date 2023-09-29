package br.com.kofood.pedidos.mapper

import br.com.kofood.pedidos.dto.OrderItemDto
import br.com.kofood.pedidos.dto.OrderRequest
import br.com.kofood.pedidos.dto.OrderResponse
import br.com.kofood.pedidos.model.Order
import br.com.kofood.pedidos.model.OrderItem
import org.springframework.stereotype.Component

@Component
object OrdersMapper {

    fun toDto(o: Order) = OrderResponse(
        id = o.id!!,
        dateTime = o.dateTime,
        status = o.status,
        items = o.items.map { i -> OrderItemDto(
            id = i.id!!,
            quantity = i.quantity,
            description = i.description
        ) }
    )

    fun toEntity(newOrder: OrderRequest): Order {
        val o = Order()

        o.items.addAll(newOrder.items.map {i ->
            OrderItem(
                id = i.id,
                quantity = i.quantity,
                description = i.description,
                order = o
            )
        })

        return o
    }


}