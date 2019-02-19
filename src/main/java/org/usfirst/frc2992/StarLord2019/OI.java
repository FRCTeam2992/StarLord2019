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
    public Joystick buttonBox2;

    public JoystickButton hatchExtBtn1;
    public JoystickButton hatchExtBtn2;
    public JoystickButton hatchGroundBtn;
    public JoystickButton hatchGrabRelBtn;

    public JoystickButton liftUpBtn;
    public JoystickButton liftDownBtn;
    public JoystickButton liftSetCargoBottomRocket;
    public JoystickButton liftSetCargoLoad;
    public JoystickButton liftSetBottomRocket;
    public JoystickButton liftSetMiddleRocket;
    public JoystickButton liftSetTopRocket;
    public JoystickButton liftSetGround;

    public JoystickButton gearShiftBtn;

    public JoystickButton cargoDeployBtn;
    public JoystickButton groundFwdCargoBtn;
    public JoystickButton feedWheelInBtn;
    public JoystickButton feedWheelOutBtn;
    public JoystickButton intakeDeployBtn;
    public JoystickButton groundRevCargoBtn;

    public JoystickButton climbUpRearBtn;
    public JoystickButton climbDownRearBtn;
    public JoystickButton climbUpFrontBtn;
    public JoystickButton climbDownFrontBtn;
    public JoystickButton climberCheckBtn;

    public JoystickButton autoAlignBtn;
    public JoystickButton autoGroundFeedBtn;//Cargo
    public JoystickButton autoStationFeedBtn;//Cargo
    public JoystickButton autoHatchLoadBtn; //Hatch
    public JoystickButton autoHatchScoreBtn; //Hatch
 
    public JoystickButton limitSwitchOverrideBtn;
    public JoystickButton autoSwtich1;
    public JoystickButton autoSwtich2;
    public JoystickButton autoSwtich3;
    public JoystickButton autoSwtich4;
    public JoystickButton autoSwtich5;
    public JoystickButton autoSwtich6;

    //2 output channels for LED on btnBox
    //To see where used - go to driveSticks Cmd
    public int cargoLight = 1; //1 is port#
    public int hatchLight = 2; //2 is Port#
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    
        rightJoy = new mhJoystick(0);
        leftJoy = new mhJoystick(1);
        buttonBox = new Joystick(2);
        buttonBox2 = new Joystick(3);

    //First Button Box
        liftSetTopRocket = new JoystickButton(buttonBox, 1);
        liftSetTopRocket.whenPressed(new LiftSetHeight(4, 2));

        liftSetMiddleRocket = new JoystickButton(buttonBox, 2);
        liftSetMiddleRocket.whenPressed(new LiftSetHeight(3, 2));

        liftSetCargoLoad = new JoystickButton(buttonBox, 3);
        liftSetCargoLoad.whenPressed(new LiftSetHeight(1, 2));

        liftSetBottomRocket = new JoystickButton(buttonBox, 4);
        liftSetBottomRocket.whenPressed(new LiftSetHeight(2, 2));
    

        liftSetGround = new JoystickButton(buttonBox, 5);
        liftSetGround.whenPressed(new LiftSetHeight(0, 2));

        autoGroundFeedBtn = new JoystickButton(buttonBox, 6); 
        autoGroundFeedBtn.whenPressed(new autoCargoLoadGround());

        autoStationFeedBtn = new JoystickButton(buttonBox, 7);
        autoStationFeedBtn.whenPressed(new autoCargoLoadStation());

        liftUpBtn = new JoystickButton(buttonBox, 8);
        liftUpBtn.whileHeld(new LiftMove(.7));
        liftUpBtn.whenReleased(new LiftStop());
        liftUpBtn.whileHeld(new AutoClimb(.3));
        liftUpBtn.whenReleased(new ClimbStopBack());
        liftUpBtn.whenReleased(new ClimbStopFront());

        liftDownBtn = new JoystickButton(buttonBox, 9);
        liftDownBtn.whileHeld(new LiftMove(-.3));
        liftDownBtn.whenReleased(new LiftStop());
        liftDownBtn.whileHeld(new AutoClimb(-.2));
        liftDownBtn.whenReleased(new ClimbStopBack());
        liftDownBtn.whenReleased(new ClimbStopFront());
        
        cargoDeployBtn = new JoystickButton(buttonBox, 10); // ground wheel 
        cargoDeployBtn.whenPressed(new CargoDeploySol(true));
        cargoDeployBtn.whenReleased(new CargoDeploySol(false));
       
        groundFwdCargoBtn = new JoystickButton(buttonBox, 11);
        groundFwdCargoBtn.whileHeld(new CargoGroundFeedwheel(.7));
        groundFwdCargoBtn.whenReleased(new CargoGroundFeedwheel(0));

        groundRevCargoBtn = new JoystickButton(buttonBox, 12);
        groundRevCargoBtn.whileHeld(new CargoGroundFeedwheel(-0.5));
        groundRevCargoBtn.whenReleased(new CargoGroundFeedwheel(0));
        
        feedWheelInBtn = new JoystickButton(buttonBox, 13);
        feedWheelInBtn.whileHeld(new CargoIntakeFeedWheel(.50));
        feedWheelInBtn.whenReleased(new CargoIntakeFeedWheel(0));
 
        feedWheelOutBtn = new JoystickButton(buttonBox, 14);
        feedWheelOutBtn.whileHeld(new CargoIntakeFeedWheel(-.7));
        feedWheelOutBtn.whenReleased(new CargoIntakeFeedWheel(0));

        //this btn doesn't tirgger a cmd.
        //It's used in lift and climber to make sure can still lift w/ broken sensor
        limitSwitchOverrideBtn = new JoystickButton(buttonBox, 15);



    //Second Button Box
    
        autoHatchLoadBtn = new JoystickButton(buttonBox2, 3);
        autoHatchLoadBtn.whenPressed(new autoHatchLoad());

        autoHatchScoreBtn = new JoystickButton(buttonBox2, 4);
        autoHatchScoreBtn.whenPressed(new autoHatchScore());

        hatchGroundBtn = new JoystickButton(buttonBox2, 5);
        hatchGroundBtn.whenPressed(new HatchPickupFromGround(true));
        hatchGroundBtn.whenReleased(new HatchPickupFromGround(false));

        hatchGrabRelBtn = new JoystickButton(buttonBox2, 6);
        hatchGrabRelBtn.whenPressed(new HatchIntakeGrab(true));
        hatchGrabRelBtn.whenReleased(new HatchIntakeGrab(false));

        hatchExtBtn1 = new JoystickButton(buttonBox2, 2);
        hatchExtBtn1.whenPressed(new HatchIntakeExtend(false, false));
        hatchExtBtn1.whenReleased(new HatchIntakeExtend(false, true));

        hatchExtBtn2 = new JoystickButton(buttonBox2, 7);
        hatchExtBtn2.whenPressed(new HatchIntakeExtend(true, true));
        hatchExtBtn2.whenReleased(new HatchIntakeExtend(false, true));

        climberCheckBtn = new JoystickButton(buttonBox2, 8);

        climbUpRearBtn = new JoystickButton(buttonBox2, 11);//10
        climbUpRearBtn.whileHeld(new ClimbBackUp(.5));
        climbUpRearBtn.whenReleased(new ClimbStopBack());

        climbDownRearBtn = new JoystickButton(buttonBox2, 12);//9
        climbDownRearBtn.whileHeld(new ClimbBackUp(-.5));
        climbDownRearBtn.whenReleased(new ClimbStopBack());

        climbUpFrontBtn = new JoystickButton(buttonBox2, 10);//11
        climbUpFrontBtn.whileHeld(new ClimbFrontUp(.5));
        climbUpFrontBtn.whenReleased(new ClimbStopFront());

        climbDownFrontBtn = new JoystickButton(buttonBox2, 9);//12
        climbDownFrontBtn.whileHeld(new ClimbFrontUp(-.5));
        climbDownFrontBtn.whenReleased(new ClimbStopFront());

        autoSwtich1 = new JoystickButton(buttonBox2, 13);

        autoSwtich2 = new JoystickButton(buttonBox2, 14);

        autoSwtich3 = new JoystickButton(buttonBox2, 15);

        autoSwtich4 = new JoystickButton(buttonBox2, 16);

        autoSwtich5 = new JoystickButton(buttonBox2, 17);

        autoSwtich6 = new JoystickButton(buttonBox2, 18);

        //Vision Processing Btn
        autoAlignBtn = new JoystickButton(rightJoy, 6);
        autoAlignBtn.whileHeld(new VisionProcessing());
        autoAlignBtn.whenReleased(new driveSticks());
        

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        //SmartDashboard.putData("HatchIntakeExtend", new HatchIntakeExtend(true));
        SmartDashboard.putData("HatchIntakeGrab", new HatchIntakeGrab(true));
        SmartDashboard.putData("HatchPickupFromGround", new HatchPickupFromGround(true));
        SmartDashboard.putData("LiftSetHeight", new LiftSetHeight(0, 0));
        SmartDashboard.putData("LiftUp", new LiftMove(.5));
        SmartDashboard.putData("LiftDown", new LiftMove(-.5));
        SmartDashboard.putData("LiftStop", new LiftStop());
        SmartDashboard.putData("CargoDeploySol", new CargoDeploySol(true));
        SmartDashboard.putData("CargoGroundFeedwheel", new CargoGroundFeedwheel(0.5));
        SmartDashboard.putData("CargoIntakeFeedWheel", new CargoIntakeFeedWheel(0.5));
        SmartDashboard.putData("driveSticks", new driveSticks());
        SmartDashboard.putData("ClimbFrontUp", new ClimbFrontUp(0.5));
        SmartDashboard.putData("ClimbFrontDown", new ClimbFrontUp(-0.5));
        SmartDashboard.putData("ClimbBackUp", new ClimbBackUp(0.5));
        SmartDashboard.putData("ClimbBackDown", new ClimbBackUp(-0.5));
        SmartDashboard.putData("ClimbStopFront", new ClimbStopFront());
        SmartDashboard.putData("ClimbStopBack", new ClimbStopBack());


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

