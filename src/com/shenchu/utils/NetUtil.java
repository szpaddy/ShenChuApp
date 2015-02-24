package com.shenchu.utils;

import java.util.Iterator;
import java.util.Map;

import android.content.Context;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NetUtil {
	public static void get(Context context, String url, int timeout,
			RequestCallBack<String> callBack) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(timeout);
		http.send(HttpMethod.GET, url, null, callBack);
	}

	public static void post(Context context, String url,
			Map<String, String> paramMap, int timeout,
			RequestCallBack<String> callBack) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(timeout);
		RequestParams requestParams = new RequestParams();
		if (paramMap != null) {
			Iterator<String> iterator = paramMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String value = paramMap.get(key);
				requestParams.addBodyParameter(key, value);
			}
		}

		http.send(HttpMethod.POST, url, requestParams, callBack);
	}
}
