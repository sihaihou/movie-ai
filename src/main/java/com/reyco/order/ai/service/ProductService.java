package com.reyco.order.ai.service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.reyco.order.ai.domain.Product;
import com.reyco.order.ai.utils.CodeUtils;

/**
 * 产品信息
 * @author reyco
 *
 */
@Service
public class ProductService implements InitializingBean{
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String PREFIX = "PO";
	private static Long ser = 0L;
	
	private static Random random = new Random();
	
	private static Map<String, Product> productMap = new ConcurrentHashMap<>();
	private static Map<String, String> nameMap = new ConcurrentHashMap<>();
	
	public List<Product> list() {
		return productMap.values().stream().toList();
	}

	public Product getProductById(String productId) {
		logger.debug("productId:{}",productId);
		if(productMap.containsKey(productId)) {
			return productMap.get(productId);
		}
		throw new RuntimeException("电影未上映:"+productId);
	}
	public static Product getProductByName(String name) {
		if(!name.startsWith("《")) {
			name = "《"+name;
		}
		if(!name.endsWith("》")) {
			name = name+"》";
		}
		if(nameMap.containsKey(name)) {
			return productMap.get(nameMap.get(name));
		}
		throw new RuntimeException("电影未上映:"+name);
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("ProductService Init");
		
		Product product1 = new Product();
		product1.setProductId(createProductNumber());
		product1.setName("《哪吒之魔童闹海》");
		product1.setPrice(60);
		product1.setTime("14:30");
		product1.setRemark("讲述了天劫之后，哪吒、敖丙的灵魂保住了...");
		product1.setTheaterNumber(1);
		productMap.put(product1.getProductId(), product1);
		nameMap.put(product1.getName(), product1.getProductId());
		
		Product product2 = new Product();
		product2.setProductId(createProductNumber());
		product2.setName("《唐探1990》");
		product2.setPrice(48);
		product2.setTime("15:30");
		product2.setRemark("讲述了在1900年的美国旧金山唐人街...");
		product2.setTheaterNumber(2);
		productMap.put(product2.getProductId(), product2);
		nameMap.put(product2.getName(), product2.getProductId());
		
		Product product3 = new Product();
		product3.setProductId(createProductNumber());
		product3.setName("《射雕英雄传之侠之大者》");
		product3.setPrice(45);
		product3.setTime("17:30");
		product3.setRemark("讲述了战乱时代，郭靖得传天下绝世武功...");
		product3.setTheaterNumber(3);
		productMap.put(product3.getProductId(), product3);
		nameMap.put(product3.getName(), product3.getProductId());
		
		logger.debug("ProductService Init,productMap:{}",productMap);
	}
	public static String getProductNumber() {
		List<Product> products = productMap.values().stream().toList();
		int productIndex = random.nextInt(products.size());
		return products.get(productIndex).getProductId();
	}
	private String createProductNumber() {
		ser += 1;
		return CodeUtils.buildSequenceCode(ProductService.PREFIX, ser, 8);
	}
	
	
}
