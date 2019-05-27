package com.iss.team1.LeaveApplication.repo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.CompensationLeaveClaim;
import com.iss.team1.LeaveApplication.model.CompensationLeaveClaim.Status;

@Repository
public interface CompensationClaimRepository extends JpaRepository<CompensationLeaveClaim, Integer> {

	@Query("SELECT c FROM CompensationLeaveClaim c where c.staff.id = :staffid")
	List<CompensationLeaveClaim> findByStaff(@Param("staffid") Integer staffid);
	
	@Query("SELECT c FROM CompensationLeaveClaim c where c.status= :status")
	List<CompensationLeaveClaim> findByStatus(@Param("status") Status status);
	
	@Query("SELECT c FROM CompensationLeaveClaim c where c.staff.id= :staffid and c.date=:date")
	List<CompensationLeaveClaim> findByStaffAndDate(@Param("staffid") Integer staffid,@Param("date") LocalDate date);
}
