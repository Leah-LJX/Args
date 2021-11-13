package edu.utexas.sypet.util;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents a corpus of documents.
 * It will create an inverted index for these documents.
 * @author swapneel
 *
 */
public class Corpus {
	
	/**
	 * An arraylist of all documents in the corpus.
	 */
	private ArrayList<MyDocument> documents;//文档列表
	private List<String>stopWordList=new ArrayList<String>();
	/**
	 * The inverted index. 
	 * It will map a term to a set of documents that contain that term.
	 */
	private HashMap<String, Set<MyDocument>> invertedIndex;
	
	/**
	 * The constructor - it takes in an arraylist of documents.
	 * It will generate the inverted index based on the documents.
	 * @param documents the list of documents
	 * @throws IOException 
	 */
	public Corpus(ArrayList<MyDocument> documents) throws IOException {
		this.documents = documents;
		invertedIndex = new HashMap<String, Set<MyDocument>>();
		
		
		String fileName=new String("/home/zyz/workspace/sypet-master/stopword2.txt");
		String stopWord=readFileByLines(fileName);
		String [] sw=stopWord.split(" ");
		for(int i=0;i<sw.length;i++)
		{
			stopWordList.add(sw[i]);
			
		}
		createInvertedIndex();
	}
	
	/**
	 * This method will create an inverted index.
	 */
	private void createInvertedIndex() {
		System.out.println("Creating the inverted index");
		
		for (MyDocument document : documents) {
			Set<String> terms = document.getTermList();
			for (String term : terms) {
				if(stopWordList.contains(term))
				{
					continue;
				}
				if (invertedIndex.containsKey(term)) {
					Set<MyDocument> list = invertedIndex.get(term);
					list.add(document);
				} else {
					Set<MyDocument> list = new TreeSet<MyDocument>();
					list.add(document);
					invertedIndex.put(term, list);
				}
			}
		}
	}
	
	/**
	 * This method returns the idf for a given term.
	 * @param term a term in a document
	 * @return the idf for the term
	 */
	public double getInverseDocumentFrequency(String term) {
		if (invertedIndex.containsKey(term)) {
			double size = documents.size();
			Set<MyDocument> list = invertedIndex.get(term);
			double documentFrequency = list.size();
			
			return Math.log10(size / documentFrequency);
		} else {
			return 0;
		}
	}

	/**
	 * @return the documents
	 */
	public ArrayList<MyDocument> getDocuments() {
		return documents;
	}

	/**
	 * @return the invertedIndex
	 */
	public HashMap<String, Set<MyDocument>> getInvertedIndex() {
		return invertedIndex;
	}
	
	public static String readFileByLines(String fileName) throws IOException
	{
		File file=new File(fileName);
		BufferedReader reader=new BufferedReader(new FileReader(file));
		StringBuilder res=new StringBuilder();
		String tempString=null;
		int line=1;
		while((tempString=reader.readLine())!=null)
		{
			res.append(tempString);
		}
		reader.close();
		return res.toString();
	}
	
	
	
}