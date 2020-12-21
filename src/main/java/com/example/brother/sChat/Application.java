package com.example.brother.sChat;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.brother.sChat.dao.MessageDao;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		
		MessageDao fromDatabase = new MessageDao();
		
		FileWriter myWriter = new FileWriter("src/main/webapp/data.json", false);
		myWriter.write(fromDatabase.getJson());
		myWriter.close();
		
		
		
		SpringApplication.run(Application.class, args);
	}

}
