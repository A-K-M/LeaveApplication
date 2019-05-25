package com.iss.team1.LeaveApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{
	
	Staff findByuserName(String userName);
	
	@Query("SELECT s FROM Staff s, Role r WHERE s.role = r AND r.roleName = 'Manager'")
	List<Staff> findManagers();
}
