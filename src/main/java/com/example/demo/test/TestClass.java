package com.example.demo.test;

public class TestClass {

	
	public static void main(String[] args) {
		
		try {
			
			throw new CustomException("Hello");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
