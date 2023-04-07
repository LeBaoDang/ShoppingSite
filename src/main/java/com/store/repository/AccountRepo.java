package com.store.repository;

import com.store.entity.Account;

import java.util.List;

import com.store.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {

	// lấy ra những account có vai trò là giám đốc && nhân viên
	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
	List<Account> getAdministrators();

//	@Query(" SELECT r.name FROM Authority o INNER JOIN Role r ON " +
//			"o.role = r.id WHERE o.account.username =: username")
//	@Query( value = "select Name from Authorities inner join roles on Authorities.RoleId = roles.Id where Username = ?1 ", nativeQuery = true)
//	List<Role> RoleFindAll(String uername);

}
