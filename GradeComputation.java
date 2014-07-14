import java.awt.event.*;
import java.util.*;
import java.io.*;

public class GradeComputation
{
	public static void main( String args[] )
	{
		YearLevel[] yearLevel;
		Student student;
		StringTokenizer token, idToken, subjectGradeToken;
		String[] tokenizedString;
		String inputString;			
		String[] inputSequence;		
		
		String[] ID;
		int[] year;
		String[][] subjects;
		String[][] grades;
		int[] gradeSubjectCount;
		
		int row = 0;
		inputString = "";
		tokenizedString = new String[ 100 ];
		ID = new String[ 100 ];
		year = new int[ 100 ];
		inputSequence = new String[ 100 ];

		yearLevel = new YearLevel[ 5 ];
		for ( int x = 0; x < 5; x++ )
		{
			yearLevel[ x ] = new YearLevel( x + 1 );
		}
		
		for ( int x = 0; x <= 99; x++ )
		{
			inputSequence[ x ] = "";
		}
		
		try 
		{
			BufferedReader input = new BufferedReader( new FileReader( "grades.txt" ) );
			while ( inputString != null )
			{
				inputString = input.readLine();
				if ( inputString != null )
				{
					inputSequence[ row ] = inputString;
					row++;
				}
			}
			input.close();
		}
		catch( Exception e ) {}

		for ( int x = 0; x < row; x++ )
		{
			int tokenCounter = 0;
			token = new StringTokenizer( inputSequence[ x ], "," );
			
			while ( token.hasMoreTokens() )
			{
				tokenizedString[ tokenCounter ] = token.nextToken();
				tokenCounter++;
			}
			
			idToken = new StringTokenizer( tokenizedString[ 0 ], " " );
			while ( idToken.hasMoreTokens() )
			{
				ID[ x ] = idToken.nextToken();
			}

			year[ x ] = new Integer( tokenizedString[ 1 ].trim() ).intValue();
			student = new Student( ID[ x ], year[ x ] );
			
			for ( int cnt = 2; cnt < tokenCounter - 1; cnt++ )
			{
				subjectGradeToken = new StringTokenizer( tokenizedString[ cnt ], "  " );
				while ( subjectGradeToken.hasMoreTokens() )
				{
					student.addLoad( subjectGradeToken.nextToken(),
									 subjectGradeToken.nextToken() );
				}
			}

			try
			{
				PrintStream s = new PrintStream( new FileOutputStream( "qpi.txt", true ) );
				System.out.print( "Student " + student.getID() + ", " );
				s.print( "Student " + student.getID() + ", " );
				System.out.print( "QPI: " + write2Dec( student.getQPI() ) + ", " );
				s.print( "QPI: " + (double)student.getQPI() + ", " );
				System.out.print( "Required Year Level QPI: " );
				s.print( "Required Year Level QPI: " );
			
				if ( student.passed() ) 
				{
					System.out.print( "Passed\n" );
					s.print( "Passed\n" );
				}
				else 
				{
					System.out.print( "Failed\n" );
					s.print( "Failed\n" );
				}
				s.flush();
				s.close();
			}
			catch ( Exception e ) {}
			
			for ( int i = 0; i < 5; i++ )
			{
				if ( yearLevel[ i ].getYearLevel() == year[ x ] )
				{
					yearLevel[ i ].addStudent( student.getQPI(), student.passed() );
				}
			}
		}

		try
		{
			PrintStream s = new PrintStream( new FileOutputStream( "qpi.txt", true ) );
			System.out.println( "\n" );
			s.println( "\n" );
			for ( int i = 0; i < 5; i++ )
			{
				System.out.println( "Year " + yearLevel[ i ].getYearLevel()  );
				s.println( "Year " + yearLevel[ i ].getYearLevel()  );
				System.out.println( "   Number of students passing: " + 
										yearLevel[ i ].getTotalPassed() );
				s.println( "   Number of students passing: " + 
							yearLevel[ i ].getTotalPassed() );
				System.out.println( "   Number of students failing: " + 
										yearLevel[ i ].getTotalFailed() );
				s.println( "   Number of students failing: " + 
							yearLevel[ i ].getTotalFailed() );
				System.out.println( "   Ave QPI: " + write2Dec ( yearLevel[ i ].getAveQPI() ) + "\n" );
				s.println( "   Ave QPI: " + write2Dec( yearLevel[ i ].getAveQPI() ) + "\n" );
			}
			s.flush();
			s.close();
		}
		catch ( Exception e ) {}
	}
	
	public static String write2Dec( double d )
	{
		String s = String.valueOf( Double.parseDouble( String.valueOf
								 ( Math.round( d * 100 ) ) ) / 100 );
		if ( s.length() == 4 ) return s;
		else return s.concat( "0" );
    }
}
