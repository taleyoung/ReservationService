package com.ty.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ty.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2021000117623828";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCLnYYOMtvno0tp5rTYFTBIEGt9/n7Tq2HuOpVXqwud8OE1EMUdNwf0j5sPmYmPwmHQbWj8lecdQmPicOrkdgsOSh6IJpyCRcfqj1iksipX4S1sQ5Tkad5xjvxJN1R6dzNuAvo2n3aCEuDn4V1pIJ3e8nAxJnZ6+kT0E1xgBc4m4zTB6SvjcvRuJo3z2cpqsomuAunsQhnL6SESUMidY9vcSYORbJ12fukJ7UgK2YDxDCwjtTzyoyORubw1GJAIiUmTxb3xpNbYEQNan880gIaN1wLGyGfbqbGurd7XkfQ2VbEoQeRJhDIg4vH4pLJzOzKZdW8ZgFaTWeMcmFy5AS8JAgMBAAECggEAdZKkT9S4p6s2WOp2JPpC/I0HWMU23N4YzFv9ZCSg1dMCdIO42096nZNJy6tK0wuo/7MDlKJQTK2aHBlnVhmqWdBFQfbyGirVnkw1JAz955+zdta6zlfxgcQd5TJ5K+CyKNTNDa7gR83KJLv+xAc5q4SSwnFXmVylML4VLq/02X2B9r3w+pbKvMCGZ1EMuulugmQP5fpj5P+Oz1hCTC8lF0JXC2ssrTFWJ4wzjRXpfQMiwnXAufW3Enf5lLDmtESLaos33SO7zYHvh5ueCinJj0kIfh8zt0Qf2txr7uDpQlhS/QFMWyj/9yHrWNwzS5fNG8Ve6azuXgLoCBzX7Rg3AQKBgQDIqA5qm2oA69h1IbyZG+wN8v/bayGL032oAC07DgxEUd36osajUa1UIA8K9pkEL+6fGaRkjDxKmnM6KrJAYg4HomZiqBU0sGcsNqvm3i8vTxk8TwIzW2hdIkeqOO75vTktiOWNRGu0XTLBo9p8IX0srmH+mj5viCQ+oNxuPOA3cQKBgQCyH3oSKlvYPvKoNRylIbwKQSojtidFkN87lnPPcYTgZpXiGFHe+puqouNHu4KchiEMp/yNCN5Zf52CAnvt8GeJO1z9MqrNL3UWGLjjft5Vp1sT7QJ4MYMsa6/mEJ6nmG6/u0iEkWxXW1mRGw+ajwfWWPqVRDWz+COp1NrHDseVGQKBgA9qcekY1ZUKiY++LsoojgMoVyXZXjNq5S2CDfeKhcC99WrrFI25mbJr4Bv8hBcBQ7jvdGTRLVSh9xZR723o2qEVhf4wYaixoMuRy7Up/sAIuKyceVPOwv7cwlq+kwWW1wy7hr9KsWei/WJJH2EIBaDoKNkxqnhRIk+5dhX2EVfRAoGBAJ51fiU25aoEpd6aETeeQShmPFS0sU8jyFO8Tp6XAHyZBnGy7PKOw3kTm1mvKEySzkfyEaxt3VCFRNvp1dIg8hqVYu4k6unKdEEKEUDhFm3gihe5216NvYIA8uSamGnEI5IRzDuTG2zDaXS7PDBlWbWIRrQPkC7gaumk4FQBg+/RAoGAELx1jYoOiyEwcWlpgi6+BCICXjKrSpIIX8cwT786rmCzavCv51yxgfPIoDLkDTWKnnVZ10l54DHz9SFL3EvcE+cvp7kVI7D9J+taCfxkbLzFN09HqIvrzMQGpN9a5vQ7qKlawHb51SR7DKHoY55Qz7Y5sfFV8o9ywbnFuIIjlv4=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjoqwY5hn5Jy2dmu5/evCQjNusS/o9ozJ2oRpHqcH8VO/EawFii9XTTrc0jmG7973k8lB7uE8ZcrdnYUvplT4v0Y864zqhcB+iLN9yOcTNBb9KMAxjgDCxRl3xjLKK3r7aAfeKArM4eExEqzXjiKngz6gyDSNa8qK95+G8X3OImIJTvOAkPVROsfZiuQ237uldUWo7k4nok6q7mR3MVq5QofPWkJs7ZhK1J396EggomwFOh8AD2Fk+1t3KgNF2Ni2GMFQ8lWjEd4P2F/YebXBr+hGfBBiiaGyPCwASz5X290soZA/BYBPXkEcL0YPCswrwjgGW1GcPkH/txaPDEhivwIDAQAB";
	// 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url = "http://localhost:88/api/order/hote-order";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url = "http://localhost:3333/#/hotel/1";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    // 自动关单时间
    private String timeout = "15m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        // 30分钟内不付款就会自动关单
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\"" + timeout + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        return result;
    }
}
