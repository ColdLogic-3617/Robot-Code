package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.templates.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.Timer;

import edu.gappleto.common.Logger;

/**
 * @author ThePenultimateOne
 */
public class DriveTrain extends Subsystem
{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Jaguar  FrontLeftMotor = new Jaguar(RobotMap.LeftMotor1),
		    FrontRightMotor = new Jaguar(RobotMap.RightMotor1),
		    RearLeftMotor = new Jaguar(RobotMap.LeftMotor2),
		    RearRightMotor = new Jaguar(RobotMap.RightMotor2);
    private RobotDrive Drive;
    OI oi = new OI();
    Logger Log = new Logger();

    public DriveTrain()
    {
	Drive = new RobotDrive(FrontLeftMotor, RearLeftMotor, FrontRightMotor, RearRightMotor);
	Drive.setSafetyEnabled(false);
	Log.println("DriveTrain online.");
    }

    public void initDefaultCommand()
    {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
	setDefaultCommand(new Driver());
    }
    
    /**
     * Moves the system forward at specified percent of voltage
     * @author ThePenultimateOne
     * @param speed	Double—Percentage of desired voltage
    */
    public void GoForward(double speed)
    {
	Log.println("Setting engines to " + speed * 100 + "%.");
	FrontLeftMotor.set(speed);
	FrontRightMotor.set(speed);
	RearLeftMotor.set(speed);
	RearRightMotor.set(speed);
    }
    
    /**
     * Moves the system forward at specified percent of voltage for each side
     * @author ThePenultimateOne
     * @param leftspeed	Double—Percentage of desired voltage for left side
     * @param righspeed	Double—Percentage of desired voltage for right side
    */
    public void GoForward(double leftspeed, double rightspeed)
    {
	Log.println("Left engines to " + leftspeed * 100 + "%.");
	Log.println("Right engines to " + rightspeed * 100 + "%.");
	FrontLeftMotor.set(leftspeed);
	FrontRightMotor.set(rightspeed);
	RearLeftMotor.set(leftspeed);
	RearRightMotor.set(rightspeed);
    }
    
    /**
     * Moves the system backward at specified percent of voltage for each side
     * @author ThePenultimateOne
     * @param leftspeed	Double—Percentage of desired voltage for left side
     * @param righspeed	Double—Percentage of desired voltage for right side
    */
    public void GoBackward(double leftspeed, double rightspeed)
    {
	Log.println("Left engines to " + -leftspeed * 100 + "%.");
	Log.println("Right engines to " + -rightspeed * 100 + "%.");
	FrontLeftMotor.set(-leftspeed);
	FrontRightMotor.set(-rightspeed);
	RearLeftMotor.set(-leftspeed);
	RearRightMotor.set(-rightspeed);
    }
    
    /**
     * Moves the system backward at specified percent of voltage
     * @author ThePenultimateOne
     * @param speed	Double—Percentage of desired voltage
    */
    public void GoBackward(double speed)
    {
	Log.println("Setting engines to " + -speed);
	FrontLeftMotor.set(-speed);
	FrontRightMotor.set(-speed);
	RearLeftMotor.set(-speed);
	RearRightMotor.set(-speed);
    }
    
     /**
     * Halts the drivetrain
     * @author ThePenultimateOne
     * @since Scarab (1.1.1)
     */
    public void Stop()
    {
	Log.println("Stopping all motors.");
	FrontLeftMotor.set(0);
	RearLeftMotor.set(0);
	FrontRightMotor.set(0);
	RearRightMotor.set(0);
    }

     /**
     * Sets all motors to 30% reverse speed
     * @author ThePenultimateOne
     * @since Scarab (1.1.1)
     */
    public void GoBackward()
    {Log.println("Setting engines to -0.3");
	FrontLeftMotor.set(-.3);
	RearLeftMotor.set(-.3);
	FrontRightMotor.set(-.3);
	RearRightMotor.set(-.3);
    }

     /**
     * Sets all motors to 30% speed
     * @author ThePenultimateOne
     * @since Scarab (1.1.1)
     */
    public void GoForward()
    {
	Log.println("Setting engines to 0.3");
	FrontLeftMotor.set(.3);
	RearLeftMotor.set(.3);
	FrontRightMotor.set(.3);
	RearRightMotor.set(.3);
    }

     /**
     * Drives based on the Operator Input from the joystick
     * @author ThePenultimateOne
     * @since Scarab (1.0.0)
     */
    public void JoyDrive()
    {
	Drive.arcadeDrive(oi.getDriveX(), oi.getDriveY());
    }
    
    /**
     * Turns the system 180 degrees in specified amount of time
     * @author ThePenultimateOne
     * @param time	int—time desired to turn in
     * @deprecated
    */
    public void F180(int time)
    {
	Log.println("Turning around for " + time + " seconds at 100% speed");
	Timer timer = new Timer();
	RearLeftMotor.set(-1);
	FrontLeftMotor.set(-1);
	RearRightMotor.set(1);
	FrontRightMotor.set(1);
	timer.delay(time/1000);
	Stop();
    }
	
    /**
     * Turns the system 180 degrees at specified gear ratio
     * @author ThePenultimateOne
     * @param time	Double—Current gear ratio
     * @deprecated
    */
    public void F180(double ratio)
    {
	Log.println("Turning around for " + RobotMap.Time180 * ratio + 
			    " seconds at 100% speed");
	Timer timer = new Timer();
	RearLeftMotor.set(-1);
	FrontLeftMotor.set(-1);
	RearRightMotor.set(1);
	FrontRightMotor.set(1);
	timer.delay(RobotMap.Time180 * ratio);
	Stop();
    }

    /**
     * Turns the system 180 degrees at specified percent of voltage, in specified ratio
     * @author ThePenultimateOne
     * @param ratio	Double—Current gear ratio
     * @param time	Double—Desired percent of voltage
     * @deprecated
    */
    public void F180(double ratio, double speed)
    {
	Log.println("Turning around for " + RobotMap.Time180 * ratio + 
			    " seconds at " + speed * 100 + "% speed");
	Timer timer = new Timer();
	RearLeftMotor.set(-speed);
	FrontLeftMotor.set(-speed);
	RearRightMotor.set(speed);
	FrontRightMotor.set(speed);
	timer.delay(RobotMap.Time180 * ratio / speed);
	Stop();
    }

    /**
     * Turns the system 180 degrees in specified amount of time
     * @author ThePenultimateOne
     * @param direction	int—desired direction to turn
     * @param ratio	Double—Current gear ratio
     * @deprecated
    */
    public void F90(int direction, double ratio)
    {
	String dir;
	if (direction >= RobotMap.TurnLeft)
	{
	    dir = "left";
	}
	else
	{
	    dir = "right";
	}
	Log.println("Turning " + dir + " for " + RobotMap.Time90 * ratio
			    + " seconds at 100% speed");
	Timer timer = new Timer();
	if (direction >= RobotMap.TurnLeft)
	{
	    RearLeftMotor.set(-1);
	    FrontLeftMotor.set(-1);
	    RearRightMotor.set(1);
	    FrontRightMotor.set(1);
	}
	else
	{
	    FrontLeftMotor.set(1);
	    RearLeftMotor.set(1);
	    FrontRightMotor.set(-1);
	    RearRightMotor.set(-1);
	}
	timer.delay(RobotMap.Time90 * ratio);
	Stop();
    }


    /**
     * Grabs average speed of left motors
     * @author ThePenultimateOne
     * @returns	the average volt percentage of the left side motors
     * @since Scarab (1.1.1)
    */
    public double getAllLeft()
    {
	Log.println("Robot has requested average Left speed.");
	return (getRearLeft() + getFrontLeft()) / 2;
    }

    /**
     * Grabs average speed of right motors
     * @author ThePenultimateOne
     * @returns	the average volt percentage of the right side motors
     * @since Scarab (1.1.1)
    */
    public double getAllRight()
    {
	Log.println("Robot has requested average Right speed.");
	return (getRearRight() + getFrontRight()) / 2;
    }

    /**
     * Grabs speed of the rear right motor
     * @author ThePenultimateOne
     * @returns	the volt percentage of the rear right motor
     * @since Scarab (1.1.1)
    */
    public double getRearRight()
    {
	Log.println("Robot has requested Rear Right speed.");
	return RearRightMotor.get();
    }

    /**
     * Grabs speed of the front right motor
     * @author ThePenultimateOne
     * @returns	the volt percentage of the front right motor
     * @since Scarab (1.1.1)
    */
    public double getFrontRight()
    {
	Log.println("Robot has requested Front Right speed.");
	return FrontRightMotor.get();
    }

    /**
     * Grabs speed of the rear left motor
     * @author ThePenultimateOne
     * @returns	the volt percentage of the rear left motor
     * @since Scarab (1.1.1)
    */
    public double getRearLeft()
    {
	Log.println("Robot has requested Rear Left speed.");
	return RearLeftMotor.get();
    }

    /**
     * Grabs speed of the front left motor
     * @author ThePenultimateOne
     * @returns	the volt percentage of the front left motor
     * @since Scarab (1.1.1)
    */
    public double getFrontLeft()
    {
	Log.println("Robot has requested Front Left speed.");
	return FrontLeftMotor.get();
    }

    /**
     * Grabs speeds of all drivetrain motors, and returns it as an array
     * @author ThePenultimateOne
     * @returns	an array of the percent voltages for all motors
     * @since Scarab (1.1.1)
    */
    public double[] getState()
    {
	double[] motorStates = new double[4];
	motorStates[0] = FrontLeftMotor.getSpeed();
	motorStates[1] = FrontRightMotor.getSpeed();
	motorStates[2] = RearLeftMotor.getSpeed();
	motorStates[3] = RearRightMotor.getSpeed();
	return motorStates;
    }
}
