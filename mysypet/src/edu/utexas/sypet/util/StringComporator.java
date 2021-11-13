package edu.utexas.sypet.util;

import java.util.Comparator;

public class StringComporator implements Comparator<String>{
	@Override
	public int compare(String o1, String o2) {
		
//		o1.getValue() == o2.getValue() ? 0 :  
//            (o1.getValue() > o2.getValue() ? 1 : -1);
		
		return o1.split("_").length==o2.split("_").length ? 0: (o1.split("_").length>o2.split("_").length ? 1:-1);
		
//			if(o1.split("_").length==o2.split("_").length)
//			{
//				return 1;
//			}
//			else
//			{
//				return -1;
//			}
		}

}
