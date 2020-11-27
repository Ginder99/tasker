package com.cdi.tasker.ui.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdi.tasker.service.helper.AvailabilityHelper;
import com.cdi.tasker.ui.model.AppointmentTableModel;

@Component
public class AppointmentMouseListener extends MouseAdapter
{
    @Autowired
    private AvailabilityHelper availabilityHelper;

    private JTable table;

    public AppointmentMouseListener()
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
                if (model instanceof AppointmentTableModel)
                {
                    System.out.println("hello");
                }
            }
        }
    }

    public void initialize(JTable table)
    {
        this.table = table;
    }
}