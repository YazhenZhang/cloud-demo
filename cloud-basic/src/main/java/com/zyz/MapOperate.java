package com.zyz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/5/6 11:03:18
 */
public class MapOperate {

    private List<String> m1(){
        Map<String, String> map =new HashMap<>();

        List<String> res = new ArrayList<>();

        // 写法1 需要多一次查询
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }

        // 推荐写法
        map.forEach((key, value) -> res.add(value));

        return res;
    }
}
