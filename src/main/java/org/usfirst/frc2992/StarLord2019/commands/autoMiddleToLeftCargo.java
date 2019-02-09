/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2992.StarLord2019.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autoMiddleToLeftCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public autoMiddleToLeftCargo() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    //Need to start at the bottom left corner at the center of the middle HAB
    addSequential(new AutoDriveFwd(47.5,.5, 2, true, 0));
    addSequential(new AutoDriveFwd(88.51,.5, 2, true, -1));
    addParallel(new HatchIntakeExtend(true));
    addSequential(new autoHatchScore());
    
  }
}
