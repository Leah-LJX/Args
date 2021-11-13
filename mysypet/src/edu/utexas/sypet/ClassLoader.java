package edu.utexas.sypet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;


public class ClassLoader {
	static ImmutableSet<ClassInfo> ALL_CLASSES;
    static {
        try {
            ALL_CLASSES = ClassPath.from(Thread.currentThread().getContextClassLoader()).getAllClasses();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getReturnType(List<String> packages, String pre_class, String pre_method){
    	Set<Class<?>> classes = new HashSet<Class<?>>();;
    	for (String p : packages) {
             for (final ClassInfo info : ALL_CLASSES) {
                 if (p.endsWith("*"))
                     p = p.substring(0, p.length() - 1);
                 if (info.getName().startsWith(p)) {
                     try {
                         Class<?> cls = info.load();
                         if (!Throwable.class.isAssignableFrom(cls) && Modifier.isPublic(cls.getModifiers()))
                             classes.add(cls);
                     } catch (NoClassDefFoundError | TypeNotPresentException | SecurityException e) {
                         // e.printStackTrace();
                     }
                 }
             }
         }
    	
//    	 System.out.println(classes.size());
    	 for(Class<?> cls : classes) {
    		 String cls_name = cls.getName().substring(cls.getName().lastIndexOf(".") + 1, cls.getName().length());
//    		 System.out.println(cls_name);
    		 if(cls_name.toLowerCase().equals(pre_class)) {
    			 Method[] methods = cls.getDeclaredMethods();
    			 for(Method m: methods) {
    				 if(m.getName().toLowerCase().equals(pre_method)) {
    					 String returnType = m.getReturnType().toString();//interface org.apache.commons.math.linear.DecompositionSolver
    					 String type = returnType.substring(returnType.lastIndexOf(".")+1, returnType.length());
    					 return type;
    				 }
    			 }
    		 }
    	 }
    	return null;
    	
//		for (final ClassInfo info : ALL_CLASSES) {
//			try {
//				Class<?> cls = info.load();
//				if (!Throwable.class.isAssignableFrom(cls) && Modifier.isPublic(cls.getModifiers()) ) { //&& !Modifier.isFinal(cls.getModifiers())
//					String cls_name = cls.getName().substring(cls.getName().lastIndexOf(".") + 1,
//							cls.getName().length());
////					String cls_name = cls.getSimpleName(); // 触发IncompatibleClassChangeError
//					if (cls_name.equals(pre_class)) {
//						Method[] methods = cls.getDeclaredMethods();
//						for (Method m : methods) {
//							if (m.getName().equals(pre_method)) {
//								String returnType = m.getReturnType().getSimpleName().toString();//m.getReturnType().toString(); interface
//																					// org.apache.commons.math.linear.DecompositionSolver
////								String type = returnType.substring(returnType.lastIndexOf(".") + 1,
////										returnType.length());
//								System.out.println(returnType);
//								return returnType;
//							}
//						}
//					}
//				}
//			} catch (NoClassDefFoundError | TypeNotPresentException |  SecurityException e) {//IncompatibleClassChangeError | VerifyError |
//				// e.printStackTrace();
//			}
//		}
//
//		return null;
    	
    	
    	////////用于解析google API
//		for (final ClassInfo info : ALL_CLASSES) {
//			String cls_name = info.getSimpleName();
//			System.out.println(cls_name);
//			if (cls_name.equals(pre_class)) {
//				try {
//					Method[] methods = info.load().getDeclaredMethods();
//					for (Method m : methods) {
//						if (m.getName().equals(pre_method)) {
//							String returnType = m.getReturnType().getName().toString(); // m.getReturnType().toString();
//																						// interface
//																						// org.apache.commons.math.linear.DecompositionSolver
//							Pattern pattern = Pattern.compile("([A-Z]+.*)");
//							Matcher matcher = pattern.matcher(returnType);
//							matcher.find();
//							String type = matcher.group();
//							type = type.replace("$", ".");
////							System.out.println(type);
//							return type;
//						}
//					}
//				} catch (NoClassDefFoundError | TypeNotPresentException | SecurityException | IllegalStateException e) {// IncompatibleClassChangeError  //IllegalStateException 当匹配类不对时，即找到了另外的jar中包含，return void时
//																								// | VerifyError |
//					continue;
//					// e.printStackTrace();
//				}
//			}
//		}
//		return null;

	}
    
    public static void main(String args[]) {
    	List<String> packs = Arrays.asList("java.awt", "java.awt.geom");
    	String type = getReturnType(packs, "shape", "getbounds2d");//Line2D.Double继承了Line2D
//    	List<String> packs = Arrays.asList("org.apache.commons.math.linear", "com.opengamma.analytics.math");
//    	String type = getReturnType(packs, "SingularValueDecomposition", "getSolver");
    	System.out.println(type);
    	String coreAPI = "org.w3c.dom.Document#getDocumentElement";
    	String usingAPI = coreAPI.split("#")[0].split("\\.")[coreAPI.split("#")[0].split("\\.").length -1]+"."+coreAPI.split("#")[1];
    	System.out.println(usingAPI);
    }
}
