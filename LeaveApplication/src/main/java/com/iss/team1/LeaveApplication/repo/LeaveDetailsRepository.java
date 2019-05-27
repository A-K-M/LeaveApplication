package com.iss.team1.LeaveApplication.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.LeaveHistory;

@Repository
public interface LeaveDetailsRepository extends JpaRepository<LeaveHistory, Integer> {

	@Query("SELECT l FROM LeaveHistory l where l.staff.id = :staffid")
	List<LeaveHistory> findByStaff(@Param("staffid") Integer staffid);

	@Query("SELECT l FROM LeaveHistory l where l.staff.id = :staffid and l.status in (0,1) and ((l.fromDate >=:fromDate and l.fromDate <= :toDate) or (l.toDate >= :fromDate and l.toDate<= :toDate) )")
	List<LeaveHistory> findExistingByStaffAndDateRangeAndStatus(@Param("staffid") Integer staffid,@Param("fromDate") LocalDate fromDate,@Param("toDate") LocalDate toDate);
	
	@Query("SELECT l FROM LeaveHistory l where l.staff.id = :staffid and ((l.fromDate >=:fromDate and l.fromDate <= :toDate) or (l.toDate >= :fromDate and l.toDate<= :toDate) )")
	List<LeaveHistory> findAllByStaffAndDateRange(@Param("staffid") Integer staffid,@Param("fromDate") LocalDate fromDate,@Param("toDate") LocalDate toDate);

	   
    @Query("SELECT l FROM LeaveHistory l where l.status = 'PENDING' and l.staff.manager.id= :id ")
    List<LeaveHistory> findLeaveHistoriesByStatusPendingAndManagerId(@Param("id") Integer id);
    
}
