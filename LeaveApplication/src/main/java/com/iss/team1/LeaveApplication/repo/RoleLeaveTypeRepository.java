package com.iss.team1.LeaveApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.RoleLeaveType;

@Repository
public interface RoleLeaveTypeRepository extends JpaRepository<RoleLeaveType, Integer> {

	@Query("SELECT r FROM RoleLeaveType r WHERE r.role.id = :roleid AND r.leaveType.id = :leavetypeid")
	RoleLeaveType findByRoleAndLeaveType(@Param("roleid") Integer roleId, @Param("leavetypeid") Integer leaveTypeId);
	
}
