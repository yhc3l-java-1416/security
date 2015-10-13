package se.coredev.secure.ex6;

import se.coredev.secure.ex6.Main.OurSecurityManager;

public final class Unsafe {
	
	static {
		if (!(System.getSecurityManager() instanceof OurSecurityManager)) {
			throw new SecurityException("Invalid security manger");
		}
	}

	private final String masterPassword = "ultr4zicrii3t";

	public String getSecretMessage(String password) {
		if (masterPassword.equals(password)) {
			return "Hello master!";
		}
		else {
			return "Unauthorized!";
		}
	}
}