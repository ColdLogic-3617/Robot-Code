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
 * @author Gabe Appleton
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
    
    public void GoForward(double speed)
    {
	Log.println("Setting engines to " + speed * 100 + "%.");
	FrontLeftMotor.set(speed);
	FrontRightMotor.set(speed);
	RearLeftMotor.set(speed);
	RearRightMotor.set(speed);
    }
    
    public void GoForward(double leftspeed, double rightspeed)
    {
	Log.println("Left engines to " + leftspeed * 100 + "%.");
	Log.println("Right engines to " + rightspeed * 100 + "%.");
	FrontLeftMotor.set(leftspeed);
	FrontRightMotor.set(rightspeed);
	RearLeftMotor.set(leftspeed);
	RearRightMotor.set(rightspeed);
    }
    
    public void GoBackward(double leftspeed, double rightspeed)
    {
	Log.println("Left engines to " + -leftspeed * 100 + "%.");
	Log.println("Right engines to " + -rightspeed * 100 + "%.");
	FrontLeftMotor.set(-leftspeed);
	FrontRightMotor.set(-rightspeed);
	RearLeftMotor.set(-leftspeed);
	RearRightMotor.set(-rightspeed);
    }
    
    public void GoBackward(double speed)
    {
	Log.println("Setting engines to " + -speed);
	FrontLeftMotor.set(-speed);
	FrontRightMotor.set(-speed);
	RearLeftMotor.set(-speed);
	RearRightMotor.set(-speed);
    }

    public void Stop()
    {
	Log.println("Stopping all motors.");
	FrontLeftMotor.set(0);
	RearLeftMotor.set(0);
	FrontRightMotor.set(0);
	RearRightMotor.set(0);
    }

    public void GoBackward()
    {Log.println("Setting engines to -0.3");
	FrontLeftMotor.set(-.3);
	RearLeftMotor.set(-.3);
	FrontRightMotor.set(-.3);
	RearRightMotor.set(-.3);
    }

    public void GoForward()
    {
	Log.println("Setting engines to 0.3");
	FrontLeftMotor.set(.3);
	RearLeftMotor.set(.3);
	FrontRightMotor.set(.3);
	RearRightMotor.set(.3);
    }

    public void JoyDrive()
    {
	Drive.arcadeDrive(oi.getDriveX(), oi.getDriveY());
    }

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

    public double getAllLeft()
    {
	Log.println("Robot has requested average Left speed.");
	return (getRearLeft() + getFrontLeft()) / 2;
    }

    public double getAllRight()
    {
	Log.println("Robot has requested average Right speed.");
	return (getRearRight() + getFrontRight()) / 2;
    }

    public double getRearRight()
    {
	Log.println("Robot has requested Rear Right speed.");
	return RearRightMotor.get();
    }

    public double getFrontRight()
    {
	Log.println("Robot has requested Front Right speed.");
	return FrontRightMotor.get();
    }

    public double getRearLeft()
    {
	Log.println("Robot has requested Rear Left speed.");
	return RearLeftMotor.get();
    }

    public double getFrontLeft()
    {
	Log.println("Robot has requested Front Left speed.");
	return FrontLeftMotor.get();
    }

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
