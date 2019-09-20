package GUI.WebSocketDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.URI;

public class websocketdemo extends JFrame {

    // 定义组件
    JPanel jpLeft = new JPanel();
    JPanel jpLeftUp = new JPanel();
    JPanel jpLeftDown = new JPanel();

    JTextField ipText = new JTextField("ws://192.168.10.201:6012",20);
    JTextArea sendText = new JTextArea("{\"type\":\"SZM\",\"action\":\"init\",\"model\":[{\"sceneId\":\"2\"}]}",10,25);
    public static JTextArea showText = new JTextArea("",1,1);

    JButton connectButton = new JButton("连接");
    JButton breakButton = new JButton("断开");
    JButton sendButton = new JButton("发送");

    public static Channel channel;
    public static String host;
    public static int port;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        websocketdemo d1 = new websocketdemo();


    }

    // 构造函数
    public websocketdemo() {
        jpLeftUp.add(ipText);
        jpLeftUp.add(connectButton);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
        jpLeftUp.add(breakButton);
        breakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroy();
            }
        });
        breakButton.setEnabled(false);
        jpLeftDown.add(sendText);
        jpLeftDown.add(sendButton);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(channel != null){
                    channel.writeAndFlush(new TextWebSocketFrame(sendText.getText()));

                    showText.append("\r\n"+ host +" :"+ port +"发送数据："+sendText.getText());
                }else{
                    System.out.println("channel 为空");
                }
            }
        });

        jpLeft.add(jpLeftUp);
        jpLeft.add(jpLeftDown);

        this.setLayout(new GridLayout(2, 1));
        this.add(jpLeft);
        this.add(showText);
        JScrollPane scroll = new JScrollPane(showText);
        this.add(scroll);

        this.setSize(1000, 550);
        this.setTitle("Websocket测试");
        this.setLocation(400,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void connect(){
        try{
            EventLoopGroup group=new NioEventLoopGroup();
            Bootstrap boot=new Bootstrap();
            boot.option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_BACKLOG,1024*1024*10)
                    .group(group)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new ChannelHandler[]{new HttpClientCodec(),
                                    new HttpObjectAggregator(1024*1024*10)});
                            p.addLast("hookedHandler", new ClientHandler());
                        }
                    });
            URI uri = new URI(ipText.getText());
            HttpHeaders httpHeaders = new DefaultHttpHeaders();
            //进行握手
            WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, (String)null, true,httpHeaders);
            channel=boot.connect(uri.getHost(),uri.getPort()).sync().channel();
            ClientHandler handler = (ClientHandler)channel.pipeline().get("hookedHandler");
            handler.setHandshaker(handshaker);
            handshaker.handshake(channel);
            //阻塞等待是否握手成功
            handler.handshakeFuture().sync();

            breakButton.setEnabled(true);
            connectButton.setEnabled(false);
            InetSocketAddress inetsocketaddress = (InetSocketAddress) channel.remoteAddress();//得到ip和端口集成的数据
            host = inetsocketaddress.getAddress().getHostAddress();//这是得到ip
            port = inetsocketaddress.getPort();
            showText.append("\r\n" + host +" :"+ port +" : 连接成功");
        }catch (Exception e){
            showText.append("\r\n"+ host +" :"+ port +"连接错误");
        }finally {

        }
    }

    public void destroy(){
        channel.close();
        showText.append("\r\n"+ host +" :"+ port +" 已关闭 ！");

        breakButton.setEnabled(false);
        connectButton.setEnabled(true);
    }
}

