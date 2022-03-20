package com.lhst.execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.lhst.util.StringUtil;


public class ConvertLink {
	
	public static final String INPUT_FILE = "links.txt";
	public static final String OUTPUT_FILE = "links.txt";
	
	public static void convertLink(String fileDir, Integer count) throws Exception{
		InputStreamReader reader = new InputStreamReader(
				  new FileInputStream(fileDir + INPUT_FILE), "utf-8");// 考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(reader);
		PrintWriter printWriter = new PrintWriter(new File(fileDir + OUTPUT_FILE));
		String line = null;
		Integer index = 1;
		while(!StringUtil.isEmpty(line = bufferedReader.readLine())) {
			line = line.replace("blob", "raw");
			break;
		}
		
		bufferedReader.close();
		
		while(index <= count) {
			if (index == 1) {
				printWriter.println(line);
				printWriter.flush();
			} else {
				line = line.replaceAll(String.valueOf(index-1), String.valueOf(index));
				printWriter.println(line);
			}
			index++;
		 }
		 printWriter.close();
	}
	
	public static void convertLink(String link, String fileDir, Integer count) throws Exception{
		PrintWriter printWriter = new PrintWriter(new File(fileDir + OUTPUT_FILE));
			
		Integer index = 1;
		while(index <= count) {
			if (index == 1) {
				printWriter.println(link);
				printWriter.flush();
			} else {
				link = link.replaceAll(String.valueOf(index-1), String.valueOf(index));
				printWriter.println(link);
			}
			index++;
		}
		printWriter.close();
	}
	

	public static void main(String[] args) throws Exception{
		
		//Method_1
/*		String rootPath =  System.getProperty("user.dir");
		String targetFolder = "auto_caption";
		String fildDir = rootPath + "/blog/images/" + targetFolder + "/";
		convertLink(fildDir,19);*/
		
		//Method_2
		String rootPath =  System.getProperty("user.dir");
		String originLink =  "https://cdn.jsdelivr.net/gh/yekongle/PhotoManager@master/blog/images/%s/%s/%s/%s";
		String originLink1 =  "https://cdn.jsdelivr.net/gh/yekongle/PhotoManager@master/blog/images/%s/%s/%s/%s/%s";

		String firstFolder = "frontend";
		String secondFolder =  "mobile";
		String thirdFolder = "properties_sample";
		String fourthFolder = "properties_sample";

		String fileName = "1.jpg";
		
		String targetLink = String.format(originLink, firstFolder, secondFolder, thirdFolder, fileName);
//		String targetLink1 = String.format(originLink, firstFolder, secondFolder, thirdFolder, fourthFolder, fileName);


		String path = rootPath + "/blog/images/%s/%s/%s/";
		String targetFileDir = String.format(path, firstFolder, secondFolder, thirdFolder);

		Integer linkNum = 5;
		
		convertLink(targetLink, targetFileDir, linkNum);
		System.out.println("Convert success!");
		
	}
}
