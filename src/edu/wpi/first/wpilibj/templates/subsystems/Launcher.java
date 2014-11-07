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
 * Subsystem that controls the launcher/Loader motors
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
    
    /**
     * Sets the Arduino's LEDs to indicate that the robot is enabled
     * @author ThePenultimateOne
     * @return	Boolean—whether the Launcher is moving or not
     * @since Scarab (1.1.2)
    */
    public boolean getLeftMove()
    {
	if (LeftLoader.get() != 0)
	{
	    return true;
	}
	else
	    return false;
    }
     
    /**
     * Sets the Laoder's speed to 100% in specified positive/negative direction
     * @author ThePenultimateOne
     * @param speed	int—set speed to 100% in specified direction
     * @since Scarab (1.1.2)
    */
    public void setSpeed(int speed)
    {
	topMove = true;
	if (Math.abs(speed) > 1)
	    speed = speed / Math.abs(speed);
	TopLoader.set(speed);
	Log.println("Loader set to " + speed * 100 + "%.");
    }

    /**
     * Sets the Laoder's speed to 100% in reverse
     * @author ThePenultimateOne
     * @since Scarab (1.1.1)
    */
    public void Load()
    {
	topMove = true;
	TopLoader.set(-1);
	Log.println("Loader set to -100%.");
    }

    /**
     * Sets the Laoder's speed to 100% forward
     * @author ThePenultimateOne
     * @since Scarab (1.1.1)
    */
    public void Dump()
    {
	topMove = true;
	TopLoader.set(1);
	Log.println("Loader set to 100%.");
    }

    /**
     * Halts the Loader
     * @author ThePenultimateOne
     * @since Scarab (1.1.1)
    */
    public void Stop()
    {
	TopLoader.set(0);
	if (topMove == true)
	{
	    topMove = false;
	    Log.println("Loader stopped.");	    
	}
    }
    
    /**
     * Sets the Launcher's speed to the specified percent of voltage
     * @author ThePenultimateOne
     * @param speed	double—set voltage to specified percent value
     * @since Scarab (1.1.2)
    */
    public void FlipFire(double speed)
    {
        LeftLoader.set(speed);
	Log.println("Flipper fired.");
    }
    
    /**
     * Sets the Launcher's speed to 100% forward
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    public void FlipFire()
    {
        LeftLoader.set(1);
	Log.println("Flipper fired.");
    }
    
    /**
     * Sets the Launcher's speed to 10% backward
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    public void FlipRetract()
    {
        LeftLoader.set(-.1);
	Log.println("Flipper retracting.");
    }
    
    /**
     * Sets the Launcher's speed to 1% backward
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    public void FlipReserve()
    {
	LeftLoader.set(-0.01);
	Log.println("Flipper slowly retracting.");
    }
    
    /**
     * Halts the Launcher
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    public void FlipStop()
    {
        LeftLoader.set(0);
	Log.println("Flipper stopped.");
    }

    /**
     * Returns voltage of Loader
     * @author ThePenultimateOne
     * @return	Double—percent voltage given to Loader
     * @since Scarab (1.1.2)
    */
    public double getTop()
    {
	Log.println("Robot has requested Loader speed.");
	return TopLoader.getSpeed();
    }

    /**
     * Returns voltage of Launcher
     * @author ThePenultimateOne
     * @return	Double—percent voltage given to Launcher
     * @since Scarab (1.1.2)
    */
    public double getLeft()
    {
	Log.println("Robot has requested Launcher speed.");
	return LeftLoader.getSpeed();
    }

    /**
     * Returns voltages of Loader and Launcher
     * @author ThePenultimateOne
     * @return	Double[]—an array of the percent voltages of both Loader and Launcher
     * @since Scarab (1.1.2)
    */
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
