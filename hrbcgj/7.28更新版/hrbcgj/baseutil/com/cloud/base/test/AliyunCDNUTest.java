package com.cloud.base.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
   
public class AliyunCDNUTest{
     
    private static  IAcsClient client=new DefaultAcsClient(); 
     
    private static  String accessKeyID="LTAI8AVzQ2Jf9zSy";
    private static  String accessKeySecret="0lQYGsSVW3zntCW5XuHvfBAGCa68Yh";
    private static  String res_url="http://www.xwood.net/images/xwood_logo_well.gif";
     
    private static  String  domain="dysmsapi.aliyuncs.com";
    private static  String  domain_path="/images/xwood_logo_well.gif";
     
    static{
        init();
    }
     
    public static void init(){
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyID,accessKeySecret);
            client= new DefaultAcsClient(profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    /**
     * 刷新资源方式-域名和资源路径分开
     */
//    public static void purgeObjectCaches() {
//        PurgeObjectCachesRequest request = new PurgeObjectCachesRequest();
//        //要刷新的域名
//        request.setDomainName(domain);
//        //要刷新的文件路径
//        request.setObjectPath(domain_path);
//        //刷新类型，默认是File,刷新目录为Directory
//        request.setObjectType("File");
//        //设置返回格式为JSON
//        request.setAcceptFormat(FormatType.JSON);
//        try {
//            HttpResponse httpResponse = client.doAction(request);
//            System.out.println(httpResponse.getUrl());
//            System.out.println(new String(httpResponse.getContent()));
//            System.out.println(httpResponse.getStatus());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//    }
// 
//    /**
//     * 刷新资源方式-域名和资源路径不分开
//     */
//    public static void refreshObjectCaches() {
//        RefreshObjectCachesRequest request = new RefreshObjectCachesRequest();
//        //要刷新的URI
//        request.setObjectPath(res_url);
//        //刷新类型，默认是File,刷新目录为Directory
//        request.setObjectType("File");
//        //设置返回格式为JSON
//        request.setAcceptFormat(FormatType.JSON);
// 
//        try {
//            HttpResponse httpResponse = client.doAction(request);
//            System.out.println(httpResponse.getUrl());
//            System.out.println(new String(httpResponse.getContent()));
//            System.out.println(httpResponse.getStatus());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//    }
// 
//    /**
//     * 查询资源刷新纪录
//     */
//    public static void describeRefreshTasks() {
//        DescribeRefreshTasksRequest request = new DescribeRefreshTasksRequest();
//        request.setObjectPath(res_url);
//        request.setPageSize(10);
//        request.setPageNumber(1);
//        //设置返回格式为JSON
//        request.setAcceptFormat(FormatType.JSON);
// 
//        try {
//            HttpResponse httpResponse = client.doAction(request);
//            System.out.println(httpResponse.getUrl());
//            System.out.println(new String(httpResponse.getContent()));
//            System.out.println(httpResponse.getStatus());
//    } catch (ServerException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    } catch (ClientException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
// 
//    }
// 
//    /**
//     * 查询CDN刷新剩余量
//     */
//    public static void describeRefreshQuota() {
//        DescribeRefreshQuotaRequest request = new DescribeRefreshQuotaRequest();
//        //设置返回格式为JSON
//        request.setAcceptFormat(FormatType.JSON);
//        try {
//            HttpResponse httpResponse = client.doAction(request);
//            System.out.println(httpResponse.getUrl());
//            System.out.println(new String(httpResponse.getContent()));
//            System.out.println(httpResponse.getStatus());
//        } catch (ServerException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ClientException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
// 
//    }
 
    public static void main(String[] args) {
//        AliyunCDNUTest.refreshObjectCaches();
    }
 
}