/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gappleto.common;

import edu.wpi.first.wpilibj.Timer;
import edu.cold.logic.subsystems.DriveTrain;
import edu.cold.logic.commands.CommandBase;

/**
 * This custom timer will implement the Timer.delay(), provided by WPI, and at
 * the same time update the DriveTrain controls, while still freezing other
 * subsystems.  This will prevent accidental collisions.
 * @author gappleto97
 * @since Bruce (2.3.0)
 */
public class ActiveTimer 
{
    /**
     * This timer should keep the DriveTrain updated, while still delaying other functions.
     * If DriveTrain delay is needed, use WPI's supplied Timer.delay().
     * @author gappleto97
     * @param seconds	Double—the amount of time to delay in seconds
     * @since Bruce (2.3.0)
     */
    public static void delay(double seconds)
    {
		DriveTrain DT = CommandBase.DriveTrain; //currently not defined
		double sensitivity = 0.02;
		while (seconds > 0)
		{
		    DT.JoyDrive(); //currently not named
		    if (seconds >= sensitivity)
		    {
			Timer.delay(sensitivity);
			seconds -= sensitivity;
		    }
		    else
		    {
			Timer.delay(seconds);
			seconds = 0;
		    }
		}
    }
}
