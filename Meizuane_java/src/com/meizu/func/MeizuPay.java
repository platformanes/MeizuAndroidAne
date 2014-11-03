package com.meizu.func;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.meizu.gamecenter.sdk.MzBuyInfo;
import com.meizu.gamecenter.sdk.MzGameCenterPlatform;
import com.meizu.gamecenter.sdk.MzPayListener;
import com.meizu.gamecenter.sdk.PayResultCode;

/**
 * 执行付费
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class MeizuPay implements FREFunction {

	private String TAG = "MeizuPay";
	private static String mUid;
	private String payUrl = "http://121.14.58.36:8080/game/demo/createorder?uid=";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		mUid = MeizuLogin.getUID();
		try
		{
			payUrl = arg1[0].getAsString();
		}
		catch (Exception e) {
			// TODO: handle exception
			String status = "参数错误";
			callBack(status);
		}
		
		callBack("BeginPay");
		pay();
		
		//--------------------------------
		
		return result;
	}

	private void pay(){
		final ProgressDialog progressDialog = new ProgressDialog(_context.getActivity());
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("请稍等...");
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();
		AsyncTask<Void, Void, MzBuyInfo> task = new AsyncTask<Void, Void, MzBuyInfo>(){
			@Override
			protected MzBuyInfo doInBackground(Void... params) {
				MzBuyInfo info = createOrder();
				progressDialog.dismiss();
				return info;
			}

			@Override
			protected void onPostExecute(MzBuyInfo result) {
				if(result != null){
					startPay(result);
				}else{
					callBack("生成订单失败.");
				}
			}
			
		};
		task.execute();
	}
	
	private MzBuyInfo createOrder(){
		if(!TextUtils.isEmpty(mUid)){
			try{
				// TODO 以下信息的获取需要游戏厂商访问自己的服务器，生成对应的订单信息
				String url = payUrl + mUid + "&product_id=1&buy_amount=1&pay_type=0";
				String res = UrlRequest.request(url);
				JSONObject object = new JSONObject(res);
				object = object.getJSONObject("value");
				String orderId = object.getString("cp_order_id");
				String sign = object.getString("sign");
				String signType = object.getString("sign_type");
				int buyCount = object.getInt("buy_amount");
				String cpUserInfo = object.has("user_info") ? object.getString("user_info") : "";
				String amount = object.has("total_price") ? object.getString("total_price") : "0";
				String productId = object.getString("product_id");
				String productSubject = object.getString("product_subject");
				String productBody = object.getString("product_body");
				String productUnit = object.getString("product_unit");
				String appid = object.getString("app_id");
				String uid = object.getString("uid");
				String perPrice = object.getString("product_per_price");
				long createTime = object.getLong("create_time");
				int payType = object.getInt("pay_type");
				return new MzBuyInfo().setBuyCount(buyCount).setCpUserInfo(cpUserInfo)
						.setOrderAmount(amount).setOrderId(orderId).setPerPrice(perPrice)
						.setProductBody(productBody).setProductId(productId).setProductSubject(productSubject)
						.setProductUnit(productUnit).setSign(sign).setSignType(signType).setCreateTime(createTime)
						.setAppid(appid).setUserUid(uid).setPayType(payType);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void startPay(MzBuyInfo buyInfo){
		MzGameCenterPlatform.payOnline(_context.getActivity(), buyInfo, new MzPayListener() {			
			@Override
			public void onPayResult(int code, MzBuyInfo info, String errorMsg) {
				// TODO 支付结果回调，该回调跑在应用主线程
				switch(code){
				case PayResultCode.PAY_SUCCESS:
					// TODO 如果成功，接下去需要到自己的服务器查询订单结果
					callBack("支付成功:" + info.getOrderId());
					break;
				case PayResultCode.PAY_ERROR_CANCEL:
					// TODO 用户取消支付操作
					break;
				default:
					// TODO 支付失败，包含错误码和错误消息。
					// TODO 注意，错误消息需要由游戏展示给用户，错误码可以打印，供调试使用
					callBack("支付失败:" + errorMsg + ":code:" + code);
					break;
				}
			}
		});
	}
	
	public void callBack(String str){
		Log.d(TAG, str);
		_context.dispatchStatusEventAsync(TAG, str);
	}
}
