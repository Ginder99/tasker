package com.cdi.tasker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APPOINTMENTS")
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ADVISOR_ID")
    private Integer advisorId;

    @Column(name = "AVAILABILITY_ID")
    private Integer availabilityId;

    @Column(name = "ADVISOR_NAME")
    private String advisorName;

    @Column(name = "STUDENT_NAME")
    private String studentName;

    @Column(name = "STUDENT_ID")
    private Integer studentId;

    @Column(name = "APPOINTMENT_START")
    private Date appointmentStart;

    @Column(name = "APPOINTMENT_END")
    private Date appointmentEnd;

    @Column(name = "APPOINTMENT_STATUS")
    private String appointmentStatus;

    @Column(name = "WAIT_SEQUENCE")
    private Integer waitSequence;

    public Appointment()
    {
    }

    public Appointment(Integer advisorId, Integer availabilityId, String advisorName, String studentName,
            Integer studentId, Date appointmentStart, Date appointmentEnd, String appointmentStatus,
            Integer waitSequence)
    {
        super();
        this.advisorId = advisorId;
        this.availabilityId = availabilityId;
        this.advisorName = advisorName;
        this.studentName = studentName;
        this.studentId = studentId;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.appointmentStatus = appointmentStatus;
        this.waitSequence = waitSequence;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getAdvisorId()
    {
        return advisorId;
    }

    public void setAdvisorId(Integer advisorId)
    {
        this.advisorId = advisorId;
    }

    public Integer getStudentId()
    {
        return studentId;
    }

    public void setStudentId(Integer studentId)
    {
        this.studentId = studentId;
    }

    public Date getAppointmentStart()
    {
        return appointmentStart;
    }

    public void setAppointmentStart(Date appointmentStart)
    {
        this.appointmentStart = appointmentStart;
    }

    public Date getAppointmentEnd()
    {
        return appointmentEnd;
    }

    public void setAppointmentEnd(Date appointmentEnd)
    {
        this.appointmentEnd = appointmentEnd;
    }

    public String getAppointmentStatus()
    {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus)
    {
        this.appointmentStatus = appointmentStatus;
    }

    public Integer getWaitSequence()
    {
        return waitSequence;
    }

    public void setWaitSequence(Integer waitSequence)
    {
        this.waitSequence = waitSequence;
    }

    public String getAdvisorName()
    {
        return advisorName;
    }

    public void setAdvisorName(String advisorName)
    {
        this.advisorName = advisorName;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public Integer getAvailabilityId()
    {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId)
    {
        this.availabilityId = availabilityId;
    }
}
