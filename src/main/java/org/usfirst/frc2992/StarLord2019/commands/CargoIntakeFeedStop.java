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
import org.usfirst.frc2992.StarLord2019.Robot;

/**
 *
 */
public class CargoIntakeFeedStop extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public CargoIntakeFeedStop() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
    
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.cargoIntake);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        this.setInterruptible(true);
        Robot.cargoIntake.CargoIntakeFeedWheel(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //run the carriage only if in cargo mode and neither OS btn is pressed
        /*
        if((Robot.isCargoMode ||  Robot.oi.OSBtn.get()) || m_InOut == 0){//(!Robot.oi.OSBtn.get() && !Robot.oi.OSJoyBtn.get())
            Robot.cargoIntake.CargoIntakeFeedWheel(m_InOut);
        }
        */
        Robot.cargoIntake.CargoIntakeFeedWheel(0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //if have a ball in the carriage, stop intaking
        return true;
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
