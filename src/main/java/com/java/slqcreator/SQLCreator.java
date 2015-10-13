package com.java.slqcreator;

import com.github.javafaker.*;

public class SQLCreator {
	
	public static void main(String[] args){
		
		int iterations = 1000;
		int count = 1;
		
		Faker faker = new Faker();
//
//		String name = faker.name().fullName(); // Miss Samanta Schmidt
//		String firstName = faker.name().firstName(); // Emory
//		String lastName = faker.name().lastName(); // Barton
//
//		String streetAddress = faker.address().streetAddress();
		
		
//		for(int i =10000 ; i <= 20001 ; i++){
//			StringBuilder sb = new StringBuilder();
//			
//			//sb.append("INSERT INTO `test`.`users`(`username`,`password`,`enabled`) VALUES('User").append(i+"'").append("'password").append"',1);");
//			
//			sb.append("INSERT INTO `test`.`users`(`username`,`password`,`enabled`) VALUES('User"+i+"','password"+i+"','1');");
//			System.out.println(sb);
//			count++;
//		}
		
		
//		for(int i =10000 ; i <= 20001 ; i++){
//			StringBuilder sb = new StringBuilder();
//			
//			//sb.append("INSERT INTO `test`.`users`(`username`,`password`,`enabled`) VALUES('User").append(i+"'").append("'password").append"',1);");
//			
//			sb.append("INSERT INTO `test`.`user_roles`(`username`,`role`)VALUES('User"+i+"','ROLE_USER');");
//			System.out.println(sb);
//			count++;
//		}
	
		
		
		for(int i =15002 ; i <= 17002 ; i++){
		StringBuilder sb = new StringBuilder();
		
		//sb.append("INSERT INTO `test`.`users`(`username`,`password`,`enabled`) VALUES('User").append(i+"'").append("'password").append"',1);");
		
		sb.append("INSERT INTO `test`.`employee`(`username`,`jobtitle`,`location`,`email`,`phonenumber`) VALUES('User"+i+"','HR','Dallas','email"+i+"','915"+i+"');");
		System.out.println(sb);
		count++;
	}

		
	}

}
