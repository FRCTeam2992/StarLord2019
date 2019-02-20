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

/**
 *
 */
public class CargoGroundFeedwheel extends Command {
    private int sensorValue = Robot.lift.liftTalon.getSelectedSensorPosition(0);
    private double m_Power;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public CargoGroundFeedwheel(double power) {
        m_Power = power;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.cargoGroundIntake);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        this.setInterruptible(true);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //make sure the lift is down before intaking
        //if lift down or override lift btn pressed - run cargo
        if(m_Power < 0){
            Robot.cargoGroundIntake.CargoGroundFeed(m_Power);
        } else if ((sensorValue < (Constants.driveEncDist * 0.5) || Robot.oi.limitSwitchOverrideBtn.get())
            && Robot.cargoGroundIntake.groundIntakeSol.get() 
            && !Robot.cargoIntake.cargoDistSensor.get()) { 
                Robot.cargoGroundIntake.CargoGroundFeed(m_Power);
        }else {
            Robot.cargoGroundIntake.CargoGroundFeed(0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
        
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
