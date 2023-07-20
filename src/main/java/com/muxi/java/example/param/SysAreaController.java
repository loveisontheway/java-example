package com.muxi.java.example.param;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中国行政区划-Controller
 *
 * @author elec
 * @date 2023-05-05
 */
@RestController
@RequestMapping("/sys/area")
public class SysAreaController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * get
     */
    @GetMapping("/get")
    public AjaxResult get(@RequestParam Long uid) {
        System.out.println(uid);
        return AjaxResult.success();
    }

    @GetMapping("/get/id")
    public AjaxResult getId(@RequestParam(value = "id") Long uid) {
        System.out.println(uid);
        return AjaxResult.success();
    }

    // 推荐 RPC调用 > 必须带 @RequestParam
    @GetMapping("/get/val")
    public AjaxResult getVal(@RequestParam(defaultValue = "0") Long uid) {
        System.out.println(uid);
        return AjaxResult.success();
    }

    @GetMapping("/get/nothing")
    public AjaxResult getNothing(Long uid) {
        System.out.println(uid);
        return AjaxResult.success();
    }

    @GetMapping("/get/pv/{uid}")
    public AjaxResult getPv(@PathVariable Long uid) {
        System.out.println(uid);
        return AjaxResult.success();
    }

    @GetMapping("/get/obj")
    public AjaxResult getObj(@RequestParam SysArea sysArea) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        return AjaxResult.success();
    }

    @GetMapping("/get/obj/valid")
    public AjaxResult getObjValid(@RequestParam @Valid SysArea sysArea) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        return AjaxResult.success();
    }

    @GetMapping("/get/obj/nothing")
    public AjaxResult getObjNothing(SysArea sysArea) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        return AjaxResult.success();
    }

    @GetMapping("/get/obj/model")
    public AjaxResult getObjModel(@ModelAttribute SysArea sysArea) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        return AjaxResult.success();
    }

    @GetMapping("/get/list")
    public AjaxResult getList(@RequestParam List<String> list) {
        System.out.println(JSONUtil.toJsonStr(list));
        return AjaxResult.success();
    }

    // postman 传参对象list（无法验证）form-data
    @GetMapping("/get/list/obj")
    public AjaxResult getListObj(@ModelAttribute List<SysArea> list) {
        System.out.println(JSONUtil.toJsonStr(list));
        return AjaxResult.success();
    }

    // axios get请求 传参json格式（axios不支持，postman支持）
    @GetMapping("/get/list/obj/json")
    public AjaxResult getListObjJson(@RequestBody List<SysArea> list) {
        System.out.println(JSONUtil.toJsonStr(list));
        return AjaxResult.success();
    }

    @GetMapping("/get/arr")
    public AjaxResult getArr(@RequestParam String[] arr) {
        System.out.println(JSONUtil.toJsonStr(arr));
        return AjaxResult.success();
    }

    // link > /get/list/obj
    @GetMapping("/get/arr/obj")
    public AjaxResult getArrObj(@ModelAttribute SysArea[] areaArr) {
        System.out.println(JSONUtil.toJsonStr(areaArr));
        return AjaxResult.success();
    }

    // link > /get/list/obj/json
    @GetMapping("/get/arr/obj/json")
    public AjaxResult getArrObjJson(@RequestBody SysArea[] areaArr) {
        System.out.println(JSONUtil.toJsonStr(areaArr));
        return AjaxResult.success();
    }

    @GetMapping("/get/map")
    public AjaxResult getMap(@RequestParam Map<String, Object> map) {
        System.out.println(JSONUtil.toJsonStr(map));
        return AjaxResult.success();
    }

    @GetMapping("/get/rpc")
    public AjaxResult getRpc() {
        HttpHeaders headers = new HttpHeaders();
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjIyMTU4MDRmLTA1ZDYtNDU4OS05MmJiLTJkYTQxNzI1MWRlNCJ9.-U1twS-w4lQQwpcIT7DMuYwa15FV9xnJ7wNbhkDBJdD7JWm9vsDH2r8uXjz85YuOc2jvayzyuKR0mojRDnbZrA";
        headers.add("Authorization", token);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", "101");
        ResponseEntity<AjaxResult> getVal = restTemplate.exchange(
                "http://localhost:8080/sys/area/get/val",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                AjaxResult.class,
                paramMap);
        ResponseEntity<AjaxResult> getNothing = restTemplate.exchange(
                "http://localhost:8080/sys/area/get/nothing",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                AjaxResult.class);
        return AjaxResult.success();
    }

    /**
     * post
     */
    @PostMapping("/post/obj")
    public AjaxResult postObj(@ModelAttribute SysArea sysArea) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        return AjaxResult.success();
    }

    @PostMapping("/post/obj/json")
    public AjaxResult postObjJson(@RequestBody SysArea sysArea) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        return AjaxResult.success();
    }

    @PostMapping("/post/multi")
    public AjaxResult postMulti(@RequestParam Long uid, @ModelAttribute SysArea sysArea) {
        System.out.println(uid);
        System.out.println(JSONUtil.toJsonStr(sysArea));
        return AjaxResult.success();
    }

    @PostMapping("/post/multi/list")
    public AjaxResult postMultiList(@RequestParam Long uid,
                                    @ModelAttribute SysArea sysArea,
                                    @RequestBody List<SysArea> list) {
        System.out.println(uid);
        System.out.println(JSONUtil.toJsonStr(sysArea));
        System.out.println(JSONUtil.toJsonStr(list));
        return AjaxResult.success();
    }

    /**
     * file
     */
    @PostMapping("/file")
    public AjaxResult file(@RequestParam MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return AjaxResult.success();
    }

    @PostMapping("/file/multi")
    public AjaxResult fileMulti(@RequestPart MultipartFile[] files) {
        for (MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
        }
        return AjaxResult.success();
    }

    @PostMapping("/file/obj")
    public AjaxResult fileObj(@ModelAttribute SysArea sysArea,
                              @RequestParam MultipartFile file) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        System.out.println(file.getOriginalFilename());
        return AjaxResult.success();
    }

    @PostMapping("/file/obj/nothing")
    public AjaxResult fileObjNothing(SysArea sysArea,
                                     MultipartFile file) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        System.out.println(file.getOriginalFilename());
        return AjaxResult.success();
    }

    @PostMapping("/file/part")
    public AjaxResult filePart(@RequestPart SysArea sysArea,
                               @RequestPart MultipartFile file) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        System.out.println(file.getOriginalFilename());
        return AjaxResult.success();
    }

    @PostMapping("/file/part/model")
    public AjaxResult filePartModel(@ModelAttribute SysArea sysArea,
                                    @RequestPart MultipartFile file) {
        System.out.println(JSONUtil.toJsonStr(sysArea));
        System.out.println(file.getOriginalFilename());
        return AjaxResult.success();
    }
}