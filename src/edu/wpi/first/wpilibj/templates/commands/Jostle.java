/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author gappleto97
 */
public class Jostle extends CommandBase
{
    double twitch = 0.02;
    int multiple = 10;
    Timer ourTimer = new Timer();
    boolean isFinished = false;
    
    public Jostle()
    {
        // Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Air);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
	isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	Log.println("Starting Jostle.java.");
	Air.JawDown();
	ourTimer.delay(0.5);
	Air.JawUp();
	isFinished = true;
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

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	Log.println("Jostle was inturrupted. Investigate.");
    }
}
