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
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author thayeryates
 */
public class DriveTrain extends Subsystem
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private CANTalon 	frontLeftMotor = new CANTalon(Var.leftMotor1),  //0
						frontRightMotor = new CANTalon(Var.rightMotor1), //1
						rearLeftMotor = new CANTalon(Var.leftMotor2), //2
						rearRightMotor = new CANTalon(Var.rightMotor2); //3
	private RobotDrive drive;
	private DoubleSolenoid shift = new DoubleSolenoid(Var.highShift, Var.lowShift);
	private Value high = Value.kForward, low = Value.kReverse;
	OI oi = new OI();
	
	public DriveTrain()
	{
		frontLeftMotor.setSafetyEnabled(false);
		rearLeftMotor.setSafetyEnabled(false);
		frontRightMotor.setSafetyEnabled(false);
		rearRightMotor.setSafetyEnabled(false);
		frontLeftMotor.enableBrakeMode(false);
		rearLeftMotor.enableBrakeMode(false);
		frontRightMotor.enableBrakeMode(false);
		rearRightMotor.enableBrakeMode(false);
		drive = new RobotDrive(rearLeftMotor, frontLeftMotor, rearRightMotor, frontRightMotor);
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
		double sensitivity = Math.sqrt((oi.getDriveS() + 1) / 2);
		drive.arcadeDrive(-oi.getDriveY() * sensitivity, -oi.getDriveX() * sensitivity);
		//Function test for sensitivity control: ((oi.getDriveS() + 1 ) / 2)
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

	public void TimerMove(double voltage, double time){
		drive.setLeftRightMotorOutputs(voltage, voltage);
		Timer.delay(time);
		drive.setLeftRightMotorOutputs(0, 0);
	}

	public void TimerMoveLeft(double voltage, double footdistance){
		double d = footdistance * 12 / 39 / 2.26;
		drive.setLeftRightMotorOutputs(voltage, 0);
		Timer.delay(d);
		drive.setLeftRightMotorOutputs(0, 0);
	}

	public void TimerMoveRight(double voltage, double footdistance){
		double d = footdistance * 12 / 39 / 2.26;
		drive.setLeftRightMotorOutputs(0, voltage);
		Timer.delay(d);
		drive.setLeftRightMotorOutputs(0, 0);
	}
	
	public void TimerTurn(double degree, boolean isright){
		
		if (isright)	{
			frontLeftMotor.enableBrakeMode(true);
			rearLeftMotor.enableBrakeMode(true);
			TimerMoveRight(0.5,(23.0/12)*4*Math.PI);
			frontLeftMotor.enableBrakeMode(false);
			rearLeftMotor.enableBrakeMode(false);
		}
		else {
			frontRightMotor.enableBrakeMode(true);
			rearRightMotor.enableBrakeMode(true);
			TimerMoveLeft(0.5,(23.0/12)*4*Math.PI);
			frontRightMotor.enableBrakeMode(false);
			rearRightMotor.enableBrakeMode(false);
		}
	}
	
	public void AutonMove(double footDistance){
		double position = 0.0;
		setEncodertoZero();
		
		while (position * 4 * Math.PI < footDistance){
			position += ((frontLeftMotor.getEncPosition() + frontLeftMotor.getEncPosition())/2);
			setEncodertoZero();
			GoForward(1);
		}
		Stop();
	}
	
	public void AutonMoveLeft(double footDistance){
		double position = 0.0;
		setEncodertoZero();
		
		while (position * 4 * Math.PI < footDistance){
			position += frontLeftMotor.getEncPosition();
			setEncodertoZero();
			frontLeftMotor.set(1);
			rearLeftMotor.set(1);
		}
		Stop();
	}
	
	public void AutonMoveRight(double footDistance){
		double position = 0.0;
		setEncodertoZero();
		
		while (position * 4 * Math.PI < footDistance){
			position += ((frontLeftMotor.getEncPosition() + frontLeftMotor.getEncPosition())/2);
			setEncodertoZero();
			GoForward(1);
		}
		Stop();
	}
	
	public void AutonTurn(double degree, boolean isright){
		
		if (isright)	{
			frontLeftMotor.enableBrakeMode(true);
			rearLeftMotor.enableBrakeMode(true);
			AutonMoveRight((23.0/12)*4*Math.PI);
			frontLeftMotor.enableBrakeMode(false);
			rearLeftMotor.enableBrakeMode(false);
		}
		else {
			frontRightMotor.enableBrakeMode(true);
			rearRightMotor.enableBrakeMode(true);
			AutonMoveLeft((23.0/12)*4*Math.PI);
			frontRightMotor.enableBrakeMode(false);
			rearRightMotor.enableBrakeMode(false);
		}
	}
	
	public void setEncodertoZero(){
		frontLeftMotor.setPosition(0);
		frontRightMotor.setPosition(0);
		rearLeftMotor.setPosition(0);
		rearRightMotor.setPosition(0);
	}

	public void MoveOpposite(double voltage) {
		drive.setLeftRightMotorOutputs(-voltage, voltage);
	}
	
	public void EndRightTurn()	{
		drive.setLeftRightMotorOutputs(0, 0);
		frontLeftMotor.enableBrakeMode(false);
		rearLeftMotor.enableBrakeMode(false);		
	}
}
