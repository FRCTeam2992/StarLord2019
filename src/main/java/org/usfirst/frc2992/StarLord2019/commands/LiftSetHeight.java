// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2992.StarLord2019.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2992.StarLord2019.Constants;
import org.usfirst.frc2992.StarLord2019.Robot;
import  edu.wpi.first.wpilibj.Timer;
/**
 *
 */
public class LiftSetHeight extends Command {

    private int m_Floor; //Height in inches
    private double m_Timeout;    
    private double m_Speed;

    private boolean isDone = false;  //Are we at our selected position
    private boolean setHeight = false;

    Timer liftTime;
    Timer hatchTimeOut;

    private double height = 0;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public LiftSetHeight(int Floor, double speed, double timeout) {

        m_Floor = Floor;
        m_Timeout = timeout;
        m_Speed = speed;

        liftTime = new Timer();
        hatchTimeOut = new Timer();
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.lift);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        this.setInterruptible(true);
        Robot.lift.moving = true;

        hatchTimeOut.reset();
        hatchTimeOut.start();

        Robot.lift.liftTalon.configClosedLoopPeakOutput(0, m_Speed);

        //up
        if (Robot.isCargoMode){
           height = Robot.lift.ChooseCargoHeight(m_Floor); 
        } else {
            height = Robot.lift.ChooseHatchHeight(m_Floor);
        }

        height = Robot.lift.convertEncoderTicks(height);

        liftTime.reset(); //Reset and start timer for timeout
        liftTime.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if((hatchTimeOut.get() >= Constants.liftTimeout || !Robot.hatchIntake.extendPosn) 
                && !setHeight && !Robot.oi.limitSwitchOverrideBtn.get()){
            Robot.lift.goToHeight(height);  //Move to selected height
            setHeight = true;
        } 

        //Determines if the lift motor is on target
        isDone = Math.abs(height - Robot.lift.liftTalon.getSelectedSensorPosition()) <= 100;

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if(liftTime.get() >= m_Timeout || isDone){
            return true;
        }
        else {
            return false;
        }

    }
    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.lift.holdPosn();
        Robot.lift.moving = false;
        setHeight = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.lift.moving = false;
        setHeight = false;
    }
}
