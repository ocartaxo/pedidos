package br.com.kofood.pedidos.controller

import br.com.kofood.pedidos.dto.OrderRequest
import br.com.kofood.pedidos.dto.OrderResponse
import br.com.kofood.pedidos.dto.StatusDto
import br.com.kofood.pedidos.service.OrdersService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/pedidos")
class OrdersController(
    private val service: OrdersService
) {

    @PostMapping
    fun registerOrder(
        request: OrderRequest,
        builder: UriComponentsBuilder
    ): ResponseEntity<OrderResponse> {

        val response = service.registerOrder(request)
        val uri = builder.path("/pedidos/{id}").buildAndExpand(response.id).toUri()

        return ResponseEntity.created(uri).body(response)
    }


    @GetMapping
    fun list(@PageableDefault(size = 10) page: Pageable) = ResponseEntity.ok().body(service.getAll(page))

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long) = ResponseEntity.ok().body(service.getById(id))

    @GetMapping("/porta")
    fun getPort(@Value("\${local.server.port}") porta: String) = "Requisição respondida pela instância executando na porta $porta"

    @PutMapping("/{id}/status")
    fun updateStatus(
        @PathVariable id: Long,
        @RequestBody newStatus: StatusDto
    ) = ResponseEntity.ok().body(service.updateStatus(id, newStatus))

    @PutMapping("/{id}/pago")
    fun approvePayment(@PathVariable id: Long) = ResponseEntity.ok().body(service.approvePayment(id))

}