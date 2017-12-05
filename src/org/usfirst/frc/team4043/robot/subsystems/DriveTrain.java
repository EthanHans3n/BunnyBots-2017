package org.usfirst.frc.team4043.robot.subsystems;

import org.usfirst.frc.team4043.robot.Robot;
import org.usfirst.frc.team4043.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public RobotDrive drive;
	
	public DriveTrain() {
		super();
		drive = new RobotDrive(RobotMap.motorFL, RobotMap.motorBL, RobotMap.motorFR, RobotMap.motorBR);
	}
	
	public void drive(double throttle, double turn) {
		drive.arcadeDrive(throttle, turn);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

