package com.cloud.base.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.cloud.bakedb.job.BakeDataBaseJob;

/**
 * 发送短信工具类
 * @author cloud-xuanxuan
 *
 */
public class SmsSendUtil {

	//产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static String accessKeyId = "LTAI8AVzQ2Jf9zSy";
    static String accessKeySecret = "0lQYGsSVW3zntCW5XuHvfBAGCa68Yh";
    
    static {
    	String config_other = SmsSendUtil.class.getResource("/").getPath() + "/config-other.properties";					//获取该属性文件下的配置
		accessKeyId = PropertyFileUtil.getValue(config_other, "accessKeyId");
		accessKeySecret = PropertyFileUtil.getValue(config_other, "accessKeySecret");
    }
    
    public static SendSmsResponse sendSMSRandomCode(String mobile , String code) {
    	try {
    		System.out.println("手机号：" + mobile +" -> 验证码：" + code);
    		//可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            String config_other = SmsSendUtil.class.getResource("/").getPath() + "/config-other.properties";					//获取该属性文件下的配置
			String randomCodeTemplateCode = PropertyFileUtil.getValue(config_other, "randomCodeTemplateCode");
            
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-qingdao", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-qingdao", "cn-qingdao", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(mobile);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("来古科技");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(randomCodeTemplateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"product\":\"哈尔滨城市管理局官方网站\", \"code\":\"" + code + "\"}");

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            
            LoggerUtil.info(SmsSendUtil.class, "短信接口返回的数据----------------");
            LoggerUtil.info(SmsSendUtil.class, "Code=" + sendSmsResponse.getCode());
            LoggerUtil.info(SmsSendUtil.class, "Message=" + sendSmsResponse.getMessage());
            LoggerUtil.info(SmsSendUtil.class, "RequestId=" + sendSmsResponse.getRequestId());
            LoggerUtil.info(SmsSendUtil.class, "BizId=" + sendSmsResponse.getBizId());
            
            return sendSmsResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}
