package test.bayou;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.MethodInvocation; 
  
/**
 * @author zyt
 *
 */
public class Visitor extends ASTVisitor {  
	List<String> packList = new ArrayList<String>();
	List<String> apiList = new ArrayList<String>();

	//zyt:Gets the API package of method invocation.
    @Override  
    public boolean visit(MethodInvocation node) { 
    	if(node.getExpression() != null && node.getExpression().resolveTypeBinding() != null){
    		//getExpression
    		//getNode
    		String packN = node.getExpression().resolveTypeBinding().getQualifiedName();
    		String apiName = packN+"."+node.getName();
/*
 * 		//delete the same package
        	String samePack = null;
    		boolean flag = false;
    		for(int i = 0 ;i < this.packList.size(); i++) {
    			samePack = this.packList.get(i);
    			if (samePack.equals(packN))	{
    				flag = true;
    				break;
    			}
    		}
    		
    		if(!flag)	this.packList.add(packN);
    		*/
    		apiList.add(apiName);
    		
    	}	
    		
    	return true;
    }    
    
   //zyt:Gets the API package for creating objects through new object.
	@Override  
    public boolean visit(ClassInstanceCreation  node) { 
    	if(node.resolveConstructorBinding() != null && node.resolveConstructorBinding().getDeclaringClass() != null){
    		String packN = node.resolveConstructorBinding().getDeclaringClass().getQualifiedName();
    		String apiName = packN+".<init>";
/*
 * 		//delete the same package

        	String samePack = null;
    		boolean flag = false;
    		for(int i = 0 ;i < this.packList.size(); i++) {
    			samePack = this.packList.get(i);
    			if (samePack.equals(packN))	{
    				flag = true;
    				break;
    			}
    		}
    		if(!flag)	this.packList.add(packN); */
    		apiList.add(apiName);
    	}
	
    	return true;
    }
}  
