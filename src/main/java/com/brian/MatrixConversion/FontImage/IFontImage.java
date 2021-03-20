package com.brian.MatrixConversion.FontImage;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

public interface IFontImage {
	
	/**
	 * 拿取已經產生的圖片
	 * @return 如果沒有資料會回傳 null
	 */
	BufferedImage getImage();
	
	/**
	 * 以文字創建圖片，並修改此物件內的image檔緩存，以及回傳一個物件
	 * @param str 字串
	 * @param font 字體
	 * @param width 寬
	 * @param height 高
	 * @return 回傳一個處理好的圖像
	 * @throws Exception
	 */
	public BufferedImage createImage(String str, Font font, Integer width, Integer height) throws Exception;
	
	/**
	 * 以文字創建圖片，並修改此物件內的image檔緩存，以及存取檔案到指定路徑
	 * @param str 字串
	 * @param font 字體
	 * @param outFile 輸出位置
	 * @param width 寬
	 * @param height 高
	 * @return 回傳是否成功
	 * @throws Exception
	 */
	public boolean createImage(String str, Font font, File outFile, Integer width, Integer height) throws Exception ;
}
