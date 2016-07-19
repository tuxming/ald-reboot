package com.ald.reboot.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Stack;

public class Tools {
	
	public static String readFile(String filePath){
		String content = "";
		try {
	        String encoding="UTF-8";
	        File file=new File(filePath);
	        if(file.isFile() && file.exists()){ //判断文件是否存在
	            InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	content += lineTxt;
	            }
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public static String readFileAndRemoveLineComment(String filePath){
		String content = "";
		try {
	        String encoding="UTF-8";
	        File file=new File(filePath);
	        if(file.isFile() && file.exists()){ //判断文件是否存在
	            InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	
	            	content += removeLineComment(lineTxt);
	            }
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return removeBlockComment(content);
	}
	
	public static void writeFile(){
		
	}
	
	/**
	 * 
	 * @param content
	 * @return
	 */
	public static String removeBlockComment(String content){
		return content.replaceAll("/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/", "");
	}
	
	/*
	 * remove line comment 
	 */
	public static String removeLineComment(String line){
		
		String result ="";
		
		char[] cs = line.toCharArray();
		
		int quoteTotal = 0;
		boolean prevIsSprit = false;
		int commentStart = -1;
		for (int i=0; i<cs.length; i++) {
			char  c = cs[i];
			
			if(c=='\"'){
				 ++quoteTotal;
			}
			
			if(c=='/'){
				if(prevIsSprit){
					if(quoteTotal%2==0){
						commentStart = i;
						break;
					}
				}else{
					prevIsSprit =  true;
				}
			}else{
				prevIsSprit = false;
			}
			
		}
		
		if(commentStart!=-1){
			result = line.substring(0,commentStart-1);
		}else 
			result = line;
		
		return result;
	}
	
}
