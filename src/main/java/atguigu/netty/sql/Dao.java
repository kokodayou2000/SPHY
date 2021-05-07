package atguigu.netty.sql;

import java.util.List;

public interface Dao {

    //增加
    int add(User user);

    //增加message
    int addMessage(User user , int Id,String string,String DateTime);

    //删除
    //根据id删除用户
    int delete(int userId);

    //修改（更新）
    //根据id修改用户名
    int update(int userId, String NAME);

    //查找某一行
    User queryUserByUserId(int userId);


    //查找全部的
    List<User> queryAll();


}
