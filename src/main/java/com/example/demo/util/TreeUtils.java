package com.example.demo.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.SysPermission;

import java.util.List;

public class TreeUtils {

    /**
     * 菜单树
     *  @param parentId     父ID
     *  @param permissionsAll   所有permission结果集
     *  @param array            数据封装进的数组
     */
    public static void setPermissionsTree(Integer parentId, List<SysPermission> permissionsAll, JSONArray array){

        //遍历所有的权限
        for(SysPermission per : permissionsAll){
            if(per.getParentId().equals(parentId)){
                //转换成字符串
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject)JSONObject.parse(string);
                array.add(parent);
                if(permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny()!=null){
                    JSONArray child = new JSONArray();
                    parent.put("child",child);
                    setPermissionsTree(per.getId(),permissionsAll,child);

                }
            }
        }
    }
}
