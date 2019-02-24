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
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionProcessing extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx"); //target x value 
    NetworkTableEntry ty = table.getEntry("ty"); //target y value
    NetworkTableEntry ta = table.getEntry("ta"); //target area
    double x = 0;
    double dist = 0;
    private double camHt = Constants.camHt;
    private double tarHt = Constants.tarHt;
    private double camAngle = Constants.camAngle;

    Command autoCmd;
    boolean isAutoSet = false;
    //private int newDist = 0;// Needs int value for AutoDriveFwd


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public VisionProcessing() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

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
        if(!Robot.isCargoMode){//no Limelight on Cargo side, make sure no VP running
            //values from limelight
            x = tx.getDouble(0.0); //needs to be global
            double y = ty.getDouble(0.0);
            double area = ta.getDouble(0.0);

            if(x != 0){
                new lights(.15);
            }

            SmartDashboard.putNumber("LimeLightX", x);
            SmartDashboard.putNumber("LimeLightY", y);
            SmartDashboard.putNumber("LimeLightArea", area);

            if(Math.abs(x) > 1.5 && !isAutoSet){// if angle b/w middle of pic and bot, turn towards target
                autoCmd = new AutoDriveTurn(-x, 0.5, 3);
                isAutoSet = true;
            } else if(Math.abs(x) <= 1.5 && !isAutoSet){//if angle is close within 1.5deg, just drive towards it
                dist = Robot.driveTrain.getDist(camHt, tarHt, camAngle, y);
                //newDist = (int) Math.round(dist);
                dist = dist * (Math.sin(camAngle)); //Pyth. Theorem to find Horiz dist fwd
                autoCmd = new AutoDriveFwd(dist, 0.5, 3, true, x); 
                isAutoSet = true;
            }
            //autoCmd = new AutoDriveTurn(dist, 0.5, 3, true, x);
            if(autoCmd != null) autoCmd.start();
            if(autoCmd.isCompleted()) isAutoSet = false;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if((dist < 5 && Math.abs(x) < 1.5)){
            return true;
        } else{
            return false;
        }
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        if(autoCmd != null) autoCmd.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        if(autoCmd != null) autoCmd.cancel();
    }

}

