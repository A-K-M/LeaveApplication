package com.iss.team1.LeaveApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.LeaveType;
import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.RoleLeaveType;

@Repository
public interface RoleLeaveTypeRepository extends JpaRepository<RoleLeaveType, Integer> {

	@Query("SELECT r FROM RoleLeaveType r WHERE r.role.id = :roleId AND r.leaveType.id = :leavetypeId")
	RoleLeaveType findByRoleAndLeaveType(@Param("roleId") int roleId, @Param("leavetypeId") int leaveTypeId);
	
}
