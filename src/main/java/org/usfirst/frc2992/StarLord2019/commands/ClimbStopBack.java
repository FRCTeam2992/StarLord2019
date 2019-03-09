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
import org.usfirst.frc2992.StarLord2019.Constants;

/**
 *
 */
public class ClimbStopBack extends Command {
    boolean doClimbHold = false;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ClimbStopBack() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.climbBack);

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
        if(Robot.oi.climberCheckBtn.get() && Robot.oi.climbBackHold.get() 
                && !Robot.oi.liftUpBtn.get() && !Robot.oi.liftDownBtn.get()){
                    /*//FOR PRACTICE ROBOT
            if((Robot.climbBack.climbMtr2.get() == 0 && Robot.driveTrain.navx.getRoll() > Constants.climbAngleLimit) 
                    || doClimbHold){
                Robot.climbHoldBack(.4, Constants.climbAngleLimit);
                doClimbHold = true;
            }else{
                Robot.climbBack.ClimbBackUp(.75);
                doClimbHold = false;
            }
            */
            Robot.climbBack.ClimbBackUp(.4); //FOR COMPETITION ROBOT
        }else if(Robot.oi.climberCheckBtn.get() && Robot.oi.liftUpBtn.get()){
            new AutoClimb(Constants.upClimbSpeed);
        } else if(Robot.oi.climberCheckBtn.get() && Robot.oi.liftDownBtn.get()){
            new AutoClimb(Constants.downClimbSpeed);
        }else{
            Robot.climbBack.ClimbStop();
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
