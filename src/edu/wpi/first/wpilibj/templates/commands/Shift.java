/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author gappleto97
 */
public class Shift extends CommandBase
{

    int Func;

    public Shift()
    {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Air);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
	Air.CompressorInitialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	if (oi.getDB3() == true)
	{
	    Down();
	}
	else if (oi.getDB2() == true)
	{
	    Up();
	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	return false;
    }

    public void Down()
    {
	Air.ShiftDown();
    }

    public void Up()
    {
	Air.ShiftUp();
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
