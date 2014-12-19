/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.interfaces;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.io;

/**
 *
 * @author Gabe
 */
public class SensoryInputBeta extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    InputStream rPi = new InputStream();

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
