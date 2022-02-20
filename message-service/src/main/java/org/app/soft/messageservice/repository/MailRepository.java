package org.app.soft.messageservice.repository;

import org.app.soft.messageservice.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MailRepository extends JpaRepository<Mail, String> {

    @Query("SELECT m FROM Mail m WHERE m.id = :id")
    Mail findByUUID(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("DELETE from Mail m where m.id=:id")
    void deleteByUUID(String id);

}
