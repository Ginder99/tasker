package com.cdi.tasker.ui.advisor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdi.tasker.model.Advisor;
import com.cdi.tasker.model.Appointment;
import com.cdi.tasker.model.AvailabilityDetail;
import com.cdi.tasker.service.AdvisorService;
import com.cdi.tasker.service.AppointmentService;
import com.cdi.tasker.service.AvailabilityService;
import com.cdi.tasker.service.helper.AvailabilityHelper;
import com.cdi.tasker.ui.alert.AlertDialog;
import com.cdi.tasker.ui.model.AppointmentTableModel;
import com.cdi.tasker.ui.model.AvailabilityTableModel;
import com.cdi.tasker.ui.util.AppointmentMouseListener;
import com.cdi.tasker.ui.util.AvailabilityMouseListener;
import com.cdi.tasker.ui.util.JTableButtonRenderer;
import com.cdi.tasker.ui.util.TaskerUtil;

@Component
public class AdvisorPage extends JFrame
{
    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private AvailabilityHelper availabilityHelper;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private AvailabilityMouseListener availabilityMouseListener;

    @Autowired
    private AppointmentMouseListener appointmentMouseListener;

    private Advisor advisor;

    private JFrame mainFrame;

    public AdvisorPage()
    {
        setSize(600, 600);
    }

    public void initialize()
    {
        mainFrame = new JFrame();
        mainFrame.setSize(900, 600);

        JPanel advisorPanel = new JPanel();
        advisorPanel.setLayout(new BoxLayout(advisorPanel, BoxLayout.Y_AXIS));
        advisorPanel.setSize(600, 200);

        JButton createTimeSlotButton = new JButton("Create A Time Slot");

        JPanel timeSlotForm = new JPanel();
        JLabel startTimeLabel = new JLabel("Start Time:");
        timeSlotForm.add(startTimeLabel);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        JFormattedTextField startTime = new JFormattedTextField(dateFormat);
        startTime.setColumns(15);
        timeSlotForm.add(startTime);
        JLabel endTimeLabel = new JLabel("End Time:");
        timeSlotForm.add(endTimeLabel);
        JFormattedTextField endTime = new JFormattedTextField(dateFormat);
        endTime.setColumns(15);
        timeSlotForm.add(endTime);
        JButton createTimeSlot = new JButton("Create");
        timeSlotForm.add(createTimeSlot);

        timeSlotForm.setVisible(false);
        advisorPanel.add(createTimeSlotButton);

        advisorPanel.add(timeSlotForm);
        createTimeSlotButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                timeSlotForm.setVisible(true);
            }
        });
        createTimeSlot.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Date start = (Date) startTime.getValue();
                Date end = (Date) endTime.getValue();
                if (start.after(end))
                {
                    new AlertDialog("Invalid Dates!!").setVisible(true);
                }
                else
                {
                    availabilityHelper.createTimeSlot(advisor, start, end);
                }
            }
        });
        if (advisor != null)
        {
            JPanel timeSlotsPanel = new JPanel();
            JLabel timeSlotsHeaderLabel = new JLabel("Your Time Slots");
            timeSlotsPanel.add(timeSlotsHeaderLabel);
            List<AvailabilityDetail> timeSlotsList = availabilityService.getTimeSlotsForAdvisor(advisor.getId());
            JTable timeSlotsTable = new JTable(new AvailabilityTableModel(timeSlotsList, false));
            TableCellRenderer buttonRenderer = new JTableButtonRenderer();
            timeSlotsTable.getColumn("").setCellRenderer(buttonRenderer);
            availabilityMouseListener.initialize(timeSlotsTable, advisor.getName(), null);
            timeSlotsTable.addMouseListener(availabilityMouseListener);

            JScrollPane availTablePane = new JScrollPane(timeSlotsTable);

            timeSlotsPanel.add(availTablePane);

            advisorPanel.add(timeSlotsPanel);
            TaskerUtil.adjustScrollPane(availTablePane, timeSlotsTable);

            JPanel appointmentsPanel = new JPanel();
            JLabel appointmentsHeaderLabel = new JLabel("Your Appointments");
            appointmentsPanel.add(appointmentsHeaderLabel);
            List<Appointment> appointmentList = appointmentService.getAppointmentsForAdvisor(advisor.getId());
            JTable appointmentsTable = new JTable(new AppointmentTableModel(appointmentList, false));
            appointmentsTable.addMouseListener(appointmentMouseListener);
            appointmentMouseListener.initialize(appointmentsTable);

            buttonRenderer = new JTableButtonRenderer();
            appointmentsTable.getColumn("").setCellRenderer(buttonRenderer);

            JScrollPane appointmentTablePane = new JScrollPane(appointmentsTable);
            TaskerUtil.adjustScrollPane(appointmentTablePane, appointmentsTable);

            appointmentsPanel.add(appointmentTablePane);

            advisorPanel.add(appointmentsPanel);
        }
        mainFrame.add(advisorPanel);
        mainFrame.setVisible(true);
    }

    public Advisor getAdvisor()
    {
        return advisor;
    }

    public void setAdvisor(Advisor advisor)
    {
        this.advisor = advisor;
    }
}