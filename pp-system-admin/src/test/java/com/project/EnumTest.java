package com.project;

import com.project.constant.status.UserState;
import com.project.entity.dao.dto.UserDTO;
import com.project.entity.dao.qo.UserQO;
import com.project.service.CommonService;
import common.utils.IdUtil;
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
    private CommonService commonService;

    @Test
    public void userState() {
        commonService.getUserDao().updateState(1L, UserState.YES);

        List<UserDTO> data = commonService.getUserDao().getAllUser(null);
        System.out.println(data);
    }

    @Test
    public void addUser() {
        UserQO userQO = new UserQO();

        userQO.setId(IdUtil.getId());
        userQO.setAccount("yupan8");
        userQO.setName("吁盼");

        commonService.getUserDao().insertUser(userQO);

        commonService.getUserDao().updateState(userQO.getId(), UserState.NO);
    }
}