package com.cdi.tasker.ui.model;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.cdi.tasker.model.Appointment;

public class AppointmentTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private List<Appointment> dataList;

    private Boolean forStudent;

    private String[] headers = { "Student Id", "Student Name", "Start Time", "End Time", "" };

    public AppointmentTableModel(List<Appointment> dataList, Boolean forStudent)
    {
        this.dataList = dataList;
        this.forStudent = forStudent;
        this.headers[0] = forStudent ? "Advisor Id" : "Student Id";
        this.headers[1] = forStudent ? "Advisor Name" : "Student Name";
    }

    public String getColumnName(int columnIndex)
    {
        return headers[columnIndex];
    }

    @Override
    public int getRowCount()
    {
        return dataList.size();
    }

    @Override
    public int getColumnCount()
    {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Appointment appointment = dataList.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return forStudent ? appointment.getAdvisorId() : appointment.getStudentId();
            case 1:
                return forStudent ? appointment.getAdvisorName() : appointment.getStudentName();
            case 2:
                return appointment.getAppointmentStart();
            case 3:
                return appointment.getAppointmentEnd();
            case 4:
                final JButton cancelButton = new JButton("Cancel");
                return cancelButton;
        }
        return null;
    }

    public List<Appointment> getDataList()
    {
        return dataList;
    }

    public void setDataList(List<Appointment> dataList)
    {
        this.dataList = dataList;
    }

}