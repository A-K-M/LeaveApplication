package com.iss.team1.LeaveApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.LeaveType;;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer>{
	
	@Query("SELECT l FROM LeaveType l")
	List<LeaveType> findAllLeaveType();

}