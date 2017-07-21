package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * BaseController.java
 *
 * @author liwenjian
 * @date 2017年7月19日 上午11:51:10
 * @version 1.0.0
 */
public class BaseController {

	protected String success(Object obj) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", "0");
		resultMap.put("msg", "成功");
		resultMap.put("result", obj);
		return JSONObject.toJSONString(resultMap);
	}

	protected String success() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", "0");
		resultMap.put("msg", "成功");
		resultMap.put("result", null);
		return JSONObject.toJSONString(resultMap);
	}

	protected String error(Object obj) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", "1");
		resultMap.put("msg", "失败");
		resultMap.put("result", obj);
		return JSONObject.toJSONString(resultMap);
	}

	protected String error() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", "1");
		resultMap.put("msg", "失败");
		resultMap.put("result", null);
		return JSONObject.toJSONString(resultMap);
	}
}
