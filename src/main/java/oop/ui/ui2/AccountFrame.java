package oop.ui.ui2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import oop.jdbc.JDBCDemo;

/**
 * Created by zn on 2018/7/13.
 */
public class AccountFrame extends JFrame {
    // 上部放置查询相关组件的面板
    private JPanel panelSearch = new JPanel();
    // 下部提供add ,del , modify 操作的面板
    private JPanel panelProcess = new JPanel();
    // 搜索框
    private JTextField txtSearch = new JTextField();
    // 搜索按钮
    private JButton btnSearch = new JButton("search");
    // 创建一个滚动面板，水平和垂直滚动条示情况显示
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable();
    //private JLabel labView = new JLabel("看我啊");
    //添加功能所使用的面板，包含很多组件
    private JPanel panelAdd = new JPanel();

    private JLabel labAddAcount = new JLabel("账号:");
    private JTextField txtAddAccount = new JTextField("");

    private JLabel labAddPassword = new JLabel("密码");
    private JTextField txtAddPassword = new JTextField();

    private JButton btnAdd = new JButton("add");
    private JButton btnDelete = new JButton("delete");
    private JButton btnModify = new JButton("modify");

    JPanel panelContent = new JPanel();

    JDBCDemo jdbcDemo = new JDBCDemo();

    public AccountFrame() {
        // === 初始化组件 =======
        panelSearch.setLayout(new BorderLayout());//设置布局
        panelSearch.add(txtSearch);//添加搜索框到中间部分
        panelSearch.add(btnSearch,BorderLayout.EAST);//添加搜索按钮到右边
        // == 初始化panelAdd面板组件 ==
        panelAdd.setLayout(new GridLayout(2,2));
        panelAdd.add(labAddAcount);
        panelAdd.add(txtAddAccount);

        panelAdd.add(labAddPassword);
        panelAdd.add(txtAddPassword);
        //panelAdd.setVisible(false);

        //== 初始化底部面板
        panelProcess.add(btnAdd);
        panelProcess.add(btnDelete);
        panelProcess.add(btnModify);

        panelContent.setLayout(new BorderLayout());
        this.setContentPane(panelContent);
        //=== ====
        panelContent.add(panelSearch, BorderLayout.NORTH);//添加搜索面板到上方
        panelContent.add(panelProcess,BorderLayout.SOUTH);//添加操作面板到下方
        //panelContent.add(panelAdd);
        initTable("");
        scrollPane.getViewport().add(table);

        panelContent.add(scrollPane);

        // === 添加事件处理 =====

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1. 获得用户输入的文本内容
                String keyword = txtSearch.getText();
                initTable(keyword);
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. 拿到文本的text内容，根据这个内容决定逻辑
                String text = btnAdd.getText();
                if(text.equals("add")) {// 进入添加纪录的操作
                    // 移除labView 组件
                    AccountFrame.this.panelContent.remove(scrollPane);
                    // 添加panel到中间
                    AccountFrame.this.panelContent.add(panelAdd);
                    btnDelete.setText("cancel");
                    btnModify.setVisible(false);
                    text = "save";
                } else {// 还原回初始的状态
                    text = "add";
                    AccountFrame.this.panelContent.remove(panelAdd);
                    //labView.setText("保存成功！！");
                    AccountFrame.this.panelContent.add(scrollPane);
                    btnDelete.setText("delete");
                    btnModify.setVisible(true);
                }
                btnAdd.setText(text);
                // 类似于页面刷新重绘。
                AccountFrame.this.panelContent.setVisible(false);
                AccountFrame.this.panelContent.setVisible(true);
            }
        });
        txtSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                // 获取到用户输入的查询关键字，并更新表格数据
                initTable(txtSearch.getText());
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 实现删除操作：
                //1. 判断是否有数据被选中
                int row = table.getSelectedRow();
                if(row < 0) {
                    //告诉用户没有选择任何数据
                    JOptionPane.showMessageDialog(null,
                            "请选择需要删除的数据","提示",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    //2. 让用户确实是否要删除，如果用户选择是则进行删除，否则不做删除动作
                    int option = JOptionPane.showConfirmDialog(AccountFrame.this,
                            "确定要删除选中的数据吗？","删除确认",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if(option == 0) {
                        // 删除操作. 1. 拿到用户选择的数据的id
                        Object value = table.getValueAt(row,0);
                        if(value != null && !(value + "").trim().equals("")) {
                            int id = Integer.parseInt(table.getValueAt(row,0) + "");
                            jdbcDemo.deleteData(id);
                            //删除后重新加载数据
                            initTable(txtSearch.getText());
                        }
                    }
                }
            }
        });
        // === 设定窗体相关属性 ====
        this.setSize(800,600);
        this.setTitle("账号首页");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initTable(String keyword) {
        String [][] datas = null;
        //2. 如果为空，这查询所有数据，否则按关键字搜索。
        if(keyword == null || keyword.trim().equals("")) {
            datas = jdbcDemo.bestFindAllData();
        } else {
            datas = jdbcDemo.findAccountDataLikeKeyWord(keyword);
        }
        table.setModel(getTableModel(datas));
    }

    private DefaultTableModel getTableModel(Object [][] rowDatas) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("账号");
        model.addColumn("密码");
        for(Object [] data : rowDatas) {
            model.addRow(data);
        }
        return model;
    }

    public static void main(String ... args) {
        new AccountFrame();
    }

}
