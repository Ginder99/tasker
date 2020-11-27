package com.cdi.tasker.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdi.tasker.model.Advisor;
import com.cdi.tasker.model.AvailabilityDetail;
import com.cdi.tasker.repo.AdvisorRepository;
import com.cdi.tasker.repo.AvailabilityRepository;

@Service
public class AdvisorService
{
    @Autowired
    private AdvisorRepository advisorRepo;

    @Autowired
    private AvailabilityRepository availabilityRepo;

    @Transactional
    public void createTimeSlot(Advisor advisor, Date startTime, Date endTime)
    {
        AvailabilityDetail newTimeSlot = new AvailabilityDetail(advisor.getId(), advisor.getName(), startTime, endTime);
        availabilityRepo.save(newTimeSlot);
    }

    @Transactional
    public Advisor getAdvisorByUserId(Integer userId)
    {
        return advisorRepo.findByUserId(userId);
    }
}
