package com.app.auditservice.repository;

import com.app.auditservice.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {

    @Query("SELECT r FROM Record r WHERE r.id = :id")
    Record findByUUID(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("DELETE from Record r where r.id=:id")
    void deleteByUUID(String id);

    List<Record> findAllByEntityId(String id);

}
