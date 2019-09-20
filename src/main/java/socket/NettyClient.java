package main.java.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap;
        Channel channel = null;
        ChannelFuture future = null;
        try {
            bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);//保持连接
            bootstrap.option(ChannelOption.TCP_NODELAY, true);//有数据立即发送
            bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);
            bootstrap.option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 64 * 1024);
            bootstrap.option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 32 * 1024);

            bootstrap.handler(new NettyClientInitializer());

            future = bootstrap.connect("192.168.0.178",4001);
//            channelFuture.channel().closeFuture().sync();
            channel = future.awaitUninterruptibly().channel();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true){
            Scanner in = new Scanner(System.in);
            String a = in.nextLine();
            System.out.println(a);
            if("quit".equals(a)){
                channel.close();
                future.channel().closeFuture();
                eventExecutors.shutdownGracefully();
                break;
            }
            switch (a){
                case "2":   //清空
                    sendTcp(channel,"11111");
                    break;
                default:
                    sendTcp(channel,a);
                    break;
            }
        }
    }

    /**
     * 把16进制字符串转换成字节数组
     * @param hex hexString
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hex) {

        hex = hex.replace(" ","");

        if(hex.isEmpty()) return null;
        char[] achar = hex.toCharArray();//把字符串转换为字符数组

        int len = (hex.length() / 2);//每两个字符为一个byte，得到最终转换后的byte数组的长度
        byte[] result = new byte[len];//声明一个byte数组

        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }        return result;
    }
    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        if(b == -1) b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }

    public static void sendTcp(Channel channel, String s){
        if("".equals(s)){
            return;
        }
        ByteBuf byteBuf = channel.alloc().buffer();
        byteBuf.resetWriterIndex();
        byteBuf.writeBytes(hexStringToByte(s));
        channel.writeAndFlush(byteBuf);
    }
}
