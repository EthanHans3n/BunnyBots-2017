package org.usfirst.frc.team4043.robot.subsystems;

import org.usfirst.frc.team4043.robot.Robot;
import org.usfirst.frc.team4043.robot.RobotMap;
import org.usfirst.frc.team4043.robot.commands.Drive;

import edu.wpi.first.wpilibj.Joystick;
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
		
		//drive.arcadeDrive(throttle, turn);
		
		drive.tankDrive(throttle, turn); //Throttle = left, turn = right
	}
	
	double inputSpeed = 0;
	double inputTurn = 0;
	
	public void drive(Joystick joy) {
		inputSpeed = -joy.getRawAxis(1);
		//inputTurn = -joy.getRawAxis(4);
		
		inputTurn = -joy.getRawAxis(5);	//For tank drive
		
		drive(inputSpeed, inputTurn);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
}

