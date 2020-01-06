package com.project.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class BeanCopyUtil {

    public static <DO, VO> VO copyObject(DO source, Class<VO> targetClass) {
        try {
            VO vo = targetClass.newInstance();
            return new BenCopy<DO, VO>().copyObject(source, vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static <DO, VO> List<VO> copyList(List<DO> sources, Class<VO> targetClass) {
        List<VO> targets = new ArrayList<>(sources.size());
        try {
            // 初始化targets
            for (int i = 0; i < sources.size(); i++) {
                targets.add(targetClass.newInstance());
            }
            return new BenCopy<DO, VO>().copyList(sources, targets);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class BenCopy<DO, VO> {

        /**
         * 字段名称和属性保持一致
         * 例如：时间字段名称相同，类型不同，需要再VO类中修改成一致
         *
         * @param source 来源
         * @param target 目标
         */
        public VO copyObject(DO source, VO target) {
            BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopier.copy(source, target, null);
            return target;
        }

        public List<VO> copyList(List<DO> sources, List<VO> targets) {
            for (int i = 0; i < sources.size(); i++) {
                DO source = sources.get(i);
                VO target = targets.get(i);
                copyObject(source, target);
            }

            return targets;
        }
    }
}