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
	
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static String byteToHex(byte byte_) {
	    char[] hexChars = new char[2];
	        int v = byte_ & 0xFF;
	        hexChars[0] = HEX_ARRAY[v >>> 4];
	        hexChars[1] = HEX_ARRAY[v & 0x0F];
	    return new String(hexChars);
	}
}
