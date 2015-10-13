package se.coredev.secure.ex6;

import java.lang.reflect.Field;
import java.security.Permission;

public class Main {

	public static final class OurSecurityManager extends SecurityManager {

		@Override
		public void checkPermission(final Permission permission) {
			if (permission.getName().equals("supressAccessChecks")) {
				throw new SecurityException("Nice try!");
			}
			super.checkPermission(permission);
		}
	}

	public static void main(String[] args) throws Exception {

//		System.setSecurityManager(new OurSecurityManager());
		
		Unsafe unsafe = new Unsafe();

		Class<? extends Unsafe> clazz = unsafe.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			System.out.println("Field name:" + field);
			System.out.println("Field value:" + field.get(unsafe));
		}

		System.out.println(unsafe.getSecretMessage("ultr4zicrii3t"));
	}
}
