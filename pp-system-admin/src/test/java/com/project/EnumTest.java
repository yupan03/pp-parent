package com.project;

import com.project.constant.status.UserState;
import com.project.dao.UserDao;
import com.project.entity.dao.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void userState(){
        userDao.updateState(1L, UserState.YES);

        List<UserDTO> data = userDao.getAllUser(null);
        System.out.println(data);
    }
}