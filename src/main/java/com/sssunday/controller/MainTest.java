package com.sssunday.controller;

import com.sssunday.common.utils.ReplaceEmojiUtils;

public class MainTest {

	public static void main(String[] args) {
		
		String a = "\\xF0\\x9F\\x98\\x8A";
		
		String b = ReplaceEmojiUtils.filterEmoji(a);
		System.out.println(a);
		System.out.println(b);
	}
}
