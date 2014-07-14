import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class GradeComputationApplet extends Applet
{
	Student student;
	YearLevel[] year;
	Button newStudentButton, submitStudentButton, showSummaryButton;
	Label idLabel, yearLabel, subjectLabel, gradeLabel, inputLabel;
   	Choice yearChoice, subjectChoice, gradeChoice;
   	TextField id, status;
   	TextArea outputBox;
   	Panel inputPane, statusPane, buttonPane, outputPane, northPane, 
   			centerPane, southPane, main;
   	String subject;
   	String grade;
   	int s;
   
   	final String yearElements[] = { "1", "2", "3", "4", "5" };
   	final String subjectElements[] = { "Ps141", "CS101", "CS161", "Ph103", "Ps21",
   									   "Ma21", "CS154", "Pos100", "th151" };
   	final String gradeElements[] = { "A", "B+", "B", "C+", "C", "D", "F" };
   
   	public void init()
   	{
		year = new YearLevel[ 5 ];
		for ( int x = 0; x < 5; x++ )
		{
			year[ x ] = new YearLevel( x + 1 );
		}
		
   		yearChoice = new Choice();
   	  	for( int x = 0; x < 5; x++ )
   	  	{
   	  		yearChoice.add( yearElements[ x ] );
   		}

		subjectChoice = new Choice();
   	  	for( int x = 0; x < 9; x++ )
   	  	{
   	  		subjectChoice.add( subjectElements[ x ] );
   		}

		gradeChoice = new Choice();
   	  	for( int x = 0; x < 7; x++ )
   	  	{
   	  		gradeChoice.add( gradeElements[ x ] );
   		}
		
		submitStudentButton = new Button( "Submit Record" );
		newStudentButton = new Button( "Enter New Student" );
		showSummaryButton = new Button( "Show Summary" );
		idLabel = new Label( "Student ID" );
		yearLabel = new Label( "Year" );
		subjectLabel = new Label( "Subject" );
		gradeLabel = new Label( "Grade" );
		inputLabel = new Label( "Input" );
		id = new TextField( 8 );
		status = new TextField( 80 );
		outputBox = new TextArea();
		inputPane = new Panel();
		statusPane = new Panel();
		buttonPane = new Panel();
		outputPane = new Panel();
		northPane = new Panel();
		centerPane = new Panel();
		southPane = new Panel(); 
		main = new Panel();
		northPane.setLayout( new BorderLayout() );
		main.setLayout( new BorderLayout() );
		
		inputPane.add( idLabel );
		inputPane.add( id );
		inputPane.add( yearLabel );
		inputPane.add( yearChoice );
		inputPane.add( subjectLabel );
		inputPane.add( subjectChoice );
		inputPane.add( gradeLabel );
		inputPane.add( gradeChoice );
		statusPane.add( inputLabel );		
		statusPane.add( status );
		buttonPane.add( submitStudentButton );
		buttonPane.add( newStudentButton );
		buttonPane.add( showSummaryButton );
		outputPane.add( outputBox );
		
		northPane.add( inputPane, "North" );
		northPane.add( statusPane, "South" );
		centerPane.add( buttonPane );
		southPane.add( outputPane );		
		
		main.add( northPane, "North" );
		main.add( centerPane, "Center" );
		main.add( southPane, "South" );
		add( main );
		
		yearChoice.addItemListener( new ItemListener()
		{
		public void itemStateChanged( ItemEvent i )
			{
				try
				{
					Object theYear = i.getItem();
					s = new Integer( "" + theYear ).intValue();
					status.setText( "student " + id.getText() + ", " + theYear );
					student = new Student ( id.getText(), s );
				}
				catch( Exception e ) {}
			}
		} );
	
		subjectChoice.addItemListener( new ItemListener()
		{
		public void itemStateChanged( ItemEvent i )
			{
				try
				{
					Object theSubject = i.getItem();
					status.setText( status.getText() + "," + theSubject );
					subject = "" + theSubject;
				}
				catch( Exception e ) {}
			}
		});

		gradeChoice.addItemListener( new ItemListener()
		{
		public void itemStateChanged( ItemEvent i )
			{
				try
				{
					Object theGrade = i.getItem();
					status.setText( status.getText() + " " + theGrade );
					grade = "" + theGrade;
					student.addLoad( subject, grade );
				}
				catch( Exception e ) {}
			}
		} );
	}
	
   	public boolean action( Event click, Object o )
   	{
   		if ( click.target == submitStudentButton )
   	  	{
   	  		
   	  		String p;
   	  		if ( student.passed() )
   	  		{
   	  			p = "Passed";
   	  		}
   	  		else 
   	  		{
   	  			p = "Failed";
   	  		}
   	  		
   	  		String out = "Student " + student.getID() + ", " + 
   	  							"QPI: " + student.getQPI() +
   	  							" Required Year Level QPI: " + p;
   	  		outputBox.setText( outputBox.getText() + out + "\n" );

   	  		for ( int m = 0; m < 5; m++ )
   	  		{
   	  			if ( year[ m ].getYearLevel() == s )
   	  			{
   	  				year[ m ].addStudent( student.getQPI(), student.passed() );
   	  			}
   	  		}
      	}
   	  	else if ( click.target == newStudentButton )
   	  	{
   	  		status.setText( "" );
   	  		id.setText( "" );
   	  	}	
   	  	else if ( click.target == showSummaryButton )
   	  	{
   	  		for ( int w = 0; w < 5; w++ )
   	  		{
   	  			outputBox.setText( outputBox.getText() + "\nYear " + year[ w ].getYearLevel() );
   	  			outputBox.setText( outputBox.getText() + "\n   Number of students passing: " + year[ w ].getTotalPassed() );
 	  			outputBox.setText( outputBox.getText() + "\n   Numbert of students failing: " + year[ w ].getTotalFailed() );
	  			outputBox.setText( outputBox.getText() + "\n   Ave QPI: " + year[ w ].getAveQPI() + "\n\n" );
   	  		}
   	  	}	
		return true;
   	}
}