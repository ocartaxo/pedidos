package br.com.kofood.pedido.repository

import br.com.kofood.pedido.model.Order
import br.com.kofood.pedido.model.Status
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.*

interface OrdersRepository : JpaRepository<Order, Long> {

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Order o SET o.status = :status WHERE o = :order")
//    fun updateOrderStatus(status: Status, order: Order)

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.items WHERE o.id = :id")
    fun getOrderByIdWithItems(id: Long): Optional<Order>
}