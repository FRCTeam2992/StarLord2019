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



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Constants {
    public static final int liftEncDist = 4096;

    public static final double normalLiftUpSpeed = .8;
    public static final double normalLiftDownSpeed = 0.4;
    

    //Cargo lift heights
    public static final double topRocketCargoHeight = 66;
    public static final double middleRocketCargoHeight = 39;
    public static final double bottomRocketCargoHeight = 13;
    public static final double cargoLoadHeight = 28.5;
    public static final double cargoGroundHeight = -5;

    //Hatch lift heights
    public static final double topRocketHatchHeight = 53.5;
    public static final double middleRocketHatchHeight = 26;
    public static final double bottomRocketHatchHeight = -5;

    public static final double hatchLoadingGrabTimeout = 0.3;
    public static final double liftTimeout = 0.5;

    //VP Constants
    public static final double camHt = 44.5;//inches
    public static final double camOffset = 2.5;
    public static final double tarHt = 30;//inches
    public static final double camAngle = 53;

    //Climb Constants
    public static double upClimbSpeed = .85;
    public static double downClimbSpeed = -.65;
    public static double rollCorrection = -3;
    public static double climbAngleLimit = 7;

    //Drive Constants
    public static double slowSpeed = 2.5;

    //Lift height delta practice chassis to main chassis
    public static final double cargoDeltaPrac = -2; // Practice chassis cargo heights need to be 2 inches lower
     

}

