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
//From Limelight Ex
    public boolean m_hasValidTarget = false;
    private double m_driveCmd = 0.0;
    private double m_turnCmd = 0.0;

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tv = table.getEntry("tv"); //if has valid target 
    NetworkTableEntry tx = table.getEntry("tx"); //target x value 
    NetworkTableEntry ty = table.getEntry("ty"); //target y value
    NetworkTableEntry ta = table.getEntry("ta"); //target area
    NetworkTableEntry light = table.getEntry("ledMode");
    double x = 0;
    double dist = 0;
    private double camHt = Constants.camHt;
    private double tarHt = (Constants.tarHt);
    private double camAngle = Constants.camAngle;

    private double rightJoyVal = 0;
    private double leftJoyVal = 0;

    Command autoCmd;
    boolean isAutoSet = false;

    boolean haveOvershot = false;
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
        light.setDouble(3); //for limelight LEDs on
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(!Robot.isCargoMode && (Robot.lift.liftTalon.getSelectedSensorPosition() <= 200 || Robot.oi.limitSwitchOverrideBtn.get())){//no Limelight on Cargo side, make sure no VP running
            updateLimelightTracking();
            if(m_hasValidTarget){
                if(!Robot.isAutoTime){
                    Robot.VPLights = true;
                    Robot.lightCode.setLightSequence(.175);
                }
                Robot.driveTrain.arcadeDrive(m_driveCmd, -m_turnCmd);
            } else{
                if(!Robot.isAutoTime){
                    Robot.VPLights = true;
                    Robot.lightCode.setLightSequence(.225);
                }
                rightJoyVal = -Robot.oi.rightJoy.smoothGetX();//for arcade
                leftJoyVal = -Robot.oi.leftJoy.smoothGetY();
                Robot.driveTrain.arcadeDrive(leftJoyVal, -rightJoyVal);
                //do we want this or allow manual driving?
            }
/*
            //values from limelight
            x = tx.getDouble(0.0); //needs to be global
            double y = ty.getDouble(0.0);
            double area = ta.getDouble(0.0);

            SmartDashboard.putNumber("LimeLightX", x);
            SmartDashboard.putNumber("LimeLightY", y);
            SmartDashboard.putNumber("LimeLightArea", area);

            if(x>0 && !haveOvershot){
                autoCmd = new AutoDriveFwd(10, 0.5, 3, true, x+10);
            }else if(x<0 && !haveOvershot){
                autoCmd = new AutoDriveFwd(10, 0.5, 3, true, x-10);
            }

            if(Math.abs(x) > 1.5 && !isAutoSet && haveOvershot){// if angle b/w middle of pic and bot, turn towards target
                autoCmd = new AutoDriveTurn(-x, 0.5, 3);
                isAutoSet = true;
            } else if(Math.abs(x) <= 1.5 && !isAutoSet && haveOvershot){//if angle is close within 1.5deg, just drive towards it
                dist = Robot.driveTrain.getDist(camHt, tarHt, camAngle, y);
                //newDist = (int) Math.round(dist);
                dist = Robot.driveTrain.convertEncoderTicks(dist * (Math.sin(camAngle))); //Pyth. Theorem to find Horiz dist fwd
                autoCmd = new AutoDriveFwd(dist, 0.5, 3, true, x); 
                isAutoSet = true;
            }
*/
            
            //if(autoCmd.isCompleted()) isAutoSet = false;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
/*
        if((dist < 5 && Math.abs(x) < 1.5)){
            return true;
        } else{
            return false;
        }
*/
        return false;  // Count on driver to interrupt by releasing button
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.VPLights = false;
        light.setDouble(1);//force limelight light off
    //    if(autoCmd != null) autoCmd.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.VPLights = false;
        light.setDouble(1); //force limelight light to turn off
    //    if(autoCmd != null) autoCmd.cancel();
    }

    public void updateLimelightTracking(){
        final double STEER_K = 0.02;
        final double DRIVE_K = 0.26;
        final double DESIRED_TARGET_AREA = 13.0;
        final double MAX_DRIVE = 0.4;

        double TV = tv.getDouble(0.0);
        double TX = tx.getDouble(0.0);
        double TY = ty.getDouble(0.0);
        double TA = ta.getDouble(0.0);

        if(TV < 1.0){
            m_hasValidTarget = false;
            m_driveCmd = 0.0;
            m_turnCmd = 0.0;
        } else{
            m_hasValidTarget = true;

            //start with proportional steering
            double steerCmd = TX * STEER_K;
            m_turnCmd = steerCmd;
    
            //try to drive fwd until target area reaches desired area
            double driveCmd = (DESIRED_TARGET_AREA - TA) * DRIVE_K;
    
            //don't let robot drive too fast into goal
            if(driveCmd > MAX_DRIVE){
                driveCmd = MAX_DRIVE;
            }
            m_driveCmd = driveCmd;
        }
        
    }

}

