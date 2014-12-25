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
public class Assist extends CommandBase {
    
    public Assist() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drive);
        requires(flipper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Timer.delay(7);
        Log.println("Moving forward.");
        drive.GoForward(0.5);
        Timer.delay(2);
        drive.Stop();
        Log.println("Entering drive configuration.");
        flipper.JawClose();
        flipper.JawLower();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        Log.println("Assist mode has ended. I did nothing. Boo hoo.");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Log.println("Assist mode got interrupted. Big whoop. I still did nothing.");
    }
}
