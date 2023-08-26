package br.com.kofood.pedido.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime


@Entity
@Table(name = "pedidos")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotNull
    @Column(name="data_hora")
    val dateTime: LocalDateTime = LocalDateTime.now(),

    @NotNull
    @Enumerated(EnumType.STRING)
    var status: Status = Status.ACCOMPLISHED,

    @OneToMany(cascade = [CascadeType.PERSIST], mappedBy = "order")
    val items: MutableList<OrderItem> = mutableListOf()
)
