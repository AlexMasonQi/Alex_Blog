package com.alex.blog.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@Configuration
@PropertySource("classpath:rabbit.properties")
public class RabbitMQConfig
{
    @Value("${mq.host}")
    private String host;

    @Value("${mq.port}")
    private Integer port;

    @Value("${mq.user}")
    private String userName;

    @Value("${mq.password}")
    private String password;

    @Value("${mq.timeout}")
    private Long timeout;

    @Value("${mq.vhost}")
    private String vHost;

    @Value("${mq.exchange}")
    private String exchange;

    @Value("${mq.queue}")
    private String queue;

    @Value("${mq.key}")
    private String routingKey;

    private String msg;

    /**
     * @description Config the connection info
     * @author Alex
     * @date 2018.06.11 18:05
     */
    @Bean
    public ConnectionFactory connectionFactory()
    {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vHost);
        connectionFactory.setPublisherConfirms(true); //must be configed

        return connectionFactory;
    }

    /**
     * @description config the exchange(to consumer)
     * @author Alex
     * @date 2018.06.11 18:19
     */
    @Bean
    public DirectExchange defaultExchange()
    {
        return new DirectExchange(exchange, true, false);
    }

    /**
     * @description config the queue(to consumer)
     * @author Alex
     * @date 2018.06.11 18:20
     */
    @Bean
    public Queue queue()
    {
        return new Queue(queue, true);
    }

    /**
     * @description binding the queue to exchange(to consumer)
     * @author Alex
     * @date 2018.06.11 18:23
     */
    @Bean
    public Binding bindingQueueAndExchange()
    {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(routingKey);
    }

    /**
     * @description initialize the RabbitTemplate
     * @author Alex
     * @date 2018.06.12 10:52
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate()
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange(exchange);
        template.setRoutingKey(routingKey);
        template.setReplyTimeout(timeout);
        template.setQueue(queue);

        return template;
    }

    /**
     * @description listen to the port and receive message(to consumer)
     * @author Alex
     * @date 2018.06.11 18:38
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer()
    {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue());
//        container.setQueueNames(queue);
        container.setExposeListenerChannel(true);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener()
        {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception
            {
                String revMessage = new String(message.getBody(), "utf-8");
                System.out.println(revMessage);
                for (SocketHandler handler : SocketHandler.webSocketSet)
                {
                    try
                    {
                        handler.sendMessage(message.toString());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                //acknowledge the message has been received
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        });

        return container;
    }
}
