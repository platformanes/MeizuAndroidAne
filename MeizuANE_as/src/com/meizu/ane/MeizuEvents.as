package com.meizu.ane 
{ 
	/**
	 * 
	 * @author Rect  2013-5-6 
	 * 
	 */
	public class MeizuEvents 
	{ 
		public function MeizuEvents()
		{
		} 
		/**************************平台通知************************************/
		/**
		 *init 
		 */		
		public static const MEIZU_SDK_STATUS:String = "MeizuInit";
		/**
		 * 用户登录
		 */
		public static const MEIZU_LOGIN_STATUS : String = "MeizuLogin";
		
		/**
		 * 用户注销
		 */
		public static const MEIZU_LOGOUT_STATUS : String = "MeizuExit";
		
		/**
		 * 充值
		 */
		public static const MEIZU_PAY_STATUS : String = "MeizuPay";
	} 
}