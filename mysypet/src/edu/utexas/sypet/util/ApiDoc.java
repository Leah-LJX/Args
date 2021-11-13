package edu.utexas.sypet.util;


public class ApiDoc {
	String MethodName;
	String Signature;
	String Doc;
	
	public ApiDoc (String c,String s,String d)
	{
		this.MethodName=c;
		this.Signature=s;
		this.Doc=d;
	}
	public String getMethodName()
	{
		return MethodName;
	}
	public String getSignature()
	{
		return Signature;
	}
	public String getDoc()
	{
		return Doc;
	}

}
