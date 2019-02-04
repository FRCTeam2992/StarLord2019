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


import org.usfirst.frc2992.StarLord2019.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Climber extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX climbMtr1;
    private WPI_TalonSRX climbMtr2;
    private Encoder climbHeightEnc;
    private DigitalInput climbTopSwitch;
    private DigitalInput climbBottomSwitch;
    private Solenoid climbExtendArmsSol;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Climber() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        climbMtr1 = new WPI_TalonSRX(11);
        
        
        
        climbMtr2 = new WPI_TalonSRX(12);
        
        
       /* 
        climbHeightEnc = new Encoder(4, 5, false, EncodingType.k4X);
        addChild("ClimbHeightEnc",climbHeightEnc);
        climbHeightEnc.setDistancePerPulse(1.0);
        climbHeightEnc.setPIDSourceType(PIDSourceType.kRate);
        */
        climbTopSwitch = new DigitalInput(2);
        addChild("ClimbTopSwitch",climbTopSwitch);
        
        
        climbBottomSwitch = new DigitalInput(3);
        addChild("ClimbBottomSwitch",climbBottomSwitch);
        
        
        climbExtendArmsSol = new Solenoid(0, 5);
        addChild("ClimbExtendArmsSol",climbExtendArmsSol);
        
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ClimbStop());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void ClimbUp(){
        climbMtr1.set(.5);
        climbMtr2.set(.5);
    }
    public void ClimbStop(){
        climbMtr1.set(0);
        climbMtr2.set(0);
    }
    public void ClimbExtendArmsSol(Boolean Extend){
        climbExtendArmsSol.set(Extend);

    }
}

