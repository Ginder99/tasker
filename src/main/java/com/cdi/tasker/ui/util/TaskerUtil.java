package com.cdi.tasker.ui.util;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class TaskerUtil
{
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth)
    {
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++)
        {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((tablePreferredWidth));
        }
    }

    public static void adjustScrollPane(JScrollPane scrollPane, JTable table)
    {
        setJTableColumnsWidth(table, 170);
        Dimension d = table.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(d.width, table.getRowHeight() * (table.getRowCount() + 1)));

    }
}
