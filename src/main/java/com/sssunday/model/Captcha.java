package com.sssunday.model;

import java.awt.image.BufferedImage;

public class Captcha {

	private String code;
	
	private BufferedImage buffImg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BufferedImage getBuffImg() {
		return buffImg;
	}

	public void setBuffImg(BufferedImage buffImg) {
		this.buffImg = buffImg;
	}
	
	
}
