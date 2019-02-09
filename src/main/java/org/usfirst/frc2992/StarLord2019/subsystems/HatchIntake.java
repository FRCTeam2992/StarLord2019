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
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
    private Solenoid hatchPushSol;
    private Solenoid hatchGroundPickupSol;
    public DigitalInput hatchLoadingSwitch;

    public Boolean hatchLoadedBool;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public HatchIntake() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        hatchGrabSol = new Solenoid(0, 3);
        addChild("HatchGrabSol",hatchGrabSol);
        
        hatchPushSol = new Solenoid(0, 4);
        addChild("HatchPushSol",hatchPushSol);
        
        hatchGroundPickupSol = new Solenoid(0, 6);
        addChild("HatchGroundPickupSol",hatchGroundPickupSol);
        
        hatchLoadingSwitch = new DigitalInput(6);

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
        if(hatchLoadingSwitch.get()){
            hatchLoadedBool = true;
        } else{
            hatchLoadedBool = false;
        }

        if(hatchLoadingSwitch.get() && !hatchLoadedBool){
            new HatchIntakeGrab(true);
        }

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void HatchIntakeExtend(Boolean extend){
        hatchPushSol.set(extend);
    }
    public void HatchIntakeGrab(Boolean grab){
        hatchGrabSol.set(grab);
    }
    public void HatchPickupFromGround(Boolean ground){
        hatchGroundPickupSol.set(ground);
    }
    /*
    public void yeeHaw(int cowboy){
        String text = "";
        for(int i = 0; i < cowboy; i++){
            text += "Yeehaw! ";
        }
        System.out.println(text);
    }
    */
}

