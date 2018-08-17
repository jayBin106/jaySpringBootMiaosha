package com.jaymiaosha.common;

/**
 * Created by lenovo on 2018/7/31.
 */
public class MiaoShaUserKey extends BasePrefix {
    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;
    public static final int TOKEN_EXPIRE_PIC = 3600 * 2;
    public static final int TOKEN_EXPIRE_PATH = 3600 * 2;

    public MiaoShaUserKey(String prefix) {
        super(prefix);
    }

    public MiaoShaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoShaUserKey token = new MiaoShaUserKey(TOKEN_EXPIRE, "tk:");
    public static MiaoShaUserKey tokenPic = new MiaoShaUserKey(TOKEN_EXPIRE_PIC, "tkPic:");
    public static MiaoShaUserKey tokenPath = new MiaoShaUserKey(TOKEN_EXPIRE_PATH, "tkPath:");
    public static MiaoShaUserKey getMiaoshaGoodsStock = new MiaoShaUserKey(0, "goodsStock:");
    public static MiaoShaUserKey getMiaoshaOrder = new MiaoShaUserKey(0, "goodOrder:");

    /*接口限流5秒钟访问5次*/
    public static MiaoShaUserKey getMiaoshaCount(int expireSeconds) {
        return new MiaoShaUserKey(expireSeconds, "MiaoShaCount:");
    }
}
