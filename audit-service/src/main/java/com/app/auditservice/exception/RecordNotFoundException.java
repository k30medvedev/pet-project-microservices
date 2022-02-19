package com.app.auditservice.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String id) {super("Record not found for id: " + id);}
}
