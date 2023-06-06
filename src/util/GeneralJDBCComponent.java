package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 获取数据库连接
 */
public class GeneralJDBCComponent {
        /**
         *  获取数据库连接
         * @return 数据库连接
         */
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

    /**
     *  关闭连接
     * @param conn 数据库连接
     * @param statement 数据库Statement
     * @param rs 结果集
     */
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

    /**
     * 读取jdbc配置文件
     * @param filePath 配置文件名称路径
     * @return
     */
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

    /**
     * 获取项目文件夹地址
     * @return
     */
    public static String getProjectDirectory() {
        String currentDir = System.getProperty("user.dir");
        return currentDir;
    }

}
