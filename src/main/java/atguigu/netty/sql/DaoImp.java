package atguigu.netty.sql;

import atguigu.netty.pool.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImp implements  Dao {
    @Override
    public int addMessage(User user ,int Id, String msg , String DateTime) {
        int affectedRow = 0;
        Connection connection = null;
        try {
            //得到一个连接
            connection = new DBUtil().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Message (name,message,time) values (?,?,?) ");
            preparedStatement.setString(1, user.getNAME());
            preparedStatement.setString(2, msg);
            preparedStatement.setString(3, DateTime);
            //执行
            affectedRow = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回执行的行数
        return affectedRow;

    }

    @Override
    public int add(User user) {
        int affectedRow = 0;
        Connection connection = null;

        try {
            //得到一个连接
            connection = new DBUtil().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("insert into GroupAll (userId ,PASSWORD,NAME) values (?,?,?)");
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setInt(2, user.getPASSWORD());
            preparedStatement.setString(3, user.getNAME());
            //执行
            affectedRow = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回执行的行数
        return affectedRow;

    }

    @Override
    public int update(int userId, String NAME) {

        int affectedRow = 0;
        Connection connection = null;
        try {
            connection = new DBUtil().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update student set NAME='" + NAME + " ' " + " where userId= '" + userId + "'");
            affectedRow = preparedStatement.executeUpdate();
            if (affectedRow > 0) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return affectedRow;
    }

    @Override
    public int delete(int userId) {

        Connection conn = null;
        PreparedStatement st = null;
        int affectedRow = 0;

        try {
            conn = new DBUtil().getConnection();
            st = conn.prepareStatement("delete from GroupAll where userId=?");

            st.setInt(1, userId);

            affectedRow = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return affectedRow;
    }


    @Override
    public User queryUserByUserId(int userId) {

        Connection conn = null;
        PreparedStatement st = null;
        User user = null;
        //得到结果的集合 rs
        ResultSet rs = null;
        conn = new DBUtil().getConnection();
        try {

            st = conn.prepareStatement("select * from GroupAll where userId=?");

            st.setInt(1, userId);

            rs = st.executeQuery();
            while(rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setPASSWORD(rs.getInt("PASSWORD"));
                user.setNAME(rs.getString("NAME"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> queryAll() {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try {
            conn = new DBUtil().getConnection();
            st = conn.prepareStatement("select * from GroupAll");

            rs = st.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setNAME(rs.getString("NAME"));
                user.setUserId(rs.getInt("userId"));
                user.setPASSWORD(rs.getInt("PASSWORD"));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  users;
    }
}
