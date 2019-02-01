// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2992.StarLord2019;

import org.usfirst.frc2992.StarLord2019.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc2992.StarLord2019.subsystems.*;
import org.usfirst.frc2992.StarLord2019.mhJoystick;
import org.usfirst.frc2992.StarLord2019.Constants;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public mhJoystick rightJoy;
    public mhJoystick leftJoy;
    public Joystick buttonBox;
    public JoystickButton hatchExtendBtn;
    public JoystickButton hatchRetractBtn;
    public JoystickButton hatchGrabBtn;
    public JoystickButton hatchGroundBtn;
    public JoystickButton hatchReleaseBtn;
    public JoystickButton liftUpBtn;
    public JoystickButton liftDownBtn;
    public JoystickButton liftSetBottomRocket;
    public JoystickButton liftSetMiddleRocket;
    public JoystickButton liftSetTopRocket;
    public JoystickButton liftSetCargo;
    public JoystickButton gearShiftBtn;
    public JoystickButton cargoDeployBtn;
    public JoystickButton groundWheelsBtn;
    public JoystickButton feedWheelInBtn;
    public JoystickButton feedWheelOutBtn;
    public JoystickButton feedWheelStopBtn;
    public JoystickButton intakeDeployBtn;
    public JoystickButton climbExtendBtn;
    public JoystickButton climbRetractBtn;
    public JoystickButton climbUpBtn;
    public JoystickButton climbStopBtn;
    public JoystickButton autoAlignBtn;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        rightJoy = new mhJoystick(0);
        leftJoy = new mhJoystick(1);
        buttonBox = new Joystick(2);
        
        //climbing btns
        climbStopBtn = new JoystickButton(buttonBox, 10);
        climbStopBtn.whenPressed(new ClimbStop());
        climbUpBtn = new JoystickButton(buttonBox, 9);
        climbUpBtn.whileHeld(new ClimbUp());
        climbRetractBtn = new JoystickButton(buttonBox, 8);
        climbRetractBtn.whenPressed(new ClimbExtendArms(false));
        climbExtendBtn = new JoystickButton(buttonBox, 7);
        climbExtendBtn.whenPressed(new ClimbExtendArms(true));
        
        //cargo intake btns
        intakeDeployBtn = new JoystickButton(leftJoy, 6);
        intakeDeployBtn.whenPressed(new CargoIntakeSol(true));
        intakeDeployBtn.whenReleased(new CargoIntakeSol(false));

        feedWheelStopBtn = new JoystickButton(leftJoy, 5);
        feedWheelStopBtn.whileHeld(new CargoIntakeFeedWheel(0));
        feedWheelOutBtn = new JoystickButton(leftJoy, 4);
        feedWheelOutBtn.whileHeld(new CargoIntakeFeedWheel(-.50));
        feedWheelInBtn = new JoystickButton(leftJoy, 3);
        feedWheelInBtn.whileHeld(new CargoIntakeFeedWheel(.50));
        
        groundWheelsBtn = new JoystickButton(leftJoy, 2);
        groundWheelsBtn.whileHeld(new CargoGroundFeedwheel(.5));
        groundWheelsBtn.whenReleased(new CargoGroundFeedwheel(0));
        
        cargoDeployBtn = new JoystickButton(leftJoy, 7);
        cargoDeployBtn.whenPressed(new CargoDeploySol(true));
        cargoDeployBtn.whenReleased(new CargoDeploySol(false));

        //lift btns
        liftDownBtn = new JoystickButton(rightJoy, 4);
        liftDownBtn.whileHeld(new LiftMove(-.5));
        liftUpBtn = new JoystickButton(rightJoy, 3);
        liftUpBtn.whileHeld(new LiftMove(.5));
        liftSetBottomRocket = new JoystickButton(buttonBox, 11);
        liftSetBottomRocket.whenPressed(new LiftSetHeight(Constants.bottomRocketHeight, 2));    //maybe change timeout
        liftSetMiddleRocket = new JoystickButton(buttonBox, 12);
        liftSetMiddleRocket.whenPressed(new LiftSetHeight(Constants.middleRocketHeight, 2));
        liftSetTopRocket = new JoystickButton(buttonBox, 13);
        liftSetTopRocket.whenPressed(new LiftSetHeight(Constants.topRocketHeight, 2));
        liftSetCargo = new JoystickButton(buttonBox, 14);
        liftSetCargo.whenPressed(new LiftSetHeight(Constants.cargoLoadHeight, 2));


        //hatch intake btns
        hatchGrabBtn = new JoystickButton(rightJoy, 2);
        hatchGrabBtn.whenPressed(new HatchIntakeGrab(true));
        //hatchGrabBtn.whenReleased(new HatchIntakeGrab(false));

        hatchReleaseBtn = new JoystickButton(rightJoy, 7);
        hatchReleaseBtn.whenPressed(new HatchIntakeGrab(false));

        hatchExtendBtn = new JoystickButton(rightJoy, 6);
        hatchExtendBtn.whenPressed(new HatchIntakeExtend(true));
        //hatchExtendBtn.whenReleased(new HatchIntakeExtend(false));

        hatchRetractBtn = new JoystickButton(rightJoy, 8);
        hatchRetractBtn.whenPressed(new HatchIntakeExtend(false));

        hatchGroundBtn = new JoystickButton(rightJoy, 5);
        hatchGroundBtn.whenPressed(new HatchPickupFromGround(true));
        hatchGroundBtn.whenReleased(new HatchPickupFromGround(false));

        //Vision Processing Btn
        autoAlignBtn = new JoystickButton(buttonBox, 6);
        autoAlignBtn.whileHeld(new VisionProcessing());
        autoAlignBtn.whenReleased(new driveSticks());

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("HatchIntakeExtend", new HatchIntakeExtend(true));
        SmartDashboard.putData("HatchIntakeGrab", new HatchIntakeGrab(true));
        SmartDashboard.putData("HatchPickupFromGround", new HatchPickupFromGround(true));
        SmartDashboard.putData("LiftSetHeight", new LiftSetHeight(0, 0));
        SmartDashboard.putData("LiftUp", new LiftMove(.5));
        SmartDashboard.putData("LiftDown", new LiftMove(-.5));
        SmartDashboard.putData("LiftStop", new LiftStop());
        SmartDashboard.putData("CargoDeploySol", new CargoDeploySol(true));
        SmartDashboard.putData("CargoIntakelSol", new CargoIntakeSol(true));
        SmartDashboard.putData("CargoGroundFeedwheel", new CargoGroundFeedwheel(0.5));
        SmartDashboard.putData("CargoIntakeFeedWheel", new CargoIntakeFeedWheel(0.5));
        SmartDashboard.putData("driveSticks", new driveSticks());
        SmartDashboard.putData("ClimbUp", new ClimbUp());
        SmartDashboard.putData("ExtendArms", new ClimbExtendArms(true));
        SmartDashboard.putData("ClimbStop", new ClimbStop());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getrightJoy() {
        return rightJoy;
    }

    public Joystick getleftJoy() {
        return leftJoy;
    }

    public Joystick getbuttonBox() {
        return buttonBox;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

