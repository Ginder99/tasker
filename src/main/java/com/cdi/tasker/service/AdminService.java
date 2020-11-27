package com.cdi.tasker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdi.tasker.model.Advisor;
import com.cdi.tasker.model.LoginUser;
import com.cdi.tasker.model.Student;
import com.cdi.tasker.repo.AdvisorRepository;
import com.cdi.tasker.repo.LoginUserRepository;
import com.cdi.tasker.repo.StudentRepository;
import com.cdi.tasker.ui.UserType;

@Service
public class AdminService
{
    @Autowired
    LoginUserRepository loginUserRepo;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    AdvisorRepository advisorRepo;

    @Transactional
    public void deleteUser(String userName)
    {
        loginUserRepo.deleteByUserName(userName);
    }

    @Transactional
    public void createUser(LoginUser loginUser)
    {
        LoginUser createdUser = loginUserRepo.saveAndFlush(loginUser);
        if (UserType.STUDENT.getValue().equals(loginUser.getUserType()))
        {
            studentRepo.save(new Student(createdUser.getId(), loginUser.getName()));
        }
        else if (UserType.ADVISOR.getValue().equals(loginUser.getUserType()))
        {
            advisorRepo.save(new Advisor(createdUser.getId(), loginUser.getName()));
        }
    }

    @Transactional
    public Boolean validateUserName(String userName)
    {
        LoginUser existingUser = loginUserRepo.findByUserName(userName);

        return existingUser == null;
    }
}
