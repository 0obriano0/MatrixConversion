package com.brian.MatrixConversion.tools;

public class tools {
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
