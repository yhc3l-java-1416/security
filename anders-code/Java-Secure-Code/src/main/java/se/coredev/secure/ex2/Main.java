package se.coredev.secure.ex2;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		Set<User> users = new HashSet<>();
		User user1 = new User("master");
		User user2 = new User("master");
		users.add(user1);
		users.add(user2);

		System.out.println("Size:" + users.size());
	}
}

final class User {
	private final String username;

	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		System.out.println("hashCode called");
		return username.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		System.out.println("equals called");
		if (!(o instanceof User)) {
			return false;
		}

		User u = (User) o;
		return username.equals(u.username);
	}

	@Override
	public String toString() {
		return username;
	}
}