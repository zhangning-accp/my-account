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

    JPanel panelContent = new JPanel();

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
        panelContent.add(labView);

        // === 添加事件处理 =====
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. 拿到文本的text内容，根据这个内容决定逻辑
                String text = btnAdd.getText();
                if(text.equals("add")) {// 进入添加纪录的操作
                    // 移除labView 组件
                    AccountFrame.this.panelContent.remove(labView);
                    // 添加panel到中间
                    AccountFrame.this.panelContent.add(panelAdd);
                    btnDelete.setText("cancel");
                    btnModify.setVisible(false);
                    text = "save";
                } else {// 还原回初始的状态
                    text = "add";
                    AccountFrame.this.panelContent.remove(panelAdd);
                    labView.setText("保存成功！！");
                    AccountFrame.this.panelContent.add(labView);
                    btnDelete.setText("delete");
                    btnModify.setVisible(true);
                }
                btnAdd.setText(text);
                // 类似于页面刷新重绘。
                AccountFrame.this.panelContent.setVisible(false);
                AccountFrame.this.panelContent.setVisible(true);
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
