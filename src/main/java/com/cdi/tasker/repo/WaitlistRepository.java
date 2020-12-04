package com.cdi.tasker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdi.tasker.model.WaitlistDetail;

@Repository
public interface WaitlistRepository extends JpaRepository<WaitlistDetail, String>
{
    public WaitlistDetail findTopByAdvisorIdOrderByWaitlistNumberAsc(Integer advisorId);

    public WaitlistDetail findTopByAdvisorIdOrderByWaitlistNumberDesc(Integer advisorId);
}
