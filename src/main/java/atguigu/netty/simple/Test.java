package atguigu.netty.simple;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //System.out.println(NettyRuntime.availableProcessors());
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.stream().forEach(System.out::println);
    }
}