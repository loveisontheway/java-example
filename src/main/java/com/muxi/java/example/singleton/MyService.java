package com.muxi.java.example.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jjl
 * @date 2023/8/16
 */
@Service
public class MyService {
    @Autowired
    private GlobalObject globalObject;

    public void doSomething() {
        String str = globalObject.toString();
        System.out.println(str);
    }
}
