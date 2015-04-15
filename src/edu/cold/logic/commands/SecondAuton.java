package edu.cold.logic.commands;

import edu.cold.logic.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.cold.logic.subsystems.DriveTrain;

/**
 *
 */
public class SecondAuton extends Command {
	
	DriveTrain drive = Robot.drive;

    public SecondAuton() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timeMove(-0.4,0.75);
    	timeMove(0,0.5);
    	timeMove(0.4,0.6);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
    
    private void timeMove(double voltage, double seconds)	{
    	long milis = (long)(seconds * 1000);
    	long time = System.currentTimeMillis();
    	while (System.currentTimeMillis() < time + milis)	{
    		drive.GoForward(voltage);
    		if (System.currentTimeMillis() - time > 10)
    			try {
    				Thread.sleep(10);
    			} catch (Exception e) {}
    	}
    	drive.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
