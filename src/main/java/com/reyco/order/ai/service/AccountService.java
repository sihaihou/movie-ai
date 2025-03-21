package com.reyco.order.ai.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.reyco.order.ai.domain.Account;
import com.reyco.order.ai.utils.CodeUtils;

@Service
public class AccountService implements InitializingBean{
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String PREFIX = "AO";
	
	private static Long ser = 0L;
	
	private Map<String,Account> userMap = new ConcurrentHashMap<>();
	
	public Account get(String userId) {
		logger.debug("userId:{}",userId);
		if(userMap.containsKey(userId)) {
			return userMap.get(userId);
		}
		throw new RuntimeException("用户不存在");
	}
	
	public List<Account> list() {
		return userMap.values().stream().toList();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("AccountService Init");
		
		Account account0 = new Account();
		account0.setUserId(createUserId());
		account0.setUsername("admin");
		userMap.put(account0.getUserId(), account0);
		
		Account account1 = new Account();
		account1.setUserId(createUserId());
		account1.setUsername("张三");
		userMap.put(account1.getUserId(), account1);
		
		Account account2 = new Account();
		account2.setUserId(createUserId());
		account2.setUsername("李四");
		userMap.put(account2.getUserId(), account2);
		
		Account account3 = new Account();
		account3.setUserId(createUserId());
		account3.setUsername("王五");
		userMap.put(account3.getUserId(), account3);
		
		logger.debug("AccountService Init:{}",userMap);
	}
	public static String createUserId() {
		ser += 1;
		return CodeUtils.buildSequenceCode(AccountService.PREFIX, ser, 6);
	}
}
