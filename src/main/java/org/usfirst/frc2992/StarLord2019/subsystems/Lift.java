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


import org.usfirst.frc2992.StarLord2019.Constants;
import org.usfirst.frc2992.StarLord2019.Robot;
import org.usfirst.frc2992.StarLord2019.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Timer;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Lift extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public WPI_TalonSRX liftTalon;
    private WPI_VictorSPX liftVictor;

    private final int liftMaxTravel = convertEncoderTicks(Constants.topRocketCargoHeight + 1);//Inches we can move from bottom to top

    public boolean moving;
    public boolean setPosition = false;
    private double height = 0;

    private boolean limitSwitchMode = true;
    private boolean limitOverrideBtn = false;

    private Timer timer;

    public SlotConfiguration slot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Lift() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        liftTalon = new WPI_TalonSRX(7);
        liftTalon.setNeutralMode(NeutralMode.Brake);
        liftTalon.configPeakCurrentLimit(40);
        liftTalon.configPeakCurrentDuration(3000);//ms
        liftTalon.configContinuousCurrentLimit(30);
        //liftTalon.configClearPositionOnLimitR(true, 10); //(clear Posn, TimeoutMS

        liftVictor = new WPI_VictorSPX(8);
        liftVictor.setNeutralMode(NeutralMode.Brake);
        liftVictor.follow(liftTalon);
        liftVictor.setInverted(InvertType.OpposeMaster);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        liftTalon.setSensorPhase(false);
        liftTalon.configClosedloopRamp(0);// (seconds from min to full power)
        //liftTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        //liftTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        liftTalon.setSafetyEnabled(false);

    //LiftUp PID Values (Slot 0)
        liftTalon.config_kP(0, 0.5000001);//.1500001 (slot, value)
        liftTalon.config_kI(0, 0.0);
        liftTalon.config_kD(0, 17.0);//7.5
        liftTalon.config_kF(0, .2623);//.4169
        //liftTalon.config_IntegralZone(0, 2000);
        liftTalon.configAllowableClosedloopError(0, 100, 1000);// (slot, error, timeoutMs)
        liftTalon.configClosedLoopPeakOutput(0, 1.0);

    //LiftDown PID Values (Slot 1)
        liftTalon.config_kP(1, .32);
        liftTalon.config_kI(1, 0);
        liftTalon.config_kD(1, 6.5);
        liftTalon.config_kF(0, .4169);//.4169
        liftTalon.configAllowableClosedloopError(1, 100, 1000);
        liftTalon.configClosedLoopPeakOutput(1, 1.0);

        //motion magic - acceleration and max cruise velocity
        liftTalon.configMotionAcceleration(7500, 10);//(accel in enc ticks/100ms/sec, timeout ms)
        liftTalon.configMotionCruiseVelocity(3900, 10);//(velocity in enc ticks/100ms, timeout ms)
        
        timer = new Timer();

    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new LiftStop());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        limitOverrideBtn = Robot.oi.limitSwitchOverrideBtn.get();
        if( limitOverrideBtn && limitSwitchMode){
            liftTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
            liftTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
            limitSwitchMode = false;
        } else if(!limitOverrideBtn && !limitSwitchMode){
            liftTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
            liftTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
            limitSwitchMode = true;
        }

        
        if(liftTalon.getSensorCollection().isRevLimitSwitchClosed()){
            //encoder attached to climb mtr bc sentinel board probs

            if(Robot.isPracBot){
                Robot.climbFront.climbMtr1.setSelectedSensorPosition(0);
            } else {
                Robot.driveTrain.leftTalonDrive.setSelectedSensorPosition(0); //TODO Maybe change sides of drivetrain
            }
        }
        
 
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void liftMove(double speed){
        //Robot.hatchIntake.HatchIntakeExtend(false, false);

        if(!moving){
            timer.reset();
            timer.start();
        }
        moving = true;
        //if(liftTalon.configForwardLimitSwitchSource(type, normalOpenOrClose))
        //Wait to move so hatch extend solenoid can retract
        if(timer.get() >= Constants.liftTimeout || !Robot.hatchIntake.extendPosn){
            liftTalon.set(speed);
        }
        
        liftTalon.set(speed);
    }

    public void goToHeight(double height){
        moving = true;

        height = Math.max(convertEncoderTicks(-2), Math.min(height, liftMaxTravel));

        //liftTalon.set(ControlMode.Position, height, DemandType.ArbitraryFeedForward, .15);
        liftTalon.set(ControlMode.MotionMagic, height);
        
        //auto re-zeroing the encoder hitting btm so don't have to keep track of where you are
        //height += liftTalon.getSelectedSensorPosition();
    }

    public void liftStop(){
        //m_speed = 0;
        liftTalon.set(0);
        moving = false;
    }

    public void holdPosn(){
        moving = false;
        if(!setPosition){
            height = liftTalon.getSelectedSensorPosition();
            setPosition = true;
        }
        
        //liftTalon.set(ControlMode.Position, height);        
        liftTalon.set(ControlMode.MotionMagic, height);
    }

    public double ChooseCargoHeight(int floor){
        switch (floor){
            case 0: return Constants.cargoGroundHeight;

            case 1: return Constants.cargoLoadHeight;

            case 2: return Constants.bottomRocketCargoHeight;

            case 3: return Constants.middleRocketCargoHeight;

            case 4: return Constants.topRocketCargoHeight;

            default: return Constants.cargoGroundHeight;
        }
    }

    public double ChooseHatchHeight(int Floor){
        switch (Floor){
            case 2: return Constants.bottomRocketHatchHeight;

            case 3: return Constants.middleRocketHatchHeight;
            
            case 4: return Constants.topRocketHatchHeight;
            
            default: return Constants.bottomRocketHatchHeight;
        }
    }

    public int convertEncoderTicks(double inches){
        return (int) Math.round(((inches* 4096) /8));
    }

}

