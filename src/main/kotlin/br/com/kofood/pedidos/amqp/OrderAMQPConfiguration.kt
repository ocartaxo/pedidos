package br.com.kofood.pedidos.amqp

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderAMQPConfiguration {

    @Bean
    fun rabbitTemplate(
        cf: ConnectionFactory,
        mc: Jackson2JsonMessageConverter
    ): RabbitTemplate {
        val rt = RabbitTemplate(cf)
        rt.messageConverter = mc

        return rt
    }

    @Bean
    fun jackson2JsonMessageConverter() = Jackson2JsonMessageConverter()

    @Bean
    fun orderDetailsQueue(): Queue = QueueBuilder
        .nonDurable(PAYMENT_ORDER_DETAILS)
        .build()

    @Bean
    fun fanoutExchange(): FanoutExchange = ExchangeBuilder
        .fanoutExchange(PAYMENT_EXCHANGE)
        .build()

    @Bean
    fun bindPaymentOrder(fe: FanoutExchange, queue: Queue): Binding = BindingBuilder.bind(queue).to(fe)

    companion object {
        const val PAYMENT_EXCHANGE = "payment.exchange"
        const val PAYMENT_ORDER_DETAILS = "payment.order-details"
    }

}