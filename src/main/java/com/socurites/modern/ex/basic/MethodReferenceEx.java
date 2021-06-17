package com.socurites.modern.ex.basic;

import java.io.File;
import java.io.FileFilter;

public class MethodReferenceEx {
	public static void main(String[] args) {
		MethodReferenceEx ex = new MethodReferenceEx();
		
	}
	
	public void exampleBefore001() {
		File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				return file.isHidden();
			}
		});
	}
	
	public void exampleAfter001() {
		File[] hiddenFiles = new File(".").listFiles(File::isHidden);
	}
	
	
}
