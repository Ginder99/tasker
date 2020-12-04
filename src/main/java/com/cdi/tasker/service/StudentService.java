package com.cdi.tasker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdi.tasker.model.Student;
import com.cdi.tasker.repo.StudentRepository;

@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepo;

    @Transactional
    public Student getStudentByUserId(Integer userId)
    {
        return studentRepo.findByUserId(userId);
    }

    @Transactional
    public Student getStudentById(Integer studentId)
    {
        return studentRepo.findById(studentId).get();
    }
}
