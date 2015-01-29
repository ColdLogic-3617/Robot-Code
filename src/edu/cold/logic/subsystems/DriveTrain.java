/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.subsystems;

import edu.cold.logic.OI;
import edu.cold.logic.Var;
import edu.cold.logic.commands.Driver;
import edu.gappleto.common.Log;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author thayeryates
 */
public class DriveTrain extends Subsystem
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Talon frontLeftMotor = new Talon(Var.leftMotor1), frontRightMotor = new Talon(Var.rightMotor1), rearLeftMotor = new Talon(Var.leftMotor2), rearRightMotor = new Talon(Var.rightMotor2);
	private RobotDrive drive;
	private DoubleSolenoid shift = new DoubleSolenoid(Var.highShift, Var.lowShift);
	private Value high = Value.kForward, low = Value.kReverse;
	OI oi = new OI();
	
	public DriveTrain()
	{
		drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		Log.println("DriveTrain online");
	}
	
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Driver());
	}
	
	/**
	 * Moves the system forward at specified percent of voltage
	 * 
	 * @author thayeryates
	 * @param voltage
	 *            Double—Percentage of desired voltage
	 * @since Scarab (1.2.0)
	 */
	public void GoForward(double voltage)
	{
		drive.setLeftRightMotorOutputs(voltage, voltage);
		Log.println("Set DriveTrain to " + voltage + "% voltage");
	}
	
	/**
	 * Moves the system backward at specified percent of voltage
	 * 
	 * @author thayeryates
	 * @param voltage
	 *            Double—Percentage of desired voltage
	 * @since Scarab (1.2.0)
	 */
	public void GoBackward(double voltage)
	{
		drive.setLeftRightMotorOutputs(-voltage, -voltage);
		Log.println("Set DriveTrain to " + -voltage + "% voltage");
	}
	
	/**
	 * Halts the drivetrain
	 * 
	 * @author thayeryates
	 * @since Scarab (1.1.1)
	 */
	public void Stop()
	{
		drive.setLeftRightMotorOutputs(0, 0);
		Log.println("Stopped DriveTrain motors");
	}
	
	/**
	 * Interprets Joystick input for motors
	 * 
	 * @author thayeryates
	 * @since Scarab (1.0.0)
	 */
	public void JoyDrive()
	{
		drive.arcadeDrive(oi.getDriveX(), oi.getDriveY());
	}
	
	/**
	 * Shifts into high gear
	 * 
	 * @author thayeryates
	 * @since Scarab (1.1.2)
	 */
	public void ShiftHigh()
	{
		shift.set(high);
		Log.println("Shifted into high gear");
		
	}
	
	/**
	 * Shifts into high gear
	 * 
	 * @author thayeryates
	 * @since Scarab (1.1.2)
	 */
	public void ShiftLow()
	{
		shift.set(low);
		Log.println("Shifted into low gear");
	}
	
}
