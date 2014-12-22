/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.commands;

//import edu.cold.logic.commands.autonomous.*;
import edu.cold.logic.Var;
import edu.gappleto.common.Log;

/**
 *
 * @author Gabe
 */
public class Autonomous extends CommandBase {
    
    public Autonomous() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drive);
        requires(flipper);
        requires(loader);
        requires(compressor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        compressor.Initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Var.mode == Var.high)
            ;// HighGoal.start();
        else if (Var.mode == Var.low)
            ;//LowGoal.start();
        else
            ;//Assist.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Log.println("Autonomous was interrupted");
    }
}
