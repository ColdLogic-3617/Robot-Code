/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gappleto.common;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author gappleto97
 */
public class Cons extends Subsystem
{
    public Cons(DriverStationLCD dStation)
    {
	Station = dStation;
    }
    
    private int currentLine = 1;
    
    private DriverStationLCD Station;
    private Line    Line1 = DriverStationLCD.Line.kUser1,
		    Line2 = DriverStationLCD.Line.kUser2,
		    Line3 = DriverStationLCD.Line.kUser3,
		    Line4 = DriverStationLCD.Line.kUser4,
		    Line5 = DriverStationLCD.Line.kUser5,
		    Line6 = DriverStationLCD.Line.kUser6;
    
    Logger Log = new Logger();
    
    public void update()
    {
	Station.updateLCD();
    }
    
    public void clear()
    {
	Log.println("clearing");
	Station.clear();
	Log.println("Updating.");
	Station.updateLCD();
	currentLine = 1;
    }
    
    public void println(String input, int Line)
    {
	Log.println("Determining line");
	Log.println("Line is "+Line);
	if (Line % 6 == 1)
	{
	    Station.println(Line1, 0, input);
	}
	else if (Line % 6 == 2)
	{
	    Station.println(Line2, 0, input);
	}
	else if (Line % 6 == 3)
	{
	    Station.println(Line3, 0, input);
	}
	else if (Line % 6 == 4)
	{
	    Station.println(Line4, 0, input);
	}
	else if (Line % 6 == 5)
	{
	    Station.println(Line5, 0, input);
	}
	else if (Line % 6 == 0)
	{
	    Station.println(Line6, 0, input);
	}
	Log.println("Written");
	currentLine = Line + 1;
	if (currentLine > 6)
	{
	    currentLine = currentLine % 6;
	}
	Log.println("Updating screen");
	Station.updateLCD();
    }
    
    public void println(String input)
    {
	if (currentLine % 6 == 1)
	{
	    Station.println(Line1, 0, input);
	}
	else if (currentLine % 6 == 2)
	{
	    Station.println(Line2, 0, input);
	}
	else if (currentLine % 6 == 3)
	{
	    Station.println(Line3, 0, input);
	}
	else if (currentLine % 6 == 4)
	{
	    Station.println(Line4, 0, input);
	}
	else if (currentLine % 6 == 5)
	{
	    Station.println(Line5, 0, input);
	}
	else if (currentLine % 6 == 0)
	{
	    Station.println(Line6, 0, input);
	}
	currentLine++;
	if (currentLine > 6)
	{
	    currentLine = currentLine % 6;
	}
	Station.updateLCD();	
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
}
