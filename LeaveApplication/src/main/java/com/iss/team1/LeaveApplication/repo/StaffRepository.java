package com.iss.team1.LeaveApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{
	
	Staff findByuserName(String userName);
}
