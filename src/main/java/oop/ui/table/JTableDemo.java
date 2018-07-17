package oop.ui.table;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import oop.jdbc.JDBCDemo;

/**
 * Created by zn on 2018/7/17.
 */
public class JTableDemo extends JFrame{
    JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    public JTableDemo() {
        //1. 声明表头一维数组
        String [] heads = {"id","isCheck","color"};
        //2. 声明表数据二维数组
        Object [][] datas =new Object[100][3];
        for(int i = 0; i < datas.length; i ++) {
            datas[i][0] = i;
            datas[i][1] = (i % 2 == 0);
            datas[i][2] = new Color(i,i * 2, i * 2);
        }
        //3. 创建JTable对象
        JTable table = new JTable();
        //datas = new JDBCDemo().bestFindAllData();
        DefaultTableModel tableModel = getTableModel(datas,heads);
        table.setModel(tableModel);
        // 设置第1列的数据显示为checkbox
        TableColumn column = table.getColumnModel().getColumn(1);
        column.setCellEditor(table.getDefaultEditor(Boolean.class));
        column.setCellRenderer(table.getDefaultRenderer(Boolean.class));


        table.setAutoCreateRowSorter(true);
        //JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().add(table);
        this.add(scrollPane);
        this.setSize(600,800);
        this.setVisible(true);
    }

    private DefaultTableModel getTableModel(Object [][] datas,String [] heads) {
        DefaultTableModel tableModel = new DefaultTableModel();
        for(String head : heads) {//为model添加表头
            tableModel.addColumn(head);
        }
        //添加数据
        for(Object [] rowData : datas) {
//            Object [] tmpRowData = new Object[rowData.length];
//            if(rowData[1] instanceof  Boolean) {
//                Checkbox checkbox = new Checkbox();
//                checkbox.setState(Boolean.valueOf(rowData[1] + ""));
//                tmpRowData[1] = checkbox;
//            }
//            tmpRowData[0] = rowData[0];
//            tmpRowData[2] = rowData[2];
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    public static void main(String [] args) {
        JTableDemo demo = new JTableDemo();
    }
}
