package com.store.repository;

import com.store.entity.Account;
import com.store.entity.Authority;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority,Long> {
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO Authorities(username, roleId) VALUES(?1, 'CUST')", nativeQuery = true)
	void register(String username);
	
	// lấy ra các quyền đã được cấp cho nhóm tài khoản account (admin)
	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);

	@Query("SELECT a FROM Authority a WHERE a.account.username = ?1 ")
	List<Authority> findAuthority(String username);
}
