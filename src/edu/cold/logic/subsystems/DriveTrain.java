/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.cold.logic.Var;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.gappleto.common.Logger;
import edu.cold.logic.OI;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.cold.logic.commands.Driver;

/**
 *
 * @author gappleto97
 */
public class DriveTrain extends Subsystem
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Jaguar  frontLeftMotor = new Jaguar(Var.leftMotor1),
             frontRightMotor = new Jaguar(Var.rightMotor1),
             rearLeftMotor = new Jaguar(Var.leftMotor2),
             rearRightMotor = new Jaguar(Var.rightMotor2);
    private RobotDrive drive;
<<<<<<< HEAD
    private DoubleSolenoid shift=new DoubleSolenoid(Var.highShift,Var.lowShift); 
    private Value high = Value.kForward,
                  low = Value.kReverse;
    Logger log = new Logger();
    OI oi=new OI();
=======
>>>>>>> origin/New-Build
    
    public DriveTrain() {
        drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
        Var.log.println("DriveTrain online");
    }

    public void initDefaultCommand()    {
        // Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new Driver());
    }
    
     /**
     * Moves the system forward at specified percent of voltage
     * @author thayeryates
     * @param voltage	Double—Percentage of desired voltage
    */
    public void GoForward (double voltage)  {
        drive.setLeftRightMotorOutputs(voltage, voltage);
        Var.log.println("Set left & right motor to " + voltage);
    }
    
    /**
     * Moves the system backward at specified percent of voltage
     * @author thayeryates
     * @param voltage	Double—Percentage of desired voltage
    */
    public void GoBackward (double voltage)  {
        drive.setLeftRightMotorOutputs(-voltage, -voltage);
        Var.log.println("Set left & right motor to " + -voltage);
    }
    
     /**
     * Halts the drivetrain
     * @author thayeryates
     * @since Scarab (1.1.1)
     */
    public void Stop()  {
        drive.setLeftRightMotorOutputs(0, 0);
        Var.log.println("Stopped Left and Right Motors");
    }
    /**
     * Interprets Joystick input for motors
     * @author thayeryates
     * @since Scarab (1.0.0)
     */
    public void JoyDrive(){
        drive.arcadeDrive(oi.getDriveX(),oi.getDriveY());
    }

    public void ShiftHigh(){
        shift.set(high);
        log.println("Shift into high gear");
        
    }
    public void ShiftLow(){
        shift.set(low);
        log.println("Shift into low gear");
    }
        
}
