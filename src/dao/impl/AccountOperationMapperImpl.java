package dao.impl;

import dao.AccountOperationMapper;
import entity.CardInfoEntity;
import util.CURDUtil;
import util.JDBCUtil;

import java.sql.*;

public class AccountOperationMapperImpl implements AccountOperationMapper {
    Connection conn = null;

    /**
     * 开户JDBC层
     * @param user
     * @return
     */
    @Override
    public int openAnCount(CardInfoEntity user) {
        String sql = "insert into card_info(account_num, password, bal, " +
                "state, make_user_name, cert_no, mobile_no, open_dt) " +
                "values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        int res = 0;
        try {
            conn = JDBCUtil.getConnetion();
            pstmt = conn.prepareStatement(sql);
            JDBCUtil.setParameters(pstmt,user);
            res = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("数据库操作失败！");
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * 销户
     * @param user
     * @return
     */
    @Override
    public int cancelTheCount(CardInfoEntity user) {
        String sql = "delete from card_info where account_num = ?";
        PreparedStatement pstmt = null;
        int res = 0;
        try {
            conn = JDBCUtil.getConnetion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getAccountNum());
            res = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("数据库操作失败！");
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * 挂失补办
     * @param user
     * @return
     */
    @Override
    public int reportTheLoss(CardInfoEntity user) {
        String sql = null;
        if(user.getState().equals("1")) {
            sql = "update card_info set state = ? where account_num = ? and state = 0";
        }else{
            sql = "update card_info set state = ? where account_num = ? and state = 1";
        }
//        PreparedStatement pstmt = null;
//        int res = 0;
//        try {
//            conn = JDBCUtil.getConnetion();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, user.getState());
//            pstmt.setString(2, user.getAccountNum());
//            res = pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("数据库操作失败！");
//            throw new RuntimeException(e);
//        }
//        return res;
        //以下是通过通用方法实现
        int res = CURDUtil.WriteOne(sql, user.getState(), user.getAccountNum());
        return res;
    }

    @Override
    public int updatePassword(CardInfoEntity user) {
        String sql = "update card_info set password = ? where account_num = ? and state = 1";
//        PreparedStatement pstmt = null;
//        int res = 0;
//        try {
//            conn = JDBCUtil.getConnetion();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, user.getPassword());
//            pstmt.setString(2, user.getAccountNum());
//            res = pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("数据库操作失败！");
//            throw new RuntimeException(e);
//        }
//        return res;
        //以下是通过通用方法实现
        int res = CURDUtil.WriteOne(sql, user.getState(), user.getAccountNum());
        return res;
    }

    @Override
    public boolean login(CardInfoEntity user) {
        String sql = "select password from card_info where account_num = ?";
        PreparedStatement pstmt = null;
        String password = null;
        try {
            conn = JDBCUtil.getConnetion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getAccountNum());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("数据库操作失败！");
            throw new RuntimeException(e);
        }
        if(user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public int depositAndWithrawMoney(CardInfoEntity user) {
        String sql = "update card_info set bal = bal + ? where account_num = ? and state = 1";
//        PreparedStatement pstmt = null;
//        int res = 0;
//        try {
//            conn = JDBCUtil.getConnetion();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setDouble(1, user.getBal());
//            pstmt.setString(2, user.getAccountNum());
//            res = pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("数据库操作失败！");
//            throw new RuntimeException(e);
//        }
//        return res;
        //以下是通过通用方法实现
        int res = CURDUtil.WriteOne(sql,user.getBal(),user.getAccountNum());
        return res;
    }

    @Override
    public CardInfoEntity selctUser(CardInfoEntity user) {
        String sql = "select * from card_info where account_num = ? and state = 1";
//        PreparedStatement pstmt = null;
//        double bal = 0;
//        try {
//            conn = JDBCUtil.getConnetion();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, user.getAccountNum());
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                bal = rs.getDouble("bal");
//            }
//        } catch (SQLException e) {
//            System.out.println("数据库操作失败！");
//            throw new RuntimeException(e);
//        }
//        return bal;
        //以下是通过通用方法实现
        CardInfoEntity res = CURDUtil.selectOne(CardInfoEntity.class, sql, user.getAccountNum());
        return res;
    }
}
