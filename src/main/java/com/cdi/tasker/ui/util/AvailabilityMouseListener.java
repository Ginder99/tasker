package com.cdi.tasker.ui.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdi.tasker.model.AvailabilityDetail;
import com.cdi.tasker.model.Student;
import com.cdi.tasker.service.helper.AvailabilityHelper;
import com.cdi.tasker.ui.model.AvailabilityTableModel;

@Component
public class AvailabilityMouseListener extends MouseAdapter
{
    @Autowired
    private AvailabilityHelper availabilityHelper;

    private JTable table;

    private String advisorName;

    private Student student;

    public AvailabilityMouseListener()
    {
    }

    public void mouseClicked(MouseEvent e)
    {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX());
        int row = e.getY() / table.getRowHeight();

        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0)
        {
            Object value = table.getValueAt(row, column);
            if (value instanceof JButton)
            {
                TableModel model = table.getModel();
                if (model instanceof AvailabilityTableModel)
                {
                    List<AvailabilityDetail> dataList = ((AvailabilityTableModel) model).getDataList();
                    table.repaint();
                    if ("Cancel".equalsIgnoreCase(((JButton) value).getText()))
                    {
                        availabilityHelper.handleAdvisorCancellation(dataList.remove(row));
                    }
                    if ("Book".equalsIgnoreCase(((JButton) value).getText()))
                    {
                        availabilityHelper.handleStudentBooking(dataList.get(row), advisorName, student);
                    }
                }
            }
        }
    }

    public void initialize(JTable table, String advisorName, Student student)
    {
        this.table = table;
        this.advisorName = advisorName;
        this.student = student;
    }
}