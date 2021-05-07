package atguigu.netty.pool;

import atguigu.netty.sql.DaoImp;
import atguigu.netty.sql.User;

import java.util.Scanner;

public class JDBC_DAO {

    //负责添加用户数据
    public static User add_user( ) {
        Scanner scanner = new Scanner(System.in);
        //1.需要输入用户信息
        System.out.println("请输入昵称");
        String name = scanner.nextLine();
        System.out.println("请输入id");
        int Id = Integer.parseInt(scanner.nextLine());
        System.out.println("请输入密码");
        int password = Integer.parseInt(scanner.nextLine());
        //添加
        User user = new User(Id, password, name);
        new DaoImp().add(user);
        return user;
    }
}