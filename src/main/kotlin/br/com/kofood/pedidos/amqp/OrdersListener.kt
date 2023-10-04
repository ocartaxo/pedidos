package br.com.kofood.pedidos.amqp

import br.com.kofood.pedidos.amqp.OrderAMQPConfiguration.Companion.PAYMENT_ORDER_DETAILS
import br.com.kofood.pedidos.dto.PaymentResponse
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class OrdersListener {

    @RabbitListener(queues = [PAYMENT_ORDER_DETAILS])
    fun getMessage(@Payload payment: PaymentResponse) {

        val message = """
            Dados do pagamento: ${payment.id}
            Número do pedido: ${payment.orderId}
            Valor: R$${payment.value}
            Status: ${payment.status}
        """.trimIndent()

        println("Pagamento recebido: $message")
    }

}