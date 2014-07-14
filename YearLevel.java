public class YearLevel
{
	public int year, totalPassed, totalFailed, noOfStudents;
	public double totalQPI;

	public YearLevel()
	{
	}
	
	public YearLevel( int year )
	{
		this.year = year;
		totalPassed = 0;
		totalFailed = 0;
		noOfStudents = 0;
		totalQPI = 0;
	}
	
	public void addStudent( double qpi, boolean pass )
	{
		noOfStudents++;
		totalQPI = totalQPI + qpi;
		if ( pass )
		{
			totalPassed++;
		}
		else
		{
			totalFailed++;
		}
	}
	
	public int getYearLevel()
	{
		return year;
	}
	
	public int getTotalPassed()
	{
		return totalPassed;
	}
	
	public int getTotalFailed()
	{
		return totalFailed;
	}
	
	public double getAveQPI()
	{
		return totalQPI / (double)noOfStudents;
	}
}