package util;

import entity.CardInfoEntity;

import java.sql.*;

public class JDBCUtil {


    public static Connection getConnetion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/banksys?serverTimezone=UTC";
        String username = "root";
        String password = "12345678";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    public static void setParameters(PreparedStatement pstmt, CardInfoEntity user) throws SQLException {
        pstmt.setString(1,user.getAccountNum());
        pstmt.setString(2,user.getPassword());
        pstmt.setDouble(3,user.getBal());
        pstmt.setString(4,user.getState());
        pstmt.setString(5,user.getMakeUserName());
        pstmt.setString(6,user.getCertNo());
        pstmt.setString(7,user.getMobileNo());
        pstmt.setDate(8, user.getOpenDt());
    }
}

/**
 String sql = "SELECT * FROM mytable WHERE id = ?";
 PreparedStatement pstmt = conn.prepareStatement(sql);
 for (int i = 0; i < parameters.length; i++) {
 pstmt.setString(i, parameters[i]);
 }
 ResultSet rs = pstmt.executeQuery();
 while (rs.next()) {
 int recordId = rs.getInt("id");
 String name = rs.getString("name");
 int age = rs.getInt("age");
 System.out.println(recordId + "\t" + name + "\t" + age);
 }
 */
