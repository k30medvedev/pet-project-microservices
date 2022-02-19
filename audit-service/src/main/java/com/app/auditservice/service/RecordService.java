package com.app.auditservice.service;

import com.app.auditservice.domain.RecordDto;
import com.app.auditservice.domain.RecordRequestDto;
import com.app.auditservice.exception.RecordNotFoundException;
import com.app.auditservice.model.Record;
import com.app.auditservice.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordService {

    private final RecordMapper mapper;

    private final RecordRepository recordRepository;

    public void addRecordInDb(RecordRequestDto mailData) {

        Record record = new Record();

        record.setEntityId(mailData.getEntityId());
        record.setEntityType(mailData.getEntityType());
        record.setEntityAction(mailData.getAction());
        record.setBody(mailData.getBody());
        String date = mailData.getDate();
        log.info(date);
        LocalDateTime result = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        record.setDate(result);
        recordRepository.save(record);
        log.debug("save mailData in to db" + record);
    }

    public List<RecordDto> findAllRecords() {
        List<Record> recordList = recordRepository.findAll();
        return mapper.listRecordToListOrderDto(recordList);
    }

    public void deleteById(String id) {
        recordRepository.deleteByUUID(id);
    }

    public List<RecordDto> findRecordsByEntityId(String id) {
        List<Record> recordList = recordRepository.findAllByEntityId(id);
        return mapper.listRecordToListOrderDto(recordList);
    }

    public RecordDto findRecordById(String id) {
        Record existRecord = recordRepository.findByUUID(id);
        if (existRecord != null) {
            return mapper.recordToRecordDto(existRecord);
        } else throw new RecordNotFoundException(id);
    }
}
