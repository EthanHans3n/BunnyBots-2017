
package org.usfirst.frc.team4043.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4043.robot.commands.ClampJOD;
import org.usfirst.frc.team4043.robot.commands.ExampleCommand;
import org.usfirst.frc.team4043.robot.commands.GetI2C;
import org.usfirst.frc.team4043.robot.commands.MoveJODDown;
import org.usfirst.frc.team4043.robot.commands.MoveJODUp;
import org.usfirst.frc.team4043.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4043.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4043.robot.subsystems.JawsOfDeath;
import org.usfirst.frc.team4043.robot.subsystems.Shifter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static DriveTrain driveTrain;
	public static JawsOfDeath jod;
	public static Shifter shifter;
	public DigitalInput limitSwitch;
	
	boolean flase = false;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		jod = new JawsOfDeath();
		shifter = new Shifter();
		oi = new OI();
		chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		limitSwitch = new DigitalInput(1);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	
	double bucketAngle;
	int step = 1;
	double time;
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void autonomous() {
		bucketAngle = GetI2C.getData();
		
		if (step == 1) {
			if (bucketAngle > 50) {
				driveTrain.drive.arcadeDrive(.5, .1);
			} else if (bucketAngle < 50) {
				driveTrain.drive.arcadeDrive(.5, -.1);
			} else {
				driveTrain.drive.arcadeDrive(.5, 0);
			}
			if (limitSwitch.get()){
				step = 2;
				time = Timer.getFPGATimestamp();
			}
		} else if (step == 2) {
			if (Timer.getFPGATimestamp() < time + 1) {
				new MoveJODDown();
			} else {
				step = 3;
			}
		} else if (step == 3) {
			new ClampJOD();
			time = Timer.getFPGATimestamp();
			step = 4;
		} else if (step == 4) {
			if (Timer.getFPGATimestamp() < time + 1) {
				new MoveJODUp();
			} else {
				step = 5;
			}
		}
		
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//System.out.println(GetI2C.getData());
		System.out.println(limitSwitch.get());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
