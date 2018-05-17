package com.example.demo.Repository;

import com.example.demo.Entity.LogisticsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

public interface LogisticsRecordRepository extends JpaRepository<LogisticsRecord,Integer> {

    //create logistics record
    LogisticsRecord saveAndFlush(LogisticsRecord record);

    //find logistics record by id
    Optional<LogisticsRecord> findById(Integer id);

    //alter the status of logistics
    @Modifying
    @Transactional
    @Query(value = "update LogisticsRecord set logisticsStatus=?1 where id=?2 and orderId=?3")
    void updateLogisticsRecordState(String state,Integer id,Integer orderId);

    @Modifying
    @Transactional
    @Query(value = "update LogisticsRecord set deliveryTime=?1 where id=?2")
    void updatedeliveryTime(Date date, Integer orderId);

    @Modifying
    @Transactional
    @Query(value = "update LogisticsRecord set signedTime=?1 where id=?2")
    void updateSignedTime(Date date, Integer orderId);
}
