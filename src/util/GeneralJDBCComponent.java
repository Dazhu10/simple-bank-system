package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class GeneralJDBCComponent {
        public static Connection getConnection() {
            Connection connection = null;
            try {
                Properties properties = readJdbcProperties(getProjectDirectory() + "/jdbc.properties");
                String url = properties.getProperty("url");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                String driverName = properties.getProperty("driverName");
                // 加载驱动类
                Class.forName(driverName);
                // 获取连接
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }

        public static void closeResource(Connection conn, Statement statement, ResultSet rs) {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    public static Properties readJdbcProperties(String filePath) {
        Properties properties = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(filePath);
            // 加载配置文件
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return properties;
    }
    public static String getProjectDirectory() {
        String currentDir = System.getProperty("user.dir");
        return currentDir;
    }

}
