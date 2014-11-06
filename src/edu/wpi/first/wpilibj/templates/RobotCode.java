/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.templates.commands.*;
import edu.wpi.first.wpilibj.templates.subsystems.LED;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotCode extends IterativeRobot
{

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
	// instantiate the command used for the autonomous period
	autonomousCommand = new Autonomous();

	// Initialize all subsystems
	CommandBase.init();
	CommandBase.enabled();
    }
    
    public void autonomousInit()
    {
	// schedule the autonomous command (example)
	CommandBase.enabled();
	autonomousCommand.start();
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
	if (autonomousCommand.isRunning() == true)
	{
	    autonomousCommand.cancel();
	    CommandBase.fromAuton();
	}
	else
	{
	    CommandBase.enabled();
	}
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
	LiveWindow.run();
    }
    
    public void disabledPeriodic()
    {
	super.disabledPeriodic();
	LED.setDisabled();
    }
}
