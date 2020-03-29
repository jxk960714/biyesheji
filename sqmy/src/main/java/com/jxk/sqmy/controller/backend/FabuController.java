package com.jxk.sqmy.controller.backend;

import com.jxk.sqmy.entity.Fabu;
import com.jxk.sqmy.service.FabuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * (Fabu)表控制层
 *
 * @author makejava
 * @since 2020-03-01 19:11:51
 */
@RestController
@RequestMapping("/backend")
public class FabuController {
    /**
     * 服务对象
     */
    @Resource
    private FabuService fabuService;
    @PostMapping ("/fabu")
    public  Map<String,Object> fabu (@RequestBody Fabu fabu){
        Map<String,Object> resultMap=new HashMap<>();
        if (fabuService.fabu(fabu)){
            resultMap.put("success",true);
            resultMap.put("msg","发布成功");
            return resultMap;
        }else {
            resultMap.put("success",false);
            resultMap.put("msg","已发布，不用再发布");
            return resultMap;
        }



    }

}