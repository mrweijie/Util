package main.java.GUI.Warehouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class JTableAppFrame extends JFrame {
    JPanel contentPane;
    Vector CellsVector = new Vector();
    Vector TitleVector = new Vector();
    JScrollPane scp = new JScrollPane();
    JTable tab = null;
    DBCon dbcon = new DBCon();
    JLabel lbl_name = new JLabel();
    JLabel lbl_age = new JLabel();
    JLabel lbl_address = new JLabel();
    JTextField txt_name = new JTextField();
    JTextField txt_age = new JTextField();
    JTextField txt_address = new JTextField();
    JButton btn_add = new JButton();
    JButton btn_del = new JButton();
    JButton btn_update = new JButton();
    JButton btn_reset = new JButton();

    private JTabbedPane tabbedPane = new JTabbedPane();

    public JTableAppFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        this.setResizable(false);
        setSize(new Dimension(1500, 800));
        setTitle("JTable");
        scp.setBounds(new Rectangle(46, 32, 297, 157));
        lbl_name.setFont(new Font("宋体", Font.BOLD, 12));
        lbl_name.setText("姓名：");
        lbl_name.setBounds(new Rectangle(46, 205, 42, 15));
        lbl_age.setFont(new Font("宋体", Font.BOLD, 12));
        lbl_age.setText("年龄：");
        lbl_age.setBounds(new Rectangle(200, 205, 42, 15));
        lbl_address.setFont(new Font("宋体", Font.BOLD, 12));
        lbl_address.setText("地址：");
        lbl_address.setBounds(new Rectangle(46, 232, 42, 15));
        txt_name.setFont(new Font("宋体", Font.BOLD, 12));
        txt_name.setBounds(new Rectangle(98, 205, 72, 21));
        txt_age.setFont(new Font("宋体", Font.BOLD, 12));
        txt_age.setBounds(new Rectangle(250, 205, 72, 21));
        txt_address.setFont(new Font("宋体", Font.BOLD, 12));
        txt_address.setBounds(new Rectangle(98, 232, 72, 21));
        btn_add.setBounds(new Rectangle(46, 271, 83, 25));
        btn_add.setFont(new Font("宋体", Font.BOLD, 12));
        btn_add.setText("添 加");
        btn_add.addActionListener(new JTableAppFrame_btn_add_actionAdapter(this));
        btn_del.setBounds(new Rectangle(155, 271, 83, 25));
        btn_del.setFont(new Font("宋体", Font.BOLD, 12));
        btn_del.setText("删 除");
        btn_del.addActionListener(new JTableAppFrame_btn_del_actionAdapter(this));
        btn_update.setBounds(new Rectangle(260, 271, 83, 25));
        btn_update.setFont(new Font("宋体", Font.BOLD, 12));
        btn_update.setText("修 改");
        btn_update.addActionListener(new
                JTableAppFrame_btn_update_actionAdapter(this));
        btn_reset.setBounds(new Rectangle(258, 232, 83, 25));
        btn_reset.setFont(new Font("宋体", Font.BOLD, 12));
        btn_reset.setText("重 置");
        btn_reset.addActionListener(new JTableAppFrame_btn_reset_actionAdapter(this));
        contentPane.add(scp);
        contentPane.add(lbl_name);
        contentPane.add(lbl_age);
        contentPane.add(lbl_address);
        contentPane.add(txt_name);
        contentPane.add(txt_age);
        contentPane.add(txt_address);
        contentPane.add(btn_del);
        contentPane.add(btn_update);
        contentPane.add(btn_add);
        contentPane.add(btn_reset);
        dbcon.connectDB();
        this.showTable();
        tab = new JTable(CellsVector, TitleVector);
        tabbedPane.addTab("选项卡A", new JLabel("选项卡A"));//将标签组件添加到选项卡中，并且要求有提示
        tabbedPane.addTab("选项卡B", new JLabel("选项卡B"));
        tabbedPane.addTab("选项卡C", new JLabel("选项卡C"));
        scp.getViewport().add(tab);
    }

    public void showTable() {
        this.TitleVector.add("姓名");
        this.TitleVector.add("年龄");
        this.TitleVector.add("地址");
        dbcon.select();
        ArrayList list = dbcon.select();
        for (int i = 0; i < list.size(); i++) {
            Student stu = (Student) list.get(i);
            Vector v = new Vector();
            v.add(stu.getName());
            v.add(stu.getAge());
            v.add(stu.getAddress());
            CellsVector.add(v);
        }
    }


    String name;
    String age;
    String address;
    public int checkInformation() {
        name = this.txt_name.getText();
        age = this.txt_age.getText();
        address = this.txt_address.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "您好！请输入姓名！", "提示", 1);
            this.txt_name.grabFocus();
            return 0;
        }
        if (age.equals("")) {
            JOptionPane.showMessageDialog(this, "您好！请输入年龄！", "提示", 1);
            this.txt_age.grabFocus();
            return 0;
        }
        char[] ans = age.toCharArray();
        for (int i = 0; i < ans.length; i++) {
            if (!Character.isDigit(ans[i])) {
                JOptionPane.showMessageDialog(this, "您好！年龄输入错误！", "提示", 1);
                this.txt_age.setText("");
                this.txt_age.grabFocus();
                return 0;
            }
        }
        if (age.length() > 3) {
            JOptionPane.showMessageDialog(this, "您好！年龄最高只能为 100 岁！", "提示", 1);
            this.txt_age.setText("");
            this.txt_age.grabFocus();
            return 0;
        }
        if (address.equals("")) {
            JOptionPane.showMessageDialog(this, "您好！请输入地址！", "提示", 1);
            this.txt_address.grabFocus();
            return 0;
        }
        ages = Integer.valueOf(age);
        return 1;
    }

    int ages;
    public void btn_add_actionPerformed(ActionEvent e) {
        if (this.checkInformation() == 0) {
            return;
        }
        dbcon.insert(name, ages, address);
        this.CellsVector.clear();
        this.showTable();
        this.tab.updateUI();
        this.btn_reset_actionPerformed(e);
    }

    ResultSet rs;
    public void btn_del_actionPerformed(ActionEvent e) {
        String name = this.txt_name.getText();
        this.txt_age.setEditable(false);
        this.txt_address.setEditable(false);
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "您好！请输入姓名！", "提示", 1);
            this.txt_name.grabFocus();
            return;
        }
        try {
            String sql = "select * from Student where name=?";
            PreparedStatement ps = DBCon.con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                dbcon.delete(name);
                this.CellsVector.clear();
                this.showTable();
                this.tab.updateUI();
                this.btn_reset_actionPerformed(e);
            } else {
                JOptionPane.showMessageDialog(this, "您好！该学员不存在！", "提示", 1);
                this.btn_reset_actionPerformed(e);
                return;
            }
        } catch (SQLException ex) {
            System.out.println("核对学员信息发生异常" + ex.getMessage());
        }
    }

    public void btn_update_actionPerformed(ActionEvent e) {
        if (this.checkInformation() == 0) {
            return;
        }
        try {
            String sql = "select * from Student where name=?";
            PreparedStatement ps = DBCon.con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                dbcon.update(name, ages, address);
                this.CellsVector.clear();
                this.showTable();
                this.tab.updateUI();
                this.btn_reset_actionPerformed(e);
            } else {
                JOptionPane.showMessageDialog(this, "您好！该学员不存在！", "提示", 1);
                this.btn_reset_actionPerformed(e);
                return;
            }
        } catch (SQLException ex) {
            System.out.println("核对学员信息发生异常" + ex.getMessage());
        }
    }

    public void btn_reset_actionPerformed(ActionEvent e) {
        this.txt_name.setText("");
        this.txt_age.setText("");
        this.txt_address.setText("");
        this.txt_age.setEditable(true);
        this.txt_address.setEditable(true);
    }
}

class JTableAppFrame_btn_del_actionAdapter implements ActionListener {
    private JTableAppFrame adaptee;
    JTableAppFrame_btn_del_actionAdapter(JTableAppFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btn_del_actionPerformed(e);
    }
}

class JTableAppFrame_btn_update_actionAdapter implements ActionListener {
    private JTableAppFrame adaptee;
    JTableAppFrame_btn_update_actionAdapter(JTableAppFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btn_update_actionPerformed(e);
    }
}

class JTableAppFrame_btn_reset_actionAdapter implements ActionListener {
    private JTableAppFrame adaptee;
    JTableAppFrame_btn_reset_actionAdapter(JTableAppFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btn_reset_actionPerformed(e);
    }
}

class JTableAppFrame_btn_add_actionAdapter implements ActionListener {
    private JTableAppFrame adaptee;
    JTableAppFrame_btn_add_actionAdapter(JTableAppFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.btn_add_actionPerformed(e);
    }
}