package edu.cold.logic.subsystems;

import edu.cold.logic.OI;
import edu.cold.logic.Var;
import edu.cold.logic.commands.ElCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class EjectConveyor extends Subsystem { /*untested and unchecked*/

	private DoubleSolenoid plate = new DoubleSolenoid(0,1);
	OI oi = new OI();
	private Talon conveyor = new Talon(Var.conveyorMotor);
	private Relay spike = new Relay(1);
	
	
	
	/*
	public void conveyorBelt(){
		boolean belt = false;
		conveyorMotor.set(0);
		if(belt == false)	{
			if(oi.getGB1()){
				belt = true;
				conveyorMotor.set(1);
			}
			else if(belt == true){
				if(oi.getGB1()){
					belt = false;
					conveyorMotor.set(0);
				}
			}}
		}

	 */
		public void setConveyor(int direction)	{			
			if (direction != 0)
				conveyor.set(direction/Math.abs(direction));
			else	
				conveyor.set(0);
		}
		
		public void setPlate(boolean up){
			if (up)
				plate.set(Value.kForward);
			else
				plate.set(Value.kReverse);
		}
		
		public void setConveyor(boolean belt)	{
			if (belt)
				spike.set(Relay.Value.kForward);
			else
				spike.set(Relay.Value.kOff);
		}

		@Override
		protected void initDefaultCommand() {
			
			setDefaultCommand(new ElCommand());
		}
			
}