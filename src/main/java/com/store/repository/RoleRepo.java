package com.store.repository;

import com.store.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, String> {
	
	@Query(value = "select * from roles where id = ?1", nativeQuery = true )
	Role getRole(String id);

	
}
