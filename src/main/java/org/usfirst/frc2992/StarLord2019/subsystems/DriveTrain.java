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
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import com.kauailabs.navx.frc.AHRS;
import java.util.ArrayList;
import com.ctre.phoenix.motorcontrol.ControlMode;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX rightTalonDrive;
    private WPI_TalonSRX leftTalonDrive;
    private WPI_VictorSPX leftVictorDrive1;
    private WPI_VictorSPX leftVictorDrive2;
    private WPI_VictorSPX rightVictorDrive1;
    private WPI_VictorSPX rightVictorDrive2;
    private Solenoid driveGearShift;
    private WPI_TalonSRX talon6;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public final AHRS navx;
    
    public PIDController lDistPID, rDistPID;
    

    //right distance pid
    //double rkp = rightTalonDrive.config_kP(slotIdx, value, timeOutMs);
    //double rki = rightTalonDrive.config_kI(slotIdx, value, timeOutMs);
    //double rkd = rightTalonDrive.config_kD(slotIdx, value, timeOutMs);
    
    //left distance pid
    //double lkp = leftTalonDrive.config_kP(slotIdx, value, timeOutMs);
    //double lki = leftTalonDrive.config_kI(slotIdx, value, timeOutMs);
    //double lkd = leftTalonDrive.config_kD(slotIdx, value, timeOutMs);
    
    //gyro pid
    double gkp = .01; 
    double gkd = .0;
    double gError = 0.0;
    
    public PIDController turnPID;
    
    //rotate pid
    //final double rkp = .015;
    //final double rki = .0;
    //final double rkd = .03;


    public DriveTrain() {
        //liftTalon.config_kP(slotIdx, value, timeoutMs)
        //liftTalon.config_kI(slotIdx, value, timeoutMs)
        //liftTalon.config_kD(slotIdx, value, timeoutMs)

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        rightTalonDrive = new WPI_TalonSRX(4);

        leftTalonDrive = new WPI_TalonSRX(1);
        
        leftVictorDrive1 = new WPI_VictorSPX(2);
        
        leftVictorDrive2 = new WPI_VictorSPX(3);
        
        rightVictorDrive1 = new WPI_VictorSPX(5);

        rightVictorDrive2 = new WPI_VictorSPX(6);
        
        navx = new AHRS(SPI.Port.kMXP);

        talon6 = new WPI_TalonSRX(6);
        talon6.setName("Talon6");
        talon6.setSubsystem("testing");
        LiveWindow.add(talon6);
        talon6.setSensorPhase(true);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


        driveGearShift = new Solenoid(0,0);//(device #, port #)
        driveGearShift.setSubsystem("DriveTrain");
        driveGearShift.setName("driveGearShift");
        LiveWindow.add(driveGearShift);

        rightTalonDrive.setInverted(false);
        rightTalonDrive.setSubsystem("DriveTrain");
        rightTalonDrive.setName("rightTalonDrive");
        LiveWindow.add(rightTalonDrive);
    
        leftTalonDrive.setInverted(true);
        rightTalonDrive.setSubsystem("DriveTrain");
        rightTalonDrive.setName("leftTalonDrive");
        LiveWindow.add(leftTalonDrive);

        rightVictorDrive1.setInverted(false);
        rightVictorDrive1.follow(rightTalonDrive);

        rightVictorDrive2.setInverted(false);
        rightVictorDrive2.follow(rightTalonDrive);
        
        leftVictorDrive1.setInverted(false);
        leftVictorDrive1.follow(leftTalonDrive);
        
        leftVictorDrive2.setInverted(false);
        leftVictorDrive2.follow(leftTalonDrive);       
        
        //current limiting for the Talons
        rightTalonDrive.configPeakCurrentLimit(55,0); //(peakCurrentAmps, timeout)
        rightTalonDrive.configPeakCurrentDuration(200, 0);//(time in ms after reaches threshold before limiting, timeout)
        rightTalonDrive.configContinuousCurrentLimit(55,0);//(currentAmps can go after limited, timeout)
        rightTalonDrive.enableCurrentLimit(true);

        leftTalonDrive.configPeakCurrentLimit(55,0);
        leftTalonDrive.configPeakCurrentDuration(200,0);
        leftTalonDrive.configContinuousCurrentLimit(55,0);
        leftTalonDrive.enableCurrentLimit(true);

        //voltage ramping (basically 48 volts/sec)
        rightTalonDrive.configOpenloopRamp(0.45, 0);// (seconds from 0-full volts, timeout in millisec do 0);
        leftTalonDrive.configOpenloopRamp(0.45,0);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new driveSticks());

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
    public void allStop(){
        rightTalonDrive.set(0);
        leftTalonDrive.set(0);
    }

    public void tankDrive(double left, double right){
        double lrAvg = (left+right)/2.0;
    	
    	// Dampen turns by pushing left and right powers slightly toward the average
    	
    	left = (left + 0.8 * left + 1.2 * lrAvg) / 3.0;
    	right = (right + 0.8 * right + 1.2 * lrAvg) / 3.0;
    	
        right = Math.max(-1.0, Math.min(1.0,  right));
        left = Math.max(-1.0, Math.min(1.0,  left));

        //rightTalonDrive.set(right);
        talon6.set(right);
        leftTalonDrive.set(left);
    }

    public void highGear(){
        driveGearShift.set(true);
    }
    

    public void lowGear(){
        driveGearShift.set(false);
    }

}

