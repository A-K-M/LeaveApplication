package com.iss.team1.LeaveApplication.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iss.team1.LeaveApplication.model.PublicHoliday;

@Repository
public interface PublicHolidayRepository extends JpaRepository<PublicHoliday , Integer> {

//	@Query("SELECT p FROM PublicHoliday l where p.date = :date")
//	List<PublicHoliday> findPublicHolidaysByDate(@Param("date") LocalDate date);

}
