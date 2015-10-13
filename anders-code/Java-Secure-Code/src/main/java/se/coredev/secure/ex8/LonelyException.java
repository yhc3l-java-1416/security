package se.coredev.secure.ex8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class LonelyException extends Exception {

	public transient static final LonelyException INSTANCE = new LonelyException("There can be only one!");

	private LonelyException(String message) {
		super(message);
	}
	
	private Object readResolve() {
		return INSTANCE;
	}
}

class Main {

	public static void main(String[] args) throws Exception{
		LonelyException lonelyException = LonelyException.INSTANCE;
		LonelyException clone = (LonelyException) deepCopy(lonelyException);

		System.out.println(lonelyException == clone);
	}

	private static Object deepCopy(final Object source) throws Exception {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new ObjectOutputStream(out).writeObject(source);
		
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		return new ObjectInputStream(in).readObject();
	
	}
}