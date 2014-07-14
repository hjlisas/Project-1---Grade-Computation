public class QualityPointValue
{
	private static final int GRADECOUNT = 7;
	private static final String gradePtVal[][] = 
	{
		{ "A", "4.0" },
		{ "B+", "3.5" },
		{ "B", "3.0" },
		{ "C+", "2.5" },
		{ "C", "2.0" },
		{ "D", "1.0" },
		{ "F", "0.0" }
	};
	
	public double getQPV( String s )
	{
		for ( int i = 0; i < GRADECOUNT; i++ )
			if ( s.equals( gradePtVal[i][0] ) )
				return new Double( gradePtVal[i][1] ).doubleValue();
			return 0;
	}
}