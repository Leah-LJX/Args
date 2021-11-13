package edu.utexas.sypet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	//子字符串modelStr在字符串str中第count次出现时的下标
		private static int getFromIndex(String str, String modelStr, Integer count) {
			//对子字符串进行匹配
		    Matcher slashMatcher = Pattern.compile(modelStr).matcher(str);
			int index = 0;
		       //matcher.find();尝试查找与该模式匹配的输入序列的下一个子序列
		    while(slashMatcher.find()) {
			    index++;
			    //当modelStr字符第count次出现的位置
			    if(index == count){
			       break;
			    }
			}
		        //matcher.start();返回以前匹配的初始索引。
		    return slashMatcher.start();
		}
	public static void main(String args[]) {
		String str1 = "RoundRectangle2D.getX()";//Double.Double
		String[] str1Split = str1.split("\\.");
		System.out.println(str1Split.length);
		String sub = "\\.";
		int N = 2;
//		System.out.println(getFromIndex(str1, sub,N));
		
		String cName;
		if(str1Split.length == 3) {
			int point_pos = getFromIndex(str1, "\\.", 2);
			cName = str1.substring(0, point_pos);
			System.out.println(cName);
		}
		//methodName = signature.split('(')[0].split('.')[len(signature.split('(')[0].split('.')) - 1]
		
		String methodName = str1Split[str1Split.length - 1];
		if(str1Split.length == 3 && (str1Split[1]).equals(methodName)) {
			int point_pos = getFromIndex(str1, "\\.", 2);
			cName = str1.substring(0, point_pos);
			methodName = cName + ".<init>";
		}
		else if((str1.substring(0, str1.indexOf("."))).equals(methodName)) {
			methodName = methodName + ".<init>";
		}
		System.out.println("methodName:"+methodName);
		
		String lineTxt = "new Line2D.Double()";
		find(lineTxt);
	}
	
	public static void find(String lineTxt) {
		String pattern1="\\.([a-zA-Z0-9]+?)\\(";//寻找.和括号之间的内容
		String pattern2="new ([a-zA-Z0-9.]+?)\\(";//寻找new 和括号之间的内容//添加 . --new Line2D.Double()
		String pattern3=" ([a-zA-Z0-9]+?)\\(";//寻找空格和括号之间的内容
		Pattern p1=Pattern.compile(pattern1);
		Pattern p2=Pattern.compile(pattern2);
		Pattern p3=Pattern.compile(pattern3);
		
		Matcher m2=p1.matcher(lineTxt);
		int index=1;
		while(m2.find())
		{			
			String curAPI=m2.group(1).toLowerCase();
			System.out.println("curAPI: "+curAPI);
		}
	}
}
