/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.gappleto.common.ActiveTimer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author gappleto97
 */
public class Shoot extends CommandBase 
{
    //0.02 seconds ~ 1 "twitch"
    static double forward = 8 * 0.02,
	    idle = 50 * 0.02, //1 second
	    backward = 75 * 0.02;
    boolean isFinished = false;
    
    public Shoot() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	requires(Jaws);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
	isFinished = false;
	if (RobotMap.Cripple)
	{
	    forward = forward/8;
	    backward = backward/8;
	}
	Jaws.FlipFire();
	ActiveTimer.delay(forward);
	Jaws.FlipStop();
	ActiveTimer.delay(idle);
	Jaws.FlipRetract();
	ActiveTimer.delay(backward);
	Jaws.FlipStop();
	isFinished = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
	//Don't put anything here.
	//It won't do anything.
    }
    
    public boolean done()
    {
	return isFinished;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    }
    
    public double idle()
    {
	return idle;
    }
    
    public double forward()
    {
	return forward;
    }
    
    public double backward()
    {
	return backward;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
	Log.println("The shooting process has been interrupted.");
    }
}
