package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
    //*Autonomous Section*
    public static final boolean Cripple = false;
    public static final String AutonMode = "high";
    //Configured values are "low" or "high"
    
    public static final double GearRatio = 2.65;
    
    //*LED Section*
    public static final int enabler = 5,
			    team = 6,
			    fire = 9,
			    auto = 10;

    //*Motor section*
    public static final int LeftMotor1 = 1,
			    RightMotor1 = 2,
			    LeftMotor2 = 3,
			    RightMotor2 = 4,
			    LeftLoader = 5,
			    RightLoader = 6,
			    TopLoader = 7;

    //*Solenoid Section*
    public static final int HighShift = 1,
			    LowShift = 2,
			    LowJawOn = 3,
			    LowJawOff = 4,
			    HighJawOn = 5,
			    HighJawOff = 6;

    //*Compressor Section*
    public static final int SwitchPort = 1,
			    RelayPort = 1;

    //*Command reference number section*
    //Shifter
    public static final int ShiftDown = 0,
			    ShiftUp = 1,
			    ShiftRun = 2,
			    JawUp = 3,
			    JawDown = 4,
			    JawOpen = 5,
			    JawClose = 6,
			    stop = 7;
    //QuickTurn
    public static final int TurnLeft = 1,
			    TurnRight = -1,
			    DontCare = 0,
			    TurnRound = 2,
			    TopLoad = 7,
			    TopDump = 8;

    //*TimeOut reference section*
    public static final double	Time180 = 0.6,
				Time90 = Time180 / 2,
				Time45 = Time90 / 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
