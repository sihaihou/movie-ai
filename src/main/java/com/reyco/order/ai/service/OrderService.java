package com.reyco.order.ai.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reyco.order.ai.domain.Account;
import com.reyco.order.ai.domain.Order;
import com.reyco.order.ai.domain.Product;
import com.reyco.order.ai.utils.CodeUtils;

@Service
public class OrderService implements InitializingBean {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Random random = new Random();
	private static final String PREFIX = "NO";
	private static Long ser = 0L;
	private Map<String, Order> orderMap = new ConcurrentHashMap<>();
	private Map<String, Map<String, Order>> userOrderMap = new ConcurrentHashMap<>();

	@Autowired
	private ProductService productService;
	@Autowired
	private AccountService accountService;

	public List<Order> list() {
		return orderMap.values().stream().toList();
	}

	public Order get(String orderNumber) {
		logger.debug("orderNumber:{}",orderNumber);
		if (orderMap.containsKey(orderNumber)) {
			return orderMap.get(orderNumber);
		}
		throw new RuntimeException("订单不存在");
	}

	public List<Order> getByUserId(String userId) {
		logger.debug("userId:{}",userId);
		return userOrderMap.get(userId).values().stream().toList();
	}

	public Order add(Order order) {
		logger.debug("order:{}",order);
		if (!orderMap.containsKey(order.getOrderNumber())) {
			put(order);
			return order;
		}
		throw new RuntimeException("订单号重复");
	}

	public Order edit(Order order) {
		logger.debug("order:{}",order);
		if (orderMap.containsKey(order.getOrderNumber())) {
			put(order);
			return order;
		}
		return null;
	}

	public Order delete(final String orderNumber) {
		logger.debug("orderNumber:{}",orderNumber);
		if (orderMap.containsKey(orderNumber)) {
			Order order = orderMap.get(orderNumber);
			order.setState((byte)1);
			return order;
		}
		throw new RuntimeException("订单不存在");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("OrderService Init");
		
		List<Account> accounts = accountService.list();
		List<Product> products = productService.list();
		Set<String> dis = new HashSet<>();
		int count = accounts.size() + products.size();
		for (int i = 0; i < count; i++) {
			int userIndex = random.nextInt(products.size());
			int productIndex = random.nextInt(products.size());
			String key = userIndex + "-" + productIndex;
			while (dis.contains(key)) {
				userIndex = random.nextInt(products.size());
				productIndex = random.nextInt(products.size());
				key = userIndex + "-" + productIndex;
			}
			dis.add(key);
			String userId = accounts.get(userIndex).getUserId();
			String productId = products.get(productIndex).getProductId();
			Order order = createOrder(userId, createOrderNumber(), productId);
			put(order);
		}
		logger.debug("OrderService Init,orderMap:{}",orderMap);
	}

	private void put(Order order) {
		String orderNumber = order.getOrderNumber();
		orderMap.put(orderNumber, order);
		String userId = order.getUserId();
		Map<String, Order> selectUserOrderMap = userOrderMap.get(userId);
		if (selectUserOrderMap == null) {
			selectUserOrderMap = new ConcurrentHashMap<String, Order>();
			userOrderMap.put(userId, selectUserOrderMap);
		}
		selectUserOrderMap.put(orderNumber, order);
	}

	public Order createOrder(String userId, String orderNumber, String productId) {
		Product product = productService.getProductById(productId);
		Account account = accountService.get(userId);
		Order order = new Order();
		order.setUserId(userId);
		order.setUsername(account.getUsername());
		order.setOrderNumber(orderNumber);
		order.setName(product.getName());
		order.setPrice(product.getPrice());
		order.setTheaterNumber(product.getTheaterNumber());
		order.setTime(product.getTime());
		order.setRemark(product.getRemark());
		order.setCreateTime(new SimpleDateFormat().format(new Date()));
		return order;
	}

	public static String createOrderNumber() {
		ser += 1;
		return CodeUtils.buildSequenceCode(OrderService.PREFIX, ser, 12);
	}
}
