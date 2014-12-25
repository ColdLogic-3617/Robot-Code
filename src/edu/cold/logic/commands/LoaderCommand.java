/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.commands;

import edu.cold.logic.OI;
import edu.cold.logic.Var;
import edu.gappleto.common.Log;

/**
 *
 * @author alanhinz
 */
public class LoaderCommand extends CommandBase {
    
    OI oi = new OI();
    
    public LoaderCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (oi.getGB2())
            loader.Dump();
        else if (oi.getGB3())
            loader.Load();
        else
            loader.Stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Log.println("Loader processing interrupted");
    }
}
