package com.cloud.base.attachment;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * HttpServletResponse帮助�?
 * 
 * 
 */
public final class ResponseUtils {
	public final static Logger logger =Logger.getLogger(ResponseUtils.class);

	/**
	 * 发�?�文本�?�使用UTF-8编码�?
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发�?�的字符�?
	 */
	public static void renderText(HttpServletResponse response, String text) {
		
		render(response, "text/plain;charset=UTF-8", text);
	}

	/**
	 * 发�?�json。使用UTF-8编码�?
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发�?�的字符�?
	 */
	public static void renderJson(HttpServletResponse response, String text) {
		render(response, "application/json;charset=UTF-8", text);
	}

	/**
	 * 发�?�xml。使用UTF-8编码�?
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发�?�的字符�?
	 */
	public static void renderXml(HttpServletResponse response, String text) {
		render(response, "text/xml;charset=UTF-8", text);
	}

	/**
	 * 发�?�内容�?�使用UTF-8编码�?
	 * 
	 * @param response
	 * @param contentType
	 * @param text
	 */
	public static void render(HttpServletResponse response, String contentType,
			String text) {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public static String convertJsonResult(boolean isSuccess){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("'success':");
		sb.append("'");
		sb.append(isSuccess);
		sb.append("'");
		sb.append("}");
		return sb.toString();
	}
}
