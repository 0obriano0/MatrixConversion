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
	private BufferedImage ImageRGB = null;
	private BufferedImage ImageGRAY = null;
	private Integer width = 0;
	private Integer height = 0;
	
    public  BufferedImage createImage(String str, Font font, Integer width, Integer height) throws Exception {  
        // 创建图片  
    	this.width = width;
    	this.height = height;
    	ImageRGB = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
        Graphics g = ImageRGB.getGraphics();  
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
        return ImageRGB;
    }
    
    public boolean createImage(String str, Font font, File outFile, Integer width, Integer height) throws Exception {  
    	ImageRGB = createImage(str,font,width,height);
        return ImageIO.write(ImageRGB, "png", outFile);// 输出png图片  
    }

	public BufferedImage getImage_RGB() {
		return ImageRGB;
	}
	
	public BufferedImage getImage_GRAY() {
		if(ImageRGB != null) {
			ImageGRAY = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_GRAY);
			int[][] gray=new int[width][height];
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					gray[x][y]=getGray(ImageRGB.getRGB(x, y));
				}
			}
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					ImageGRAY.setRGB(x, y, getAverageColor(gray, x, y, width, height));
				}
			}
		}else {
			return null;
		}
		return ImageGRAY;
	}

	private int getGray(int rgb) {
		String str=Integer.toHexString(rgb);
		
		int r=Integer.parseInt(str.substring(2,4),16);
		int g=Integer.parseInt(str.substring(4,6),16);
		int b=Integer.parseInt(str.substring(6,8),16);
		
		//or 直接new個color物件
		Color c=new Color(rgb);
		
		r=c.getRed();
	    g=c.getGreen();
		b=c.getBlue();
		
		int top=(r+g+b)/3;
		return (int)(top);
	}
	
	/**
	 * 自己加周圍8個灰度值再除以9，算出其相對灰度值
	 * @param gray
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	private static int  getAverageColor(int[][] gray, int x, int y, int w, int h)
    {
        int rs = gray[x][y]
              	+ (x == 0 ? 255 : gray[x - 1][y])
	            + (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])
	            + (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1])
	            + (y == 0 ? 255 : gray[x][y - 1])
	            + (y == h - 1 ? 255 : gray[x][y + 1])
	            + (x == w - 1 ? 255 : gray[x + 1][ y])
	            + (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])
	            + (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);
        return rs / 9;
    }

	public BufferedImage getImage_Binarization() {
		return getImage_Binarization(160);
	}

	public BufferedImage getImage_Binarization(int num) {
		if(ImageRGB != null) {
			ImageGRAY = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_BINARY);
			int[][] gray=new int[width][height];
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					gray[x][y]=getGray(ImageRGB.getRGB(x, y));
				}
			}
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if(getAverageColor(gray, x, y, width, height)>num){
						int max=new Color(255,255,255).getRGB();
						ImageGRAY.setRGB(x, y, max);
					}else{
						int min=new Color(0,0,0).getRGB();
						ImageGRAY.setRGB(x, y, min);
					}
				}
			}
		}else {
			return null;
		}
		return ImageGRAY;
	}
}
