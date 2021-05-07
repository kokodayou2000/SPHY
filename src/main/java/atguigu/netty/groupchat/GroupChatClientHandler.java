package atguigu.netty.groupchat;

import atguigu.netty.sql.DaoImp;
import atguigu.netty.sql.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //msg.trim() 会输出  [自己]发送了消息1
        //为啥会死循环？
        //ctx.writeAndFlush("你好");
        System.out.println(user.getNAME()+msg.trim());
        //目标：将msg存储到mysql里面
        String format = GroupChatServerHandler.sdf.format(new Date());
        if(msg.substring(0,msg.indexOf("]")+1).equals("[自己]")) {
            String substring = msg.substring(4).trim();
            int i = new DaoImp().addMessage(user, user.getUserId(), substring, format);
        }

    }

    User user = null;
    public GroupChatClientHandler(User user){
        this.user = user;
    }

}
