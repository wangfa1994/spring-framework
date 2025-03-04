package com.wf.xmgAop02.a11;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GenerateNoUnit {

    private static final String[] CHARS = {"A", "B", "C", "D", "E"};
    private static final int RANDOM_PART_LENGTH = 8;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    /**
     * 生成编号 type 为 CRED，LOAN，REPAY
     */
    public static String generateNo(String type) {
        StringBuilder sb = new StringBuilder();
        // 添加前缀
        sb.append(type);
        // 添加当前时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append(sdf.format(new Date()));
        // 随机选择一个字符
        sb.append(CHARS[RANDOM.nextInt(CHARS.length)]);
        // 生成随机字符串
        for (int i = 0; i < RANDOM_PART_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateNo("CRED"));
        System.out.println(generateNo("LOAN"));
        System.out.println(generateNo("REPAY"));
    }

}
