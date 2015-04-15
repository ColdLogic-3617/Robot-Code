
package edu.cold.logic;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{

    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    /**
     * From this point forward DriveStick shall refer to the Driver's joystick,
     * and GunStick shall refer to the Button Monkey's joystick. This is not
     * necessarily final terminology, but for the point of this coding, it will
     * work. --Gabe
     * PS, yes I am aware that these names break convention, but unless I am
     * told otherwise, I will assume that acronyms are exempt.*/
	
    private final Joystick DriveStick = new Joystick(0); //X & Y is drive control
    private final Button DB1 = new JoystickButton(DriveStick, 1),
	    DB2 = new JoystickButton(DriveStick, 2), 
	    DB3 = new JoystickButton(DriveStick, 3), 
	    DB4 = new JoystickButton(DriveStick, 4), 
	    DB5 = new JoystickButton(DriveStick, 5), 
	    DB6 = new JoystickButton(DriveStick, 6),
	    DB7 = new JoystickButton(DriveStick, 7),
	    DB8 = new JoystickButton(DriveStick, 8),
	    DB9 = new JoystickButton(DriveStick, 9),
	    DB10 = new JoystickButton(DriveStick, 10),
	    DB11 = new JoystickButton(DriveStick, 11),
	    DB12 = new JoystickButton(DriveStick, 12);
    
    private final Joystick GunStick = new Joystick(1); //conveyer belt
    private final Button GB1 = new JoystickButton(GunStick, 1), //cycle elevator
	    GB2 = new JoystickButton(GunStick, 2), 
	    GB3 = new JoystickButton(GunStick, 3), 
	    GB4 = new JoystickButton(GunStick, 4), //elevator down
	    GB5 = new JoystickButton(GunStick, 5), //elevator cancel
	    GB6 = new JoystickButton(GunStick, 6), //elevator up 
	    GB7 = new JoystickButton(GunStick, 7), 
	    GB8 = new JoystickButton(GunStick, 8),
	    GB9 = new JoystickButton(GunStick, 9),
	    GB10 = new JoystickButton(GunStick, 10), 
	    GB11 = new JoystickButton(GunStick, 11), //raise ramp
    	GB12 = new JoystickButton(GunStick, 12); //lower ramp
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    /**
     * Returns the value of the Driver's Throttle/Sensitivity
     * @author Gabe
     * @return Double—Percent tilted in X-axis
     * @since Project Echo (3.0.3)
     */
    public double getDriveS()	{
    	return -DriveStick.getThrottle();
    }
    
 /**
 * Returns the value of the Driver's x-axis
 * @author Gabe
 * @return Double—Percent tilted in X-axis
 * @since Scarab (1.0.0)
 */
    public double getDriveX()
    {    	
	return DriveStick.getX(); 
    }

/**
 * Returns the value of the Driver's y-axis
 * @author Gabe
 * @return Double—Percent tilted in Y-axis
 * @since Scarab (1.0.0)
 */
    public double getDriveY()
    {
	return DriveStick.getY();
    }

/**
 * Returns the value of the Driver's z-axis
 * @author Gabe
 * @return Double—Percent tilted in Z-axis
 * @since Scarab (1.2.0)
 */
    public double getDriveZ()
    {
	return DriveStick.getZ();
    }
    
    /**
     * Returns the value of the Button Monkey's Throttle/Sensitivity
     * @author Gabe
     * @return Double—Percent tilted in X-axis
     * @since Project Echo (3.0.3)
     */
    public double getGunS()	{
    	return -GunStick.getThrottle();
    }

/**
 * Returns the value of the Button Monkey's x-axis
 * @author Gabe
 * @return Double—Percent tilted in X-axis
 * @since Scarab (1.2.0)
 */
    public double getGunX()
    {
	return GunStick.getX();
    }

/**
 * Returns the value of the Button Monkey's y-axis
 * @author Gabe
 * @return Double—Percent tilted in Y-axis
 * @since Scarab (1.2.0)
 */
    public double getGunY()
    {
	return GunStick.getY();
    }

/**
 * Returns the value of the Button Monkey's z-axis
 * @author Gabe
 * @return Double—Percent tilted in Z-axis
 * @since Scarab (1.2.0)
 */
    public double getGunZ()
    {
	return GunStick.getZ();
    }

/**
 * Returns the value of the Button Monkey's button one
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.1.7)
 */
    public boolean getGB1()
    {
	return GB1.get();
    }

/**
 * Returns the value of the Button Monkey's button two
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.1.7)
 */
    public boolean getGB2()
    {
	return GB2.get();
    }
    
/**
 * Returns the value of the Button Monkey's button three
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.1.7)
 */
    public boolean getGB3()
    {
	return GB3.get();
    }

/**
 * Returns the value of the Button Monkey's button four
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getGB4()
    {
	return GB4.get();
    }

/**
 * Returns the value of the Button Monkey's button five
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getGB5()
    {
	return GB5.get();
    }

/**
 * Returns the value of the Button Monkey's button six
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getGB6()
    {
	return GB6.get();
    }

/**
 * Returns the value of the Button Monkey's button seven
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getGB7()
    {
	return GB7.get();
    }

/**
 * Returns the value of the Button Monkey's button eight
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getGB8()
    {
	return GB8.get();
    }

/**
 * Returns the value of the Button Monkey's button nine
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getGB9()
    {
	return GB9.get();
    }

/**
 * Returns the value of the Button Monkey's button ten
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getGB10()
    {
	return GB10.get();
    }

/**
 * Returns the value of the Button Monkey's button eleven
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getGB11()
    {
	return GB11.get();
    }
    
    /**
     * Returns the value of the Button Monkey's button twelve
     * @author Gabe
     * @return Boolean—The value of the referenced button
     * @since Scarab (1.2.0)
     */
        public boolean getGB12()
        {
    	return GB12.get();
        }

/**
 * Returns the value of the Driver's button one
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB1()
    {
	return DB1.get();
    }
/**
 * Returns the value of the Driver's button two
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB2()
    {
	return DB2.get();
    }

/**
 * Returns the value of the Driver's button three
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB3()
    {
	return DB3.get();
    }

/**
 * Returns the value of the Driver's button four
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB4()
    {
	return DB4.get();
    }

/**
 * Returns the value of the Driver's button five
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB5()
    {
	return DB5.get();
    }

/**
 * Returns the value of the Driver's button six
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB6()
    {
	return DB6.get();
    }

/**
 * Returns the value of the Driver's button seven
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB7()
    {
	return DB7.get();
    }

/**
 * Returns the value of the Driver's button eight
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB8()
    {
	return DB8.get();
    }

/**
 * Returns the value of the Driver's button nine
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB9()
    {
	return DB9.get();
    }

/**
 * Returns the value of the Driver's button ten
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB10()
    {
	return DB10.get();
    }

/**
 * Returns the value of the Driver's button eleven
 * @author Gabe
 * @return Boolean—The value of the referenced button
 * @since Scarab (1.2.0)
 */
    public boolean getDB11()
    {
	return DB11.get();
    }
    
    /**
     * Returns the value of the Driver's button twelve
     * @author Gabe
     * @return Boolean—The value of the referenced button
     * @since Scarab (1.2.0)
     */
        public boolean getDB12()
        {
    	return DB12.get();
        }
}
