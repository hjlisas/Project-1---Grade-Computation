public class CourseChecker
{
	private static final int COURSECOUNT = 9;
	private static final String courseList[][] = 
	{
		{ "Ps141", "5" },
		{ "CS101", "3" },
		{ "CS161", "3" },
		{ "Ph103", "3" },
		{ "Ps21", "5" },
		{ "Ma21", "6" },
		{ "Cs154", "3" },
		{ "Pos100", "3" },
		{ "Th151", "3" }
	};
	
	public int getUnits( String s )
	{
		for ( int i = 0; i < COURSECOUNT; i++ )
			if ( s.equals( courseList[i][0] ) )
				return new Integer( courseList[i][1] ).intValue();
			return 0;
	}
}