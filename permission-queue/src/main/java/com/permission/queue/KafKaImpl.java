package com.permission.queue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Repository;

@Repository
public class KafKaImpl implements QueueService {

	private static final String ProducerCONFIG = "/kafka/context.xml";
	private static final String ConsumerCONFIG = "/kafka/consumer_context.xml";
	private static Random rand = new Random();

	@Override
	public void send(String Key, String topic,String message) {
		// TODO Auto-generated method stub
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(ProducerCONFIG, KafKaImpl.class);
		ctx.start();

		final MessageChannel channel = ctx.getBean("inputToKafka", MessageChannel.class);

		channel.send(MessageBuilder.withPayload("Message-" + rand.nextInt()).setHeader("messageKey", Key).setHeader(topic, message).build());

		ctx.close();
	}

	private static Logger logger = Logger.getLogger(ErrorConsumer.class);  
	
	@Override
	@SuppressWarnings({ "unchecked", "unchecked", "rawtypes" })
	public void consume() {
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(ConsumerCONFIG, KafKaImpl.class);
		ctx.start();

		final QueueChannel channel = ctx.getBean("inputFromKafka", QueueChannel.class);
		Message msg;		
		while((msg = channel.receive()) != null) {
			HashMap map = (HashMap)msg.getPayload();
			Set<Map.Entry> set = map.entrySet();
			for (Map.Entry entry : set) {
				String topic = (String)entry.getKey();
				System.out.println("Topic:" + topic);
				ConcurrentHashMap<Integer,List<byte[]>> messages = (ConcurrentHashMap<Integer,List<byte[]>>)entry.getValue();
				if (topic.equals("error")) {			    	
					//ExecutorService executor = Executors.newFixedThreadPool(1);			    	
					//executor.submit(new ErrorConsumer(messages));
					Collection<List<byte[]>> values = messages.values();
					for (Iterator<List<byte[]>> iterator = values.iterator(); iterator.hasNext();) {
						List<byte[]> list = iterator.next();
						for (byte[] object : list) {
							String message = new String(object);
							logger.error(message);
						}			
					}
				}			
			}

		}		
		ctx.close();
	}

}
