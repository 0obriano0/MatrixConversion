package com.brian.MatrixConversion.FontImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.brian.MatrixConversion.tools.tools;

public class FontImage extends Image implements IFontImage {
	protected Font font = null;
	protected String str = null;
	
	public  BufferedImage createImage(String str, Font font, Integer width, Integer height) throws Exception {  
        // 创建图片  
		this.str = str;
		this.font = font;
    	this.width = width;
    	this.height = height;
    	ImageRGB = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
        Graphics g = ImageRGB.getGraphics();  
        g.setClip(0, 0, width, height);
        g.setColor(Color.white);  
        g.fillRect(0, 0, width, height);// 先用白色填充整张图片,也就是背景  
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
        return ImageRGB;
    }
    
    public boolean createImage(String str, Font font, File outFile, Integer width, Integer height) throws Exception {  
    	ImageRGB = createImage(str,font,width,height);
        return ImageIO.write(ImageRGB, "png", outFile);// 输出png图片  
    }
    
	public String getcode(ShowModeType mode) {
		if(this.ImageRGB != null && this.str != null) {
			if (mode.equals(ShowModeType.Horizontal_LEFT)) {
				BufferedImage image;
				try {
					image = this.getImage_binary();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
				
				int white = new Color(0,0,0).getRGB();
				String allStr = "";
				
				for(int StrIndex = 0;StrIndex < str.length();StrIndex++) {
					String strdata = "//取模文字\"" + str.charAt(StrIndex) + "\" \n";
					for(int y = 0;y < font.getSize();y++) {
						byte codebuffer = 0;
						for(int x = 0;x < font.getSize();x++) {
							if(image.getRGB(x+StrIndex*font.getSize(), y) == white)
								codebuffer = (byte) (codebuffer<<1|0x01);
							else
								codebuffer = (byte) (codebuffer<<1|0x00);
							
							if((x+1)%8 == 0) {
								strdata = strdata + ((x==0)?"":",") +"0x" + tools.byteToHex(codebuffer);
								codebuffer = 0;
							}
						}
						strdata = strdata + (((y+1)*2)%16 == 0?"\n":"") ;
					}
					allStr = allStr + "\n" + strdata;
				}
				
				System.out.println(allStr);
				
			}
		}
		
		return null;
	}
}
