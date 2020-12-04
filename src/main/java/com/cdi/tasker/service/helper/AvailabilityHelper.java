package com.cdi.tasker.service.helper;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdi.tasker.model.Advisor;
import com.cdi.tasker.model.Appointment;
import com.cdi.tasker.model.AvailabilityDetail;
import com.cdi.tasker.model.Student;
import com.cdi.tasker.model.WaitlistDetail;
import com.cdi.tasker.service.AppointmentService;
import com.cdi.tasker.service.AvailabilityService;
import com.cdi.tasker.service.StudentService;
import com.cdi.tasker.service.WaitlistService;

@Component
public class AvailabilityHelper
{
    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private WaitlistService waitlistService;

    @Autowired
    private StudentService studentService;

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

    public void createTimeSlot(Advisor advisor, Date start, Date end)
    {
        List<AvailabilityDetail> availabilityByAdvisorId = availabilityService
                .getAvailabilityByAdvisorId(advisor.getId());
        AvailabilityDetail newTimeSlot = new AvailabilityDetail(advisor.getId(), advisor.getName(), start, end);
        newTimeSlot = availabilityService.createTimeSlot(newTimeSlot);
        if (availabilityByAdvisorId == null || availabilityByAdvisorId.isEmpty())
        {
            WaitlistDetail nextWaitlistDetail = waitlistService.findNextWaitlistDetail(advisor.getId());
            if (nextWaitlistDetail != null)
            {
                Student student = studentService.getStudentById(nextWaitlistDetail.getStudentId());
                Appointment newAppointment = new Appointment(advisor.getId(), newTimeSlot.getId(), advisor.getName(),
                        student.getName(), student.getId(), start, end, "Confirmed", null);
                appointmentService.createAppointment(newAppointment);
                waitlistService.deleteWaitlistDetail(nextWaitlistDetail);
            }
        }
    }
}
