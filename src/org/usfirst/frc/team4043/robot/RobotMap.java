package org.usfirst.frc.team4043.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static CANTalon motorFR = new CANTalon(4);
	public static CANTalon motorFL = new CANTalon(6);
	public static CANTalon motorBR = new CANTalon(5);
	public static CANTalon motorBL = new CANTalon(7);
	public static CANTalon jodMover1 = new CANTalon(3);
	public static CANTalon jodMover2 = new CANTalon(2);
	
	public static Solenoid shifter = new Solenoid(0, 1);
	public static Solenoid jodClamper = new Solenoid(0, 0);
	
	
}
