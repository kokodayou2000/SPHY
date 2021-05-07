package atguigu.netty.pool;

import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolImpl implements ConnectionPool{

    /**
     * 空闲连接池
     */

    private LinkedBlockingQueue<Connection> idleConnectPool;

    /**
     * 活跃连接池
     */

    private LinkedBlockingQueue<Connection> busyConnectPool;

    /**
     * 当前正在被使用的连接数
     */

    private AtomicInteger activeSize = new AtomicInteger(0);

    /**
     * 最大连接数
     */
    private final int maxSize;

    public ConnectionPoolImpl(int maxSize) {
        this.maxSize = maxSize;
        init();// init pool
    }

    /**
     * 连接池初始化
     */
    @Override
    public void init() {
        idleConnectPool = new LinkedBlockingQueue<>();
        busyConnectPool = new LinkedBlockingQueue<>();
    }

    @Override
    public Connection getConnnection() {
        // 从idle池中取出一个连接
        Connection connection = idleConnectPool.poll();
        if (connection != null) {
            // 如果有连接，则放入busy池中
            busyConnectPool.offer(connection);
            System.out.println("获取到连接");
            return connection;
        }

//        synchronized (DbPoolImpl.class) { // 锁--效率低下
        // idle池中没有连接
        // 如果idle池中连接未满maxSize，就新建一个连接
        if (activeSize.get() < maxSize) {
            // 通过 activeSize.incrementAndGet() <= maxSize 这个判断
            // 解决 if(activeSize.get() < maxSize) 存在的线程安全问题
            if (activeSize.incrementAndGet() <= maxSize) {
                connection = new DBUtil().getConnection();// 创建新连接
                busyConnectPool.offer(connection);
                return connection;
            }
        }
//        }

        // 如果空闲池中连接数达到maxSize， 则阻塞等待归还连接
        try {
            System.out.println("排队等待连接");
            connection = idleConnectPool.poll(10000, TimeUnit.MILLISECONDS);// 阻塞获取连接，如果10秒内有其他连接释放，
            if (connection == null) {
                System.out.println("等待超时");
                throw new RuntimeException("等待连接超时");
            }
            System.out.println("等待到了一个连接");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) {

    }

    @Override
    public void destroy() {

    }
}
