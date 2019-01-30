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
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class CargoIntake extends Subsystem {

    public double counter;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private Solenoid goundIntakeSol;
    private WPI_VictorSPX cargoIntakeMtr;
    private WPI_VictorSPX cargoPickUpMtr;
    public DigitalInput cargoDistSensor;
    private Solenoid cargoCarriageSol;


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public CargoIntake() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        goundIntakeSol = new Solenoid(0, 1);
        addChild("GoundIntakeSol",goundIntakeSol);
        
        
        cargoIntakeMtr = new WPI_VictorSPX(9);
        
        
        
        cargoPickUpMtr = new WPI_VictorSPX(10);
        
        
        
        cargoDistSensor = new DigitalInput(0);
        addChild("CargoDistSensor",cargoDistSensor);
        
        
        cargoCarriageSol = new Solenoid(0, 2);
        addChild("CargoCarriageSol",cargoCarriageSol);
        
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        counter = 0;
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

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void CargoDeploy(Boolean Deploy) {
        goundIntakeSol.set(Deploy);
    }
    public void CargoGroundFeed(double Power) {
        cargoIntakeMtr.set(Power);
    }
    public void CargoIntakeSol (Boolean IntakeDeploy) {
        cargoCarriageSol.set(IntakeDeploy);
    }
    public void CargoIntakeFeedWheel(double Power){
        //makes sure the motor run longer so cargo is in all the way
        if (!cargoDistSensor.get()){ //if get sensor increment counter
            counter++;
        }else {// otherwise reset counter
            counter = 0;
        }
        if (counter >= 3){//sets power to 0 when counter exceeds number
            cargoPickUpMtr.set(0);
        }else {//otherwise sets power 
            cargoPickUpMtr.set(Power);
        }

    }

}

