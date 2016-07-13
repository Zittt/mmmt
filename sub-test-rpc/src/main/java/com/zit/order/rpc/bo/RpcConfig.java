package com.zit.order.rpc.bo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class RpcConfig {
	@Value("${settle.rel.order.routing.key}")
	private String settleRoutingKey;
	@Value("${order.routing.key}")
	private String orderRoutingKey;
	//order系统错误信息队列key
	@Value("${order.error.routing.key}")
	private String orderErrorRoutingKey;
	//重试时间间隔，默认单位是秒
	@Value("${expiration}")
	private String expiration;
	//失败重试次数
	@Value("${failed.retry.count}")
	private int failedRetryCount;
	@Value("${paysystem.error.routing.key}")
	private String paysystemErrorRoutingKey;
	//延迟队列
	@Value("${order.delay.routing.download.key}")
	private String orderDownloadRoutingDelayKey;
	
	public String getOrderRoutingKey() {
		return orderRoutingKey;
	}

	public void setOrderRoutingKey(String orderRoutingKey) {
		this.orderRoutingKey = orderRoutingKey;
	}

	public String getOrderErrorRoutingKey() {
		return orderErrorRoutingKey;
	}

	public void setOrderErrorRoutingKey(String orderErrorRoutingKey) {
		this.orderErrorRoutingKey = orderErrorRoutingKey;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public int getFailedRetryCount() {
		return failedRetryCount;
	}

	public void setFailedRetryCount(int failedRetryCount) {
		this.failedRetryCount = failedRetryCount;
	}

	public String getSettleRoutingKey() {
		return settleRoutingKey;
	}

	public void setSettleRoutingKey(String settleRoutingKey) {
		this.settleRoutingKey = settleRoutingKey;
	}

	public String getPaysystemErrorRoutingKey() {
		return paysystemErrorRoutingKey;
	}

	public void setPaysystemErrorRoutingKey(String paysystemErrorRoutingKey) {
		this.paysystemErrorRoutingKey = paysystemErrorRoutingKey;
	}

	public String getOrderDownloadRoutingDelayKey() {
		return orderDownloadRoutingDelayKey;
	}

	public void setOrderDownloadRoutingDelayKey(String orderDownloadRoutingDelayKey) {
		this.orderDownloadRoutingDelayKey = orderDownloadRoutingDelayKey;
	}
}
