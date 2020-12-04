package com.cdi.tasker.ui.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdi.tasker.model.Appointment;
import com.cdi.tasker.model.AvailabilityDetail;
import com.cdi.tasker.model.Student;
import com.cdi.tasker.service.AppointmentService;
import com.cdi.tasker.service.AvailabilityService;
import com.cdi.tasker.service.helper.WaitlistHelper;
import com.cdi.tasker.ui.model.AppointmentTableModel;
import com.cdi.tasker.ui.model.AvailabilityTableModel;
import com.cdi.tasker.ui.util.AppointmentMouseListener;
import com.cdi.tasker.ui.util.AvailabilityMouseListener;
import com.cdi.tasker.ui.util.JTableButtonRenderer;
import com.cdi.tasker.ui.util.TaskerUtil;

@Component
public class StudentPage extends JFrame
{
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private WaitlistHelper waitlistHelper;

    @Autowired
    private AvailabilityMouseListener availabilityMouseListener;

    @Autowired
    private AppointmentMouseListener appointmentMouseListener;

    private Student student;

    private JFrame mainFrame;

    public StudentPage()
    {
        setSize(800, 600);
    }

    public void initialize()
    {
        mainFrame = new JFrame();
        mainFrame.setSize(900, 600);
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentPanel.setSize(600, 600);

        JPanel bookedAppointmentsPanel = new JPanel();

        JLabel yourAppointments = new JLabel("Your Appointments");
        bookedAppointmentsPanel.add(yourAppointments);
        studentPanel.add(bookedAppointmentsPanel);

        if (student != null)
        {
            List<Appointment> appointmentList = appointmentService.getAppointmentsForStudent(student.getId());
            JTable appointmentsTable = new JTable(new AppointmentTableModel(appointmentList, true));

            TableCellRenderer buttonRenderer = new JTableButtonRenderer();
            appointmentsTable.getColumn("").setCellRenderer(buttonRenderer);
            appointmentsTable.addMouseListener(appointmentMouseListener);
            appointmentMouseListener.initialize(appointmentsTable);

            JScrollPane appointmentTablePane = new JScrollPane(appointmentsTable);
            TaskerUtil.adjustScrollPane(appointmentTablePane, appointmentsTable);

            bookedAppointmentsPanel.add(appointmentTablePane);
        }

        JPanel advisorAvailPanel = new JPanel();
        JLabel checkAdvisorAvailability = new JLabel("Check Availaibility for Advisor by Advisor Name");
        advisorAvailPanel.add(checkAdvisorAvailability);
        JTextField advisorNameTextField = new JTextField(15);
        advisorAvailPanel.add(advisorNameTextField);
        JButton availCheckButton = new JButton("Check");
        advisorAvailPanel.add(availCheckButton);
        JButton joinWaitListButton = new JButton("Join Waitlist");
        JTable availabilityTable = new JTable();
        advisorAvailPanel.add(availabilityTable);
        advisorAvailPanel.add(joinWaitListButton);
        joinWaitListButton.setVisible(false);

        // JScrollPane availTablePane = new JScrollPane(availabilityTable);

        // advisorAvailPanel.add(availTablePane);

        // TaskerUtil.adjustScrollPane(availTablePane, availabilityTable);

        availCheckButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                List<AvailabilityDetail> availablityList = availabilityService
                        .getAvailabilityByAdvisorName(advisorNameTextField.getText());

                if (availablityList == null || availablityList.isEmpty())
                {
                    joinWaitListButton.setVisible(true);
                }
                else
                {
                    joinWaitListButton.setVisible(false);
                }
                availabilityTable.setModel(new AvailabilityTableModel(availablityList, true));
                TableCellRenderer buttonRenderer = new JTableButtonRenderer();
                availabilityTable.getColumn("").setCellRenderer(buttonRenderer);
                availabilityMouseListener.initialize(availabilityTable, advisorNameTextField.getText(), student);

                availabilityTable.addMouseListener(availabilityMouseListener);

                TaskerUtil.setJTableColumnsWidth(availabilityTable, 170);
            }
        });

        joinWaitListButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                waitlistHelper.addStudentToWaitlist(advisorNameTextField.getText(), student);
            }
        });

        studentPanel.add(advisorAvailPanel);

        mainFrame.add(studentPanel);
        mainFrame.setVisible(true);
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }
}