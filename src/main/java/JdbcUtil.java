import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtil {
    public void init(){
        //案例源码
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/db_city";
        String user = "root";
        String password = "root";
        String sql = "SELECT * from stock";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //注册数据库驱动
            Class.forName(driver);
            //取得数据库连接
            conn = DriverManager.getConnection(url, user, password);
            //进行预编译，这里进行参数设置
            pstmt = conn.prepareStatement(sql);
            //进行编译
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String remarks = rs.getString("remarks");
                float price = rs.getFloat("price");
                System.out.println(id + "--" + name + "--" + remarks + "--"+ price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null){//轻量级，创建和销毁rs所需要的时间和资源较小
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(pstmt!=null){//轻量级，创建和销毁rs所需要的时间和资源较小
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){//重量级，创建和销毁rs所需要的时间和资源较小
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JdbcUtil database = new JdbcUtil();
        database.init();
    }
}
