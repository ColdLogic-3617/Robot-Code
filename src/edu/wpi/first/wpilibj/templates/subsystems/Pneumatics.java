/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.commands.*;
import edu.wpi.first.wpilibj.templates.RobotMap;

import edu.gappleto.common.Logger;

/**
 * @author ThePenultimateOne
 */
public class Pneumatics extends Subsystem
{
    
    public Pneumatics()
    {
	Log.println("Pneumatics system online.");
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Compressor OurCompressor = new Compressor(RobotMap.SwitchPort, RobotMap.RelayPort);

    private Solenoid LowShift = new Solenoid(RobotMap.HighShift),
	    HighShift = new Solenoid(RobotMap.LowShift),
	    JawDown = new Solenoid(RobotMap.LowJawOff),
	    JawUp = new Solenoid(RobotMap.LowJawOn),
	    JawOpen = new Solenoid(RobotMap.HighJawOn),
	    JawClosed = new Solenoid(RobotMap.HighJawOff);

    OI oi = new OI();
    
    Logger Log = new Logger();

    /**
     * Activates the compressor
     * @author ThePenultimateOne
     * @since Scarab (1.0.0)
    */
    public void CompressorInitialize()
    {
	OurCompressor.start();
	Log.println("Compressor started.");
    }

    /**
     * Deactivates the compressor
     * @author ThePenultimateOne
     * @since Scarab (1.0.0)
    */
    public void CompressorShutdown()
    {
	OurCompressor.stop();
	Log.println("Comressor stopped.");
    }

    /**
     * Raises the jaw
     * @author ThePenultimateOne
     * @since Scarab (1.0.0)
    */
    public void JawUp()
    {
	if (JawUp.get() == false)
	{
	    Log.println("Jaw raised.");
	}
	JawUp.set(true);
	JawDown.set(false);
    }

    /**
     * Lowers the jaw
     * @author ThePenultimateOne
     * @since Scarab (1.0.0)
    */
    public void JawDown()
    {
	if (JawUp.get() == true)
	{
	    Log.println("Jaw lowered.");	    
	}
	JawUp.set(false);
	JawDown.set(true);
    }

    /**
     * Opens the jaw
     * @author ThePenultimateOne
     * @since Scarab (1.0.0)
    */
    public void JawOpen()
    {
	if (JawOpen.get() == false)
	{
	    Log.println("Jaw opened.");	    
	}
	JawOpen.set(true);
	JawClosed.set(false);
    }

    /**
     * Closes the jaw
     * @author ThePenultimateOne
     * @since Scarab (1.0.0)
    */
    public void JawClose()
    {
	if (JawOpen.get() == true)
	{
	    Log.println("Jaw closed.");	    
	}
	JawOpen.set(false);
	JawClosed.set(true);
    }

    /**
     * Shifts into high gear
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    public void ShiftUp()
    {
	if (LowShift.get() == false)
	{
	    Log.println("Shifted to high gear.");	    
	}
	LowShift.set(true);
	HighShift.set(false);
    }

    /**
     * Shifts into low gear
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    public void ShiftDown()
    {
	if (LowShift.get() == true)
	{
	    Log.println("Shifted to low gear.");	    
	}
	LowShift.set(false);
	HighShift.set(true);
    }

    /**
     * Sets the pneumatics system into initial configuration
     * @author ThePenultimateOne
     * @since Scarab (1.0.0)
    */
    public void InitShift()
    {
	LowShift.set(true);
	HighShift.set(false);
	JawOpen.set(false);
	JawClosed.set(true);
	JawUp.set(true);
	JawDown.set(false);
	Log.println("Set to starting configuration.");
    }

    /**
     * Transitions pneumatics system from autonomous to drive function
     * @author ThePenultimateOne
     * @since Scarab (1.0.0)
    */
    public void fromAuton()
    {
	LowShift.set(false);
	HighShift.set(true);
	JawOpen.set(false);
	JawClosed.set(true);
	JawUp.set(false);
	JawDown.set(true);
	Log.println("Set to driving configuration.");
    }

    /**
     * Returns the open/close state of the jaw
     * @author ThePenultimateOne
     * @return	Boolean—whether the jaw is open
     * @since Scarab (1.1.1)
    */
    public boolean getJawState()
    {
	Log.println("Robot has requested Jaw State.");
	return JawOpen.get();
    }

    /**
     * Returns the high/low state of the jaw
     * @author ThePenultimateOne
     * @return	Boolean—whether the jaw is raised
     * @since Scarab (1.1.1)
    */
    public boolean getJawPosition()
    {
	Log.println("Robot has requested Jaw Position.");
	return JawUp.get();
    }

    /**
     * Returns the gear
     * @author ThePenultimateOne
     * @return	Boolean—whether the jaw is in low gear
     * @since Scarab (1.1.1)
    */
    public boolean getGear()
    {
	Log.println("Robot has requested Gear position.");
	return LowShift.get();
    }

    /**
     * Returns the gear ratio
     * @author ThePenultimateOne
     * @return	Double—the current gear ratio
     * @since Scarab (1.1.1)
    */
    public double getRatio()
    {
	Log.println("Robot has requested Gear Ratio.");
	if (LowShift.get() == true)
	{
	    return 1.0;
	}
	else if (LowShift.get() == false)
	{
	    return 2.65;
	}
	else
	{
	    Log.println("Something went wrong with the ratio fetch.");
	    return 0;
	}
    }

    /**
     * Returns a ratio of the current pneumatics states
     * @author ThePenultimateOne
     * @return	Boolean[]—an array of the current pneumatics states
     * @since Scarab (1.1.1)
    */
    public boolean[] getState()
    {
	boolean[] solenoidState = new boolean[3];
	solenoidState[0] = LowShift.get();
	solenoidState[1] = JawOpen.get();
	solenoidState[2] = JawUp.get();
	return solenoidState;
    }

    // Solenoid state methods here
    // i.e. firingSolenoidExtend()...
    public void initDefaultCommand()
    {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());

	setDefaultCommand(new Shift());
    }
}
