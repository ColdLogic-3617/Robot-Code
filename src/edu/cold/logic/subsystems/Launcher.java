/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.cold.logic.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.cold.logic.Var;
import edu.wpi.first.wpilibj.Jaguar;
import edu.cold.logic.OI;

/**
 *
 * @author MaximUltimatum
 */
public class Launcher extends Subsystem
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.


    // Put methods for controlling this subsystem
    private Jaguar LeftLoader = new Jaguar(Var.launcher);
    OI oi = new OI();
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
    
    public Launcher()
    {
	Var.log.println("Launcher online.");
    }
    
    public void SafeFire()  {
        //This is the most important thing
        
    }
    
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
     * Sets the Launcher's speed to the specified percent of voltage
     * @author ThePenultimateOne
     * @param speed	double—set voltage to specified percent value
     * @since Scarab (1.1.2)
    */
    private void FlipFire(double speed)
    {
        LeftLoader.set(speed);
	Var.log.println("Flipper fired.");
    }
    
    /**
     * Sets the Launcher's speed to 100% forward
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    private void FlipFire()
    {
        LeftLoader.set(1);
	Var.log.println("Flipper fired.");
    }
    
    /**
     * Sets the Launcher's speed to 10% backward
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    private void FlipRetract()
    {
        LeftLoader.set(-.1);
	Var.log.println("Flipper retracting.");
    }
    
    /**
     * Sets the Launcher's speed to 1% backward
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    private void FlipReserve()
    {
	LeftLoader.set(-0.01);
	Var.log.println("Flipper slowly retracting.");
    }
    
    /**
     * Halts the Launcher
     * @author ThePenultimateOne
     * @since Scarab (1.1.2)
    */
    public void FlipStop()
    {
        LeftLoader.set(0);
	Var.log.println("Flipper stopped.");
    }

    /**
     * Returns voltage of Launcher
     * @author ThePenultimateOne
     * @return	Double—percent voltage given to Launcher
     * @since Scarab (1.1.2)
    */
    public double getFlip()
    {
	Var.log.println("Robot has requested Launcher speed.");
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
	return State;
    }

}