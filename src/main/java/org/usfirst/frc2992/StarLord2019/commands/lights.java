/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2992.StarLord2019.commands;

import org.usfirst.frc2992.StarLord2019.subsystems.LightCode;
import org.usfirst.frc2992.StarLord2019.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class lights extends Command {

  double m_pulseLength;

  public lights(double pulseLength) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    
    requires(Robot.lightCode);
    m_pulseLength = pulseLength;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.setInterruptible(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.lightCode.setLightSequence(m_pulseLength);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
