/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.subsystems;

import edu.cold.logic.Var;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.cold.logic.OI;
/**
 *
 * @author gappleto97
 */
public class Loader extends Subsystem
{
    Jaguar loader = new Jaguar(Var.loader);
    OI oi = new OI();
    private boolean topMove = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
        
    }
    
    public void setSpeed(int speed)
    {
	topMove = true;
	if (Math.abs(speed) > 1)
	    speed = speed / Math.abs(speed);
	loader.set(speed);
	Var.log.println("Loader set to " + speed * 100 + "%.");
    }
     
    public void Load()
    {
	topMove = true;
	loader.set(-1);
	Var.log.println("Loader set to -100%.");
    }
        
    public void Dump()
    {
	topMove = true;
	loader.set(1);
	Var.log.println("Loader set to 100%.");
    }
     
    public void Stop()
    {
	loader.set(0);
	if (topMove == true)
	{
	    topMove = false;
	    Var.log.println("Loader stopped.");	    
	}
    }
}