package edu.cold.logic.commands;

import edu.cold.logic.OI;
import edu.cold.logic.Robot;
import edu.cold.logic.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;

public class ElCommand extends Command {
	
	Elevator elVader = Robot.elRaise;
	EjectConveyor conveyor = Robot.conveyor;
	OI oi = Robot.oi;
	
    public ElCommand() {
    	requires(elVader);
    	requires(conveyor);
    }

    protected void initialize() {
        //elVader.elRise();
    }

    protected void execute() {
    	if (oi.getGB1())       // elevator down, then it goes up again
    		elVader.elCycle();
    	else if (oi.getGB6())       //elevator up
    		elVader.elRise();
    	else if (oi.getGB4())  //elevator down
    		elVader.elLower();
		if (oi.getGB7())
			elVader.set(-1);
		else if (oi.getGB8())
			elVader.set(1);
		else
			elVader.set(0);
    	if (oi.getGB11())           //ramp down
    		conveyor.setPlate(true);//possibly inverted...
    	else if (oi.getGB12())      //ramp up
    		conveyor.setPlate(false);//with this.
    	if (oi.getGunY() > 0.3)      //conveyer forward
    		conveyor.setConveyor(1);//also possibly inverted...
    	else if (oi.getGunY() < -0.3)//conveyer back
    		conveyor.setConveyor(-1);//with this.
    	else
    		conveyor.setConveyor(0);//stop when done
    	/*
    	elVader.elRelay();
    	conveyor.testSpike(oi.getDB1());
    	conveyor.setConveyor(oi.getGB1());*/
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        elVader.elStop();
    }

    protected void interrupted() {
        end();
    }
}