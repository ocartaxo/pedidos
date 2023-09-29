package br.com.kofood.pedidos.service

import br.com.kofood.pedidos.dto.OrderRequest
import br.com.kofood.pedidos.dto.OrderResponse
import br.com.kofood.pedidos.dto.StatusDto
import br.com.kofood.pedidos.mapper.OrdersMapper
import br.com.kofood.pedidos.model.Status
import br.com.kofood.pedidos.repository.OrdersRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class OrdersService(
    private val repository: OrdersRepository,
    private val mapper: OrdersMapper
) {

    fun getAll(page: Pageable) = repository.findAll(page).map { mapper::toDto }

    fun getById(id: Long) = repository.findById(id)
        .orElseThrow { EntityNotFoundException("Pedido não encontrado") }
        .let { o -> mapper.toDto(o) }

    fun registerOrder(newOrder: OrderRequest): OrderResponse {

        val o = mapper.toEntity(newOrder)

        repository.save(o)

        return mapper.toDto(o)

    }


    fun updateStatus(id: Long, newStatus: StatusDto): OrderResponse {
        val o = repository.getOrderByIdWithItems(id).orElseThrow {
            EntityNotFoundException("Pedido não encontrado!")
        }

        o.status = newStatus.status

        return mapper.toDto(o)
    }


    fun approvePayment(id: Long) {

        val o = repository.getOrderByIdWithItems(id)
            .orElseThrow { EntityNotFoundException("Pedido não encontrado!") }

        o.status = Status.PAID_OUT

    }
}