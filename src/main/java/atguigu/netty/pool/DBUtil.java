package atguigu.netty.pool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
    private Connection connection = null;
    private Statement statement = null;

    //通过构造方法得到连接对象
    public DBUtil() {
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
    }

    public Connection getConnection() {
        return this.connection;
    }

    public Statement getStatement(){
        return this.statement;
    }

    public void close(){
        try {
            this.statement.close();
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
