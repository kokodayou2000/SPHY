import MyNetty.Connection.ConnnectionDemo;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class text {

    @Test
    public void testConnection(){
        Connection conncetion = ConnnectionDemo.getConncetion();
        System.out.println(conncetion);
    }
}
