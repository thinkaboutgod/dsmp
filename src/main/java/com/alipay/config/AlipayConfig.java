package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092900624451";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCC1MSdPBjxbfz1IdGmfHVcpZ4uRd7FS2CazK3jPBkkCIQmzUCJIlxppiXAibS9vQ7XlH5VG7wF7d6dQEiQMpwlj3WqpzrJ/RyKFLqdr8/pkhc9mq7weu0z5I67qdZswSVGRNQVSeOtZ2PyrhIICJ3JJyI5ft60QsyFXCithlfazOQ3KHlRJTakGGIEDVixhnOREWH6PRPdhwIuf1pklsWiRgIoN1soc6XgpCC6XZYngGK4Kyd2Sdg1sWz9022cJaB5OM+N/Na7YbTJgg85i3dsfMI9B10IhXiIsEt8anrXpbpPc2UEJ/65/2/2g4k4uXNIbdU6zAt/sRjUdQzKOqMdAgMBAAECggEAHfhpte8/c4cTrH/7y9y08e1KqCT/XEjhL3T13Jk21SdSRqU8RG3np5gVC98/1TtK/B2bNxnsX5tXJMTGGqpNVNlhRh+a+wyNjiAjg93zjfkWc9lMe/BdCEcyX1EPusggJtG7l8jebCnEHqK7pLV8l8jsMSmcEMVAnsg21VFyLL/xKGyB3dz3nkhizGfehH9A10qJThbGoHNyO3pJpP3LlTZxuMCIrtAa+7eN3xFHjwoMhKG1Rr6t/mbVN61tMtjpgSEn4/hbg4/TVqcjy5595SAhTYc7P6i9A6GvJC9fooNEL/C1TLFTwzpefwtYfbEUmw8RsqTQPcU5XP89A/Fl4QKBgQC5mUOf5942Ng6piIDIcx6G0viX8lMm5YUgnfqvKd1U1MYZp4iT/O1hqiYDn+7ry2OcHGA0/nH07S4FPRp2wehcwd3rll7qjXlreTV7p4xM/zPdNHP1MmLtbjeyMTV6xjxbmcktA07StqHQ22B1JcCbxaevjZAUVOU7L0K1r6Ul5QKBgQC0dT95n9z/ufhPLHfUZNhGHokvYG3CN0BmE3fGirCzRVcAeWoNLd08DEwObZMg96Hfk/ZLa4y148YRTt+FTJ8aLVfFCHFf8is3fH1Nl+0H4uQlEbTdfxF2g/WPFPkmtaPYk3GNHpfa/XsGDuncNiPtSKHzPfqf5xeovx9PZK802QKBgGGJKuG5D607oV70zoEJBoziCCSKA6rYSasHertc6+Tt4KGQypTDAdkmes3C2eNuwo1C2EhhQ2/syReVsfehFcTRWRe6sWzgJmQVkcbHGVNJdm8UZq//579RAdISTEGWNhgJbJDMQTs5ITbKYxmNWnhCWkpGweX9a247+vnSMZ8FAoGAMTr3WR/is6Uxd7AC9N+aHO8Br/oXl29pUy+mcnStpgqZpJh69SN5AoO8UAdWpxzHfW4pOAQcPBbqlxg0ZHI1yXdrRWZ7P5iab6W3rW5wDMG6Sd6dv/uFcRao7CLlocjV7SS0SrJ47RZP9KQm6puei4JQJELZV7w8uR2C1i76mWkCgYEAlPL9fzKF2SEg84QLJR1sVFplb1JamlrWslThZTppIdUjNVziu4pq7aenTHE7ROJcgwIlVenlLVWWbggn+ZchnIwCi7a/Em9DSp44623oq7+pIo8Or5C7NzMQnRfROhFQVCf1QWmWelRRQRHCDlsLG75x8/WSvMrbILmr3kgnGEU=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhi++yKSIMeAHZF05NXgq2dISizshts2O/Q212iHNl8kfC8rLmgDXrcDq3ZKSlGjjs9RiUL/khEqhN9+laLFjEuISCiIkn+GvBm7haDW41Mm6uHjYGdqI4nSGYK6DCr0oV0lHGgd2fzYyxB3RxQzvF9rWj9Gi3YM3ta6/Cu6+9ZTsijmyI8lh40LaCa9MUvUoSwM4HEBJafkOc9C8GWeFgTdaut3A5JJZ7t+x00xr1sBXSiY6PQlqaohDPa7+7t2A6WAhU6MN/AsZ06VjaqgzXe6Dg1ynDNVNoI/gMy9kSwHynkr8Yc2Gkgjd3fHrIsU2NFikWOR3DV2U/vSmr78daQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
	public static String notify_url = "http://192.168.43.102:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
	public static String return_url = "http://192.168.43.102:8080/dsmp/student/main.action?";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
    	AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2016072200101XXXX","请复制第1步中生成的密钥中的商户应用私钥","json","utf-8","沙箱环境RSA2支付宝公钥","RSA2");
    	
    	
    	
    	
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}

