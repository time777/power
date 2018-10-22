package com.yunshuju.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * web工具类
 */
public class WebUtils {

	/**
	 * ajax请求的相关常量定义
	 */
	public static final String AJAX_REQUEST_HEADER = "X-Requested-With";
	public static final String AJAX_REQUEST_VALUE = "XMLHttpRequest";
	public static final String AJAX_REQUEST_TYPE_JSON = "application/json";
	public static final String AJAX_REQUEST_TYPE_JSON_CHARTSET = "application/json;charset=UTF-8";


	public static boolean isAjax(HttpServletRequest request) {
		String ajaxHeader = request.getHeader(AJAX_REQUEST_HEADER);
		return !StringUtils.isEmpty(ajaxHeader) && ajaxHeader.contains(AJAX_REQUEST_VALUE);
	}

	/**
	 * 判断是否为ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(ServletRequest request) {
		HttpServletRequest httpRequest = null;
		if (request instanceof HttpServletRequest) {
			httpRequest = (HttpServletRequest) request;
		}
		if (httpRequest == null) {
			return false;
		}
		if (httpRequest.getHeader(AJAX_REQUEST_HEADER) != null) {
			return true;
		}
		String accept = httpRequest.getHeader("accept");
		if (accept == null) {
			return false;
		}
		return accept.indexOf(AJAX_REQUEST_TYPE_JSON) > -1;
//		return httpRequest.getHeader("X-Requested-With") != null || httpRequest.getHeader("accept").indexOf("application/json") > -1;
	}

	/**
	 * 获取当前网络ip
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
				//根据网卡取本机配置的IP
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress= inet.getHostAddress();
			}
		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		//"***.***.***.***".length() = 15
		if(ipAddress!=null && ipAddress.length()>15){
			if(ipAddress.indexOf(",")>0){
				ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
