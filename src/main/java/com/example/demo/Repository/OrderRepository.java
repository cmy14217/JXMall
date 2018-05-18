package com.example.demo.Repository;

import com.example.demo.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    //create new Order
    Orders saveAndFlush(Orders order);

    //alter the status of order
    @Modifying
    @Transactional
    @Query(value = "update Orders set status=?1 where id=?2")
    void updateOrderStatus(String status,Integer id);

    //show all orders
    List<Orders> findAll();

    //find order by orderId
    Optional<Orders> findById(Integer id);

    //find order by UseId
    Optional<List<Orders>> findByUserId(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update Orders set paidTime=?1 where id=?2")
    void updatePaidTime(Date date,Integer id);

    @Modifying
    @Transactional
    @Query(value = "update Orders set withdrawnTime=?1 where id=?2")
    void updateWithdrawnTime(Date date,Integer id);

    @Modifying
    @Transactional
    @Query(value = "update Orders set finishTime=?1 where id=?2")
    void updateFinishTime(Date date,Integer id);

    @Modifying
    @Transactional
    @Query(value = "update Orders set totalPrice=?1 where id=?2")
    void updateTotalPrice(Float price,Integer id);
}
