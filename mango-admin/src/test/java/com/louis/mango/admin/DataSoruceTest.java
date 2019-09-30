package com.louis.mango.admin;

import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by liyue
 * Time 2019-09-16 11:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSoruceTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void test89() {


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 加密$2a$10$2nbzdeNnOFCvQTZPlCcIm.nHStart/27Ufxhf7GHi.R8C/cQdqLQ6
        String encodedPassword = passwordEncoder.encode("123");
        System.out.println(encodedPassword);
    }

    @Test
    public void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement prepareStatement = connection
                .prepareStatement("select * from sys_user where 1=1");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            String cityName = resultSet.getString("name");
            System.out.println(cityName);
        }
    }
}