package com.jaymiaosha;

import com.jaymiaosha.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JaySpringBootMiaoshaApplicationTests {
    @Autowired
    GoodsService goodsService;

    @Test
    public void AsycTets() {
    }

}
