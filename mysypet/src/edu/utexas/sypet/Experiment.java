/*
 * Copyright (C) 2017 The SyPet Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.utexas.sypet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.google.gson.Gson;

import edu.utexas.sypet.synthesis.PathFinder;
import edu.utexas.sypet.synthesis.Sketcher;
import edu.utexas.sypet.synthesis.SypetTestUtil;
import edu.utexas.sypet.synthesis.model.Benchmark;
import edu.utexas.sypet.synthesis.model.Pair;
import edu.utexas.sypet.synthesis.sat4j.PetrinetEncoding.Option;
import edu.utexas.sypet.util.ApiDoc;
import edu.utexas.sypet.util.Corpus;
import edu.utexas.sypet.util.MyDocument;
import edu.utexas.sypet.util.SootUtil;
import edu.utexas.sypet.util.TimeUtil;
import edu.utexas.sypet.util.VectorSpaceModel;
import retest.word2api.Re_W2ASimilarity;
import soot.CompilationDeathException;
import soot.Scene;
import soot.options.Options;
import test.bayou.BayouAPI;
import uniol.apt.adt.pn.PetriNet;
import uniol.apt.adt.pn.Place;
import uniol.apt.adt.pn.Transition;

// Status: ?
/**
 * Parametric framework for running benchmarks
 *
 */
public class Experiment {
	public static boolean VERBOSE = false;
	public static String benchLoc = null;
	public static long TIMEOUT = 600000; 

	public static final int maxTokens = 10;

	public static Option objectiveOption = Option.AT_LEAST_ONE;
	public static int maxIterations = 5;
	
	public static List<String> clones;
	
	public static boolean usingGoogleResult= true;
	public static List<String> GoogleAPIList=new ArrayList<String>();
	public static boolean usingThreshold=false;   
	public static int threshold=160;// threshold
	 
	public static boolean usingWord2API = false;
	
	public static boolean usingBayou = false;

	public static boolean usingRack = false;
	
	public static boolean usingNLP2API = false;
	
	public static boolean usingDeepAPI = false;
	
	public static String description="";
	public static int mustNum=5;
	public static ArrayList<String>sequenceListMust=new ArrayList<String>();

	public static PathFinder initPetriNet(Benchmark qb, List<PetriNet> pNetList, int pn, int local) throws IOException {

		if (pNetList.size() == 1) {
			pn = 0;
		}

		PetriNet pNet = pNetList.get(pn);

		System.out.println("PetriNet for path length: " + local + " [places: " + pNet.getPlaces().size()
				+ " ; transitions: " + pNet.getTransitions().size() + " ; edges: " + pNet.getEdges().size() + "]");

		List<Place> inits = new ArrayList<>();
		List<Pair<String, String>> vars = new ArrayList<>();
		int index = 0;
		for (String src : qb.getSrcTypes()) {
			Place srcPlace = pNet.getPlace(src);
			inits.add(srcPlace);
			String varName = qb.getParamNames().get(index);
			Pair<String, String> arg = new Pair<>(src, varName);
			vars.add(arg);
			index++;
		}
		// adding void to initial marking.
		inits.add(pNet.getPlace("void"));
		// tgt place.
		String tgt = qb.getTgtType();
		Place tgtPlace = pNet.getPlace(tgt);
		
		PathFinder pf = new PathFinder(pNet, inits, tgtPlace, local, maxTokens, clones, objectiveOption, maxIterations);
		pf.setVars(vars);
		pf.setTgt(tgt);
		return pf;

	}

	public static void main(String[] args) throws Exception {
		long startSoot = System.nanoTime();
		int roundRobinPosition = 0;
		int roundRobinIterations = 0;
		int roundRobinIterationsLimit = 40;
		int roundRobinRange = 3;
		boolean roundRobinFlag = true;

		Cli cmdOptions = new Cli(args);
		cmdOptions.parse();

		VERBOSE = cmdOptions.getVerbose();
		TIMEOUT = cmdOptions.getTimeout();
		roundRobinFlag = cmdOptions.getRoundRobin();
		// The objective function can be used to add preferences over which
		// methods to explore
		objectiveOption = objectiveOption.AT_LEAST_ONE;
		maxIterations = cmdOptions.getSolverLimit();

		cmdOptions.printOptions();

		benchLoc = cmdOptions.getFilename();
		if (!new File(benchLoc).exists()) {
			System.out.println("Cannot find json file: " + benchLoc);
			System.exit(2);
		}

		double timeGetPath = 0;
		double timeInitSketch = 0;
		double timeFillHoles = 0;
		double timeCompilation = 0;
		double timeRunTest = 0;
		double timeTotal = 0;
		long cntFillHoles = 0;
		Gson gson = new Gson();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(benchLoc));
			Benchmark qb = gson.fromJson(br, Benchmark.class);
			// generate the method header
			qb.setMethodHeader(genMethodHeader(qb));
			// generate the test string
			qb.setTestBody(genTest(qb));
			/////////////////////////////////////////////////////////////////////////////////
			System.out.println("----------" + benchLoc);
			System.out.println("Benchmark Id: " + qb.getId());
			System.out.println("Method name: " + qb.getMethodName());
			System.out.println("Packages: " + qb.getPackages());
			System.out.println("Libraries: " + qb.getLibs());
			System.out.println("Source type(s): " + qb.getSrcTypes());
			System.out.println("Target type: " + qb.getTgtType());
			System.out.println("--------------------------------------------------------");

			///////////////////////////////////////////////////
			Set<String> pkgs = qb.getPackages();
			String keyword = qb.getMethodName();
			description=qb.getDescription();
			StringBuilder options = new StringBuilder();
			options.append("-prepend-classpath");
			options.append(" -full-resolver");
			options.append(" -allow-phantom-refs");
			StringBuilder cp = new StringBuilder();
			for (String lib : qb.getLibs()) {
				cp.append(lib);
				cp.append(":");
				options.append(" -process-dir " + lib);
			}

			options.append(" -cp " + cp.toString());

			if (!Options.v().parse(options.toString().split(" ")))
				throw new CompilationDeathException(CompilationDeathException.COMPILATION_ABORTED,
						"Option parse error");

			Scene.v().loadBasicClasses();
			Scene.v().loadNecessaryClasses();

			List<PetriNet> pNetList = new ArrayList<>();

			// SootUtil.genDepGraph(qb.getLibs(), pkgs, qb.getTgtType());
			// FIXME: get lower bound. Will place with shortest path.
			// int low = Math.max(1, SootUtil.getLowerBound(qb));
			if(usingGoogleResult)
			{
				System.out.println("============usingGoogle===============");
				List<String> TempGoogleAPIList=new ArrayList<String>();
				
				String TextPath="content_results/";
				int benchmarkId=qb.getId();
				System.out.println(benchmarkId);
				String curPath=TextPath+String.valueOf(benchmarkId)+"_content";
				
				String content="";
				BufferedReader bReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(curPath)), "UTF-8"));
				String lineTxt=null;
				
				while((lineTxt = bReader.readLine())!=null) {
					lineTxt=lineTxt.replaceAll("\\p{C}","");
					String api = "";
					if(lineTxt.startsWith("Number")) 
						continue;					
					if(lineTxt.indexOf(".") == -1) 
						continue;
					if(lineTxt.contains("(")) { 
						lineTxt = lineTxt.trim();
						String method =  lineTxt.split("\\(")[0];
						String methodName = method.split("\\.")[method.split("\\.").length -1];
						if(lineTxt.substring(0, lineTxt.indexOf(".")).equals(methodName)) {
							method = methodName + ".<init>";
						}
						api = method.toLowerCase().trim();	
					}
					else {
						api = lineTxt.trim().toLowerCase();
					}
					TempGoogleAPIList.add(api);
				}
				
				
				if(usingThreshold)// useless
				{
					String javaDocFilePath="JavaDocs/";
					File file = new File(javaDocFilePath);
			        LinkedList<File> list = new LinkedList<File>();
			        File[] files = file.listFiles();
			        
			        List<String> PkgApiSet=new ArrayList<String>();
			        
					for(String pkg:qb.getPackages())
					{
						for(File f:files)
				        {
				        	String fileSub=((f.toString()).substring(0,f.toString().length()-4));
				        	if(fileSub.endsWith(pkg))
				        	{
				        		BufferedReader bReader1=new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
								while((lineTxt=bReader1.readLine())!=null)
								{
									String str1=(lineTxt.split("\\("))[0];
									str1=str1.toLowerCase();
									String[] str1Split=str1.split("\\.");
									String methodName = str1Split[str1Split.length - 1];
									String cName = "";
									if(str1Split.length > 2 && (str1Split[1]).equals(methodName)) {
										int point_pos = getFromIndex(str1, "\\.", 2);
										cName = str1.substring(0, point_pos);
										methodName = cName + ".<init>";
									}
									if((str1.substring(0, str1.indexOf("."))).equals(methodName)) {
										methodName = methodName + ".<init>";
									}		
									PkgApiSet.add(methodName);
								}
								bReader1.close();
								break;
				        	}
				        }
					}
					int number=0;
					for(String ss:TempGoogleAPIList)
					{
						if(PkgApiSet.contains(ss))
						{
							GoogleAPIList.add(ss);
							number++;
						}
						if(number>=threshold)
						{
							break;
						}
					}
					GoogleAPIList.add("useAnchoringBounds".toLowerCase());
					System.out.println(GoogleAPIList.size());
				}
				else
				{
					
					GoogleAPIList=TempGoogleAPIList;
					GoogleAPIList.add("Matcher.useAnchoringBounds".toLowerCase());
				}
			
			}
			
			if(usingWord2API){
				long start = System.nanoTime();
				SootUtil.usingWord2API = usingWord2API;
				Map<String, Double> word2apiSimi = Re_W2ASimilarity.getWord2APISimi(description, pkgs);
				SootUtil.word2apiSimiMap = word2apiSimi;
				long end = System.nanoTime();
				double word2apiTime = TimeUtil.computeTime(start, end);
			}
			
				
			if(usingBayou){
				SootUtil.usingBayou = usingBayou;
				Set<String> apiList = new HashSet<String>();
				Set<String> temp_apiList = BayouAPI.getBayou_APIList(String.valueOf(qb.getId()));
				System.out.println("=========bayou-program-api=======");
				for(String s : temp_apiList) {
					System.out.println(s);
				}
				apiList.addAll(temp_apiList);
				apiList.add("java.util.regex.Matcher.useAnchoringBounds");				
				SootUtil.bayouAPI = apiList;
			}
			
			if(usingRack) {
				String rackPath = "data/rack_result.txt";
				BufferedReader rackReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(rackPath))));
				String apis = "";
				Set<String> api_class = new HashSet<String>();
				while((apis = rackReader.readLine())!=null) {
					if(Integer.valueOf(apis.split("#")[0]) == qb.getId()) {
					String[] temp = apis.split("#")[1].split("\t");
					for(String t : temp) {
						api_class.add(t);
					}
					break;
					}
					else 
						continue;
				}
				rackReader.close();
				SootUtil.rackAPIClass = api_class;
				SootUtil.usingRack = usingRack;
			}
			
			if(usingNLP2API) {
				String nlp2apiPath = "data/new_nlp2api_result.txt";
				BufferedReader nlpReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(nlp2apiPath))));
				String apis = "";
				Set<String> api_class = new HashSet<String>();
				while((apis = nlpReader.readLine())!=null) {
					if(Integer.valueOf(apis.split("#")[0]) == qb.getId()) {
					String[] temp = apis.split("#")[1].split(" ");
					for(String t : temp) {
						api_class.add(t.trim());
					}
					break;
					}
					else 
						continue;
				}
				nlpReader.close();
				SootUtil.nlpAPIClass = api_class;
				SootUtil.usingNLP2API = usingNLP2API;
			}
			
			if(usingDeepAPI) {
				System.out.println("==========usingDeepAPI===========");
				String deepapi_path = "data/deepapi_result.txt";
				BufferedReader deepReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(deepapi_path))));
				String api_seq = "";
				Set<String> apis = new HashSet<String>();
				while((api_seq = deepReader.readLine()) != null) {
					if(Integer.valueOf(api_seq.split("#")[0]) == qb.getId()) {
						String[] temp = api_seq.split("#")[1].split(" ");
						for(String t : temp) {
							apis.add(t.trim());
						}
						break;
					}
					else
						continue;
				}
				deepReader.close();
				SootUtil.usingDeepAPI = usingDeepAPI;
				apis.add("Matcher.useAnchoringBounds");
				SootUtil.deepAPI = apis;
			}
			

			int low = 1;

			PetriNet pNet = new PetriNet();
			// only one petrinet without pruning.
			for (String lib : qb.getLibs()) {
				SootUtil.processJar(lib, pkgs, pNet);
			}
			
			SootUtil.handlePolymorphism(pNet);
			System.out.println("#Classes: " + SootUtil.classNum);
			System.out.println("#Methods: " + SootUtil.methodNum);
			long endSoot = System.nanoTime();
			double sootTime = TimeUtil.computeTime(startSoot, endSoot);
			System.out.println("Soot Time: " + sootTime);
			pNetList.add(pNet);

			// Multiple args: check if we need put clone as initial constraints.
			
			clones = SootUtil.getClones(qb.getSrcTypes());
			int petriIterator = 0;
	
			// System.out.println("Petri nets: " + pNetList.size());
		
			int cnt = 0;
			// int localMax = 1 + clones.size();
			// System.out.println("Lower bound: " + low);
			int localMax = low;
			boolean flag = false;
			long start0 = System.nanoTime();

			assert (roundRobinRange < 7);
			ArrayList<PathFinder> roundRobin = new ArrayList<>();
			if (roundRobinFlag) {
				for (int i = 0; i < roundRobinRange; i++) {
					roundRobin.add(initPetriNet(qb, pNetList, petriIterator++, localMax++));
				}
			}
			

			while (!flag) {
				PathFinder pf;
				if (roundRobinFlag) {
					pf = roundRobin.get(roundRobinPosition);
				} else {
					if (pNetList.size() > 1)
						assert (petriIterator < pNetList.size());
					pf = initPetriNet(qb, pNetList, petriIterator++, localMax);
					
				}

				if (VERBOSE && !roundRobinFlag)
					System.out.println("Searching with local max: " + localMax);
				while (roundRobinIterations < roundRobinIterationsLimit || !roundRobinFlag) {
					long start1 = System.nanoTime();
					List<String> res = pf.get();
					//ljx
					System.out.println("=========sketch========");
					for(String s : res) {
					System.out.println(s);
					}
                                        System.out.println("#Program: "+cntFillHoles);
					
					long end1 = System.nanoTime();
					timeGetPath += TimeUtil.computeTime(start1, end1);
					if (VERBOSE)
						TimeUtil.reportTime(start1, end1, "get path: ");
					if (VERBOSE)
						System.out.println("call SAT." + cnt + " val: " + res);

					if (res.isEmpty())
						break;
					cnt++;

					List<String> solution = new ArrayList<>();
					String curS="";
					for (String meth : res) {
						curS=curS+meth;
						if (meth.startsWith("sypet_clone_"))
							continue;
						

						solution.add(meth);
					}
				
					int use=0;
					 
					// init sketcher.
					long start2 = System.nanoTime();
					Sketcher sk = new Sketcher(solution, pf.getVars(), pf.getTgt());
					boolean hasSketch = sk.initSketch();
					long end2 = System.nanoTime();
					timeInitSketch += TimeUtil.computeTime(start2, end2);
					if (VERBOSE)
						System.out.println("#holes: " + sk.getHolesNum());
					if (VERBOSE)
						TimeUtil.reportTime(start2, end2, "init sketch: ");
					while (true) {
						++cntFillHoles;
						long start3 = System.nanoTime();
						String snippet = sk.fillHoles();
						long end3 = System.nanoTime();
						timeFillHoles += TimeUtil.computeTime(start3, end3);
						if (VERBOSE)
							TimeUtil.reportTime(start3, end3, "fill hole: ");
						if (snippet.equals("UNSAT"))
							break;
						if(use==1)
						{
							System.out.println(snippet);
						}
						
						// invoke yuepeng's method.
						if (VERBOSE)
							System.out.println("snippet:" + snippet);
						qb.setBody(snippet);
//						System.out.println(snippet);
						boolean passTest = SypetTestUtil.runTest(qb);
						timeCompilation += SypetTestUtil.getCompilationTime();
						timeRunTest += SypetTestUtil.getRunningTime();
						if (VERBOSE)
							System.out.println("Test result-------------" + passTest);
						if (VERBOSE)
							System.out.println("Compilation Time: " + SypetTestUtil.getCompilationTime());
						if (VERBOSE)
							System.out.println("Running Time: " + SypetTestUtil.getRunningTime());
						long end0 = System.nanoTime();
						// note: this should be = instead of +=
						timeTotal = TimeUtil.computeTime(start0, end0);
						if (passTest) {
							System.out.println("=========Statistics (time in milliseconds)=========");
							System.out.println("Benchmark Id: " + qb.getId());
		                    System.out.println("Recommend Api Number:" + SootUtil.cnt);
							System.out.println("Sketch Generation Time: " + timeGetPath);
							System.out.println("Sketch Completion Time: " + (timeInitSketch + timeFillHoles));
							System.out.println("Compilation Time: " + timeCompilation);
							System.out.println("Running Test cases Time: " + timeRunTest);
							System.out.println(
									"Synthesis Time: " + (timeGetPath + timeInitSketch + timeFillHoles + timeRunTest));
							System.out.println("Total Time: "
									+ (sootTime+timeGetPath + timeInitSketch + timeFillHoles + timeRunTest + timeCompilation));
							
							System.out.println("Number of components: " + res.size());
							System.out.println("Number of holes: " + sk.getHolesNum());
							System.out.println("Number of completed programs: " + cntFillHoles);
							System.out.println("Number of sketches: " + cnt);
							System.out.println("Solution:\n " + snippet.replace(";", ";\n "));

							System.out.println("============================");
							br.close();
							return;
						} else if (timeTotal > TIMEOUT) {
							System.out.println("=========Statistics=========");
							System.out.println("Benchmark Id: " + qb.getId());
							System.out.println("Recommend Api Number:" + SootUtil.cnt);
							System.out.println("Soot Time: "+sootTime);
							System.out.println("Sketch Generation Time: " + timeGetPath);
							System.out.println("Sketch Completion Time: " + (timeInitSketch + timeFillHoles));
							System.out.println("Compilation Time: " + timeCompilation);
							System.out.println("Running Test cases Time: " + timeRunTest);
							
							System.out.println(
									"Synthesis Time: null");
							System.out.println("Total Time: null");
							System.out.println("Number of components: null");
							System.out.println("Number of holes: null");
							System.out.println("Number of completed programs: " + cntFillHoles);
							System.out.println("Number of sketches: " + cnt);
							System.out.println("TIMEOUT after " + TIMEOUT + " ms");
							System.out.println("============================");
							br.close();
							return;
						}
					}
					roundRobinIterations++;
				}
				if (roundRobinFlag) {
					if (roundRobinIterations != roundRobinIterationsLimit) {
						if (pNetList.size() > 1)
							assert (petriIterator < pNetList.size());
						roundRobin.set(roundRobinPosition, initPetriNet(qb, pNetList, petriIterator++, localMax++));
					}
					roundRobinPosition = (roundRobinPosition + 1) % roundRobin.size();
					roundRobinIterations = 0;
				} else {
					localMax++;
				}
			}
		} catch (CompilationDeathException e) {
			e.printStackTrace();
			if (e.getStatus() != CompilationDeathException.COMPILATION_SUCCEEDED)
				throw e;
			else
				return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected static String genMethodHeader(Benchmark bench) {
		StringBuilder builder = new StringBuilder();
		builder.append(bench.getTgtType().replaceAll("\\$", ".")).append(' ');
		builder.append(bench.getMethodName()).append('(');
		ArrayList<String> paramTypes = new ArrayList<String>(bench.getSrcTypes());
		ArrayList<String> paramNames = new ArrayList<String>(bench.getParamNames());
		assert paramTypes.size() == paramNames.size();
		for (int i = 0; i < paramTypes.size(); ++i) {
			builder.append(paramTypes.get(i)).append(' ').append(paramNames.get(i));
			if (i < paramTypes.size() - 1) {
				builder.append(", ");
			}
		}
		builder.append(')');
		return builder.toString();
	}

	protected static String genTest(Benchmark bench) {
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(bench.getTestPath()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	private static int getFromIndex(String str, String modelStr, Integer count) {
	    Matcher slashMatcher = Pattern.compile(modelStr).matcher(str);
		int index = 0;
	    while(slashMatcher.find()) {
		    index++;
		    if(index == count){
		       break;
		    }
		}
	    return slashMatcher.start();
	}
}
