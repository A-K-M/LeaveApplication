package com.iss.team1.LeaveApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.LeaveBalance;


@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Integer> {

	@Query("SELECT l FROM LeaveBalance l where l.staff.id = :staffid and l.leaveType.id= :leavetypeid")
	LeaveBalance findLeaveBalanceByStaffAndLeaveType(@Param("staffid") Integer staffid,@Param("leavetypeid") Integer leavetypeid);
}
