package com.cdi.tasker.ui.model;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.cdi.tasker.model.AvailabilityDetail;

public class AvailabilityTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private List<AvailabilityDetail> dataList;

    private Boolean forStudent;

    private String[] headers = { "Start Time", "End Time", "" };

    public AvailabilityTableModel(List<AvailabilityDetail> dataList, Boolean forStudent)
    {
        this.dataList = dataList;
        this.forStudent = forStudent;
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
        AvailabilityDetail timeSlot = dataList.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return timeSlot.getStartTime();
            case 1:
                return timeSlot.getEndTime();
            case 2:
                final JButton button = new JButton(forStudent ? "Book" : "Cancel");
                return button;
        }
        return null;
    }

    public List<AvailabilityDetail> getDataList()
    {
        return dataList;
    }

    public void setDataList(List<AvailabilityDetail> dataList)
    {
        this.dataList = dataList;
    }

}