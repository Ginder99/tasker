package com.cdi.tasker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WAITLIST_DETAIL")
public class WaitlistDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ADVISOR_ID")
    private Integer advisorId;

    @Column(name = "STUDENT_ID")
    private Integer studentId;

    @Column(name = "WAITING_NUMBER")
    private Integer waitlistNumber;

    public WaitlistDetail()
    {
    }

    public WaitlistDetail(Integer advisorId, Integer studentId, Integer waitlistNumber)
    {
        super();
        this.advisorId = advisorId;
        this.studentId = studentId;
        this.waitlistNumber = waitlistNumber;
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

    public Integer getWaitlistNumber()
    {
        return waitlistNumber;
    }

    public void setWaitlistNumber(Integer waitlistNumber)
    {
        this.waitlistNumber = waitlistNumber;
    }

}
