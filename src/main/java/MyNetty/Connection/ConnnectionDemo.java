package MyNetty.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnnectionDemo {
        //使用单例返回安全的Connection
    public enum MyEnumSingleton {

        connectionFactory;

        private  Connection conn;

        private MyEnumSingleton()  {
            try {
//            InputStream in=Connection.class.getClassLoader()
//                    .getResourceAsStream("dbconfig.properties");
//            Properties properties = new Properties();
//            properties.load(in);

                File config = new File("src/main/resources/dbconfig.properties");
                Properties properties = new Properties();
                try {
                    FileInputStream fis = new FileInputStream(config);
                    properties.load(fis);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            String url = properties.getProperty("URL");
            String name = properties.getProperty("NAME");
            String password = properties.getProperty("PASSWORD");
            String driver = properties.getProperty("DRIVER");


            Class.forName(driver);

            conn = DriverManager.getConnection(url, name, password);

            }catch (Exception e){
                    e.printStackTrace();
            }
        }

        public Connection getConn() {
            return conn;
        }
    }

    public  static Connection getConncetion(){
        return MyEnumSingleton.connectionFactory.getConn();
    }

}
