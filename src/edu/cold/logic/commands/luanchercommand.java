/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.commands;

/**
 *
 * @author Thayer
 */
public class luanchercommand extends CommandBase {
    
    public luanchercommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(flipper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (oi.getGB1())
            flipper.SafeFire();
        if (oi.getGunY() >= 0.3)
            flipper.lowerJawRaised();
        if (oi.getGunY() <= -0.3)
            flipper.lowerJawLowered();
        if (oi.getGB6() || oi.getGB11())
            flipper.upperJawOpen();
        if (oi.getGB7() || oi.getGB10())
            flipper.upperJawClosed();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
