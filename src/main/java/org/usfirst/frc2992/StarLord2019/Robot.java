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

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import org.usfirst.frc2992.StarLord2019.commands.*;
import org.usfirst.frc2992.StarLord2019.subsystems.*;
import edu.wpi.first.networktables.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;    

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static HatchIntake hatchIntake;
    public static Lift lift;
    public static CargoIntake cargoIntake;
    public static ClimbFront climbFront;
    public static ClimbBack climbBack;
    public static CargoGroundIntake cargoGroundIntake;
    public static LightCode lightCode;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static Boolean isCargoMode = false;
    public static final Boolean isPracBot = true;

    //Light booleans - so no cross ups
    public static boolean isAutoTime = false;
    public static boolean disabledMode = false;
    public static boolean cargoLoadLights = false;
    public static boolean hatchLoadLights = false;
    public static boolean cargoScoreLights = false;
    public static boolean hatchScoreLights = false;
    public static boolean VPLights = false;
    public static boolean climbLights = false;

    //VP has valid target variables
    static NetworkTableEntry tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv"); //if has valid target 
    static NetworkTableEntry limeLight = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode");

    private int counter = 0;

    //Auto variables
    public static int autoCommandNum; 
    public static String autoName = "Nothing";// to send command group info to smartDashboard
    public static String autoStartPosn = "";    

    private static MjpegServer camera;
    private static final String camName = "Camera";
    private static UsbCamera hatchCam;
    private static UsbCamera cargoCam;
    private static String whichOne = "";
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        hatchIntake = new HatchIntake();
        lift = new Lift();
        cargoIntake = new CargoIntake();
        cargoGroundIntake = new CargoGroundIntake();
        climbFront = new ClimbFront();
        climbBack = new ClimbBack();
        lightCode = new LightCode();

        SmartDashboard.putData(driveTrain);
        SmartDashboard.putData(hatchIntake);
        SmartDashboard.putData(lift);
        SmartDashboard.putData(cargoIntake);
        SmartDashboard.putData(climbFront);
        SmartDashboard.putData(climbBack);
        SmartDashboard.putData(cargoGroundIntake);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        //Making two cameras 
        hatchCam = CameraServer.getInstance().startAutomaticCapture(0);
        cargoCam = CameraServer.getInstance().startAutomaticCapture(1);

        //Make a virtual cam to switch between (see startCam method)
        camera = CameraServer.getInstance().addSwitchedCamera(camName);

        //Make sure the cameras won't turn off
        hatchCam.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        cargoCam.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

        startCam("Hatch");

        
        lift.liftTalon.setSelectedSensorPosition(0);
        driveTrain.navx.reset();

        //hatchIntake.yeeHaw(2);
        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){
        driveTrain.allStop();
        lift.liftStop();
        climbFront.ClimbStop();
        climbBack.ClimbStop();
        cargoGroundIntake.CargoGroundFeed(0);
        cargoIntake.CargoIntakeFeedWheel(0);
        cargoGroundIntake.CargoDeploy(false);
        hatchIntake.HatchIntakeExtend(false,false);
        hatchIntake.HatchIntakeGrab(false);

        driveTrain.leftTalonDrive.setNeutralMode(NeutralMode.Coast);
        driveTrain.rightTalonDrive.setNeutralMode(NeutralMode.Coast);
        driveTrain.rightTalonDrive.setSelectedSensorPosition(0);
        driveTrain.leftTalonDrive.setSelectedSensorPosition(0);

        isAutoTime = false;
        disabledMode = true;
        lightCode.setLightSequence(.8);
        limeLight.setDouble(1);
    }

    @Override
    public void disabledPeriodic() {
        disabledMode = true;
        if(oi.autoAlignBtn.get()){
            limeLight.setDouble(3);
        } else{
            limeLight.setDouble(1);
        }
        lightCode.setLightSequence(.8);
        dashUpdate();
        Scheduler.getInstance().run();

        if(oi.leftJoy.getTrigger()){
            startCam("Cargo");
        } else{
            startCam("Hatch");
        }
    }

    @Override
    public void autonomousInit() {
        driveTrain.allStop();
        lift.liftStop();
        climbFront.ClimbStop();
        climbBack.ClimbStop();
        cargoGroundIntake.CargoGroundFeed(0);
        cargoIntake.CargoIntakeFeedWheel(0);
        cargoGroundIntake.CargoDeploy(false);
        hatchIntake.HatchIntakeExtend(false,false);
        hatchIntake.HatchIntakeGrab(false);

        isAutoTime = true;
        disabledMode = false;

        driveTrain.navx.zeroYaw();
        lightCode.setLightSequence(.525);
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        dashUpdate();
        Scheduler.getInstance().run();
        lightCode.setLightSequence(.525);
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        
        driveTrain.allStop();
        lift.liftStop();
        climbFront.ClimbStop();
        climbBack.ClimbStop();
        cargoGroundIntake.CargoGroundFeed(0);
        cargoIntake.CargoIntakeFeedWheel(0);
        cargoGroundIntake.CargoDeploy(false);
        hatchIntake.HatchIntakeExtend(false,false);
        hatchIntake.HatchIntakeGrab(false);

        driveTrain.rightTalonDrive.setSelectedSensorPosition(0);
        driveTrain.leftTalonDrive.setSelectedSensorPosition(0);
        isAutoTime = false;
        disabledMode = false;
        if (autonomousCommand != null) autonomousCommand.cancel();

    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        dashUpdate();
        
    }
    void dashUpdate() {
        counter++;
        if(counter > 5){
            SmartDashboard.putNumber("Gyro Angle", driveTrain.navx.getYaw());
            SmartDashboard.putNumber("Gyro Pitch", driveTrain.navx.getPitch());
            SmartDashboard.putNumber("Gyro Roll", driveTrain.navx.getRoll());
            SmartDashboard.putNumber("Left Enc", driveTrain.leftTalonDrive.getSelectedSensorPosition());
            SmartDashboard.putNumber("Right Enc", driveTrain.rightTalonDrive.getSelectedSensorPosition());
    
            SmartDashboard.putNumber("Lift Enc", lift.liftTalon.getSelectedSensorPosition());
    
            SmartDashboard.putBoolean("Cargo Sensor", cargoIntake.cargoDistSensor.get());
            SmartDashboard.putBoolean("Hatch Sensor", hatchIntake.hatchLoadingSwitch.get());

            SmartDashboard.putNumber("Auto Number", autoCommandNum);
            SmartDashboard.putString("Auto Name", autoName);
            SmartDashboard.putString("Auto Start Position", autoStartPosn);
            SmartDashboard.putBoolean("Lift top limit", lift.liftTalon.getSensorCollection().isFwdLimitSwitchClosed());
            SmartDashboard.putBoolean("Lift bottom limit", lift.liftTalon.getSensorCollection().isRevLimitSwitchClosed());
            SmartDashboard.putBoolean("Front climb limit", climbFront.getTopLimitSwitch());
            SmartDashboard.putBoolean("Rear climb limit", climbBack.climbMtr2.getSensorCollection().isFwdLimitSwitchClosed());

            SmartDashboard.putNumber("Lift Motor", lift.liftTalon.get());

            SmartDashboard.putBoolean("OS Button Engaged", oi.OSBtn.get());
            SmartDashboard.putBoolean("Straight Drive running", driveSticks.straightDrive);
            counter = 0;
        }
               
    }
    

    public static void climbGyro(double power){// use to autonomously climb and keep robot level
        double gkp = .01;//COMP ROBOT = .01

        double gyroError = (driveTrain.navx.getRoll() + Constants.rollCorrection);

        //PID for climb motors
        climbFront.ClimbFrontUp((power-(gkp*gyroError))-.1);
        //climbFront.climbMtr1.set(power-(gkp*gyroError));
        climbBack.climbMtr2.set(ControlMode.PercentOutput, power+(gkp*gyroError) + .2);
        
/*      //FOR PRACTICE ROBOT
        climbFront.climbMtr1.set(ControlMode.PercentOutput, power+(gkp*gyroError));
        climbBack.climbMtr2.set(ControlMode.PercentOutput, power + .1 -(gkp*gyroError));
        SmartDashboard.putNumber("ClimbFront Value", power+(gkp*gyroError));
        SmartDashboard.putNumber("ClimbBack Value", power + .1 -(gkp*gyroError));
*/
    }

    public static void climbHoldBack(double speed, double roll){
        double gkp = .01;
        double gyroError = (driveTrain.navx.getRoll() + Constants.rollCorrection) - roll;
        climbBack.climbMtr2.set(ControlMode.Position, speed + .1 -(gkp*gyroError));
    }

    public static void startCam(String whichCam){
        if(whichCam.contentEquals(whichOne)){//if cam not already set, set it
            if(whichOne.contentEquals("Cargo")){//Don't compare strings with ==
                camera.setSource(cargoCam);//setting virtual cam's source to cargoCam
            } else if(whichOne.contentEquals("Hatch")){
                camera.setSource(hatchCam);//setting virtual cam's source to hatchCam
            }
        }
        whichOne = whichCam;
    }

    public static boolean getLimelightValidTarget(){
        double TV = tv.getDouble(0.0);
        boolean validTar = false;
        validTar = TV<1? false : true;
        return validTar;
    }
    
}
