package com.cdi.tasker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdi.tasker.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>
{
    public Student findByUserId(Integer userId);
}
