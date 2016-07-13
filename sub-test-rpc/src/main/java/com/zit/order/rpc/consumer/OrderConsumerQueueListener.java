package com.zit.order.rpc.consumer;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.noah.order.common.utils.JsonRPC2Util;
//import com.noah.order.helper.downloadcontract.OrderController;
//import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.zit.order.rpc.bo.RpcConfig;


@Service("orderConsumerQueueListener")
public class OrderConsumerQueueListener implements MessageListener {
	private static final String ENCODING = Charset.defaultCharset().name();
	private static final String RETRY_FAILED_COUNT = "retryFailedCount";
	Logger logger = org.slf4j.LoggerFactory.getLogger(OrderConsumerQueueListener.class);

	@Autowired
	private RpcConfig rpcConfig;//相关rpc的配置信息（如：routingKey,expiration等相关信息）
	@Autowired
	private AmqpTemplate orderConsumerErrorAmqpTemplate;//消息发送模板
	
	public void onMessage(Message message) {
		logger.info("rcv message:"+message.toString());
		try{
		//	orderController.handler(message);
		//	addBizLog(message);
		}catch(Exception ex){
			message.getMessageProperties().setExpiration(rpcConfig.getExpiration());
			message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
			Map<String,Object> headerMap = message.getMessageProperties().getHeaders();

			if(isNeedToRetry(headerMap)){
				logger.error("----------------error Meaasge------------------");
				logger.error("deal rcv order info error,reason is:", ex);
				logger.error("error message:"+getBodyContentAsString(message.getBody(), message.getMessageProperties()));
				orderConsumerErrorAmqpTemplate.send(rpcConfig.getOrderErrorRoutingKey(), message);
			}else{
				logger.error("----------------error Meaasge------------------");
				logger.error("deal rcv order info error,reason is:", ex);
				logger.error("error message:"+getBodyContentAsString(message.getBody(), message.getMessageProperties()));
			}
		}
	}
	
	/**
	 * queue里的消息处理失败了，需要重试。这个方法就是判断是否需要重试，默认重试10次，每次间隔5s
	 * @param headerMap
	 * @return
	 */
	private boolean isNeedToRetry(Map<String,Object> headerMap){
		boolean needToSend = true;
		if(null == headerMap){
			headerMap = new HashMap<String,Object>();
			headerMap.put(RETRY_FAILED_COUNT, 0);
		}else if(!headerMap.containsKey(RETRY_FAILED_COUNT)){
			headerMap.put(RETRY_FAILED_COUNT, 0);
		}else{
			int retryFailedCount = (int) headerMap.get(RETRY_FAILED_COUNT);
			if(retryFailedCount <= rpcConfig.getFailedRetryCount()){
				headerMap.put(RETRY_FAILED_COUNT, ++retryFailedCount);
				logger.info("retry times: "+retryFailedCount);
			}else{
				needToSend = false;
			}
		}
		return needToSend;
	}
	
	private String getBodyContentAsString(byte[] body, MessageProperties messageProperties) {
		if (body == null) {
			return null;
		}
		try {
			String contentType = (messageProperties != null) ? messageProperties.getContentType() : null;
			if(null == contentType){
				return new String(body, ENCODING); 
			}
			if (MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT.equals(contentType)) {
				return SerializationUtils.deserialize(body).toString();
			}
			if (MessageProperties.CONTENT_TYPE_TEXT_PLAIN.equals(contentType)
					|| MessageProperties.CONTENT_TYPE_JSON.equals(contentType)
					|| MessageProperties.CONTENT_TYPE_JSON_ALT.equals(contentType)
					|| MessageProperties.CONTENT_TYPE_XML.equals(contentType)) {
				return new String(body, ENCODING);
			}
		}
		catch (Exception e) {
			// ignore
		}
		// Comes out as '[B@....b' (so harmless)
		return body.toString()+"(byte["+body.length+"])";//NOSONAR
	}
	
/*	private void addBizLog(Message message) throws Exception{
		String jsonMessage = new String(message.getBody(),"UTF-8");
		JSONRPC2Request request = JsonRPC2Util.parseRequestMsg(jsonMessage);
		net.minidev.json.JSONObject jObj = request.toJSONObject();
		
		BizLogTracer.addQueueLog(logger, BizLogTracer.BIZ_DIRECTION_QUEUE_CONSUME, 
				BizLogTracer.SYSTEM_NAME_INSURANCE, rpcConfig.getOrderDownloadRoutingDelayKey(), jObj);
	}*/
}