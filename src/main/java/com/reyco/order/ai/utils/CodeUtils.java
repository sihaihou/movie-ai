package com.reyco.order.ai.utils;

import java.util.StringJoiner;

public class CodeUtils {
	/**
	 * 获取序列code
	 * @param prefix  前缀
	 * @param incr    
	 * @param length  
	 * @return
	 */
	public static String buildSequenceCode(String prefix, Long incr, int length) {
		String incrStr = incr.toString();
		StringJoiner stringJoiner = new StringJoiner("",prefix,"");
		int len = length - incrStr.length();
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				stringJoiner.add("0");
			}
		}
		stringJoiner.add(incrStr);
		return stringJoiner.toString();
	}
}
