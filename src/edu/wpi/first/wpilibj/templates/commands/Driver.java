package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.OI;

/**
 *
 * @author Gabe
 */
public class Driver extends CommandBase
{

    int Func;

    public Driver()
    {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(DriveTrain);
	/*
	 * When ititializing Driver(), always include an integer.
	 * You can see possible values values in the RobotMap.
	 * --Gabe
	 */
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	TimeSet();
	DriveTrain.JoyDrive();
//	if (oi.getDB1() == true)
//	{
//	    DriveTrain.F180(Air.getRatio());
//	}
//	else if (oi.getDB5() == true)
//	{
//	    DriveTrain.F90(RobotMap.TurnLeft, Air.getRatio());
//	}
//	else if (oi.getDB4() == true)
//	{
//	    DriveTrain.F90(RobotMap.TurnRight, Air.getRatio());
//	}
	if (oi.getGB2() == true)
	{
	    Jaws.Dump();
	}
	else if (oi.getGB3() == true)
	{
	    Jaws.Load();
	}
	else if (oi.getGB2() == false && oi.getGB3() == false)
	{
	    Jaws.Stop();
	}
    }

    protected void TimeSet()
    {
	if (Func != RobotMap.DontCare)
	{
	    if (Func == RobotMap.TurnLeft || Func == RobotMap.TurnRight)
	    {
		setTimeout(RobotMap.Time90 * Air.getRatio());
	    }
	    else if (Func == RobotMap.TurnRound)
	    {
		setTimeout(RobotMap.Time180 * Air.getRatio());
	    }
	    else if (Func == RobotMap.TopDump || Func == RobotMap.TopLoad)
	    {
		setTimeout(0.01);
	    }
	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	if (Func == RobotMap.DontCare)
	{
	    return false;
	}
	else
	{
	    return isTimedOut();
	}
    }

    // Called once after isFinished returns true
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}
