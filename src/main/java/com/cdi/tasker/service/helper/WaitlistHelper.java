package com.cdi.tasker.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdi.tasker.model.Advisor;
import com.cdi.tasker.model.Student;
import com.cdi.tasker.service.AdvisorService;
import com.cdi.tasker.service.WaitlistService;

@Component
public class WaitlistHelper
{
    @Autowired
    private WaitlistService waitlistService;

    @Autowired
    private AdvisorService advisorService;

    public void addStudentToWaitlist(String advisorName, Student student)
    {
        Advisor advisor = advisorService.getAdvisorByName(advisorName);
        waitlistService.addStudentLastToWaitlist(advisor.getId(), student.getId());
    }
}
