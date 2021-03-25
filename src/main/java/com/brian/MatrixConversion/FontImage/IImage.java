package com.brian.MatrixConversion.FontImage;

import java.awt.image.BufferedImage;

public interface IImage {
	
	/**
	 * 拿取已經產生的圖片
	 * @return 如果沒有資料會回傳 null
	 */
	public BufferedImage getImage_RGB();
	
	/**
	 * 拿取已經產生的圖片(灰階)
	 * @return 如果沒有資料會回傳 null
	 */
	public BufferedImage getImage_GRAY() throws Exception;
	
	/**
	 * 將灰階做二值化
	 * @return 如果沒有資料會回傳 null
	 */
	public BufferedImage getImage_binary() throws Exception;
	
}
