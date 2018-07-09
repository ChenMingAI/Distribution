package com.didispace.controller;


import com.didispace.dao.*;
import com.didispace.entity.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class HelloController {

    Logger logger = Logger.getLogger(HelloController.class);

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    private UserInfoMapper userInfoMapper;
    private static final ObjectMapper mapper = new ObjectMapper();
    String DockerContainerController(String cmd){
        Runtime rt = Runtime.getRuntime();
        try{
            rt.exec(cmd);
            return JSON.toJSONString("success");

        }
        catch(IOException e){
            System.out.println("failed");
            return "Failed";
        }

    }
    String docker_containerinfo(Map<String,String> docker_info,String[] arr,String[] arr2){
        docker_info.put(arr[0]+" "+arr[1],arr2[0]);
        docker_info.put(arr[2],arr2[1]);

        docker_info.put(arr[3],arr2[2]);
        docker_info.put(arr[4],arr2[3]+" "+arr2[4]+" "+arr2[5]);
        if (arr2.length==12){
        docker_info.put(arr[5],arr2[6]+" "+arr2[7]+" "+arr2[8]+" "+arr2[9]+" "+arr2[10]);
            docker_info.put(arr[6]," ");
            docker_info.put(arr[7],arr2[11]);
        }
        else if(arr2.length==11){
            docker_info.put(arr[5],arr2[6]+" "+arr2[7]+" "+arr2[8]);
            docker_info.put(arr[6],arr2[9]);
            docker_info.put(arr[7],arr2[10]);
        }

        return JSON.toJSONString(docker_info);
    }

    String docker_infopro(Map<String,String> docker_info,String[] arr,String[] arr2){
        for(int i=0;i<2;i++){

            docker_info.put(arr[i],arr2[i]);



        }
        docker_info.put(arr[2]+" "+arr[3],arr2[2]);
        docker_info.put(arr[4],arr2[3]+" "+arr2[4]+" "+arr2[5]);
        docker_info.put(arr[5],arr2[6]);
        return JSON.toJSONString(docker_info);
    }

    String DockerStatusQuery(String cmd){
        Runtime rt = Runtime.getRuntime();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(rt.exec(cmd).getInputStream()));
            StringBuffer b = new StringBuffer();
            ArrayList<String> strArray=new ArrayList<String>();
            String line=null;
            Map<String,String> docker_info=new HashMap<String,String>();

            String[] arr = br.readLine().toString().split("\\s+");
            while((line=br.readLine())!=null) {

                String[] arr2 = line.toString().split("\\s+");
                strArray.add(docker_infopro(docker_info, arr, arr2));
            }
            return JSON.toJSONString(strArray);

        }
        catch(IOException e){
            System.out.println("failed");
            return "Failed";
        }

    }

    String DockerContainerStatusQuery(String cmd){
        Runtime rt = Runtime.getRuntime();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(rt.exec(cmd).getInputStream()));
            ArrayList<String> strArray=new ArrayList<String>();
            String line=null;
            Map<String,String> docker_info=new HashMap<String,String>();

            String[] arr = br.readLine().toString().split("\\s+");
            while((line=br.readLine())!=null) {

                String[] arr2 = line.toString().split("\\s+");
                strArray.add(docker_containerinfo(docker_info, arr, arr2));
            }
            return JSON.toJSONString(strArray);

        }
        catch(IOException e){
            System.out.println("failed");
            return "Failed";
        }

    }
    @RequestMapping(value="/DockerStop")
    public String DockerStop() {
        return DockerContainerController("docker stop mysql57");

    }
    @RequestMapping(value="/DockerStart")
    public String DockerStart() {
        return DockerContainerController("docker start mysql57");

    }
    @RequestMapping(value="/hello")
    public String hello() {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();

        logger.info("*********" + instance.getServiceId());

//        List<UserInfo> userInfos=userInfoMapper.selectAll();
//        try{
//            String res1=mapper.writeValueAsString(userInfos);
//            return res1;
//        }
//        catch (IOException e){
//            System.out.println("error");
//            return "Failed";
//        }
        return DockerStatusQuery("docker images");

    }
    @RequestMapping(value="/ContainerManager")
    public String ContainerManager() {

        return DockerContainerStatusQuery("docker ps -a");

    }
}