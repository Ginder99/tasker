package com.cdi.tasker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdi.tasker.model.AvailabilityDetail;

@Repository
public interface AvailabilityRepository extends JpaRepository<AvailabilityDetail, String>
{
    public List<AvailabilityDetail> findByAdvisorName(String advisorName);

    public List<AvailabilityDetail> findByAdvisorId(Integer advisorId);
}
