package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailRepository extends JpaRepository<Mail,Long> {
    public Optional<Mail> findByMail(String mail);
}
