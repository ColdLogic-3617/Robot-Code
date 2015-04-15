package edu.cold.logic.subsystems;

import edu.cold.logic.OI;
import edu.cold.logic.Robot;
import edu.cold.logic.Var;
import edu.cold.logic.commands.ElCommand;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends Subsystem	{

	DigitalInput up1 = new DigitalInput(9),
				 down1 = new DigitalInput(8);
				
	OI oi = new OI();
	
	Relay elRelay = new Relay(0);
	
	Talon elVator = new Talon(Var.rightElevator);
	DriveTrain drive = Robot.drive;
	EjectConveyor conveyor = Robot.conveyor;
	
	private boolean update = true, reverse = false;
	
	public Elevator(){
	}
	
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ElCommand());
	}
	
	public void elStop(){
		elVator.set(0);
	}
	
	public void elRise(){		
		while(!up1.get() && !oi.getGB5() && !oi.getDB5() && !reverse){
			elVator.set(1);
			if (oi.getGB5() || oi.getDB5() || oi.getGB7() || oi.getGB8())
				break;
			updateOthers();
			if (oi.getGB5() || oi.getDB5() || oi.getGB7() || oi.getGB8())
				break;
			updateReverse();
			if (!update && down1.get())
				break;
		}		
		while(!down1.get() && !oi.getGB5() && !oi.getDB5() && reverse){
			elVator.set(-1);
			if (oi.getGB5() || oi.getDB5() || oi.getGB7() || oi.getGB8())
				break;
			updateOthers();
			if (oi.getGB5() || oi.getDB5() || oi.getGB7() || oi.getGB8())
				break;
			updateReverse();
			if (!update && up1.get())
				break;
		}
		if (!oi.getGB3())
			update = true;
		elStop();
	}
	
	private void updateReverse()	{
		if (update && oi.getGB3())	{
			reverse = !reverse;
			update = false;
		}
	}
    
	public void elLower(){
		while(!down1.get() && !oi.getGB5() && !oi.getDB5() && !reverse){
			elVator.set(-1);
			if (oi.getGB5() || oi.getDB5() || oi.getGB7() || oi.getGB8())
				break;
			updateOthers();
			if (oi.getGB5() || oi.getDB5() || oi.getGB7() || oi.getGB8())
				break;
			updateReverse();
			if (!update && up1.get())
				break;
		}		
		while(!up1.get() && !oi.getGB5() && !oi.getDB5() && reverse){
			elVator.set(1);
			updateOthers();
			if (oi.getGB5() || oi.getDB5() || oi.getGB7() || oi.getGB8())
				break; //This is for redundency
			updateReverse();
			if (oi.getGB5() || oi.getDB5() || oi.getGB7() || oi.getGB8())
				break;
			if (!update && down1.get())
				break;
		}
		if (!oi.getGB3())
			update = true;
		elStop();
	}
	public void elTestLower()	{
		while(down1.get())
			elRelay.set(Relay.Value.kForward);
		elRelay.set(Relay.Value.kOff);
	
	}
	public void elTestRaise()	{
		while(up1.get())
			elRelay.set(Relay.Value.kReverse);
		elRelay.set(Relay.Value.kOff);
	
	}
	public void elRelay(){
		
		if (oi.getGB4()){
			elRelay.set(Relay.Value.kForward);
		}
		else if (oi.getGB6()){
			elRelay.set(Relay.Value.kReverse);
		}
		else
			elRelay.set(Relay.Value.kOff);

	}
	public void elTestCycle(){
		if (oi.getGB1()){
			elTestLower();
			elTestRaise();
		}
		
	}
	
	public void elCycle()	{
		elLower();
		elRise();
	}
	
	public void set(double voltage)	{
		elVator.set(voltage);
	}
	
	public void updateOthers()	{
		drive.JoyDrive();
		if (oi.getGB7() || oi.getGB8())	{
			if (oi.getGB7())
				elVator.set(1);
			else
				elVator.set(-1);
			return;
		}
		if (oi.getGB5() || oi.getDB5())
			return;
		if (oi.getGunY() >= 0.3)
			conveyor.setConveyor(1);
		else if (oi.getGunY() <= -0.3)
			conveyor.setConveyor(-1);
		else
			conveyor.setConveyor(0);
		if (oi.getGB5() || oi.getDB5())
			return;
		if (oi.getDB11())
			conveyor.setPlate(false);
		else if (oi.getDB12())
			conveyor.setPlate(true);
		if (oi.getGB5() || oi.getDB5())
			return;
    	long time = System.currentTimeMillis();
    	while (System.currentTimeMillis() < time + 20)
    		if (oi.getGB5() || oi.getDB5())
    			return;
	}
}