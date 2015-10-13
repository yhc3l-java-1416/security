package se.coredev.secure.ex1;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println(new ColorPoint(100, 50, "Black"));
	}
}

// Point
class Point
{
	private final int x;
	private final int y;
	private String name;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
//		this.name = getName();
	}

	protected String getName() { return "[x:" + x + ", y:" + y + "]"; }

	@Override
	public synchronized final String toString() {
		if(name == null) {
			name = getName();
		}
		return name; 
	}
}

// Color point
final class ColorPoint extends Point
{
	private final String color;
	
	public ColorPoint(int x, int y, String color) {
		super(x, y);
		this.color = color;
	}
	
	@Override
	protected String getName() { return super.getName() + ":" + color; }
}
