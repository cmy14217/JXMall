package com.example.demo.Controller;

import com.example.demo.Entity.LogisticsRecord;
import com.example.demo.Repository.LogisticsRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/logisticsRecords")
public class LogisticsRecordController {
    @Autowired
    private LogisticsRecordRepository logisticsRecordRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<LogisticsRecord> findRecordById(@PathVariable Integer id){
        Optional<LogisticsRecord> option = logisticsRecordRepository.findById(id);
        if (!option.isPresent()) {
        }
        return new ResponseEntity<>(option.get(),HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/orders/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLogisticsStatus(@PathVariable Integer id,@PathVariable Integer orderId,
                                      @RequestParam("logisticsStatus")String status){
        logisticsRecordRepository.updateLogisticsRecordState(status,id,orderId);
        if (status.equals("shipping")) {
            logisticsRecordRepository.updatedeliveryTime(new Date(),id);
        }else if(status.equals("signed")){
            logisticsRecordRepository.updateSignedTime(new Date(),id);
        }else{
            //
        }
    }
}
