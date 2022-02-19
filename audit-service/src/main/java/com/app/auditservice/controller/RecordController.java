package com.app.auditservice.controller;

import com.app.auditservice.domain.RecordDto;
import com.app.auditservice.domain.RecordRequestDto;
import com.app.auditservice.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/records")
    ResponseEntity<List<RecordDto>> getAllRecords() {
        log.debug("request all records in database");
        return new ResponseEntity<>(recordService.findAllRecords(), HttpStatus.OK);
    }

    @GetMapping("/records/entities/{id}")
    ResponseEntity<List<RecordDto>> getRecordsByEntityId(@PathVariable("id") String id) {
        log.debug("request all record by entity id:" + id);
        return new ResponseEntity<>(recordService.findRecordsByEntityId(id), HttpStatus.OK);
    }

    @GetMapping("/records/{id}")
    ResponseEntity<RecordDto> getRecordById(@PathVariable("id") String id) {
        log.debug("request record by id: " + id);
        return new ResponseEntity<>(recordService.findRecordById(id), HttpStatus.OK);
    }

    @PostMapping("/records")
    public ResponseEntity<String> addRecord(@RequestBody RecordRequestDto recordRequestDto) {
        recordService.addRecordInDb(recordRequestDto);
        log.debug("new record requested to save in Db:" + recordRequestDto);
        return ResponseEntity.status(201).body("Record sent to Db");
    }

    @DeleteMapping("/records/{id}")
    ResponseEntity<String> deleteRecordById(@PathVariable("id") String id) {
        recordService.deleteById(id);
        log.debug("record with: " + id + "was requested to delete");
        return ResponseEntity.ok("mail was deleted:" + id);
    }
}
