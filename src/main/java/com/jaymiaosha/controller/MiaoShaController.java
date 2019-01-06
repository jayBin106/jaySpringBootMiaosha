package com.jaymiaosha.controller;

import com.jaymiaosha.common.MiaoShaUserKey;
import com.jaymiaosha.common.access.AccessLimit;
import com.jaymiaosha.common.activeMQ.MiaoshaMessage;
import com.jaymiaosha.common.activeMQ.RoncooJmsComponent;
import com.jaymiaosha.pojo.MiaoshaOrder;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.pojoVo.GoodsVo;
import com.jaymiaosha.result.CodeMsg;
import com.jaymiaosha.result.Result;
import com.jaymiaosha.service.GoodsService;
import com.jaymiaosha.service.MiaoShaOrderService;
import com.jaymiaosha.service.MiaoshaGoodsService;
import com.jaymiaosha.util.RedisUtil;
import com.jaymiaosha.util.UUidUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.jaymiaosha.common.MiaoShaUserKey.tokenPath;
import static com.jaymiaosha.common.MiaoShaUserKey.tokenPic;

/**
 * Created by lenovo on 2018/7/31.
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoShaController implements InitializingBean {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MiaoshaGoodsService miaoshaGoodsService;
    @Autowired
    MiaoShaOrderService miaoShaOrderService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    private RoncooJmsComponent roncooJmsComponent;


    private HashMap<Long, Boolean> localOverMap = new HashMap<Long, Boolean>();

    /**
     * 系统初始化加载
     */
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVos = goodsService.selectGoodesVo(new HashMap());
        if (goodsVos != null && goodsVos.size() != 0) {
            for (GoodsVo goodsVo : goodsVos) {
                redisUtil.set(MiaoShaUserKey.getMiaoshaGoodsStock.getPrefix() + goodsVo.getId(), goodsVo.getStockCount() + "");
                localOverMap.put(goodsVo.getId(), false);
            }
        }
    }

    /**
     * 验证验证码和生成path
     * AccessLimit 限流注解
     *
     * @param miaoshaUser
     * @param goodsId
     * @param verifyCode
     * @return verifyCode(MiaoshaUser user, HttpServletResponse response, HttpSession httpSession, @ RequestParam ( value = " goodsId ") Integer goodsId, @RequestParam(value = "timestamp") Long timestamp) {
     */
    @AccessLimit(seconds = 5, maxCount = 5, needLogin = true)
    @ResponseBody
    @RequestMapping("/path")
    public Result<String> get(HttpServletResponse response, HttpSession httpSession, MiaoshaUser miaoshaUser, @RequestParam(value = "goodsId") Integer goodsId, @RequestParam(value = "verifyCode") String verifyCode) {
        if (miaoshaUser == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        String realKey = tokenPic.getPrefix() + miaoshaUser.getId() + "_" + goodsId;
        String realValue = redisUtil.get(realKey);
        redisUtil.del(realKey);
        if (!verifyCode.equals(realValue)) {
            return Result.error(CodeMsg.BIND_ERROR);
        }
        String s1 = UUidUtil.get();
        redisUtil.set(tokenPath, miaoshaUser.getId() + "_" + s1, s1);
        return Result.success(s1);
    }

    /**
     * 秒杀操作
     *
     * @param miaoshaUser
     * @param httpSession
     * @param path
     * @return
     */
    @ResponseBody
    @RequestMapping("/{path}/do_miaosha")
    public Result do_miaosha(MiaoshaUser miaoshaUser, HttpSession httpSession, @PathVariable(value = "path") String path, @RequestParam("goodsId") long goodsId) {
        if (miaoshaUser == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        String realkey = tokenPath.getPrefix() + miaoshaUser.getId() + "_" + path;
        String realPath = redisUtil.get(realkey);
        redisUtil.del(realkey);
        //验证path
        if (!realPath.equals(path)) {
            return Result.error(CodeMsg.BIND_ERROR);
        }
        //判断是否是否已经下单成功
        MiaoshaOrder miaoshaOrder = miaoShaOrderService.getMiaoshaOrderByUserIdGoodsId(miaoshaUser.getId(), goodsId);
        if (miaoshaOrder != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //减少redis库存
        Long increment = redisUtil.increment(MiaoShaUserKey.getMiaoshaGoodsStock.getPrefix() + goodsId, -1);
        if (increment < 0) {
            localOverMap.put(goodsId, true);
            return Result.error(CodeMsg.MIAOSHA_SCOUNT);
        }
        //订单入队
        MiaoshaMessage miaoshaMessage = new MiaoshaMessage(miaoshaUser, goodsId);
        roncooJmsComponent.send(miaoshaMessage);
        return Result.success(CodeMsg.SUCCESS);
    }

    /**
     * orderId：成功
     * -1：秒杀失败
     * 0： 排队中
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> miaoshaResult(ModelMap modelMap, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        modelMap.put("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long result = miaoShaOrderService.getMiaoshaResult(user.getId(), goodsId);
        return Result.success(result);
    }

    /**
     * 验证码生成
     *
     * @param user
     * @param response
     * @param httpSession
     * @param goodsId
     * @param timestamp
     * @return
     */
    @ResponseBody
    @RequestMapping("/verifyCode")
    public Result<String> verifyCode(MiaoshaUser user, HttpServletResponse response, HttpSession httpSession, @RequestParam(value = "goodsId") Integer goodsId, @RequestParam(value = "timestamp") Long timestamp) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        try {
            BufferedImage image = miaoshaGoodsService.createVerifyCode(user, goodsId, httpSession);
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Result.error(CodeMsg.MIAOSHA_FAIL);
        }
    }
}
