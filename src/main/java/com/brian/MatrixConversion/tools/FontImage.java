package com.brian.MatrixConversion.tools;

import java.awt.Color;  
import java.awt.Font;  
import java.awt.FontMetrics;  
import java.awt.Graphics;  
import java.awt.Rectangle;  
import java.awt.image.BufferedImage;  
import java.io.File;  
  
import javax.imageio.ImageIO;  
  
public class FontImage {  
    public static void main(String[] args) throws Exception {  
        createImage("请在这里输入文字", new Font("微软雅黑", Font.PLAIN, 32), new File("d:/a.png"), 500, 64);  
    }  
  
    // 根据str,font的样式以及输出圖片
    public static BufferedImage createImage(String str, Font font,  
            Integer width, Integer height) throws Exception {  
        // 创建图片  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_BGR);  
        Graphics g = image.getGraphics();  
        g.setClip(0, 0, width, height);  
        g.setColor(Color.white);  
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景  
        g.setColor(Color.black);// 在换成黑色  
        g.setFont(font);// 设置画笔字体  
        /** 用于获得垂直居中y */  
        Rectangle clip = g.getClipBounds();  
        FontMetrics fm = g.getFontMetrics(font);  
        int ascent = fm.getAscent();  
        int descent = fm.getDescent();  
        int y = (clip.height - (ascent + descent)) / 2 + ascent;  
        for (int i = 0; i < 6; i++) {// 256 340 0 680  
            g.drawString(str, i * 680, y);// 画出字符串  
        }  
        g.dispose();  
        return image;
    }
    
    // 根据str,font的样式以及输出文件目录  
    public static void createImage(String str, Font font, File outFile,  
            Integer width, Integer height) throws Exception {  
        // 创建图片  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_BGR);  
        Graphics g = image.getGraphics();  
        g.setClip(0, 0, width, height);  
        g.setColor(Color.white);  
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景  
        g.setColor(Color.black);// 在换成黑色  
        g.setFont(font);// 设置画笔字体  
        /** 用于获得垂直居中y */  
        Rectangle clip = g.getClipBounds();  
        FontMetrics fm = g.getFontMetrics(font);  
        int ascent = fm.getAscent();  
        int descent = fm.getDescent();  
        int y = (clip.height - (ascent + descent)) / 2 + ascent;  
        for (int i = 0; i < 6; i++) {// 256 340 0 680  
            g.drawString(str, i * 680, y);// 画出字符串  
        }  
        g.dispose();  
        ImageIO.write(image, "png", outFile);// 输出png图片  
    }  
    
    public static int getRealLength(String str) {
		int m = 0;
		char arr[] = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			// 中文字元(根據Unicode範圍判斷),中文字元長度為2
			if ((c >= 0x0391 && c <= 0xFFE5)) {
				m = m + 2;
			} else if ((c >= 0x0000 && c <= 0x00FF)) // 英文字元
			{
				m = m + 1;
			}
		}
		return m;
	}
}
