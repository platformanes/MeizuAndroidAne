package com.meizu.ane 
{ 
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * 
	 * @author Rect  2013-5-6 
	 * 
	 */
	public class MeizuExtension extends EventDispatcher 
	{ 
		public static const FUNCTION_INIT:String = "function_init";//与java端中Map里的key一致
		public static const FUNCTION_LOGIN:String = "function_login";//与java端中Map里的key一致
		public static const FUNCTION_PAY:String = "function_pay";//与java端中Map里的key一致
		public static const FUNCTION_EXIT:String = "function_exit";//与java端中Map里的key一致
		
		public static const EXTENSION_ID:String = "com.meizu.ane";//与extension.xml中的id标签一致
		private var extContext:ExtensionContext;
		
		/**单例的实例*/
		private static var _instance:MeizuExtension; 
		public function MeizuExtension(target:IEventDispatcher=null)
		{
			super(target);
			if(extContext == null) {
				extContext = ExtensionContext.createExtensionContext(EXTENSION_ID, "");
				extContext.addEventListener(StatusEvent.STATUS, statusHandler);
			}
			
		} 
		
		//第二个为参数，会传入java代码中的FREExtension的createContext方法
		/**
		 * 获取实例
		 * @return DLExtension 单例
		 */
		public static function getInstance():MeizuExtension
		{
			if(_instance == null) 
				_instance = new MeizuExtension();
			return _instance;
		}
		
		/**
		 * 转抛事件
		 * @param event 事件
		 */
		private function statusHandler(event:StatusEvent):void
		{
			dispatchEvent(event);
		}
		
		
		public function MeizuInit(meizuGameID:String,meizuGameKey:String):String{
			if(extContext ){
				return extContext.call(FUNCTION_INIT,meizuGameID,meizuGameKey) as String;
			}
			return "call MeizuInit failed";
		} 
		
		
		public function MeizuLogIn(key:int):String{
			if(extContext ){
				return extContext.call(FUNCTION_LOGIN,key) as String;
			}
			return "call MeizuLogIn failed";
		} 
		 
		public function MeizuPay(meizuServerURL:String):String{
			if(extContext){ 
				return extContext.call(FUNCTION_PAY,meizuServerURL)as String;
			}
			return "call MeizuPay failed";
		}
		
		
		public function MeizuExit(key:int):String{
			if(extContext){ 
				return extContext.call(FUNCTION_EXIT,key) as String;
			}
			return "call MeizuExit failed";
		}
	} 
}