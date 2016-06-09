package com.permission.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

public class ErrorConsumer implements Runnable {

	private static Logger logger = Logger.getLogger(ErrorConsumer.class);  

	ConcurrentHashMap<Integer,List<byte[]>>  _messages;

	public ErrorConsumer(ConcurrentHashMap<Integer,List<byte[]>> messages) {
		_messages=messages;
	}

	public void run() {   
		Collection<List<byte[]>> values = _messages.values();
		for (Iterator<List<byte[]>> iterator = values.iterator(); iterator.hasNext();) {
			List<byte[]> list = iterator.next();
			for (byte[] object : list) {
				String message = new String(object);
				logger.error(message);
			}			
		}
	}
}
