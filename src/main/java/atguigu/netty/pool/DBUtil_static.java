package atguigu.netty.pool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil_static {
    private static Connection connection = null;
    private static Statement statement = null;


    //通过静态方法得到连接对象
    public static Connection DBUtil(){
        try {
          File config = new File("src/main/resources/dbconfig.properties");
            Properties properties = new Properties();
            try {
                FileInputStream fis = new FileInputStream(config);
                properties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String Driver;
            //主机
            String url;
            //数据库用户名
            String name;
            String password;
            Driver = properties.getProperty("DRIVER");
            url = properties.getProperty("URL");
            name = properties.getProperty("NAME");
            password = properties.getProperty("PASSWORD");


            Class.forName(Driver);
            connection = DriverManager.getConnection(url, name, password);
            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement(){
        return statement;
    }

    public static void close(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
