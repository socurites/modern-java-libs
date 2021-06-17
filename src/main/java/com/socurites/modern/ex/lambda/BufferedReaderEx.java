package com.socurites.modern.ex.lambda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @FunctionalInterface 직접 만들기
 * 
 * @author socurites
 *
 */
public class BufferedReaderEx {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		BufferedReaderEx ex = new BufferedReaderEx();
		
		String line = ex.processFileOriginal();
		System.out.println(line);
		
		
		ex.processFileLambdal((BufferedReader br) -> br.readLine());
		ex.processFileLambdal((BufferedReader br) -> br.readLine() + br.readLine());
	}
	
	
	
	
	public String processFileOriginal() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("README.md"))) {
			return br.readLine();	// <-- 행위
		}
	}
	
	@FunctionalInterface
	public interface BufferedReaderProcessor {
		String process(BufferedReader br) throws IOException;
	}
	
	public String processFileLambdal(BufferedReaderProcessor p) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("README.md"))) {
			return p.process(br);	// <-- 행위
		}
	}
}
