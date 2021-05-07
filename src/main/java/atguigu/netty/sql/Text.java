package atguigu.netty.sql;

import java.util.List;

public class Text {
    public static void main(String[] args) {
        String Name  = "tom";
        User user = new User(999,999,"Name");
        //int add = new DaoImp().add(user);
        //new DaoImp().delete(999);
        //List<User> list = new ArrayList<>();
        List<User> list = new DaoImp().queryAll();
        list.stream()
                .forEach(
                        System.out::println
                );

    }

}
