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
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
public class Robot extends IterativeRobot{
	
	//<DRIVERSTATION ALLIANCE DATA>
	private static String AUTONOMOUS_GAMEDATA;
	//</DRIVERSTATION ALLIANCE DATA>

	//<INITIATING JOYSTICK COMPONENTS>
	private static final int PS4_CONTROLLER_ID = 0;
	Joystick PS4_JOYSTICK_CONTROLLER = new Joystick(PS4_CONTROLLER_ID);
	//</INITIATING JOYSTICK COMPONENTS>
	
	//<INITIATING GYROSCOPE_YAW>
	AHRS GYROSCOPE_YAW;
	AHRS GYROSCOPE_EXPERIMENTAL;
	public static float TEMPORARY_GYRO_HEADING = 0;
	//</INITIATING GYROSCOPE_YAW>
	
	//<INITIATING ACCELER0METER>
	Accelerometer accel = new BuiltInAccelerometer(Accelerometer.Range.k4G); 
	//</INITIATING ACCELER0METER>
	
	//<INITIATING ENCODER>
	Encoder LIFT_GEARBOX_ENCODER = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	private static final double PULSES_PER_REVOLUTION = 1024;
	private static final double DISTANCE_PER_REVOLUTION = 1;
	//</INITIATING ENCODER>
	
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
		try { 
			GYROSCOPE_YAW = new AHRS(SerialPort.Port.kUSB);
			GYROSCOPE_EXPERIMENTAL = new AHRS(SerialPort.Port.kUSB1);}
		catch (RuntimeException x){
	          DriverStation.reportError("Error instantiating GYROSCOPE_YAW:  " + x.getMessage(), true);}}
	
	@Override public void autonomousInit(){
		LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);
		RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(false);
		GYROSCOPE_YAW.zeroYaw();
		GYROSCOPE_YAW.resetDisplacement();
		AUTONOMOUS_GAMEDATA = DriverStation.getInstance().getGameSpecificMessage();
		DriverStation.reportWarning("Autonomous Initiated", false);
	}
	
	double motorvaluesone = 0.0;
	double motorvaluestwo = 0.0;
	
	@Override public void autonomousPeriodic(){drive();}
	
	public void drive(){
		if(GYROSCOPE_YAW.getYaw() > TEMPORARY_GYRO_HEADING + 5){
			motorvaluesone = 0.6;
			motorvaluestwo = 0.5;
		}else if(GYROSCOPE_YAW.getYaw() > TEMPORARY_GYRO_HEADING){
			motorvaluesone = 0.60;
			motorvaluestwo = 0.53;}
		
		if(GYROSCOPE_YAW.getYaw() < TEMPORARY_GYRO_HEADING - 5){
			motorvaluesone = 0.5;
			motorvaluestwo = 0.6;
		}else if(GYROSCOPE_YAW.getYaw() < TEMPORARY_GYRO_HEADING){
			motorvaluesone = 0.53;
			motorvaluestwo = 0.60;}
		
		LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(motorvaluesone);
		RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(motorvaluestwo);
		Timer.delay(0.05);
		LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(motorvaluesone/1.5);
		RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(motorvaluestwo/1.5);
	}
	
	@Override public void teleopInit(){
		LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);
		RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);
		DriverStation.reportWarning("Teleop Initiated", false);
	}
	
	double LIFT_CONTROLLER_VALUE = 0;
	
	@Override public void teleopPeriodic(){
		if(isOperatorControl() && isEnabled()){
			updateLiftGearbox();
			updateDrivetrain();}}
	
	public void updateLiftGearbox(){
		switch(PS4_JOYSTICK_CONTROLLER.getPOV()){
			case 0: LIFT_CONTROLLER_VALUE = 0.5; break;
			case 90: LIFT_CONTROLLER_VALUE = 0.75; break;
			case 180: LIFT_CONTROLLER_VALUE = -0.5; break;
			case 270: LIFT_CONTROLLER_VALUE = -0.75; break;
			default: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0); break;}
		LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(LIFT_CONTROLLER_VALUE);
		LIFT_CONTROLLER_VALUE /= 1.5; Timer.delay(.05); 
		LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(LIFT_CONTROLLER_VALUE);}
	
	public void updateDrivetrain(){
		if(PS4_JOYSTICK_CONTROLLER.getZ() > 0.05 || PS4_JOYSTICK_CONTROLLER.getZ() < -0.05){
			MAIN_DIFFERENTIAL_DRIVETRAIN.arcadeDrive(-(PS4_JOYSTICK_CONTROLLER.getY()), -(PS4_JOYSTICK_CONTROLLER.getZ()));}}
	
	@Override public void testPeriodic(){}
	@Override public void disabledInit(){}
	@Override public void robotPeriodic(){}
	@Override public void disabledPeriodic(){}}
