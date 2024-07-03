package com.green.nowon.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.nowon.domain.entity.MemberEntity;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findByEmail(String email);
	
	

}
