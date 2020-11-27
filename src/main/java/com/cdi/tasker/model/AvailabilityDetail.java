package com.cdi.tasker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AVAILABILITY_DETAIL")
public class AvailabilityDetail
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ADVISOR_ID")
    private Integer advisorId;

    @Column(name = "ADVISOR_NAME")
    private String advisorName;

    @Column(name = "AVAILABILITY_START")
    private Date startTime;

    @Column(name = "AVAILABILITY_END")
    private Date endTime;

    @Column(name = "CURRENT_WAITING")
    private Integer currentWaiting;

    public AvailabilityDetail()
    {
    }

    public AvailabilityDetail(Integer advisorId, String advisorName, Date startTime, Date endTime)
    {
        super();
        this.advisorId = advisorId;
        this.advisorName = advisorName;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Integer getCurrentWaiting()
    {
        return currentWaiting;
    }

    public void setCurrentWaiting(Integer currentWaiting)
    {
        this.currentWaiting = currentWaiting;
    }

    public String getAdvisorName()
    {
        return advisorName;
    }

    public void setAdvisorName(String advisorName)
    {
        this.advisorName = advisorName;
    }

}
