/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 * This is team 3617's autonomous program, for the Aerial Assist game.
 * @author Gabe
 */
public class Autonomous extends CommandBase
{
    Timer OurTimer = new Timer();
    boolean Finished = false;
    double forward, backward, idle;

    public Autonomous()
    {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(DriveTrain);
	requires(Air);
	requires(Jaws);
	Air.InitShift();
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
	Log.println("Autonomous mode initialized.");
	Log.println("");
	forward = Shoot.forward;
	backward = Shoot.backward;
	idle = Shoot.idle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
	if (RobotMap.AutonMode == "low" || RobotMap.AutonMode == "Low")
	{
	    lowGoal();	    
	}
	else if (RobotMap.AutonMode == "high" || RobotMap.AutonMode == "High")
	{
	    highGoal();
	}
	else if (RobotMap.AutonMode == "assist" || RobotMap.AutonMode == "Assist")
	{
	    assist();
	}
    }
    /**
    * This is the autonomous function for assisting other robots.
    **/
    
    private void assist()
    {
	
    }
    
    private void highGoal()
    {
	DriveTrain.GoForward(0.5);
	Log.println("Moving");
	OurTimer.delay(2.1);
	Air.JawOpen();
	Log.println("Opening jaw.");
	OurTimer.delay(2);
	Log.println("Deciding if robot is hobbled.");
	DriveTrain.Stop();if (RobotMap.Cripple)
	{
	    Log.println("Robot is hobbled.");
	    forward = forward/8;
	    backward = backward/8;
	}
	Log.println("Firing.");
	Jaws.FlipFire();
	OurTimer.delay(forward);
	Log.println("Pausing flipper.");
	Jaws.FlipStop();
	OurTimer.delay(idle);
	Log.println("Retracting flipper.");
	Jaws.FlipRetract();
	OurTimer.delay(backward);
	Log.println("Stopping flipper.");
	Jaws.FlipStop();
	DriveTrain.GoBackward(0.5);
	Log.println("Moving backwards");
	OurTimer.delay(2);
	DriveTrain.Stop();
	if (Air.getGear() ==  false)
	{
	    DriveTrain.F180(RobotMap.GearRatio, 0.5);
	}
	else
	{
	    DriveTrain.F180(1,0.5);
	}
	Log.println("Entering drive configuration.");
	Air.JawDown();
	Air.JawClose();
	Finished = true;
    }
    
    private void lowGoal()
    {
	DriveTrain.GoForward(0.5);
	Log.println("Moving");
	OurTimer.delay(3);
	Jaws.setSpeed(1);
	Log.println("Dumping");
	OurTimer.delay(1);
	DriveTrain.Stop();
	Log.println("Stopped");
	OurTimer.delay(1);
	Air.JawDown();
	Log.println("Lowering");
	OurTimer.delay(0.7);
	Air.JawUp();
	Log.println("Raising Jaw");
	Jaws.Stop();
	Log.println("Stopping Loader");
	OurTimer.delay(0.3);
	DriveTrain.GoBackward(0.5);
	Log.println("Moving backwards");
	OurTimer.delay(2);
	DriveTrain.Stop();
	if (Air.getGear() ==  false)
	{
	    DriveTrain.F180(RobotMap.GearRatio, 0.5);
	}
	else
	{
	    DriveTrain.F180(1,0.5);
	}
	Log.println("Entering drive configuration.");
	Air.JawDown();
	Air.JawClose();
	Finished = true;	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	return Finished;
    }

    // Called once after isFinished returns true
    protected void end()
    {
	Log.println("Autonomous finished.");
	Log.println("");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
	Log.println("Command has been interrupted.");
    }
}
