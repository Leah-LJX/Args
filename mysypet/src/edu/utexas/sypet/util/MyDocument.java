package edu.utexas.sypet.util;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This class represents one document.
 * It will keep track of the term frequencies.
 * @author swapneel
 * @param <ApiDoc>
 *
 */
public class MyDocument implements Comparable<MyDocument> {
	//public class MyDocument{
	/**
	 * A hashmap for term frequencies.
	 * Maps a term to the number of times this terms appears in this document. 
	 */
	private ApiDoc apiDoc;
	private HashMap<String, Integer> termFrequency;
	
	/**
	 * The name of the file to read.
	 */
	private String MethodName;
	private String content;
	private int similarity;
	/**
	 * The constructor.
	 * It takes in the name of a file to read.
	 * It will read the file and pre-process it.
	 * @param filename the name of the file
	 */
/*	public Document(String filename) {
		this.filename = filename;
		termFrequency = new HashMap<String, Integer>();
		
		readFileAndPreProcess();
	}*/
	public MyDocument(ApiDoc a)
	{
		this.content=a.getDoc();
		termFrequency=new HashMap<String,Integer>();
		PreProcessContent(content);
		this.MethodName=a.getMethodName();
		this.apiDoc=a;
	}
	private void PreProcessContent(String content)
	{
		String[] words=content.split(" ");
		for(int i=0;i<words.length;i++)
		{
			String filteredWord = words[i].replaceAll("[^A-Za-z0-9]", "").toLowerCase();//将单词里面的正常字符替换成""，剩余的是非正常字符
			
			if (!(filteredWord.equalsIgnoreCase(""))) {
				
				
				///将单词进行词干还原
				
				
				
				
				if (termFrequency.containsKey(filteredWord)) {
					int oldCount = termFrequency.get(filteredWord);
					termFrequency.put(filteredWord, ++oldCount);
				} else {
					termFrequency.put(filteredWord, 1);
				}
			}
		}
	}

	
	/**
	 * This method will return the term frequency for a given word.
	 * If this document doesn't contain the word, it will return 0
	 * @param word The word to look for
	 * @return the term frequency for this word in this document
	 */
	public double getTermFrequency(String word) {
		if (termFrequency.containsKey(word)) {
			return termFrequency.get(word);
		} else {
			return 0;
		}
	}
	
	/**
	 * This method will return a set of all the terms which occur in this document.
	 * @return a set of all terms in this document
	 */
	public Set<String> getTermList() {
		return termFrequency.keySet();
	}


	/**
	 * The overriden method from the Comparable interface.
	 */
/*	public int compareTo(MyDocument other) {
		return MethodName.compareTo(other.getMethodName());
	}*/

	/**
	 * @return the filename
	 */
	public String getMethodName() {
		return MethodName;
	}
	
	/**
	 * This method is used for pretty-printing a Document object.
	 * @return the filename
	 */
	public String toString() {
		return MethodName;
	}
	public String getContent()
	{
		return this.content;
	}
	public void setSimilarity(int s)
	{
		this.similarity=s;
	}
	public int getSimilarity()
	{
		return this.similarity;
	}
	@Override
	public int compareTo(MyDocument other) {
		return MethodName.compareTo(other.getMethodName());
	}
	public String getSignature()
	{
		return this.apiDoc.getSignature();
	}
	public String getDoc()
	{
		return this.apiDoc.getDoc();
	}
	
}