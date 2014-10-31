/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.gappleto.common.LEDManager;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author gappleto97
 */
public class LED extends Subsystem
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    static private final DigitalOutput enabler = new DigitalOutput(RobotMap.enabler),
				team = new DigitalOutput(RobotMap.team),
				fire = new DigitalOutput(RobotMap.fire),
				auto = new DigitalOutput(RobotMap.auto);
    
    static private final DriverStation get = DriverStation.getInstance();
    
    public void setEnabled()
    {
	enabler.set(get.isEnabled());
    }
    
    public void setFire(boolean a)
    {
	fire.set(a);
    }
    
    public void setAuto()
    {
	auto.set(get.isAutonomous());
    }
    
    public void setTeam()
    {
	if (get.getAlliance() == Alliance.kBlue)
	{
	    team.set(false);
	}
	else
	{
	    team.set(true);
	}
    }
    
    static public void setDisabled()
    {
	team.set(false);
    }

    static public void run()
    {
	team.set(get.isEnabled());
    }
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
	setDefaultCommand(new LEDManager());
    }
}
