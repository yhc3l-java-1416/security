package se.coredev.secure.ex4;

public final class User {
	private String username;

	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof User)) {
			return false;
		}
		User u = (User) o;
		return username.equals(u.username);
	}

	@Override
	public int hashCode() {
		return 37 * username.hashCode();
	}

	@Override
	public String toString() {
		return username;
	}
}