package com.example.brother.sChat.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.example.brother.sChat.dao.MessageDao;
import com.example.brother.sChat.model.Data;
import com.example.brother.sChat.model.MessageModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

	ArrayList<String> data = new ArrayList<>();
	ArrayList<MessageModel> object = new ArrayList<>();

	@RequestMapping("send")
	public String sendData(@RequestParam("name") String name, @RequestParam("message") String message) throws IOException{
		
		 data.clear();
		 object.clear();
		
		Gson json = new Gson();
		JsonReader reader = new JsonReader(new FileReader("src/main/webapp/data.json"));
		
     Data obj =    json.fromJson(reader, Data.class);
     try {
     object = obj.MessageModel;
     for (MessageModel x : object) {
    	
    	 data.add("{\"name\":\"" + x.getName() + "\", \"message\":\"" + x.getMessage() + "\", \"timestamp\":\"" + x.getTimestamp()
 		+ "\"},"); 
    	 
    	 
		
	}
     }catch (Exception e) {
    		// TODO: handle exception
     }
     
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			FileWriter myWriter = new FileWriter("src/main/webapp/data.json", false);

			myWriter.write("{\"MessageModel\":[");

			data.add("{\"name\":\"" + name + "\", \"message\":\"" + message + "\", \"timestamp\":\"" + dtf.format(now)
					+ "\"},");

			String jsonData = "";
			for (String x : data) {

				jsonData = jsonData + x;

			}
			int lastChar = jsonData.length() - 1;

			StringBuilder sb = new StringBuilder(jsonData);
			sb.deleteCharAt(lastChar);

			myWriter.write(sb.toString());
			myWriter.write("]}");

			myWriter.close();
			
			Path jsonFile = Path.of("src/main/webapp/data.json");
	        String jsonString = Files.readString(jsonFile);
			MessageDao toDatabase = new MessageDao(jsonString);
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return "data.json";
	}
	
	@RequestMapping("read")
	public String readData() throws IOException{

		
		
		 data.clear();
		 object.clear();
		
		Gson json = new Gson();
		JsonReader reader = new JsonReader(new FileReader("src/main/webapp/data.json"));
		
		
		
		Path jsonFile = Path.of("src/main/webapp/data.json");
        String jsonString = Files.readString(jsonFile);
		MessageDao toDatabase = new MessageDao(jsonString);
		
		
		
     Data obj =    json.fromJson(reader, Data.class);
     try {
     object = obj.MessageModel;
     for (MessageModel x : object) {
    	
    	 data.add("{\"name\":\"" + x.getName() + "\", \"message\":\"" + x.getMessage() + "\", \"timestamp\":\"" + x.getTimestamp()
 		+ "\"},"); 
    	 
    	 
		
	}
     }catch (Exception e) {
    		// TODO: handle exception
     }
     
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			FileWriter myWriter = new FileWriter("src/main/webapp/data.json", false);

			myWriter.write("{\"MessageModel\":[");

			

			String jsonData = "";
			for (String x : data) {

				jsonData = jsonData + x;

			}
			int lastChar = jsonData.length() - 1;

			StringBuilder sb = new StringBuilder(jsonData);
			sb.deleteCharAt(lastChar);

			myWriter.write(sb.toString());
			myWriter.write("]}");

			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return "data.json";
	}

	@RequestMapping("/clear")
	public ModelAndView clearData(){
		
		MessageDao clear = new MessageDao();
		clear.clear();
		data.clear();
		object.clear();
		return null;
	}
}
