package com.project.utils;

import com.project.entity.tables.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceTreeUtil {
    /**
     * * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static List<Resource> buildByRecursive(List<Resource> treeNodes) {
        List<Resource> trees = new ArrayList<Resource>();
        for (Resource treeNode : treeNodes) {
            if ("/".equals(treeNode.getParentUrl())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }

        return trees;
    }

    /**
     * * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    private static Resource findChildren(Resource treeNode, List<Resource> treeNodes) {
        for (Resource it : treeNodes) {
            if (treeNode.getResourceUrl().equals(it.getParentUrl())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<Resource>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    public static void main(String[] args) {
        Resource resource1 = new Resource("/", "用户管理", "/user");
        Resource resource2 = new Resource("/user", "获取用户基本信息", "/user/getUserInfo");
        Resource resource3 = new Resource("/", "角色管理", "/role");
        Resource resource4 = new Resource("/role", "新增", "/role/add");
        Resource resource5 = new Resource("/role", "修改", "/role/update");
        Resource resource6 = new Resource("/role", "删除", "/role/delete");

        List<Resource> treeNodes = new ArrayList<Resource>();
        treeNodes.add(resource1);
        treeNodes.add(resource2);
        treeNodes.add(resource3);
        treeNodes.add(resource4);
        treeNodes.add(resource5);
        treeNodes.add(resource6);

    }
}