/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2992.StarLord2019.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc2992.StarLord2019.Constants;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class autoCargoLoadStation extends CommandGroup {
  /**
   * Add your docs here.
   */
  public autoCargoLoadStation() {
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
    addParallel(new CargoDeploySol(false));
    addParallel(new HatchIntakeExtend(false,true));
    addSequential(new LiftSetHeight(1, Constants.normalLiftUpSpeed, Constants.normalLiftDownSpeed, 2));
    addSequential(new CargoIntakeFeedWheel(1));//does have an isFinished true
    addSequential(new WaitCommand(.3));
    addParallel(new HatchIntakeExtend(false,false));
    addParallel(new CargoDeploySol(true));
    addSequential(new LiftSetHeight(0, 0.4, Constants.normalLiftDownSpeed, 4));//why was this changed - we're not going up here
    addSequential(new CargoDeploySol(false));
  }
}
