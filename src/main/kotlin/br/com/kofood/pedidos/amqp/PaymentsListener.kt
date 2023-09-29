package br.com.kofood.pedidos.amqp

import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class PaymentsListener {

    @RabbitListener(queues = ["pagamento.concluido"])
    fun getMessage(message: Message) {
        println("Recebi a mensagem: ${message.body}")
    }

}