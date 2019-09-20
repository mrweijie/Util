package main.java.GUI.Warehouse;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DBCon {
    static Connection con;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/db_city?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    private static final String UserName = "root";    //用户名称
    private static final String PassWord = "root";  //用户密码
    ResultSet rs = null;
    Statement st = null;
    public DBCon() {
    }

    public void connectDB() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        if (con == null) {
            try {
                con = DriverManager.getConnection(URL,UserName,PassWord);
            } catch (SQLException ex) {
                System.out.println("创建连接发生异常:" + ex.getMessage());
                System.exit(0);
            }
        }
    }

    public ArrayList select() {
        ArrayList list = new ArrayList();
        String sql = "select * from Student";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Student stu = new Student();
                stu.setName(rs.getString(2));
                stu.setAge(rs.getInt(3));
                stu.setAddress(rs.getString(4));
                list.add(stu);
            }
        } catch (SQLException ex) {
            System.out.println("查询数据发生异常:" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex1) {
                System.out.println("查询数据关闭语句异常:" + ex1.getMessage());
            }
        }
        return list;
    }

    public void insert(String name, int age, String address) {
        String sql = "insert into Student (name, age, address) values(?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, address);
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "恭喜！插入数据成功！", "消息", 1);
            }
        } catch (SQLException ex) {
            System.out.println("插入数据发生异常:" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex1) {
                System.out.println("插入数据关闭语句异常:" + ex1.getMessage());
            }
        }
    }

    public void update(String name, int age, String address) {
        String sql = "update Student set age = ?,address = ? where name =?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, age);
            ps.setString(2, address);
            ps.setString(3, name);
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "恭喜！更新数据成功！", "消息", 1);
            }
        } catch (SQLException ex) {
            System.out.println("修改数据发生异常!");
        } finally {
            try {
                ps.close();
            } catch (SQLException ex1) {
                System.out.println("修改数据关闭语句异常:" + ex1.getMessage());
            }
        }
    }

    public void delete(String name) {
        String sql = "delete from Student where name =?";
        PreparedStatement ps = null;
        int delNumber;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            delNumber = ps.executeUpdate();
            if (delNumber != 0) {
                JOptionPane.showMessageDialog(null, "恭喜！删除数据成功！", "消息", 1);
            }
        } catch (SQLException ex) {
            System.out.println("删除数据发生异常:" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex1) {
                System.out.println("删除数据关闭语句异常:" + ex1.getMessage());
            }
        }
    }

    public void destoryConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("释放连接异常：" + ex.getMessage());
            }
        }
    }
}
