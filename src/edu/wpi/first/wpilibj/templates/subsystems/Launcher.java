/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.templates.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.templates.OI;

import edu.gappleto.common.Logger;

/**
 *
 * @author gappleto97
 */
public class Launcher extends Subsystem
{
    public Launcher()
    {
	Log.println("Launcher online.");
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Jaguar LeftLoader = new Jaguar(RobotMap.LeftLoader),
		    TopLoader = new Jaguar(RobotMap.TopLoader);
    OI oi = new OI();
    Logger Log = new Logger();
    private boolean topMove = false;
    
    public boolean getLeftMove()
    {
	if (LeftLoader.get() != 0)
	{
	    return true;
	}
	else
	    return false;
    }
    
    public void setSpeed(int speed)
    {
	topMove = true;
	TopLoader.set(speed);
	Log.println("Loader set to " + speed * 100 + "%.");
    }

    public void Load()
    {
	topMove = true;
	TopLoader.set(-1);
	Log.println("Loader set to -100%.");
    }

    public void Dump()
    {
	topMove = true;
	TopLoader.set(1);
	Log.println("Loader set to 100%.");
    }

    public void Stop()
    {
	TopLoader.set(0);
	if (topMove == true)
	{
	    topMove = false;
	    Log.println("Loader stopped.");	    
	}
    }
    
    public void FlipFire(double speed)
    {
        LeftLoader.set(speed);
	Log.println("Flipper fired.");
    }
    
    public void FlipFire()
    {
        LeftLoader.set(1);
	Log.println("Flipper fired.");
    }
    
    public void FlipRetract()
    {
        LeftLoader.set(-.1);
	Log.println("Flipper retracting.");
    }
    
    public void FlipReserve()
    {
	LeftLoader.set(-0.01);
	Log.println("Flipper slowly retracting.");
    }
    
    public void FlipStop()
    {
        LeftLoader.set(0);
	Log.println("Flipper stopped.");
    }

    public double getTop()
    {
	Log.println("Robot has requested Loader speed.");
	return TopLoader.getSpeed();
    }

    public double getLeft()
    {
	Log.println("Robot has requested Launcher speed.");
	return LeftLoader.getSpeed();
    }

    public double[] getState()
    {
	double[] State = new double[2];
	State[0] = LeftLoader.getSpeed();
	State[1] = TopLoader.getSpeed();
	return State;
    }

    public void initDefaultCommand()
    {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
	setDefaultCommand(new JawControl());
    }
}
