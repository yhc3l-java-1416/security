package se.coredev.secure.ex7;

import java.util.Optional;

public final class UserFactory
{
	public static Optional<User> createUserFromString(String userAsString)
	{
		String[] values = userAsString.split(";");
		if(values.length < 3)
		{
			return Optional.empty();
		}
		return Optional.of(new User(values[0], values[1], values[2]));
	}
}