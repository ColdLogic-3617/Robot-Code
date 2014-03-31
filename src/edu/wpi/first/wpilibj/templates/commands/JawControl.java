/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author gappleto97
 */
public class JawControl extends CommandBase
{

    public JawControl()
    {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Jaws);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        if (oi.getGB1() == true && Air.getJawState() == true && Air.getJawPosition() == true)
	{
	    Shoot shooter = new Shoot();
	    shooter.initialize();
        }
	if (oi.getGB3() == true)
	{
	    JawLoad();
	    //Console.println("Doing it.");
	}
	else if (oi.getGB2() == true)
	{
	    JawDump();
	}
	else if (oi.getGB3() == false && oi.getGB2() == false)
	{
	    JawStop();
	}
	if (oi.getGB6() == true || oi.getGB11() == true)
	{
	    JawOpen();
	}
	else if (oi.getGB7() == true || oi.getGB10() == true && Jaws.getLeft() == 0)
	{
	    JawClose();
	}
	if (oi.getGB8() == true || oi.getGB9() == true)
	{
            Jostle shake = new Jostle();
	    shake.execute();
	}
	if (oi.getGunY() >= 0.3)
	{
	    JawUp();
	}
	else if (oi.getGunY() <= -0.3)
	{
	    JawDown();
	}
    }

    public void JawDump()
    {
	Jaws.Dump();
    }

    public void JawLoad()
    {
	Jaws.Load();
    }

    public void JawStop()
    {
	Jaws.Stop();
    }
        
    public void FlipFire()
    {
        Jaws.FlipFire();
    }
    
    public void FlipRetract()
    {
        Jaws.FlipRetract();
    }
    
    public void FlipStop()
    {
        Jaws.FlipStop();
    }

    public void JawDown()
    {
	Air.JawDown();
    }

    public void JawUp()
    {
	Air.JawUp();
    }

    public void JawClose()
    {
	Air.JawClose();
    }

    public void JawOpen()
    {
	Air.JawOpen();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	return false;
    }

    // Called once after isFinished returns true
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}
