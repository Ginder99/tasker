package com.cdi.tasker.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdi.tasker.model.Appointment;
import com.cdi.tasker.model.AvailabilityDetail;
import com.cdi.tasker.model.Student;
import com.cdi.tasker.service.AppointmentService;
import com.cdi.tasker.service.AvailabilityService;

@Component
public class AvailabilityHelper
{
    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private AppointmentService appointmentService;

    public void handleAdvisorCancellation(AvailabilityDetail timeSlot)
    {
        availabilityService.deleteTimeSlot(timeSlot);
        appointmentService.deleteAppointmentByAvailId(timeSlot.getId());
    }

    public void handleStudentBooking(AvailabilityDetail availabilityDetail, String advisorName, Student student)
    {
        Appointment newAppointment = new Appointment(availabilityDetail.getAdvisorId(), availabilityDetail.getId(),
                advisorName, student.getName(), student.getId(), availabilityDetail.getStartTime(),
                availabilityDetail.getEndTime(), "Confirmed", null);

        appointmentService.createAppointment(newAppointment);
    }
}
