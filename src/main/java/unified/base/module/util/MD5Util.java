package unified.base.module.util;

import cn.hutool.crypto.digest.MD5;

import java.util.UUID;

/**
 * @version 1.0.0
 * @Date: 2023/6/25 15:37
 * @Author ZhuYouBin
 * @Description: MD5 工具类
 */
public class MD5Util extends MD5 {
    /**
     * 获取32的UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * MD5加密
     * @param text 明文
     * @param salt 盐值
     */
    public static String encrypt(String text, String salt) {
        MD5 md5 = new MD5();
        for (int i = 0; i < 5; i++) {
            text = String.format("%s-%s", text, salt);
            text = md5.digestHex(text);
        }
        return text;
    }
}
