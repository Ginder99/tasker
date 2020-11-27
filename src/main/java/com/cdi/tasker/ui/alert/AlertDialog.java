package com.cdi.tasker.ui.alert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.stereotype.Component;

@Component
public class AlertDialog extends JDialog
{
    public AlertDialog()
    {
    }

    public AlertDialog(String string)
    {
        setBounds(100, 100, 400, 200);

        JPanel mainPanel = new JPanel();

        JLabel messageLabel = new JLabel(string);
        mainPanel.add(messageLabel);
        JButton okButton = new JButton("OK");
        mainPanel.add(okButton);

        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                mainPanel.getParent().getParent().getParent().getParent().setVisible(false);
            }
        });
        add(mainPanel);
    }
}