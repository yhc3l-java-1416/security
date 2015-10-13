package se.coredev.secure.ex7;

import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		String userAsString = "anca01;Anders;Carlsson";
		Optional<User> user = UserFactory.createUserFromString(userAsString);

		if (user.isPresent()) {
			System.out.println(user.get());
		}

	}
}
