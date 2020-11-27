package com.cdi.tasker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdi.tasker.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String>
{
    public List<Appointment> findByAdvisorId(Integer advisorId);

    public List<Appointment> findByStudentId(Integer studentId);

    public void deleteByAvailabilityId(Integer availabilityId);
}
