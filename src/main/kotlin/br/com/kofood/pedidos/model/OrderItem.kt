package br.com.kofood.pedidos.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive


@Entity
@Table(name="itens_do_pedido")
data class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ?= null,

    @NotNull
    @Positive
    @Column(name = "quantidade")
    val quantity: Int,

    @Column(name="descricao")
    val description: String,

    @ManyToOne(optional = false)
    val order: Order
)
