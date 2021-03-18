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
    private   String app_id = "2016102100732649";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkCEy6DS47iHvjIx8CgyXq47iRhCXRqjhIXFgdlvGUpWlpMMBJEIzzvs1YsTiD9R8Mwwt7jjGDhoPqhWeuY/YwoD3EwDmdugK1Dp7EonSfjGqEJNPSHDYehFX/BVyI7nfBJ8NvIAw25Q2FotsRf4y2FGiylgnKH47lVSEW6LH1voWeLpen4LAYbRIR5vY9SMaRmZeFNNzr9a84aDUFbWCwyS/8Q9VWBXLtqeyNFDs44MFOnbNASQ8Hddr7paKYOTnG3SxREv28Utebge1yp07gMzdKYtF6GuzS64fb4ut+rbn+5F575AATBtoYVkSVgNiF74o2wbIT/k2tet4sfFoNAgMBAAECggEAEgA3K1O29yGLNunrxULPLm+/GXj/DXs4ofhAUz71U/KzFrAa6em/DiCqOEl6gkCsH6Jn6C86j8x7PYlWT3T4120+WhEeQYjgMy5fjgSl97xZFBdWh4BBEChFp67W8M/6pF/QuqkxrfSE/11X3Ru6PWEP/Ekq2duMuu4Uq4btwKHy2U2xx5ByqpV+p0ktrlweGEgvSLm/vMsB6H1ZZRPmenkLfj7A9/g+Y+u4o2iH8/7l2m7/sB5drzTMNzDrnxLjHzcd7/JLIZvEKD1azMLbiGrD982lrnxw8vxiOuI1/zwwl7CH/n/PsmvCf7PiQQrKO5uOhD1MLgm8CJau31SgoQKBgQDSzvKmxi544W1rA0POLi4e5JWffewZwOzW9azET/bXklUaAhIKXjYVJneClJtwX/gdGiy631IL3btLkhrUgFUb05CpgSiOmg2rPlE6R3Sa3GTwUawIzZAs4FGzF5qbwLbgZylzF/A/eUr9VvaXITYZiNXD5XB3/SH/rBO6BJKALwKBgQDHMk/Ds8U3G18A3xaXs6HCAIL81kTCXZ/QcpkzrwKC6YFB2xNZ9qC5UqeSuO21nSZeZBvAT/LRs3bzOKpQOq9bqyy4GIWE6+uNnBGNgUd68jYPESVcUVfMNngLLp/8ilg+i3IIL5Amdyecj5khLTT81ryVD6/AqZYuB5IoyGTegwKBgQDHKTUeUsW89lzFaUQ6Gm8ASL0C/Zh9jEM1os0ietmeOGWmOKaKnQ9DVCA8geGIfWKPPBdeRXXiOA2nC9XFRakirPJS8e87KlgXnLxo7vE/DVFfqgl6F5tKinMlW/t5gsxDnbMPRWJ41UL/rZUymvHdleU/OhjTg5cHUa8hYwh1iQKBgH+h9QnoPg05NH5Scx9wscykl0l54UTIyIW/Z/p9wJYxF+kifhu+su0L0ONqKqWbs+H3MvDeSS+kVisxu678ay/JVjX/QE4S/DMYNQb+NtbIK2ssCUxFsoPewrwne8tMuY4Zu08TasG6x17Bg9SGKtV2og7AplREVT3ZDAWloTwRAoGAd1XEFOLT1jXYF17t61JoydvchjT3qppAIgCzS/RvYibLXH5Zqmgt4mjTZR4ZIqMcW/uYqN5xLrKJEwNTXN1z4ZtNqzgQOkAGx5G2i/lFRouMThLjF/J9mwY+wHv6Mab/llk4lDLw+/uyQBn2yRE1NX/DUGK9M88eTyPMFL4bOSk=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApAhMug0uO4h74yMfAoMl6uO4kYQl0ao4SFxYHZbxlKVpaTDASRCM877NWLE4g/UfDMMLe44xg4aD6oVnrmP2MKA9xMA5nboCtQ6exKJ0n4xqhCTT0hw2HoRV/wVciO53wSfDbyAMNuUNhaLbEX+MthRospYJyh+O5VUhFuix9b6Fni6Xp+CwGG0SEeb2PUjGkZmXhTTc6/WvOGg1BW1gsMkv/EPVVgVy7ansjRQ7OODBTp2zQEkPB3Xa+6WimDk5xt0sURL9vFLXm4HtcqdO4DM3SmLRehrs0uuH2+Lrfq25/uRee+QAEwbaGFZElYDYhe+KNsGyE/5NrXreLHxaDQIDAQAB";
	// 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url = "http://member.glmall.com/memberOrder.html";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url = "http://member.glmall.com/memberOrder.html";

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
