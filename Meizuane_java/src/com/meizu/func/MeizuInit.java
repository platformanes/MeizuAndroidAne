package com.meizu.func;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.meizu.gamecenter.sdk.MzGameCenterPlatform;

/**
 * 初始化SDK
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class MeizuInit implements FREFunction {

	private String TAG = "MeizuInit";
	// TODO 游戏申请的信息，根据自己的进行修改。也可从自己的服务端获取
	public static  String GAME_ID = "1816347";
	public static  String GAME_KEY = "6d1e9f41d57b44d1995efcab6782a311";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		try
		{
			GAME_ID = arg1[0].getAsString();
			GAME_KEY = arg1[1].getAsString();
		}
		catch (Exception e) {
			// TODO: handle exception
			String status = "传入的参数错误!";
			callBack(status);
		}
		MzGameCenterPlatform.init(_context.getActivity(), GAME_ID, GAME_KEY);
		
		String status = "success";
		
		callBack(status);
		//--------------------------------
		
		return result;
	}

	
	public void callBack(String str){
		Log.d(TAG, str);
		_context.dispatchStatusEventAsync(TAG, str);
	}

}
