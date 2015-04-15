package edu.cold.logic.commands;

import edu.cold.logic.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.cold.logic.subsystems.DriveTrain;
import edu.gappleto.common.Log;

/**
 *
 */
public class AutonomousClass extends Command {

	DriverStation ds = DriverStation.getInstance();
	DriveTrain drive = Robot.drive;
	Boolean done = false;
    public AutonomousClass() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	    	//if(autonMode.getSelected() == "Left"){
    			timeMove(0.4,2.75);
    			//180 degree turn
    	    	long milis = (long)(3 * 1000);
    	    	long time = System.currentTimeMillis();
    	    	while (System.currentTimeMillis() < time + milis)	{
    	    		drive.MoveOpposite(0.35);
    	    		if (System.currentTimeMillis() - time > 10)
    	    			try {
    	    				Thread.sleep(10);
    	    			} catch (Exception e) {}
    	    	}
    	    	drive.Stop();
    			Log.println("done");
	    	/*}
	    	else	{
	    		drive.TimerMove(0.5, 6.102362); //verify
	    		drive.TimerTurn(90,false);
		    	if(autonMode.getSelected() == "Right"){
		    		drive.TimerMove(0.5, 7.414698);
		    	}
		    	else{
		    		drive.TimerMoveRight(0.5, 6.102362);
		    	}
		    	drive.TimerTurn(90, true);
		    	drive.TimerMove(0.5,6.102362-5.019685); //verify
	    	}*/
	    	//drive.TimerTurn(180, (autonMode.getSelected() == "Left"));
    	done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
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
