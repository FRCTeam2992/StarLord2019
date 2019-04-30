// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2992.StarLord2019.subsystems;


import org.usfirst.frc2992.StarLord2019.Robot;
import org.usfirst.frc2992.StarLord2019.commands.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Solenoid;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class HatchIntake extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private Solenoid hatchGrabSol;
    private Solenoid hatchPushSol1;
    private Solenoid hatchPushSol2;
    private Solenoid hatchGroundPickupSol;
    public DigitalInput hatchLoadingSwitch;

    public Boolean hatchLoadedBool;
    public Boolean hatchScoringBool = false;

    public Boolean extendPosn = false;

    public Boolean m_extendSol1 = false;
    public Boolean m_extendSol2 = false;

    public boolean extendSol1Last = false;
    public boolean extendSol2Last = false;
    public boolean resetSol = false;

    Command stopCommand;
    Command stopCommand2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public HatchIntake() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        hatchGrabSol = new Solenoid(0, 2);
        addChild("HatchGrabSol",hatchGrabSol);
        
        hatchPushSol1 = new Solenoid(0, 3);
        addChild("HatchPushSol1",hatchPushSol1);

        hatchPushSol2 = new Solenoid(0, 4);
        addChild("HatchPushSol2",hatchPushSol2);
        
        hatchGroundPickupSol = new Solenoid(0, 5);
        addChild("HatchGroundPickupSol",hatchGroundPickupSol);
        
        hatchLoadingSwitch = new DigitalInput(6);   
        addChild("Hatch Loading Switch",hatchLoadingSwitch);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        if(!hatchLoadingSwitch.get()){
            hatchLoadedBool = true;
        } else{
            hatchLoadedBool = false;
        }

        if(Robot.lift.moving && resetSol){
            HatchIntakeExtend(false, false);
            resetSol = false;
        }else if(!Robot.lift.moving && !resetSol){
            HatchIntakeExtend(extendSol1Last, extendSol2Last);
            resetSol = true;
        }

        if(Robot.oi.OSBtn.get() || Robot.oi.OSJoyBtn.get()){
            stopCommand = new HatchIntakeExtend(false, false);
            stopCommand2 = new HatchIntakeGrab(true);
        } else{
            stopCommand = null;
            stopCommand2 = null;
        }
        if(stopCommand!=null) stopCommand.start();
        if(stopCommand2!=null) stopCommand2.start();

    if(!Robot.disabledMode && !Robot.isCargoMode && !Robot.isAutoTime 
            && !Robot.VPLights && !Robot.climbLights){//make sure other lights not overrridden
        if(Robot.hatchIntake.getGrabSol()){
            Robot.lightCode.setLightSequence(.575);
        } else{
            Robot.lightCode.setLightSequence(.125);
        }    
    }
}

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void HatchIntakeExtend(Boolean extendSol1, Boolean extendSol2){
        extendSol1Last = m_extendSol1;
        extendSol2Last = m_extendSol2;

        m_extendSol1 = extendSol1;
        m_extendSol2 = extendSol2;

        hatchPushSol1.set(m_extendSol1);
        hatchPushSol2.set(m_extendSol2);

        if(extendSol1 || extendSol2) extendPosn = true;
        else extendPosn = false;
    }
    public void HatchIntakeGrab(Boolean grab){
        if(grab){
            hatchScoringBool = false;
        } else{
            HatchIntakeExtend(false,false);
        }
        hatchGrabSol.set(grab);
    }
    public void HatchPickupFromGround(Boolean ground){
        hatchGroundPickupSol.set(ground);
    }
    public boolean getGrabSol(){
        return hatchGrabSol.get();
    }
    public boolean getHatchSol1(){
        return hatchPushSol1.get();
    }
    public boolean getHatchSol2(){
        return hatchPushSol2.get();
    }
    
}

