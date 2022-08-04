package com.lyy.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import com.alibaba.druid.util.StringUtils;

public class NumberUtil {

    public static boolean addData(String min, String max, String pValue, String pvString, String number) {
    	number = number.replaceAll(" ", "");
    	pvString = pvString.replaceAll(" ", "");
    	boolean isMin = StringUtils.isEmpty(min) ? true : Integer.parseInt(min) <= Integer.parseInt(number);
    	boolean isMax = StringUtils.isEmpty(max) ? true : Integer.parseInt(number) <= Integer.parseInt(max);
		BigDecimal pv = new BigDecimal(pvString);
    	if(pValue.equals("0.05")) {
    		boolean isPvalue = pv.compareTo(BigDecimal.valueOf(0.05)) < 1;
    		return isMin && isMax && isPvalue;
    	}
    	// 这个是前端传来的进行比较的 e 的指次方数
    	int e_number = Integer.parseInt(pValue);
    	// 科学计数法转字符串
    	String regx = "[+-]*\\d+\\.?\\d*[Ee]*[+-]*\\d+";
		Pattern pattern = Pattern.compile(regx);
		boolean isNumber = pattern.matcher(pvString).matches();
		if (!isNumber) {
			throw new RuntimeException("科学计数法转字符串错误");
		}
		boolean isPvalue = pv.compareTo(BigDecimal.valueOf(1.0 / Math.pow(10, e_number))) < 1;
		return isMin && isMax && isPvalue;
    }

    public static boolean addData2(String min, String max, String pValue, String pvString, String number) {
    	number = number.replaceAll(" ", "");
    	pvString = pvString.replaceAll(" ", "");
    	boolean isMin = StringUtils.isEmpty(min) ? true : Integer.parseInt(min) <= Math.round(Double.parseDouble(number));
    	boolean isMax = StringUtils.isEmpty(max) ? true : Math.round(Double.parseDouble(number)) <= Integer.parseInt(max);

    	// 这个是前端传来的进行比较的 e 的指次方数
    	double e_number = Double.valueOf(pValue);
    	double p_number = Double.valueOf(pvString);
    	if(pValue.equals("0.05")) {
    		boolean isPvalue = 1.3 < p_number;
    		return isMin && isMax && isPvalue;
    	}
		boolean isPvalue = e_number < p_number;
		return isMin && isMax && isPvalue;
    }
    
}
