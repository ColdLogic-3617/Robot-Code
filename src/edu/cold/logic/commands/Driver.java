/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.commands;

import edu.cold.logic.Robot;
import edu.cold.logic.subsystems.DriveTrain;
import edu.gappleto.common.Log;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Thayer
 */
public class Driver extends Command
{
	
	final DriveTrain drive = Robot.drive;
	
	public Driver()
	{
		// Use requires() here to declare subsystem dependencies
		requires(drive);
	}
	
	// Called just before this Command runs the first time
	protected void initialize()
	{
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		drive.JoyDrive();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end()
	{
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		Log.println("Driver was interrupted");
	}
}
