package edu.cold.logic;

/**
 * The Var is a mapping from the ports sensors and actuators are wired into
 to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Var {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    public static boolean cripple = false;
    
    //*Autonomous Section*
    public static final int high = 1,
                            low = 2,
                            assist = 3;
    
    //*Autonomous Mode*
    public static final int mode = high;
    
    //*Interface Section*
    public static final int enabler = 5,
			    			team = 6,
                            sonic = 7,
                            vision = 8;

    //*Motor section*
    public static final int leftMotor1 = 1,
			    rightMotor1 = 2,
			    leftMotor2 = 3,
			    rightMotor2 = 4,
			    launcher = 5,
			    loader = 0;

    //*Solenoid Section*
    public static final int highShift = 1,
			    lowShift = 2,
			    lowJawOn = 3,
			    lowJawOff = 4,
			    highJawOn = 5,
			    highJawOff = 6;

    //*Compressor Section*
    public static final int switchPort = 1,
			    relayPort = 1;

}
