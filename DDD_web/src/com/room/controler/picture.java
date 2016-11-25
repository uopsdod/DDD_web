package com.room.controler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

/**
 *
 * @author JR
 */
public class picture {

    public static List getPic() throws IOException {
        int width = 100;
        int height = 40;
        
        //產生圖片緩衝區
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        //設定底色
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, width, height);

        //畫線及產生驗證文字
        drawWidthLine(graphics, width, height);
        int passWord = drawString(graphics);
        drawHeightLine(graphics, width, height);
        
        //釋放資源並停止繪圖
        graphics.dispose();
        
        List<Object> data = new ArrayList<Object>(); 
        data.add(passWord);
        data.add(image);
        
        return data;
        //儲存成圖檔
//        ImageIO.write(image, "png", new File("d:/jr_image_demo.png"));
    }

    public static void drawWidthLine(Graphics graphics, int width, int height) {
        for (int i = 0; i < 20; i++) {
            graphics.setColor(new Color(randomColor(), randomColor(), randomColor()));
            graphics.drawLine((int) (Math.random() * width) + 1, 0, (int) (Math.random() * width) + 1, height);
        }
    }
    
    public static void drawHeightLine(Graphics graphics, int width, int height) {
        for (int i = 0; i < 4; i++) {
            graphics.setColor(new Color(randomColor(), randomColor(), randomColor()));
            graphics.drawLine(0, (int) (Math.random() * height) + 1, width, (int) (Math.random() * height) + 1);
        }
    }

    public static int drawString(Graphics graphics) {
    	int a = randomNumber();
    	int b = randomNumber();
    	int c = randomNumber();
    	int d = randomNumber();
    	int e = randomNumber();
    	 	
    	
        graphics.setFont(new Font("Atlantic Inline", Font.BOLD, 34));
        graphics.setColor(new Color(randomColor(), randomColor(), randomColor()));
        graphics.drawString(String.valueOf(a), 0, 30);
        graphics.setColor(new Color(randomColor(), randomColor(), randomColor()));
        graphics.drawString(String.valueOf(b), 20, 30);
        graphics.setColor(new Color(randomColor(), randomColor(), randomColor()));
        graphics.drawString(String.valueOf(c), 40, 30);
        graphics.setColor(new Color(randomColor(), randomColor(), randomColor()));
        graphics.drawString(String.valueOf(d), 60, 30);
        graphics.setColor(new Color(randomColor(), randomColor(), randomColor()));
        graphics.drawString(String.valueOf(e), 80, 30);
        
        return a*10000+b*1000+c*100+d*10+e;
    }

    public static int randomNumber() {
        return (int) (Math.random() * 10);
    }

    public static int randomColor() {
        return (int) (Math.random() * 255) + 1;
    }
}