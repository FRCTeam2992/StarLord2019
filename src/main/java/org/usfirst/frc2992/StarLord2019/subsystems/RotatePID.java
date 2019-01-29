package org.usfirst.frc2992.StarLord2019.subsystems;


import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class RotatePID implements PIDOutput {
	private WPI_TalonSRX lDrive;
	private WPI_TalonSRX rDrive;
	
	
	public RotatePID (WPI_TalonSRX lMotor, WPI_TalonSRX rMotor) {
		lDrive = lMotor;
		rDrive = rMotor;
	}
	@Override
	public void pidWrite(double output) {
		lDrive.set(output);
		rDrive.set(output);
	}
}