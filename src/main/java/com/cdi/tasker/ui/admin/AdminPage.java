package com.cdi.tasker.ui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.cdi.tasker.model.LoginUser;
import com.cdi.tasker.service.AdminService;
import com.cdi.tasker.ui.UserType;
import com.cdi.tasker.ui.alert.AlertDialog;

@Component
public class AdminPage extends JFrame
{
    @Autowired
    AdminService adminService;

    public AdminPage()
    {
        setSize(600, 600);
        initialize();
    }

    private void initialize()
    {
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        adminPanel.setSize(600, 200);

        JButton deleteUserbutton = new JButton("Delete a User");

        adminPanel.add(deleteUserbutton);

        JPanel deletePanel = new JPanel();

        JLabel deleteUserNameLabel = new JLabel("Enter Username for deleting: ");
        deletePanel.add(deleteUserNameLabel);
        JTextField deleteUserNameTextField = new JTextField(15);
        deletePanel.add(deleteUserNameTextField);
        JButton deleteButton = new JButton("Delete");
        deletePanel.add(deleteButton);
        adminPanel.add(deletePanel);
        deletePanel.setVisible(false);
        deleteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                adminService.deleteUser(deleteUserNameTextField.getText());
            }
        });

        JButton createUserbutton = new JButton("Create a User");

        adminPanel.add(createUserbutton);

        JPanel createPanel = new JPanel();

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Enter Name:");
        namePanel.add(nameLabel);
        JTextField nameTextField = new JTextField(15);
        namePanel.add(nameTextField);
        createPanel.add(namePanel);

        JPanel userNamePanel = new JPanel();
        JLabel userNameLabel = new JLabel("Enter username:");
        userNamePanel.add(userNameLabel);
        JTextField userNameField = new JTextField(15);
        userNamePanel.add(userNameField);
        JButton validateUserNameButton = new JButton("Validate");
        userNamePanel.add(validateUserNameButton);
        createPanel.add(userNamePanel);

        JPanel userTypePanel = new JPanel();
        JLabel userTypeLabel = new JLabel("Select User Type:");
        userTypePanel.add(userTypeLabel);
        JComboBox<UserType> userTypeOptions = new JComboBox<UserType>(UserType.values());
        userTypePanel.add(userTypeOptions);
        createPanel.add(userTypePanel);

        JPanel createButtonPanel = new JPanel();
        JButton createButton = new JButton("Create User");
        createButtonPanel.add(createButton);
        createPanel.add(createButtonPanel);
        createButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                LoginUser loginUser = new LoginUser(nameTextField.getText(), userNameField.getText(),
                        // generateRandomPassword(), (UserType) userTypeOptions.getSelectedItem());
                        userNameField.getText(), (UserType) userTypeOptions.getSelectedItem());
                try
                {
                    adminService.createUser(loginUser);
                }
                catch (DataIntegrityViolationException e)
                {
                    new AlertDialog("Username already Exists. Please enter new one").setVisible(true);
                }
                nameTextField.setText("");
                userNameField.setText("");

                System.out.println(loginUser);

                new AlertDialog("New " + loginUser.getUserType() + ": " + loginUser).setVisible(true);
            }

            private String generateRandomPassword()
            {
                String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                StringBuilder pwd = new StringBuilder();
                Random rnd = new Random();
                while (pwd.length() < 9)
                {
                    int index = (int) (rnd.nextFloat() * charSet.length());
                    pwd.append(charSet.charAt(index));
                }
                String randomPassword = pwd.toString();
                return randomPassword;
            }
        });

        adminPanel.add(createPanel);
        createPanel.setVisible(false);

        deleteUserbutton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                deletePanel.setVisible(true);
                createPanel.setVisible(false);
            }
        });

        createUserbutton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                createPanel.setVisible(true);
                deletePanel.setVisible(false);
            }
        });

        validateUserNameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                if (adminService.validateUserName(userNameField.getText()))
                {
                    new AlertDialog("Validated").setVisible(true);
                }
                else
                {
                    new AlertDialog("Username already Exists. Please enter new one").setVisible(true);
                }
            }
        });

        add(adminPanel);
    }
}