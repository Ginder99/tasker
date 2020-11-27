package com.cdi.tasker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdi.tasker.model.Advisor;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, String>
{
    public Advisor findByUserId(Integer userId);
}
