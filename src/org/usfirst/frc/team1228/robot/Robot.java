/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2018. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/* Credits: <Gurvinder Singh>                                                 */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team1228.robot;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
public class Robot extends IterativeRobot{
	
	private static String AUTONOMOUS_GAMEDATA;

	//<INITIATING JOYSTICK COMPONENTS>
	private static final int PS4_CONTROLLER_ID = 0;
	Joystick PS4_JOYSTICK_CONTROLLER = new Joystick(PS4_CONTROLLER_ID);
	//</INITIATING JOYSTICK COMPONENTS>
	
	//<INITIATING GYROSCOPE>
	AHRS GYROSCOPE;
	public static float TEMPORARY_GYRO_HEADING = 0;
	//</INITIATING GYROSCOPE>
	
	//<INITIATING SENSORS>
	Encoder LIFT_GEARBOX_ENCODER = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	private static final double PULSES_PER_REVOLUTION = 1024;
	private static final double DISTANCE_PER_REVOLUTION = 1;
	//</INITIATING SENSORS>
	
	//<INITIATING MOTORCONTROLLER COMPONENTS>
	private static final int LEFT_FRONT_MOTOR_ID = 2; 
	private static final int LEFT_REAR_MOTOR_ID = 3; 
	private static final int RIGHT_FRONT_MOTOR_ID = 1; 
	private static final int RIGHT_REAR_MOTOR_ID = 0; 
	private static final int LEFT_LIFT_MOTOR_ID = 4;
	private static final int RIGHT_LIFT_MOTOR_ID = 5;
	private static final int LEFT_INTAKE_MOTOR_ID = 6;
	private static final int RIGHT_INTAKE_MOTOR_ID = 7;
	
	Spark LEFT_FRONT_MOTOR_CONTROLLER = new Spark(LEFT_FRONT_MOTOR_ID);
	Spark LEFT_REAR_MOTOR_CONTROLLER = new Spark(LEFT_REAR_MOTOR_ID);
	Spark RIGHT_FRONT_MOTOR_CONTROLLER = new Spark(RIGHT_FRONT_MOTOR_ID);
	Spark RIGHT_REAR_MOTOR_CONTROLLER = new Spark(RIGHT_REAR_MOTOR_ID);
	
	Spark LEFT_LIFT_MOTOR_CONTROLLER = new Spark(LEFT_LIFT_MOTOR_ID);
	Spark RIGHT_LIFT_MOTOR_CONTROLLER = new Spark(RIGHT_LIFT_MOTOR_ID);
	
	Spark LEFT_INTAKE_MOTOR_CONTROLLER = new Spark(LEFT_INTAKE_MOTOR_ID);
	Spark RIGHT_INTAKE_MOTOR_CONTROLLER = new Spark(RIGHT_INTAKE_MOTOR_ID);
	//</INITIATING MOTORCONTROLLER COMPONENTS>
	
	//<INITIATING ONTROLLER GROUPS>
	SpeedControllerGroup LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(LEFT_FRONT_MOTOR_CONTROLLER, LEFT_REAR_MOTOR_CONTROLLER); 
	SpeedControllerGroup RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(RIGHT_FRONT_MOTOR_CONTROLLER, RIGHT_REAR_MOTOR_CONTROLLER);
	DifferentialDrive MAIN_DIFFERENTIAL_DRIVETRAIN = new DifferentialDrive(LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP, RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP);
	
	SpeedControllerGroup LIFT_GEARBOX_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(LEFT_LIFT_MOTOR_CONTROLLER, RIGHT_LIFT_MOTOR_CONTROLLER);
	
	SpeedControllerGroup INTAKE_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(LEFT_INTAKE_MOTOR_CONTROLLER, RIGHT_INTAKE_MOTOR_CONTROLLER);
	//</INITIATING CONTROLLER GROUPS>
	
	@Override public void robotInit(){
		MAIN_DIFFERENTIAL_DRIVETRAIN.setSafetyEnabled(true);
		try { GYROSCOPE = new AHRS(SerialPort.Port.kUSB);}
		catch (RuntimeException x) {
	          DriverStation.reportError("Error instantiating Gyroscope:  " + x.getMessage(), true);}
		LIFT_GEARBOX_ENCODER.setMaxPeriod(.4);
		LIFT_GEARBOX_ENCODER.setDistancePerPulse(DISTANCE_PER_REVOLUTION / PULSES_PER_REVOLUTION);
		LIFT_GEARBOX_ENCODER.setMinRate(10);
		LIFT_GEARBOX_ENCODER.setReverseDirection(false);
		LIFT_GEARBOX_ENCODER.setSamplesToAverage(10);
	}
	
	@Override public void autonomousInit(){
		LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);
		RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(false);
		//GYROSCOPE.zeroYaw();
		AUTONOMOUS_GAMEDATA = DriverStation.getInstance().getGameSpecificMessage();}
	
	@Override public void autonomousPeriodic(){
		/*if(GYROSCOPE.getYaw() > TEMPORARY_GYRO_HEADING){
			LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(0.35);
			RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(0.3);
		}else if(GYROSCOPE.getYaw() < TEMPORARY_GYRO_HEADING){
			LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(0.3);
			RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(0.35);
		}*/
	}
	
	@Override public void teleopInit(){
		LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);
		RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);}
	
	@Override public void teleopPeriodic(){
		while(isOperatorControl() && isEnabled()){
			MAIN_DIFFERENTIAL_DRIVETRAIN.arcadeDrive(-(PS4_JOYSTICK_CONTROLLER.getY()), -(PS4_JOYSTICK_CONTROLLER.getZ()));
			
			if(PS4_JOYSTICK_CONTROLLER.getRawButton(1)){
				INTAKE_SPEED_CONTROLLER_GROUP.set(.50); Timer.delay(.1); INTAKE_SPEED_CONTROLLER_GROUP.set(0);
			}else if(PS4_JOYSTICK_CONTROLLER.getRawButton(2)){
				INTAKE_SPEED_CONTROLLER_GROUP.set(1); Timer.delay(.1); INTAKE_SPEED_CONTROLLER_GROUP.set(0);
			}else if(PS4_JOYSTICK_CONTROLLER.getRawButton(3)) {
				INTAKE_SPEED_CONTROLLER_GROUP.set(-.50); Timer.delay(.1); INTAKE_SPEED_CONTROLLER_GROUP.set(0);
			}else if(PS4_JOYSTICK_CONTROLLER.getRawButton(4)) {
				INTAKE_SPEED_CONTROLLER_GROUP.set(-1); Timer.delay(.1); INTAKE_SPEED_CONTROLLER_GROUP.set(0);}
			
			switch(PS4_JOYSTICK_CONTROLLER.getPOV()){
				case 0: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(.50);break;
				case 90: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(1);break;
				case 180: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(-.50);break;
				case 270: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(-1);break;
				default: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0); break;}
			Timer.delay(.1); LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0);}}
	
	@Override public void testPeriodic(){}
	@Override public void disabledInit(){}
	@Override public void robotPeriodic(){}
	@Override public void disabledPeriodic(){}}
