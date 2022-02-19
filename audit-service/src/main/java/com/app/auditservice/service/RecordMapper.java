package com.app.auditservice.service;


import com.app.auditservice.domain.RecordDto;
import com.app.auditservice.model.Record;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class RecordMapper {

    private final ModelMapper modelMapper;


    public List<RecordDto> listRecordToListOrderDto(List<Record> recordList) {
        Type listType = new TypeToken<List<RecordDto>>() {
        }.getType();
        log.debug("convert ListRecordToListOrderDto");
        return modelMapper.map(recordList, listType);
    }

    public RecordDto recordToRecordDto(Record existRecord) {
        log.debug("convert existingRecord to recordDto");
        return modelMapper.map(existRecord, RecordDto.class);
    }
}
