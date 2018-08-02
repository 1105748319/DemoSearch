package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	String pattenStr;

	public String getPattenStr() {
		return pattenStr;
	}

	public void setPattenStr(String pattenStr) {
		this.pattenStr = pattenStr;
	}

	public void getFolder(String path,Test test) throws IOException {
		File file=new File(path);
		if(file.exists()) {
			File[] files=file.listFiles();
			if(files.length==0) {
				//System.out.println("文件夹是空");
				return;
			}else {
				for(File file2:files) {
					if(file2.isDirectory()) {
						//System.out.println("文件夹名称是："+file2.getAbsolutePath());
						getFolder(file2.getAbsolutePath(),test);
					}else {
						//System.out.println("文件名称是："+file2.getAbsolutePath());
						Test.readFile(file2.getAbsolutePath(),test);
					}
					
				}
			}
		}
	}

	public static void readFile(String path,Test test) throws IOException {
		File file=new File(path);
		BufferedReader bReader =new BufferedReader(new FileReader(file));
		String str=null;
		int index =1;
		while((str=bReader.readLine())!=null) {
			//Test test=new Test();
			String pp=test.getPattenStr();
			//String regEx = "console*";
			String regEx = pp;  
		    // 编译正则表达式
		    Pattern pattern = Pattern.compile(regEx);
		    // 忽略大小写的写法
		    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(str);
		    // 查找字符串中是否有匹配正则表达式的字符/字符串
		    boolean rs = matcher.find();
			//System.out.println(path+"：文件内容是："+str);
		    if(rs) {
		    	System.out.println("找到 "+test.getPattenStr()+" 的文件是："+path+" 第"+index+"行"+ " ：文件内容是："+str);
		    }
		    index++;
		}
	}
	public static void main(String[] args) throws IOException {
		String path="/Users/mac/Documents/test";		
		Test test =new Test();
		test.setPattenStr("console");
		test.getFolder(path,test);
	}
}
