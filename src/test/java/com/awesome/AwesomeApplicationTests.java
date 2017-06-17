package com.awesome;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional	//添加事务回滚
public class AwesomeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
