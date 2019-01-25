package common;

public class HtmlSpecialChar {
	
	public static String getHtmlStr(String str) {
		return str.replaceAll("&", "&amp;").replaceAll("\"","&quot").replaceAll("<","&lt;").replaceAll(">","&gt;");
	}
	
}
