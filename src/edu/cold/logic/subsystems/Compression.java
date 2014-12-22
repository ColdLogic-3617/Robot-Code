/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cold.logic.subsystems;

import edu.cold.logic.Var;
import edu.cold.logic.commands.Compressing;

import edu.gappleto.common.Log;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author alanhinz
 */
public class Compression extends Subsystem
{
    Compressor compressor = new Compressor(Var.switchPort, Var.relayPort);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Compression()    {
        Log.println("Compressor Online");
    }

    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new Compressing());
    }
     /**
     * Activates the compressor
     * @author Gabe
     * @since Scarab (1.0.0)
    */
    public void Initialize()
    {
	compressor.start();
        Log.println("Compressor started.");
    }

    /**
     * Deactivates the compressor
     * @author Gabe
     * @since Scarab (1.0.0)
    */
    public void CompressorShutdown()
    {
	compressor.stop();
	Log.println("Comressor stopped.");
    }
}
