package se.coredev.secure.ex5;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String args[]) throws Exception {

		User user1 = new User("master", "2015-10-12");
		User user2 = new User("master", "2015-10-13");

		Set<User> users = new HashSet<>();
		users.add(user1);
		users.add(user2);
		user2.getRegistrationDate().setTime(user1.getRegistrationDate().getTime());

		System.out.println("Size:" + users.size());
		
		for (User u : users) {
			System.out.println(u);
		}

	}
}
