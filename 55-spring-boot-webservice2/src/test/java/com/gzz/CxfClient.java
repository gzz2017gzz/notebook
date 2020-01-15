package com.gzz;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CxfClient {
	public static void main(String[] args) throws Exception {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:8080/services/hello?wsdl");
		Object[] objects = client.invoke("say", "张三");
		System.out.println("say=" + objects[0]);
	}
}
