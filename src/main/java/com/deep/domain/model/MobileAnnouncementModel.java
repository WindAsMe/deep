package com.deep.domain.model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * create by zhongrui on 18-3-12.
 */
public class MobileAnnouncementModel {
    private String identityCode;    //随机生成验证码
    private String mobile;      //注册时填写手机号
    private String mobile_list;   //用于多用户同时发短信
    private String message;         //生成短信内容

    public MobileAnnouncementModel(){

    }

    /**
     * 单人验证 用于密码找回
     * 功能:发送验证码
     * @param mobile
     */
    public MobileAnnouncementModel(String mobile){
        this.identityCode = "";
        this.mobile = mobile;
        this.message ="验证码:";
    }

    /**
     * 用于多人群发短信
     * 功能:提醒功能
     * @param mobile_list
     * @param message
     */
    public MobileAnnouncementModel(String mobile_list, String message){
        this.mobile_list = mobile_list;
        this.message = message;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile_list() {
        return mobile_list;
    }

    public void setMobile_list(String mobile_list) {
        this.mobile_list = mobile_list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 用于6位验证码的生成 发送
     * @return
     */
    public String testSendSingle(){
        // just replace key here
        Random random = new Random();
        for(int i=0; i<6; i++){
            identityCode = identityCode+random.nextInt(10);
        }
        message = message+identityCode+"【东翔验证】";
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
                "api","key-22c3576f6f618dfd8aabb19de974d99f"));
        WebResource webResource = client.resource(
                "http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        //System.out.println("testSend中:"+mobile_list);
        formData.add("mobile", mobile);
        formData.add("message", message);
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
                post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(textEntity);
        //System.out.print(status);
        return textEntity;
    }

    /**
     * 用于发送批量短信
     * @return
     */
    public String testSendMany(){
        // just replace key here
        message = message+"【东翔验证】";
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
                "api","key-22c3576f6f618dfd8aabb19de974d99f"));
        WebResource webResource = client.resource(
                "http://sms-api.luosimao.com/v1/send_batch.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        //System.out.println("testSend中:"+mobile_list);
        formData.add("mobile_list", mobile_list);
        formData.add("message", message);
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
                post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(textEntity);
        //System.out.print(status);
        return textEntity;
    }

    /**
     * 用于查看 返回状态
     * @return
     */
    public String testStatus(){
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
                "api","key-22c3576f6f618dfd8aabb19de974d99f"));
        WebResource webResource = client.resource( "http://sms-api.luosimao.com/v1/status.json" );
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        ClientResponse response =  webResource.get( ClientResponse.class );
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(status);
        //System.out.print(textEntity);
        return textEntity;
    }

}
