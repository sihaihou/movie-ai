package com.reyco.order.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reyco.order.ai.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public Object list() {
		return orderService.list();
	}
}
