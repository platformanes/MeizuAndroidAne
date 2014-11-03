package com.meizu.func;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.meizu.gamecenter.sdk.LoginResultCode;
import com.meizu.gamecenter.sdk.MzAccountInfo;
import com.meizu.gamecenter.sdk.MzGameCenterPlatform;
import com.meizu.gamecenter.sdk.MzLoginListener;

/**
 * 执行登录
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class MeizuLogin implements FREFunction {

	private String TAG = "MeizuLogin";
	private static String mUid;
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		// TODO 调用登录接口
		MzGameCenterPlatform.login(_context.getActivity(), new MzLoginListener() {			
			@Override
			public void onLoginResult(int code, MzAccountInfo accountInfo, String errorMsg) {
				// TODO 登录结果回调，该回调跑在应用主线程
				switch(code){
				case LoginResultCode.LOGIN_SUCCESS:
					// TODO 登录成功，拿到uid 和 session到自己的服务器去校验session合法性
					mUid = accountInfo.getUid();
					callBack(
							"success:UserName:" + accountInfo.getName() + 
							":userID:" + accountInfo.getUid() + 
							":session:" + accountInfo.getSession());
					break;
				case LoginResultCode.LOGIN_ERROR_CANCEL:
					// TODO 用户取消登陆操作
					break;
				default:
					// TODO 登陆失败，包含错误码和错误消息。
					// TODO 注意，错误消息需要由游戏展示给用户，错误码可以打印，供调试使用
					callBack("error:" + errorMsg + ":code:" + code);
					break;
				}				
			}
		});
		String status = "success";
		callBack(status);
		//--------------------------------
		
		return result;
	}

	public static String getUID()
	{
		return mUid;
	}
	
	public void callBack(String str){
		Log.d(TAG, str);
		_context.dispatchStatusEventAsync(TAG, str);
	}
}
