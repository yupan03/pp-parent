package com.pp.service.number;

import com.pp.dao.number.NumberDao;
import com.pp.entity.tables.Number;
import com.pp.utils.SysUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NumberService {
    @Autowired
    private NumberDao numberDao;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public String getUserNumber(String type) {
        Number newNumber = new Number();

        newNumber.setType(type);
        newNumber.setCreateTime(SysUtils.getCurrentTime());

        Number number = numberDao.selectLastNumber(type);
        if (number == null) {
            newNumber.setNumber("U" + "000001");
        } else {
            String no = number.getNumber().substring(1, number.getNumber().length());
            newNumber.setNumber(String.format("U%06d", Integer.valueOf(no) + 1));// 自动补齐六位
        }

//        numberDao.insert(newNumber);

        return newNumber.getNumber();
    }
}