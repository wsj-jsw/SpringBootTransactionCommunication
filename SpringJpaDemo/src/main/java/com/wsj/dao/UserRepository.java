package com.wsj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsj.domain.UserDO;

@Repository
public interface UserRepository extends JpaRepository<UserDO, Integer> {

}
