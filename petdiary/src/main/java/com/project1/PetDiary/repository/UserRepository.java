package com.project1.PetDiary.repository;

import com.project1.PetDiary.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AccountUser, Long> {
    AccountUser findByEmail(String email);
}