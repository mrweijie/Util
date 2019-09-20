package main.java.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Arrays;
import java.util.List;

public class NettyClientHandle extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        //获取发送过来的数据包
        byte[] recvbyte = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(recvbyte);
        String cmd = printHexString(recvbyte);
        System.out.println(cmd);
        //指令执行成功，不不理
        if((recvbyte[4] == 1 || recvbyte[4] == -18) && (recvbyte[5] == 1 || recvbyte[5] == -18)){
            System.out.println("执行成功");
            byteBuf.clear();
            return;
        }else if(recvbyte[4] == -18 || recvbyte[5] == -18){ // 指令执行失败，不处理
            System.out.println("指令错误");
            byteBuf.clear();
            return;
        }else if(recvbyte.length == 6 || Arrays.equals(recvbyte,new byte[]{0x45,0x72,0x72,0x6F,0x72,0x0A})){ //特殊错误，不处理
            return;
        }
        byteBuf.clear();
    }
    /**
      * 将指定byte数组以16进制的形式打印到控制台
      *
      * @param b
      *            byte[]
      * @return void
      */
    public  String printHexString( byte[] b) {
        StringBuffer result = new StringBuffer();
         for (int i = 0; i < b.length; i++) {
             String hex = Integer.toHexString(b[i] & 0xFF);
             if (hex.length() == 1)
             {
                 hex = '0' + hex;
             }
             result.append(hex.toUpperCase()+" ");
         }
         return result.toString();
    }
}
