package cn.itrip.auth.service;

import java.util.HashMap;
import java.util.Set;


import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.applet.Main;


@Service("smsService")
public class SmsServiceImpl implements SmsService {
	HashMap<String, Object> result = null;

	//初始化SDK
	CCPRestSmsSDK restAPI = new CCPRestSmsSDK();



	//******************************注释*********************************************
	//*初始化服务器地址和端口                                                       *
	//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
	//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
	//*******************************************************************************


	//******************************注释*********************************************
	//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
	//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
	//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
	//*******************************************************************************


	//******************************注释*********************************************
	//*初始化应用ID                                                                 *
	//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
	//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
	//*******************************************************************************


	@Override
	public void send(String to,String templateId, String[] datas) throws Exception {
		restAPI.init("app.cloopen.com", "8883");
		restAPI.setAccount("8a216da86772f10d01679099c9e5172e", "7d3a4d01a98b456a9519ee691fed7d90");
		restAPI.setAppId("8a216da86772f10d01679099ca2e1734");
		result = restAPI.sendTemplateSMS(to,templateId ,datas);//免费只能给注册的号码发送
		System.out.println("SDKTestGetSubAccounts result=" + result);
	}

}
