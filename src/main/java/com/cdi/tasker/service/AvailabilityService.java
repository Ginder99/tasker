package com.cdi.tasker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdi.tasker.model.AvailabilityDetail;
import com.cdi.tasker.repo.AvailabilityRepository;

@Service
public class AvailabilityService
{
    @Autowired
    private AvailabilityRepository availabilityRepo;

    @Transactional
    public List<AvailabilityDetail> getAppointmentsForAdvisor(String advisorName)
    {
        return availabilityRepo.findByAdvisorName(advisorName);
    }

    @Transactional
    public List<AvailabilityDetail> getTimeSlotsForAdvisor(Integer advisorId)
    {
        return availabilityRepo.findByAdvisorId(advisorId);
    }

    @Transactional
    public void deleteTimeSlot(AvailabilityDetail remove)
    {
        availabilityRepo.delete(remove);
    }

}
