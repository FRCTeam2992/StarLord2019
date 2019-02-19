/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc2992.StarLord2019.subsystems;
import edu.wpi.first.wpilibj.DigitalOutput;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc2992.StarLord2019.commands.*;

/**
 * Add your docs here.
 */
public class LightCode extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DigitalOutput lightCode;

  public LightCode(){
        lightCode = new DigitalOutput(2);
        lightCode.enablePWM(0.5);        
        lightCode.setPWMRate(1000);

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new lights(0));
  }

  public void setLightSequence(double pulseLength){
        lightCode.updateDutyCycle(pulseLength);    
  }

}
