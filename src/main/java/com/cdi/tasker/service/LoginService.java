package com.cdi.tasker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdi.tasker.model.Advisor;
import com.cdi.tasker.model.LoginUser;
import com.cdi.tasker.model.Student;
import com.cdi.tasker.repo.LoginUserRepository;
import com.cdi.tasker.ui.admin.AdminPage;
import com.cdi.tasker.ui.advisor.AdvisorPage;
import com.cdi.tasker.ui.alert.AlertDialog;
import com.cdi.tasker.ui.student.StudentPage;

@Service
public class LoginService
{
    @Autowired
    private LoginUserRepository loginUserRepo;

    @Autowired
    private AdminPage adminPage;

    @Autowired
    private AdvisorPage advisorPage;

    @Autowired
    private StudentPage studentPage;

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private StudentService studentService;

    @Transactional
    public void authenticate(String userName, String password)
    {
        LoginUser loggedUser = loginUserRepo.findByUserNameAndPassword(userName, password);

        if (loggedUser == null)
        {
            new AlertDialog("Invalid Credentials").setVisible(true);
        }
        else
        {
            switch (loggedUser.getUserType())
            {
                case "ADMINISTRATOR":
                    adminPage.setVisible(true);
                    break;
                case "ADVISOR":
                {
                    Advisor adviser = advisorService.getAdvisorByUserId(loggedUser.getId());
                    advisorPage.setAdvisor(adviser);
                    advisorPage.initialize();
                    break;
                }
                case "STUDENT":
                {
                    Student student = studentService.getStudentByUserId(loggedUser.getId());
                    studentPage.setStudent(student);
                    studentPage.initialize();
                    break;
                }
            }
        }
    }
}
