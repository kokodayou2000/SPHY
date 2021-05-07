package atguigu.netty.pool;

import java.sql.Connection;

public interface ConnectionPool {

    //线程初始化
    public abstract void init();

    //获取线程
    public abstract Connection getConnnection();

    //释放一个连接
    public abstract void releaseConnection(Connection connection);

    //销毁一个线程
    public abstract  void destroy();

}
