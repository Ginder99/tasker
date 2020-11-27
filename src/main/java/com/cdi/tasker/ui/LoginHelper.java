/**
 * 
 */
package com.cdi.tasker.ui;

import java.awt.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdi.tasker.repo.LoginUserRepository;

@Service
public class LoginHelper
{
    @Autowired
    private LoginUserRepository loginUserRepo;

    public void authorize(ActionEvent actionEvent)
    {

    }
}
