package com.alex.blog.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/mq-websocket-endpoint")
@Scope("Prototype")
@Component
public class SocketHandler
{
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象，用于群发消息
    public static CopyOnWriteArraySet<SocketHandler> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session)
    {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose()
    {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * 客户端发送过来的消息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        System.out.println("来自客户端的消息:" + message);
    }


    /**
     * 发生异常时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error)
    {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    /**
     * 用于主动推送信息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException
    {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 用于群发自定义消息
     */
    public static void sendInfo(String message) throws IOException
    {
        for (SocketHandler item : webSocketSet)
        {
            try
            {
                item.sendMessage(message);
            }
            catch (IOException e)
            {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount()
    {
        return onlineCount;
    }

    public static synchronized void addOnlineCount()
    {
        SocketHandler.onlineCount++;
    }

    public static synchronized void subOnlineCount()
    {
        SocketHandler.onlineCount--;
    }
}
