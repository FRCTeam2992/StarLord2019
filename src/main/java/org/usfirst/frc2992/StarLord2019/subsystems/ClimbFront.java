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
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class ClimbFront extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public WPI_TalonSRX  climbMtr1;
    public VictorSP climbMtrComp;
    public WPI_TalonSRX climbMtrPrac;
    
    private DigitalInput climbTopLimitSwitch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private boolean limitSwitchMode = true;
    private boolean limitOverrideBtn = false;

    public ClimbFront() {

        //TODO Set up climbTopLimitSwitch for Victor

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        climbMtr1 = new WPI_TalonSRX(11);
        climbMtr1.setNeutralMode(NeutralMode.Brake);

        climbMtr1.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        climbMtr1.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        
        climbMtr1.configPeakCurrentLimit(54);
        climbMtr1.configPeakCurrentDuration(5000); //5 sec above 54 amps
        climbMtr1.configContinuousCurrentLimit(50);
       
        climbTopLimitSwitch = new DigitalInput(1);



        /* Old system. Practice and comp bot had different motor controllers
        if(Robot.isPracBot){
            climbMtrPrac = new WPI_TalonSRX(11);
            climbMtrPrac.setNeutralMode(NeutralMode.Brake);
    
            climbMtrPrac.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
            climbMtrPrac.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
            
            climbMtrPrac.configPeakCurrentLimit(54);
            climbMtrPrac.configPeakCurrentDuration(5000); //5 sec above 54 amps
            climbMtrPrac.configContinuousCurrentLimit(50);
            climbMtr1 = climbMtrPrac;
        } else {
            climbMtrComp = new VictorSP(0);
            //climbMtrComp.setNeutralMode(NeutralMode.Brake);
            climbMtr1 = climbMtrComp;
            climbTopLimitSwitch = new DigitalInput(1);
        }
        */
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ClimbStopFront());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        if(Robot.isPracBot){
            limitOverrideBtn = Robot.oi.limitSwitchOverrideBtn.get();
            if( limitOverrideBtn && limitSwitchMode){
                climbMtr1.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
                climbMtr1.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
                limitSwitchMode = false;
            } else if(!limitOverrideBtn && !limitSwitchMode){
                climbMtr1.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
                climbMtr1.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
                limitSwitchMode = true;
            }
        }        

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void ClimbFrontUp(double Speed){
        limitOverrideBtn = Robot.oi.limitSwitchOverrideBtn.get();
        if(Robot.isPracBot){
            climbMtr1.set(Speed); //If prac bot, just set speed, talon does rest
        } else {
            if(limitOverrideBtn){ //If comp bot, checks limitSwitch on Rio
                climbMtr1.set(Speed);
            } else {
                if(!climbTopLimitSwitch.get()){
                    if(Speed < 0){
                        climbMtr1.set(Speed);
                    } else {
                        climbMtr1.set(0);
                    }
                } else {
                    climbMtr1.set(Speed);
                }
            }
        }
    }

    public void ClimbStop(){
        climbMtr1.set(0);
    }

    public boolean getTopLimitSwitch(){

        if(Robot.isPracBot){
            return climbMtr1.getSensorCollection().isFwdLimitSwitchClosed();
        } else {
            return climbTopLimitSwitch.get();
        }
        
        /* When the prac and comp bots had different motor controllers
        if (Robot.isPracBot) {
            return climbMtrPrac.getSensorCollection().isFwdLimitSwitchClosed();
        }
        else {
            return !climbTopLimitSwitch.get();
        }
        */
    }
}

