package com.iss.team1.LeaveApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.LeaveHistory;

@Repository
public interface LeaveDetailsRepository extends JpaRepository<LeaveHistory, Integer> {
	
	@Query("SELECT l FROM LeaveHistory l where l.staff.staffId = :staffid")
	List<LeaveHistory> findLeaveHistoriesByStaff(@Param("staffid") Integer staffid);

}
