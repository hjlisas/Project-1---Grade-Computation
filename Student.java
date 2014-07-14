public class Student
{
    CourseCheckers units;
    QualityPointValue pv;
    public int year;
    public int totalUnits;
    public String ID;
    public double tempQPI;
    
    public Student( String ID, int year )
    {
        this.ID = ID;
        this.year = year;
        tempQPI = 0;
        totalUnits = 0;
        units = new CourseChecker();
        pv = new QualityPointValue();
    }

    public void addLoad( String subj, String grade )
    {
    	totalUnits = totalUnits + units.getUnits( subj );
    	tempQPI = tempQPI + ( (double)units.getUnits( subj ) * (double)pv.getQPV( grade ) );
    }

    public String getID()
    {
        return ID;
    }

    public int getYearLevel()
    {
        return year;
    }

    public double getQPI()
    {
        return tempQPI / totalUnits;
    }

    public boolean passed()
    {
    	return ( getQPI() >= getQPIRequired( getYearLevel() ) );
    }
    
    public double getQPIRequired( int yrLvl )
    {
    	switch( yrLvl )
    	{
    		case 1 : return 1.8;
    		case 2 : return 1.9;
    		default : return 2.0;
    	}
    }
}
