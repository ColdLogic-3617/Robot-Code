/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.commands.autonomous;

import edu.cold.logic.commands.CommandBase;

import edu.gappleto.common.Log;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Gabe
 */
public class HighGoal extends CommandBase {    
    
    public HighGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drive);
        requires(loader);
        requires(flipper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.GoForward(0.5);
	Log.println("Moving");
	Timer.delay(2.1);
	flipper.JawOpen();
	Log.println("Opening jaw.");
	Timer.delay(2);
	drive.Stop();
        flipper.SafeFire();
	drive.GoBackward(0.5);
	Log.println("Moving backwards");
	Timer.delay(2);
	drive.Stop();
	Log.println("Entering drive configuration.");
	flipper.JawLower();
	flipper.JawClose();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        Log.println("High goal autonomous is finished.");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Log.println("High goal autonomous is interrupted.");
    }
}
