/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.cold.logic.Var;
import edu.wpi.first.wpilibj.Jaguar;
import edu.cold.logic.OI;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.gappleto.common.ActiveTimer;
import edu.cold.logic.commands.luanchercommand;

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
        private DoubleSolenoid upperJaw = new DoubleSolenoid(Var.highJawOn,Var.highJawOff),
                               lowerJaw = new DoubleSolenoid(Var.lowJawOn,Var.lowJawOff); 
    private DoubleSolenoid.Value open = Value.kForward,
                  closed = Value.kReverse;
    static double forward=0.16,
            idle=1,
            backward=1.5;
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new luanchercommand());
    }
    
    public Launcher()
    {
	Var.log.println("Launcher online.");
    }
    
    public void SafeFire()  {
        //This is the most important thing
        Var.log.println("Luanching the ball");
        if (upperJaw.get() == closed || lowerJaw.get() == closed)   {
            Var.log.println("Saftey check failed");
            return;
        }
        if (Var.cripple)    {
            Var.log.println("Robot is crippled");
            forward=forward/8;
            backward=backward/8;
        }
        FlipFire();
        ActiveTimer.delay(forward);
        FlipStop();
        ActiveTimer.delay(idle);
        FlipRetract();
        ActiveTimer.delay(backward);
        FlipStop();
    }
    
    public void upperJawOpen()
    {
       if (upperJaw.get() == closed)
           Var.log.println("JawOpen");
       upperJaw.set(open);
    }
    public void upperJawClosed()
    {
       if (upperJaw.get() == open)
           Var.log.println("JawOpen");
       upperJaw.set(closed);
    }
    public void lowerJawRaised()
    {
       if (lowerJaw.get() == closed)
           Var.log.println("JawOpen");
       lowerJaw.set(open);
    }
    public void lowerJawLowered()
    {
       if (lowerJaw.get() == open)
           Var.log.println("JawOpen");
       lowerJaw.set(closed);
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
}