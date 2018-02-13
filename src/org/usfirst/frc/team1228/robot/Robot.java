/*-------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST.All Rights Reserved.                */
/* Open Source Software. Credits: <Gurvinder Singh>, @tubbyyyy       */
/*-------------------------------------------------------------------*/
package org.usfirst.frc.team1228.robot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
public class Robot extends IterativeRobot{
	//<GLOBAL VARIABALS>
	private static final int LEFT_FRONT_MOTOR_ID = 2; 
	private static final int LEFT_REAR_MOTOR_ID = 3; 
	private static final int RIGHT_FRONT_MOTOR_ID = 1; 
	private static final int RIGHT_REAR_MOTOR_ID = 0; 
	private static final int LEFT_LIFT_MOTOR_ID = 4;
	private static final int RIGHT_LIFT_MOTOR_ID = 5;
	private static final int LEFT_INTAKE_MOTOR_ID = 6;
	private static final int RIGHT_INTAKE_MOTOR_ID = 7;
	private static final int PS4_CONTROLLER_ID = 0;
	//</GLOBAL VARIABALS>

	//<INITIATING JOYSTICK COMPONENTS>
	Joystick PS4_JOYSTICK_CONTROLLER = new Joystick(PS4_CONTROLLER_ID);
	Boolean INTAKE_BUTTON_IN_ONE = PS4_JOYSTICK_CONTROLLER.getRawButton(1);
	Boolean INTAKE_BUTTON_OUT_TWO = PS4_JOYSTICK_CONTROLLER.getRawButton(2);
	Boolean INTAKE_BUTTON_OUT_THREE = PS4_JOYSTICK_CONTROLLER.getRawButton(3);
	Boolean INTAKE_BUTTON_OUT_FOUR = PS4_JOYSTICK_CONTROLLER.getRawButton(4);
	Boolean THUMBSSTICK_BUTTON_ONE = PS4_JOYSTICK_CONTROLLER.getRawButton(11);
	Boolean THUMBSSTICK_BUTTON_TWO = PS4_JOYSTICK_CONTROLLER.getRawButton(12);
	//</INITIATING JOYSTICK COMPONENTS>
	
	//<INITIATING MOTORCONTROLLER COMPONENTS>
	Spark LEFT_FRONT_MOTOR_CONTROLLER = new Spark(LEFT_FRONT_MOTOR_ID);
	Spark LEFT_REAR_MOTOR_CONTROLLER = new Spark(LEFT_REAR_MOTOR_ID);
	Spark RIGHT_FRONT_MOTOR_CONTROLLER = new Spark(RIGHT_FRONT_MOTOR_ID);
	Spark RIGHT_REAR_MOTOR_CONTROLLER = new Spark(RIGHT_REAR_MOTOR_ID);
	
	Spark LEFT_LIFT_MOTOR_CONTROLLER = new Spark(LEFT_LIFT_MOTOR_ID);
	Spark RIGHT_LIFT_MOTOR_CONTROLLER = new Spark(RIGHT_LIFT_MOTOR_ID);
	
	Spark LEFT_INTAKE_MOTOR_CONTROLLER = new Spark(LEFT_INTAKE_MOTOR_ID);
	Spark RIGHT_INTAKE_MOTOR_CONTROLLER = new Spark(RIGHT_INTAKE_MOTOR_ID);
	//</INITIATING MOTORCONTROLLER COMPONENTS>
	
	//<INITIATING SPEEDCONTROLLER GROUPS>
	SpeedControllerGroup LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(LEFT_FRONT_MOTOR_CONTROLLER, LEFT_REAR_MOTOR_CONTROLLER); 
	SpeedControllerGroup RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(RIGHT_FRONT_MOTOR_CONTROLLER, RIGHT_REAR_MOTOR_CONTROLLER);
	DifferentialDrive MAIN_DIFFERENTIAL_DRIVETRAIN = new DifferentialDrive(LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP, RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP);
	
	SpeedControllerGroup LIFT_GEARBOX_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(LEFT_LIFT_MOTOR_CONTROLLER, RIGHT_LIFT_MOTOR_CONTROLLER);
	
	SpeedControllerGroup INTAKE_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(LEFT_INTAKE_MOTOR_CONTROLLER, RIGHT_INTAKE_MOTOR_CONTROLLER);
	//</INITIATING SPEEDCONTROLLER GROUPS>
	
	@Override public void robotInit(){
		LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true); RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);
		LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.setInverted(false);
		INTAKE_SPEED_CONTROLLER_GROUP.setInverted(false); //yet to be confirmed
		MAIN_DIFFERENTIAL_DRIVETRAIN.setSafetyEnabled(true);}
	
	@Override public void teleopPeriodic(){
		while(isOperatorControl() && isEnabled()) {
			MAIN_DIFFERENTIAL_DRIVETRAIN.arcadeDrive(PS4_JOYSTICK_CONTROLLER.getY(), -(PS4_JOYSTICK_CONTROLLER.getZ()));
			
			if(INTAKE_BUTTON_IN_ONE){
				INTAKE_SPEED_CONTROLLER_GROUP.set(.25); Timer.delay(.1); INTAKE_SPEED_CONTROLLER_GROUP.set(0);
			}else if(INTAKE_BUTTON_OUT_TWO){
				INTAKE_SPEED_CONTROLLER_GROUP.set(.50); Timer.delay(.1); INTAKE_SPEED_CONTROLLER_GROUP.set(0);
			}else if(INTAKE_BUTTON_OUT_THREE) {
				INTAKE_SPEED_CONTROLLER_GROUP.set(.75); Timer.delay(.1); INTAKE_SPEED_CONTROLLER_GROUP.set(0);
			}else if(INTAKE_BUTTON_OUT_FOUR) {
				INTAKE_SPEED_CONTROLLER_GROUP.set(1); Timer.delay(.1); INTAKE_SPEED_CONTROLLER_GROUP.set(0);}
			
			if(THUMBSSTICK_BUTTON_ONE){
				LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(.75); Timer.delay(.1); LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0);
			}else if(THUMBSSTICK_BUTTON_TWO){
				LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(-.75); Timer.delay(.1); LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0);}
			
			switch(PS4_JOYSTICK_CONTROLLER.getPOV()){
				case 0: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(.25); Timer.delay(.1); LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0); break;
				case 90: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(.50); Timer.delay(.1); LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0); break;
				case 180: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(.75); Timer.delay(.1); LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0); break;
				case 270: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(1); Timer.delay(.1); LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0); break;
				default: LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0); break;}}}
	
	@Override public void teleopInit(){}
	@Override public void testPeriodic(){}
	@Override public void disabledInit(){}
	@Override public void robotPeriodic(){}
	@Override public void autonomousInit(){}
	@Override public void disabledPeriodic(){}
	@Override public void autonomousPeriodic(){}}