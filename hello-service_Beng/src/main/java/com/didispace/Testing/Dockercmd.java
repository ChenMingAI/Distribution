package com.didispace.Testing;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
public class Dockercmd {
    static String DockerContainerController(String cmd){
        Runtime rt = Runtime.getRuntime();
        try{
            rt.exec(cmd);
            return "success";

        }
        catch(IOException e){
            System.out.println("failed");
            return "Failed";
        }

    }
    static String DockerController(String cmd){
        Runtime rt = Runtime.getRuntime();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(rt.exec(cmd).getInputStream()));
            StringBuffer b = new StringBuffer();
            String line=null;
            Map<String,String> docker_info=new HashMap<String,String>();
            String [] arr = br.readLine().toString().split("\\s+");
            String[]  arr2=br.readLine().toString().split("\\s+");
            for(int i=0;i<2;i++){

                docker_info.put(arr[i],arr2[i]);



            }
            docker_info.put(arr[2]+" "+arr[3],arr2[2]);
            docker_info.put(arr[4],arr2[3]+" "+arr2[4]+" "+arr2[5]);
            docker_info.put(arr[5],arr2[6]);
            return JSON.toJSONString(docker_info);


        }
        catch(IOException e){
            System.out.println("failed");
            return "Failed";
        }

    }
    public static void main(String args[]){
        String res=DockerController("docker images");
        System.out.println(res);
    }


}
