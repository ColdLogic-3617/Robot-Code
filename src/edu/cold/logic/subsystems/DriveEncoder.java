package edu.cold.logic.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class DriveEncoder {
	
	Encoder encoder1,
	encoder = new Encoder(1, 2, true, EncodingType.k4X);
	
	public int grabCount(){
		int count = encoder1.get();
		return count;
	}
	
	public double grabDistance(){
		double distance = encoder1.getDistance();
		return distance;
	}
	
	public void resetEncoder(){
		encoder1.reset();
	}
	
	public double grabRate(){
		double rate = encoder1.getRate();
		return rate;
	}
	
	public boolean isStopped(){
		boolean stopcheck = encoder1.getStopped();
		return stopcheck;
	}
	
}
