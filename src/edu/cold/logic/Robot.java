package edu.cold.logic;

import edu.cold.logic.commands.AutonomousClass;
import edu.cold.logic.commands.PostAutonomousClass;
import edu.cold.logic.commands.SecondAuton;
import edu.cold.logic.subsystems.*;
import edu.gappleto.common.Log;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	public static final DriveTrain drive = new DriveTrain();
	public static final EjectConveyor conveyor = new EjectConveyor();
	public static final Elevator elRaise = new Elevator();
	public static OI oi;
	Boolean isAuton = false;
	String auton = "none";
	long time = System.currentTimeMillis();
	
	Command autonomousCommand = new AutonomousClass();
	Command secondAuton = new SecondAuton();
	Command postAuton = new PostAutonomousClass();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		oi = new OI();
		// instantiate the command used for the autonomous period
		// autonomousCommand = new Driver();
	}
	
	public void disabledPeriodic()
	{
		if (System.currentTimeMillis() >= time + 20)	{
			time = System.currentTimeMillis();
			if (oi.getGunS() > 0.5)
				auton = "Left";
			else if (oi.getGunS() < -0.5)
				auton = "Right";
			else
				auton = "None";
			Log.println(auton);
		}
		SmartDashboard.putString("Auton selector", auton);
		Scheduler.getInstance().run();
	}
	
	public void autonomousInit()
	{
		isAuton = true;
		//autonMode = (String) autoChooser.getSelected();
		//postMode = (String) postChooser.getSelected();
		// schedule the autonomous command (example)
		if (autonomousCommand != null && auton == "Left")
			autonomousCommand.start();
		else if (secondAuton != null && auton == "Right")
			secondAuton.start();
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		if (secondAuton != null)
			secondAuton.cancel();
		if (isAuton && auton == "Left")	{
			postAuton.start();
			isAuton = false;
		}
		conveyor.setPlate(true);
	}
	
	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit()
	{
		
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{
		if (oi.getGunS() > 0.5)	{
			drive.TimerMove(0.3, 0.75);
			drive.TimerMove(-0.3, 0.75);
		}
		long milis = (long)(1.5 * 1000);
    	long time = System.currentTimeMillis();
    	while (System.currentTimeMillis() < time + milis)
    		conveyor.setConveyor(1);
		time = System.currentTimeMillis();
		while (System.currentTimeMillis() < time + milis)
			conveyor.setConveyor(-1);
		conveyor.setConveyor(0);
		time = System.currentTimeMillis();
		while (System.currentTimeMillis() < time + milis)
			conveyor.setPlate(false);
		time = System.currentTimeMillis();
		while (System.currentTimeMillis() < time + milis)
			conveyor.setPlate(true);
		elRaise.elCycle();
		LiveWindow.run();
	}
}
