package oop.ui.ui2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    private JLabel labView = new JLabel("看我啊");
    //添加功能所使用的面板，包含很多组件
    private JPanel panelAdd = new JPanel();

    private JLabel labAddAcount = new JLabel("账号:");
    private JTextField txtAddAccount = new JTextField("");

    private JLabel labAddPassword = new JLabel("密码");
    private JTextField txtAddPassword = new JTextField();



    private JButton btnAdd = new JButton("add");
    private JButton btnDelete = new JButton("delete");
    private JButton btnModify = new JButton("modify");

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
        panelAdd.setVisible(false);

        //== 初始化底部面板
        panelProcess.add(btnAdd);
        panelProcess.add(btnDelete);
        panelProcess.add(btnModify);

        //=== ====
        this.add(panelSearch, BorderLayout.NORTH);//添加搜索面板到上方
        this.add(panelProcess,BorderLayout.SOUTH);//添加操作面板到下方
        this.add(panelAdd);
        this.add(labView);

        // === 添加事件处理 =====
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 移除labView 组件
               AccountFrame.this.getLayout().removeLayoutComponent(labView);
               AccountFrame.this.add(panelAdd);
               panelAdd.setVisible(true);
            }
        });

        // === 设定窗体相关属性 ====
        this.setSize(800,600);
        this.setTitle("账号首页");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String ... args) {
        new AccountFrame();
    }

}
