package com.didispace.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
@RestController
public class ConsumerController{
    Logger logger = Logger.getLogger(ConsumerController.class);
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping(value="/hello-consumer")
    public String helloConsumer(){
        String res=restTemplate.getForObject("http://hello-service/hello",String.class);
        return res;
    }


    @RequestMapping(value="/dockercontainerStart")
    public String DockerContainerStart(){
        String res=restTemplate.getForObject("http://hello-service/DockerStart",String.class);
        return res;
    }

    @RequestMapping(value="/dockercontainerStop")
    public String DockerContainerStop(){
        String res=restTemplate.getForObject("http://hello-service/DockerStop",String.class);
        return res;
    }

    @RequestMapping(value="/dockercontainerStatus")
    public String DockerContainerStatus(){
        String res=restTemplate.getForObject("http://hello-service/ContainerManager",String.class);
        return res;
    }
}
