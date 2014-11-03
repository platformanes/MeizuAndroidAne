package com.meizu.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.meizu.func.MeizuExit;
import com.meizu.func.MeizuInit;
import com.meizu.func.MeizuLogin;
import com.meizu.func.MeizuPay;

/**
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class MeizuContext extends FREContext {
	/**
	 * INIT sdk
	 */
	public static final String FUNCTION_INIT = "function_init";
	/**
	 * 登录Key
	 */
	public static final String FUNCTION_LOGIN = "function_login";
	/**
	 * 付费Key
	 */
	public static final String FUNCTION_PAY = "function_pay";
	/**
	 * 退出Key
	 */
	public static final String FUNCTION_EXIT = "function_exit";
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		// TODO Auto-generated method stub
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
//	       //映射
		   map.put(FUNCTION_INIT, new MeizuInit());
	       map.put(FUNCTION_LOGIN, new MeizuLogin());
	       map.put(FUNCTION_PAY, new MeizuPay());
	       map.put(FUNCTION_EXIT, new MeizuExit());
	       return map;
	}

}
