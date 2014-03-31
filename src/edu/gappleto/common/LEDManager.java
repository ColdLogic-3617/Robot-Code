/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gappleto.common;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author gappleto97
 */
public class LEDManager extends CommandBase 
{

    public LEDManager()
    {
        // Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(LED);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	LED.setEnabled();
	LED.setTeam();
	LED.setAuto();
	LED.setFire(Jaws.getLeftMove());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	return false;
    }

    // Called once after isFinished returns true
    protected void end()
    {
	LED.setDisabled();
	System.out.println("End");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
	LED.setDisabled();
	System.out.println("Interrupted");
    }
}
