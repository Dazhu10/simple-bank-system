package util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class CURDUtil {
    public static <T> T selectOne(Class<T> clazz, String sql, Object... args) {
        //Object... args表示参数个数不确定，只要继承Object的参数都可以传入
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取数据连接
            connection = GeneralJDBCComponent.getConnection();
            // 获取预编译语句对象
            ps = connection.prepareStatement(sql);
            // 填充属性值，注意下标从 1 开始
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 执行查询，获取结果集
            rs = ps.executeQuery();
            // 获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过元数据获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            // rs.next()方法判断是否存在下一条，相当于集合迭代器的 hasNext()
            if (rs.next()) {
                // 实体类必须包含空参构造器，才可以正常执行 newInstance()
                T t = clazz.newInstance();
//                try{
//                    t = clazz.newInstance();
//                } catch (Exception e) {
//                    Constructor<T> dc = clazz.getDeclaredConstructor(double.class);
//                    dc.setAccessible(true);
//                    t = dc.newInstance(0);
//                }
                for (int i = 0; i < columnCount; i++) {
                    // 获取别名（getColumnName()是获取列名，不建议使用）
                    // 下标同样是从 1 开始
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 获取列值
                    Object columnVal = rs.getObject(i + 1);
                    // 通过反射封装对象
                    Field field = clazz.getDeclaredField(toCamelCase(columnLabel));
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            GeneralJDBCComponent.closeResource(connection, ps, rs);
        }
        return null;
    }

    public static <T> List<T> selectList(Class<T> clazz, String sql, Object... args) {
        //Object... args表示参数个数不确定，只要继承Object的参数都可以传入
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取数据连接
            connection = GeneralJDBCComponent.getConnection();
            // 获取预编译语句对象
            ps = connection.prepareStatement(sql);
            // 填充属性值，注意下标从 1 开始
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 执行查询，获取结果集
            rs = ps.executeQuery();
            // 获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过元数据获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            // rs.next()方法判断是否存在下一条，相当于集合迭代器的 hasNext()
            List<T> res = new ArrayList<>();
            while (rs.next()) {
                // 实体类必须包含空参构造器，才可以正常执行 newInstance()
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 获取别名（getColumnName()是获取列名，不建议使用）
                    // 下标同样是从 1 开始
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 获取列值
                    Object columnVal = rs.getObject(i + 1);
                    // 通过反射封装对象
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                res.add(t);
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            GeneralJDBCComponent.closeResource(connection, ps, rs);
        }
        return null;
    }
    public static <T> int WriteOne(String sql, Object... args){
        //Object... args表示参数个数不确定，只要继承Object的参数都可以传入
        Connection connection = null;
        PreparedStatement ps = null;
        int res = 0;
        try {
            // 获取数据连接
            connection = GeneralJDBCComponent.getConnection();
            // 获取预编译语句对象
            ps = connection.prepareStatement(sql);
            // 填充属性值，注意下标从 1 开始
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            GeneralJDBCComponent.closeResource(connection, ps, null);
        }
        return res;
    }

    public static String toCamelCase(String name) {
        if (name == null) {
            return null;
        }
        if (name.contains("_")) {
            name = name.toLowerCase();

            StringBuilder sb = new StringBuilder(name.length());
            boolean upperCase = false;
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);

                if (c == '_') {
                    upperCase = true;
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return name;
        }
    }
}
