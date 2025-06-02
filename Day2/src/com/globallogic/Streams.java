package com.globallogic;

import java.util.*;

public class Streams {
	
	public static void main(String[] args) {
		List<String> ar = Arrays.asList("Jack", "John", "Garry", "Jill", "Jim", "Frank");
		
		ar.stream().filter(word -> word.startsWith("J") && word.length() == 4).forEach(word -> System.out.println(word));
		

	}

}
