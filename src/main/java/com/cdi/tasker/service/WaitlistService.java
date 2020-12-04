package com.cdi.tasker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdi.tasker.model.WaitlistDetail;
import com.cdi.tasker.repo.WaitlistRepository;

@Service
public class WaitlistService
{
    @Autowired
    private WaitlistRepository waitlistRepository;

    @Transactional
    public void createWaitlistDetail(WaitlistDetail waitlistDetail)
    {
        waitlistRepository.save(waitlistDetail);
    }

    @Transactional
    public WaitlistDetail findNextWaitlistDetail(Integer advisorId)
    {
        return waitlistRepository.findTopByAdvisorIdOrderByWaitlistNumberAsc(advisorId);
    }

    @Transactional
    public Integer addStudentLastToWaitlist(Integer advisorId, Integer studentId)
    {
        WaitlistDetail recentWaitlistDetail = waitlistRepository.findTopByAdvisorIdOrderByWaitlistNumberDesc(advisorId);
        WaitlistDetail newWaitlistDetail = new WaitlistDetail(advisorId, studentId,
                (recentWaitlistDetail == null) ? 1 : recentWaitlistDetail.getWaitlistNumber() + 1);
        waitlistRepository.save(newWaitlistDetail);
        return newWaitlistDetail.getWaitlistNumber();
    }

    @Transactional
    public void deleteWaitlistDetail(WaitlistDetail waitlistDetail)
    {
        waitlistRepository.delete(waitlistDetail);
    }

}
