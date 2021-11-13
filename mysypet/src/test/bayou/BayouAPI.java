package test.bayou;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;


/**
 * @author ljx
 *
 */
public class BayouAPI {
	public static String path = "data/seq_result/";	
	
	public static List<String> getAPICall_AST(String fileStr) throws IOException{
		CompilationUnit comp = JdtAstUtil.getCompilationUnit(fileStr);  
        Visitor visitor = new Visitor();
        List<String> apiList = new ArrayList<String>();
        apiList = visitor.apiList;
        comp.accept(visitor);
        return apiList;
	}
	
	public static Set<String> getBayou_APIList(String benchID) throws Exception {
		String id =benchID;
		String bench_path = path + id;
		System.out.println("ljx"+bench_path);
		ArrayList<String> files = getFiles(bench_path);
		Set<String> per_apiList = new HashSet<String>();
		for(int k =0; k<files.size(); k++) {
			per_apiList.addAll(getAPICall_AST(files.get(k)));
		}
		return per_apiList;
	}	
	
		public static void main(String args[]) throws Exception {

			Set<String> result = getBayou_APIList("12");
			for(String a : result) {
				System.out.println(a);
			}
		}
		
		public static ArrayList<String> getFiles(String path) {
		    ArrayList<String> files = new ArrayList<String>();
		    File file = new File(path);
		    File[] tempList = file.listFiles();

		    for (int i = 0; i < tempList.length; i++) {
		        if (tempList[i].isFile()) {
		              System.out.println("filename" + tempList[i]);
		            files.add(tempList[i].toString());
		        }
		        if (tempList[i].isDirectory()) {
		              System.out.println("dirname:" + tempList[i]);
//		              getFiles(tempList[i].getPath());
		        }
		    }
		    return files;
		}
		public static ArrayList<String> getDir(String path) {
		    ArrayList<String> dir = new ArrayList<String>();
		    File file = new File(path);
		    File[] tempList = file.listFiles();

		    for (int i = 0; i < tempList.length; i++) {
		        if (tempList[i].isFile()) {
		              System.out.println("��     ����" + tempList[i]);
		        }
		        if (tempList[i].isDirectory()) {
		              System.out.println("�ļ��У�" + tempList[i]);
		              dir.add(tempList[i].toString());
//		              getFiles(tempList[i].getPath());
		        }
		    }
		    return dir;
		}
}
