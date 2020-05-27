package com.project;

import com.project.controller.ControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 一键启动需要测试的所有类
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ControllerTest.class
})
public class AllClassTest {
}