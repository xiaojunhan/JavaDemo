package com.foo.app.rpc;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
/**
 * Created with IntelliJ IDEA.
 * User: zongjianhui
 * Date: 13-6-20
 * Time: 下午3:57
 * To change this template use File | Settings | File Templates.
 */
public class TestClient {
    public static void main(String[] args) {
        try {
            //配置客户端
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            //设置服务器端地址
            config.setServerURL(new URL("http://localhost:9980/Rpc/HelloHandler"));
            //创建XmlRpc客户端
            XmlRpcClient client = new XmlRpcClient();
            //绑定以上设置
            client.setConfig(config);
            //创建参数列表
            Vector<String> params = new Vector<String>();
            params.addElement("flyoung");
            //执行XML-RPC 请求
            String result =(String) client.execute("HelloHandler.execute", params);
            System.out.println("result:"+result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }
}
