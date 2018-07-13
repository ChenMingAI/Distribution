package com.didispace.Testing;
import com.didispace.HelloApplication;
import com.didispace.entity.*;
import com.didispace.dao.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HelloApplication.class)
public class Testing {
    @Autowired
    private UserInfoMapper userInfoMapper;

    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    public void Test(){

        List<UserInfo> uf=userInfoMapper.selectAll();

//        String res1=mapper.writeValueAsString(userInfos);
        System.out.println(uf.get(0).getUser_name());

    }


}
