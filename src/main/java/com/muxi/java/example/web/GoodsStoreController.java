package com.muxi.java.example.web;


import com.muxi.java.example.domain.GoodsStore;
import com.muxi.java.example.service.IGoodsStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author muxi
 * @since 2020-11-06
 */
@RestController
@RequestMapping("/")
public class GoodsStoreController {

    @Autowired
    private IGoodsStoreService iGoodsStoreService;

    public int updateStore(String code, Integer store) {
        return iGoodsStoreService.updateStore(code, store);
    }

    /**
     * 根据产品编号更新库存
     */
    public String updateGoodsStore(String code, int count) {
        return iGoodsStoreService.updateGoodsStore(code, count);
    }

    /**
     * 获取库存对象
     */
    public GoodsStore getGoodsStore(String code) {
        return iGoodsStoreService.getGoodsStore(code);
    }

    /**
     * 进入测试页面
     */
    @GetMapping("test")
    public ModelAndView stepOne(Model model) {
        return new ModelAndView("test", "model", model);
    }

    /**
     * 秒杀提交
     */
    @PostMapping("secKill")
    @ResponseBody
    public String secKill(@RequestParam(value = "code", required = true) String code,
                          @RequestParam(value = "num", required = true) Integer num) {
        return iGoodsStoreService.updateGoodsStore(code, num);
    }

}