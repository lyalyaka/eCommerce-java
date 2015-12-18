import java.nio.file.*;
import java.io.*;

public class User {
	private String username;
	
	public User(String name){
		username = name;
	}
	
	public User (String name, String newUser) {
		this.username = name;
		try {
		    Files.write(Paths.get("Users.txt"), ("\n" + name).getBytes(), StandardOpenOption.APPEND);
		    System.out.println("Username successfully added\n");
		}catch (IOException e) {
			System.out.println("File Read Error");
		}
	}
	
	public String getUsername(){
		return this.username;
	}
}