package math;

public class Utils
{
	public static double min(double a, double b, double c, double d)
	{
		return Math.min(a, Math.min(b, Math.min(c, d)));
	}
	
	public static double converteToRad(double grad)
	{
		return ((2 * Math.PI) / 360.0) * grad;
	}
	
	public static float parseFloat(String f)
	{
		System.out.println("parsing: " + f);
		try {
			return Float.parseFloat(f);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Float.MAX_VALUE;
		}
	}
}
