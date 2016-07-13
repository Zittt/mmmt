package com.zit.order.util;

import java.io.Serializable;

public class JsonRpc2Request implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7353197803982266582L;

	private String method;
	
	private Object params;
	
	private String id;
	
	private String jsonrpc;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object getParams() {
		return params;
	}
	public void setParams(Object params) {
		this.params = params;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJsonrpc() {
		return jsonrpc;
	}
	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}
	@Override
	public String toString() {
		return new StringBuffer().append("id:").append(id).append(",method:").append(method).append(",jsonrpc:").append(jsonrpc).toString();
	}
	
	
	
	

}
