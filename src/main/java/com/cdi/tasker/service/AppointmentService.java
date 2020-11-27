package com.cdi.tasker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdi.tasker.model.Appointment;
import com.cdi.tasker.repo.AppointmentRepository;

@Service
public class AppointmentService
{
    @Autowired
    private AppointmentRepository appointmentRepo;

    @Transactional
    public List<Appointment> getAppointmentsForAdvisor(Integer advisorId)
    {
        return appointmentRepo.findByAdvisorId(advisorId);
    }

    @Transactional
    public List<Appointment> getAppointmentsForStudent(Integer studentId)
    {
        return appointmentRepo.findByStudentId(studentId);
    }

    @Transactional
    public void deleteAppointmentByAvailId(Integer availabilityId)
    {
        appointmentRepo.deleteByAvailabilityId(availabilityId);
    }

    @Transactional
    public void createAppointment(Appointment newAppointment)
    {
        appointmentRepo.save(newAppointment);
    }

}
