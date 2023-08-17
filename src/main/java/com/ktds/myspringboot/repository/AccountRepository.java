package com.ktds.myspringboot.repository;

import com.ktds.myspringboot.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<AccountEntity , Long> {
    Optional<AccountEntity> findByUsername(String name);
}
