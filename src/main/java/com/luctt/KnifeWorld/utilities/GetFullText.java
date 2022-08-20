package com.luctt.KnifeWorld.utilities;

public class GetFullText {
	public static String getFullText(String txt) {
		String[] s=txt.trim().split(" ");
		StringBuilder builder=new StringBuilder("");
		for(String x:s) {
			builder.append("+"+x+" ");
		}
		return builder.toString().trim();
	}
}
