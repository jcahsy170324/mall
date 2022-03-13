package com.msb.dongbao.portal.web.code;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName ImageCode
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/13 10:46
 * @Version 1.0
 **/
@Data
public class ImageCode {
    //图形中的内容
    private String code;
    //图片
    private ByteArrayInputStream image;

    private int width = 400;
    private int height = 100;

    private ImageCode() throws IOException {
        //图形缓冲区,把图片放到内存中,图片和内存映射
        //黑板
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //画笔
        Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(255, 255, 255));
        //画矩形
        graphics.fillRect(0, 0, width, height);

        //画字，字体
        graphics.setFont(new Font("宋体", Font.PLAIN, 30));
        this.code = "";
        Random random = new Random();

        int num1 = random.nextInt(30);
        int num2 = random.nextInt(30);
        graphics.setColor(new Color(0, 0, 0));
        graphics.drawString(num1 + "", (width / 6) * 0 + 20, 50);
        graphics.drawString("+", (width / 6) * 1 + 20, 50);
        graphics.drawString(num2 + "", (width / 6) * 2 + 20, 50);
        graphics.drawString("=", (width / 6) * 3 + 20, 50);
        graphics.drawString("?", (width / 6) * 4 + 20, 50);
        int result = num1 + num2;
        this.code = result + "";


        graphics.setColor(new Color(100, 100, 100));
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(20);
            int y1 = random.nextInt(20);
            graphics.drawLine(x, y, x + x1, y + y1);
        }


        //收笔
        graphics.dispose();

        ByteArrayInputStream inputStream = null;
        ByteOutputStream outputStream = new ByteOutputStream();
        try {
            //给图片赋值,赋值给byteArrayInputStream
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(image, "jpeg", imageOutputStream);

            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            System.out.println("生成验证码失败");
        }
        this.image = inputStream;


    }

    public static ImageCode getInstance() throws IOException {
        return new ImageCode();
    }

}
