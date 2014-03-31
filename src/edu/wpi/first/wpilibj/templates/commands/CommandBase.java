package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.*;
import edu.wpi.first.wpilibj.DriverStationLCD;

import edu.gappleto.common.*;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use
 * CommandBase.exampleSubsystem
 *
 * @author Gabe Appleton (gappleto) & Justin Hanna
 */
public abstract class CommandBase extends Command
{
    public static Cons Console = new Cons(DriverStationLCD.getInstance());
    public static DriveTrain DriveTrain = new DriveTrain();
    public static Launcher Jaws = new Launcher();
    public static Pneumatics Air = new Pneumatics();
    public static LED LED = new LED();
    public static Logger Log = new Logger();
    // Note: NEVER put anything that even POTENTIALLY uses the OI before the OI.
    // It WILL crash the code.
    public static OI oi;
    // Create a single static instance of all of your subsystems

    public static void init()
    {
	// This MUST be here. If the OI creates Commands (which it very likely
	// will), constructing it during the construction of CommandBase (from
	// which commands extend), subsystems are not guaranteed to be
	// yet. Thus, their requires() statements may grab null pointers. Bad
	// news. Don't move it.
	Log.println("CommandBase initializing.");
	oi = new OI();
	Log.println("Operator Interface online.");
	LED.setEnabled();
	LED.setTeam();

	// Show what command your subsystem is running on the SmartDashboard
	SmartDashboard.putData(DriveTrain);
	SmartDashboard.putData(Jaws);
	SmartDashboard.putData(Air);
    }

    public static void fromAuton()
    {
	Air.fromAuton();
    }

    public static void enabled()
    {
	Log.println("Enabled.");
	Air.InitShift();
    }

    public CommandBase(String name)
    {
	super(name);
	Log.println("CommandBase initialized.");
    }

    public CommandBase()
    {
	super();
	Log.println("CommandBase initialized.");
    }
}
