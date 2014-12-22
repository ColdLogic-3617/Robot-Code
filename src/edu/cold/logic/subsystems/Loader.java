/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.subsystems;

import edu.cold.logic.commands.LoaderCommand;
import edu.cold.logic.OI;
import edu.cold.logic.Var;
import edu.gappleto.common.Logger;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
/**
 *
 * @author alanhinz
 */
public class Loader extends Subsystem
{
    Jaguar loader = new Jaguar(Var.loader);
    OI oi = new OI();
    private boolean moving = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Loader() {
        Logger.printlnBeta("Loader Online");
    }

    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new LoaderCommand());
    }
    
    /**
     * sets speed of loader
     * @author alanhinz
     * @param speed     double—desired % of voltage
     * @since Scarab (1.1.2)
     */
    public void setSpeed(double speed)
    {
	moving = true;
	if (Math.abs(speed) > 1)
	    speed = speed / Math.abs(speed);
	loader.set(speed);
	Var.log.println("Loader set to " + speed * 100 + "%.");
    }
     /**
      * makes loader intake item
      * @author alanhinz
      * @since Scarab (1.1.1)
      */
    public void Load()
    {
	moving = true;
	loader.set(-1);
	Var.log.println("Loader set to -100%.");
    }
    /**
     * makes loader dump item
     * @author alanhinz
     * @since Scarab (1.1.1)
     */    
    public void Dump()
    {
	moving = true;
	loader.set(1);
	Var.log.println("Loader set to 100%.");
    }
    /**
     * stops loader
     * @author alanhinz
     * @since Scarab (1.1.1)
     */
    public void Stop()
    {
	loader.set(0);
	if (moving)
	{
	    moving = false;
	    Var.log.println("Loader stopped.");	    
	}
    }
}
