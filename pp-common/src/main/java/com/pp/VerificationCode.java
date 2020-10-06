package com.pp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public final class VerificationCode {
    private String code;
    private int singleWidth = 15;
    private int singleHeight = 30;
    private int width;
    private int height;

    public VerificationCode(int number) {
        this.code = getImageCode(number);

        width = (singleWidth + 5) * code.length();
        height = singleHeight + 5;
    }

    public String getCode() {
        return code;
    }

    private String getImageCode(int number) {
        char[] str = "ABCDEFJHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

        Random random = new Random();

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < number; i++) {
            code.append(str[random.nextInt(str.length)]);
        }

        return code.toString();
    }

    // 干扰线绘画
    public BufferedImage getImage(String code) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        Graphics graphics = image.getGraphics();

        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("consolas", Font.BOLD, 28));

        for (int i = 0; i < code.length(); i++) {
            graphics.drawString(code.charAt(i) + "", (i * singleWidth) + 5, singleHeight);
        }

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);

            graphics.drawLine(x, y, x + x2, y + y2);
        }

        return image;
    }
}