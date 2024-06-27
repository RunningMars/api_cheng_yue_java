package com.cheng.api.util;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.cheng.api.entity.SendSms;

public class SmsUtil {

    /**
     * <b>description</b> :
     * <p>使用AK&amp;SK初始化账号Client</p>
     * @return Client
     *
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考。
        // 建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html。
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()

                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(ConfigUtil.getProperty("ALI_YUN_ACCESS_KEY_ID"))
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(ConfigUtil.getProperty("ALI_YUN_ACCESS_KEY_SECRET"));
        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi  成都地区
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static SendSms sendValidateSms(String mobile, String code) throws Exception {

        String smsSignName = "缘来网";

        String smsTemplateCodeValidate = ConfigUtil.getProperty("ALI_YUN_SMS_TEMPLATE_CODE_VALIDATE");

        return sendSms( mobile,smsSignName, smsTemplateCodeValidate,"{\"code\":\"" + code + "\"}" );
    }

    public static SendSms sendSms(String mobile, String SignName, String templateCode, String templateParam) throws Exception {
        //JSONObject.toJSONString(param);
        com.aliyun.dysmsapi20170525.Client client = SmsUtil.createClient();
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers(mobile)
                .setSignName(SignName)
                .setTemplateCode(templateCode)
                .setTemplateParam(templateParam);
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, new RuntimeOptions());
            SendSmsResponseBody body = sendSmsResponse.getBody();
            String status = "failed";
            if (sendSmsResponse.getStatusCode() == 200 && body.getCode().equals("OK")){
                status = "success";
            }
            SendSms sendSms = new SendSms();
            sendSms.setMobile(mobile);
            sendSms.setSignName(SignName);
            sendSms.setTemplateCode(templateCode);
            sendSms.setTemplateParam(templateParam);
            sendSms.setStatus(status);
            sendSms.setMessage(body.getMessage());
            sendSms.setCode(body.getCode());
            sendSms.setBizId(body.getBizId());
            sendSms.setRequestId(body.getRequestId());
            sendSms.setResponse(JSON.toJSONString(sendSmsResponse));

            return sendSms;
        } catch (TeaException error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
            return null;
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
            return null;
        }
    }
}
