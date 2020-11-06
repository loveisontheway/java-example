package com.muxi.java.example.web;


import com.muxi.java.example.service.IUserMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author muxi
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/userMp")
public class UserMpController {

    @Autowired
    private IUserMpService iUserMpService;

    @PostMapping("add")
    public void add() {
        iUserMpService.add();
    }
}

