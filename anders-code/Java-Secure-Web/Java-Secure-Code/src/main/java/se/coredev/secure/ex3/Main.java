package se.coredev.secure.ex3;

import java.util.HashSet;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) {
		Set<User> users = new HashSet<>();
		
		User yoda = new User("master");
		User darth = new User("maste?");
		
		users.add(yoda);
		users.add(darth);
		User newDarth = darth.setUsername("master");
		users.add(newDarth);
		
		for(User u : users)
		{
			System.out.println(u);
		}
	}
}
