package com.iss.team1.LeaveApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.Staff;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Integer>{

}
