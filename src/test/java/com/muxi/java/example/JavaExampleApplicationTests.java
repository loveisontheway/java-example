package com.muxi.java.example;

import com.muxi.java.example.generator.MysqlGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaExampleApplicationTests {

	@Test
	void contextLoads() {
		MysqlGenerator generator = new MysqlGenerator();
		generator.generatorDefault("dev_image");
	}

	/*@Test
	void contextLoadsOther() {
		MysqlGenerator generator = new MysqlGenerator();
		generator.generatorOther("sys_user");
	}*/

}
