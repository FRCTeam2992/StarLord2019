/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2992.StarLord2019.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autoLeftSideLeftRocket extends CommandGroup {
  /**
   * Add your docs here.
   */
  public autoLeftSideLeftRocket() {
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
    
    addSequential(new AutoDriveFwd(41.3, 0.5, 2, true, 0));
    addSequential(new AutoDriveTurn(61.25, .5, 2));
    addSequential(new AutoDriveFwd(195.525, .5, 10, true, 61.25));
    addParallel(new HatchIntakeExtend(true,true));
    addSequential(new AutoDriveFwd(23, .5, 5, true, 61.25));
    addSequential(new HatchIntakeGrab(false));
    addSequential(new HatchIntakeExtend(false,false));
    addSequential(new AutoDriveFwd(-10, .5, 2, true, 61.25));

  }
}
