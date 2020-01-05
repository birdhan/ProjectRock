package com.cloud.sendemail.util;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.SimpleAuthenticationHandler;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.axis.security.SecurityProvider;
import org.apache.axis.security.simple.SimpleSecurityProvider;
import org.apache.axis.utils.Messages;

public class AuthenticationHandler extends SimpleAuthenticationHandler {
	private static final long serialVersionUID = 1L;

	public void invoke(MessageContext msgContext) throws AxisFault {
		System.out.println("webservice用户验证开始...");
		SecurityProvider provider = (SecurityProvider) msgContext.getProperty("securityProvider");
		if (provider == null) {
			provider = new SimpleSecurityProvider();
			msgContext.setProperty("securityProvider", provider);
		}
		String userId = msgContext.getUsername();
//		String password = msgContext.getPassword();
		// 对用户进行认证，如果authUser==null，表示没有通过认证，抛出Server.Unauthenticated异常。
		AuthenticatedUser authUser = provider.authenticate(msgContext);
		if (authUser == null) {
			System.out.println("用户验证失败!");
			throw new AxisFault("Server.Unauthenticated", Messages.getMessage("cantAuth01", userId), null, null);
		}
		
		// 用户通过认证，把用户的设置成认证了的用户。
		System.out.println("用户通过验证！");
		msgContext.setProperty("authenticatedUser", authUser);
	}
}
