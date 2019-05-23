package com.iss.team1.LeaveApplication.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
