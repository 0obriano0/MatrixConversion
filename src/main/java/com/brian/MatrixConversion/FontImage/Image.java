package com.brian.MatrixConversion.FontImage;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Image implements IImage{
	protected BufferedImage ImageRGB = null;
	protected BufferedImage ImageGRAY = null;
	protected Integer width = 0;
	protected Integer height = 0;

	public BufferedImage getImage_RGB() {
		return ImageRGB;
	}
	
	public BufferedImage getImage_GRAY() throws Exception{
		if(ImageRGB != null) {
			BufferedImage grayBufferedImage = new BufferedImage(width, height, ImageRGB.getType());
			for (int i = 0; i < ImageRGB.getWidth(); i++) {
				for (int j = 0; j < ImageRGB.getHeight(); j++) {
					final int color = ImageRGB.getRGB(i, j);
					final int r = (color >> 16) & 0xff;
					final int g = (color >> 8) & 0xff;
					final int b = color & 0xff;
					int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
					int newPixel = colorToRGB(255, gray, gray, gray);
					grayBufferedImage.setRGB(i, j, newPixel);
				}
			}
			ImageGRAY = grayBufferedImage;
		}else {
			return null;
		}
		return ImageGRAY;
	}
	
	// 本身加周围8个灰度值再除以9，算出其相对灰度值
	public static double getGray(double[][] zuobiao, int x, int y, int w, int h) {
		double rs = zuobiao[x][y] + (x == 0 ? 255 : zuobiao[x - 1][y]) + (x == 0 || y == 0 ? 255 : zuobiao[x - 1][y - 1])
				+ (x == 0 || y == h - 1 ? 255 : zuobiao[x - 1][y + 1]) + (y == 0 ? 255 : zuobiao[x][y - 1])
				+ (y == h - 1 ? 255 : zuobiao[x][y + 1]) + (x == w - 1 ? 255 : zuobiao[x + 1][y])
				+ (x == w - 1 || y == 0 ? 255 : zuobiao[x + 1][y - 1])
				+ (x == w - 1 || y == h - 1 ? 255 : zuobiao[x + 1][y + 1]);
		return rs / 9;
	}

	/**
	 * 颜色份量转换为RGB值
	 * 
	 * @param alpha
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	private static int colorToRGB(int alpha, int red, int green, int blue) {

		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;

		return newPixel;

	}

	public BufferedImage getImage_binary() throws Exception {
	    float[] rgb = new float[3];  
	    double[][] zuobiao = new double[width][height];  
	    int black = new Color(0, 0, 0).getRGB();  
	    int white = new Color(255, 255, 255).getRGB();  
	    BufferedImage bi= new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);;  
	    for (int x = 0; x < width; x++) {  
	        for (int y = 0; y < height; y++) {  
	            int pixel = ImageRGB.getRGB(x, y);   
	            rgb[0] = (pixel & 0xff0000) >> 16;  
	            rgb[1] = (pixel & 0xff00) >> 8;  
	            rgb[2] = (pixel & 0xff);  
	            float avg = (rgb[0]+rgb[1]+rgb[2])/3;  
	            zuobiao[x][y] = avg;      
	              
	        }  
	    }  
            //这里是阈值，白底黑字仍是黑底白字，大多数状况下建议白底黑字，后面都以白底黑字为例
	    double SW = 192;  
	    for (int x = 0; x < width; x++) {  
	        for (int y = 0; y < height; y++) {  
	            if (zuobiao[x][y] < SW) {  
	                bi.setRGB(x, y, black);  
	            }else{  
	                bi.setRGB(x, y, white);  
	            }  
	        }             
	    }  

	    return bi;
	}

}
