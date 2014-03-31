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
 * @author Justin
 * @author Gabe
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

    public void CompressorInitialize()
    {
	OurCompressor.start();
	Log.println("Compressor started.");
    }

    public void CompressorShutdown()
    {
	OurCompressor.stop();
	Log.println("Comressor stopped.");
    }

    public void JawUp()
    {
	if (JawUp.get() == false)
	{
	    Log.println("Jaw raised.");
	}
	JawUp.set(true);
	JawDown.set(false);
    }

    public void JawDown()
    {
	if (JawUp.get() == true)
	{
	    Log.println("Jaw lowered.");	    
	}
	JawUp.set(false);
	JawDown.set(true);
    }

    public void JawOpen()
    {
	if (JawOpen.get() == false)
	{
	    Log.println("Jaw opened.");	    
	}
	JawOpen.set(true);
	JawClosed.set(false);
    }

    public void JawClose()
    {
	if (JawOpen.get() == true)
	{
	    Log.println("Jaw closed.");	    
	}
	JawOpen.set(false);
	JawClosed.set(true);
    }

    public void ShiftUp()
    {
	if (LowShift.get() == false)
	{
	    Log.println("Shifted to high gear.");	    
	}
	LowShift.set(true);
	HighShift.set(false);
    }

    public void ShiftDown()
    {
	if (LowShift.get() == true)
	{
	    Log.println("Shifted to low gear.");	    
	}
	LowShift.set(false);
	HighShift.set(true);
    }

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

    public boolean getJawState()
    {
	Log.println("Robot has requested Jaw State.");
	return JawOpen.get();
    }

    public boolean getJawPosition()
    {
	Log.println("Robot has requested Jaw Position.");
	return JawUp.get();
    }

    public boolean getGear()
    {
	Log.println("Robot has requested Gear position.");
	return LowShift.get();
    }

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
