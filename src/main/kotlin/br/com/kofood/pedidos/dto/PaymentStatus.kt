package br.com.kofood.pedidos.dto

enum class PaymentStatus {
    CREATED,
    CONFIRMED,
    CANCELLED,
    CONFIRMED_WITHOUT_INTEGRATION
}