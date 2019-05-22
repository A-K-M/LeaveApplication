package com.iss.team1.LeaveApplication.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.Roles;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Integer>{

}
