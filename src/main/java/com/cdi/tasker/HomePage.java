package com.cdi.tasker;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cdi.tasker.service.LoginService;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.cdi.tasker" })
@EnableJpaRepositories(basePackages = "com.cdi.tasker.repo")
@SpringBootApplication
public class HomePage extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = 4699004742225729353L;

    @Autowired
    private LoginService loginService;

    private JFrame mainFrame;

    public static void main(String[] args)
    {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(HomePage.class).headless(false).run(args);
        EventQueue.invokeLater(() -> {

            HomePage ex = ctx.getBean(HomePage.class);
            ex.mainFrame.setVisible(true);
        });
    }

    public HomePage()
    {
        initialize();
    }

    private void initialize()
    {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setLayout(new GridLayout(2, 1));

        JLabel loginLabel = new JLabel("Login", JLabel.CENTER);
        loginLabel.setBackground(new Color(46, 139, 87));
        loginLabel.setForeground(new Color(0, 0, 0));
        loginLabel.setSize(600, 300);

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(20);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);
        bottomPanel.setSize(600, 300);

        JLabel userNameLabel = new JLabel("User Name", JLabel.CENTER);
        JTextField userName = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password", JLabel.CENTER);
        JPasswordField password = new JPasswordField(15);

        JButton button = new JButton("LOGIN");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String userNameEntered = userName.getText();
                String passwordEntered = String.valueOf(password.getPassword());

                loginService.authenticate(userNameEntered, passwordEntered);
            }
        });
        bottomPanel.add(userNameLabel);
        bottomPanel.add(userName);

        bottomPanel.add(passwordLabel);
        bottomPanel.add(password);
        bottomPanel.add(button);
        mainFrame.add(loginLabel);
        mainFrame.add(bottomPanel);
    }

}