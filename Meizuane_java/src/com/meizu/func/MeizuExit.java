package com.meizu.func;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.meizu.gamecenter.sdk.MzGameCenterPlatform;

/**
 * 退出SDK 清理环境
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class MeizuExit implements FREFunction {

	private String TAG = "MeizuExit";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		MzGameCenterPlatform.logout(_context.getActivity());
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
