package com.jaymiaosha.common.activeMQ;

import com.jaymiaosha.pojo.MiaoshaOrder;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.pojoVo.GoodsVo;
import com.jaymiaosha.result.CodeMsg;
import com.jaymiaosha.result.Result;
import com.jaymiaosha.service.GoodsService;
import com.jaymiaosha.service.MiaoShaOrderService;
import com.jaymiaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.HashMap;
import java.util.List;

/**
 * activeMQ 发送和接受 消息类
 */
@Component
public class RoncooJmsComponent {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MiaoShaOrderService miaoShaOrderService;

    public void send(MiaoshaMessage msg) {
        String string = RedisUtil.beanToString(msg);
        System.out.println("发送数据：" + string);
        this.jmsMessagingTemplate.convertAndSend(this.queue, string);
    }

    @JmsListener(destination = "roncoo.queue")
    public void receiveQueue(String msg) {
        MiaoshaMessage miaoshaMessage = RedisUtil.stringToBean(msg, MiaoshaMessage.class);
        long goodsId = miaoshaMessage.getGoodsId();
        MiaoshaUser user = miaoshaMessage.getUser();
        //查库是否还有库存
        HashMap map = new HashMap();
        map.put("id", goodsId);
        List<GoodsVo> goods = goodsService.selectGoodesVo(map);
        GoodsVo goodsVo = null;
        if (goods != null && goods.size() > 0) {
            goodsVo = goods.get(0);
            Integer stockCount = goodsVo.getStockCount();
            if (stockCount <= 0) {
                return;
            }
        }
        //判断是否已经秒杀到
        MiaoshaOrder miaoshaOrder = miaoShaOrderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (miaoshaOrder != null) {
            return;
        }
        //没有则开始下订单写入秒杀订单
        miaoShaOrderService.startMiaoshaOrder(user, goodsVo);
        System.out.println("接受到：" + miaoshaMessage.getGoodsId());
    }

}
