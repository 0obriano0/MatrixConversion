package com.brian.MatrixConversion.FontImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class FontImage implements IFontImage{
	private BufferedImage image = null;
	
    public  BufferedImage createImage(String str, Font font, Integer width, Integer height) throws Exception {  
        // 创建图片  
    	image = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);  
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
        System.out.println("ascent = " + ascent);
        System.out.println("y = " + y);
        for (int i = 0; i < 6; i++) {// 256 340 0 680  
            g.drawString(str, i * 680, y);// 画出字符串  
        }  
        g.dispose();
        return image;
    }
    
    public boolean createImage(String str, Font font, File outFile, Integer width, Integer height) throws Exception {  
    	image = createImage(str,font,width,height);
        return ImageIO.write(image, "png", outFile);// 输出png图片  
    }

	public BufferedImage getImage() {
		return image;
	}   
}
