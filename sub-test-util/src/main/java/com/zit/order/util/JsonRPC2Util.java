package com.zit.order.util;

import java.util.List;
import java.util.Map;

import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;


public class JsonRPC2Util {


	public static String generateMsg(String id, String method,
			Map<String, Object> params) {
		// Create a new JSON-RPC 2.0 request
		JSONRPC2Request reqOut = new JSONRPC2Request(method, params, id);

		// Serialise the request to a JSON-encoded string
		String jsonString = reqOut.toString();
		//LoggerUtil.info(jsonString);
		return jsonString;
	}
	
	/**
	 * 将集合生成json 字符串
	 * @param id jsonRPc ID
	 * @param method 方法名
	 * @param list 集合数据
	 * @return
	 */
	public static String generateMsg(String id, String method,
			List<Object> list) {
		// Create a new JSON-RPC 2.0 request
		JSONRPC2Request reqOut = new JSONRPC2Request(method, list, id);

		// Serialise the request to a JSON-encoded string
		String jsonString = reqOut.toString();
//		LoggerUtil.info(jsonString);
		return jsonString;
	}
	
	/*
	 * 
	 * @param jsonString
	 * @return JSONRPC2Request
	 * @throws JSONRPC2ParseException
	 * usage:
	 * method     : reqIn.getMethod()
	 * parameters : reqIn.getNamedParams()
	 * id         : reqIn.getID()
	 */
	public static JSONRPC2Request parseRequestMsg(String jsonString) throws JSONRPC2ParseException{

		try {
			 return JSONRPC2Request.parse(jsonString);

		} catch (JSONRPC2ParseException e) {
//			LoggerUtil.error("parseRequestMsg error", e);
			throw e;
		}

	}
	
	/*
	 * 
	 * @param jsonString
	 * @return JSONRPC2Response
	 * @throws JSONRPC2ParseException
	 * usage:
	 * method     : reqIn.getMethod()
	 * parameters : reqIn.getNamedParams()
	 * id         : reqIn.getID()
	 */
	public static JSONRPC2Response parseResponseMsg(String jsonString) throws JSONRPC2ParseException{

		try {
			 return JSONRPC2Response.parse(jsonString);

		} catch (JSONRPC2ParseException e) {
//			LoggerUtil.error(e.getMessage());
			throw e;
		}

	}
	
	
	public static String genarateResponse(String result,JSONRPC2Request reqIn) {

//		String result = "payment-id-001";
		JSONRPC2Response respOut = new JSONRPC2Response(result, reqIn.getID());
		// Serialise response to JSON-encoded string
		String response = respOut.toString();
//		LoggerUtil.info(response);
		return response;
	}
	
	
	
	public static void main(String[] args) {
//		Map map = new HashMap();
//		map.put("s1","ssssss");
//		map.put("s2", "hhhhhhhhhhh");
//		System.out.println(generateMsg("1","order.query",map));
//		
//		Integer in= new Integer("-2");
//		System.out.println(in.intValue());
		
		try {
			String jsonString="{\"result\" : \"uid-001\",\"id\"     : 123,\"jsonrpc\":\"2.0\"}";
			System.out.println(jsonString);
			JSONRPC2Response repon = parseResponseMsg(jsonString);
			System.out.println(repon.getID());
			System.out.println(repon.getResult());
			
		} catch (JSONRPC2ParseException e) {
//			LoggerUtil.error(e);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

	}


}
