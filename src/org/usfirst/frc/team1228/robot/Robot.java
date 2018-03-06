/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2018. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/* Credits: <Gurvinder Singh>                                                 */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team1228.robot;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
public class Robot extends IterativeRobot{
	@Override public void robotInit(){
		RobotMap.MAIN_DIFFERENTIAL_DRIVETRAIN.setSafetyEnabled(true);
		try{RobotMap.GYROSCOPE_YAW = new AHRS(SerialPort.Port.kUSB);}
		catch(RuntimeException x){
	          DriverStation.reportError("Error instantiating GYROSCOPE_YAW:  " + x.getMessage(), true);}}
	
	@Override public void autonomousInit(){
		RobotMap.LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);
		RobotMap.RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(false);
		RobotMap.GYROSCOPE_YAW.zeroYaw();
		RobotMap.GYROSCOPE_YAW.resetDisplacement();
		AutonomousCommand.AUTONOMOUS_GAMEDATA = DriverStation.getInstance().getGameSpecificMessage();
		DriverStation.reportWarning("Autonomous Initiated", false);}
	
	@Override public void autonomousPeriodic(){AutonomousCommand.drive();}
	
	@Override public void teleopInit(){
		RobotMap.LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);
		RobotMap.RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.setInverted(true);
		DriverStation.reportWarning("Teleop Initiated", false);}
	
	@Override public void teleopPeriodic(){
		if(isOperatorControl() && isEnabled()){
			OperatorCommand.updateLiftGearbox();
			OperatorCommand.updateIntakeGroup();
			OperatorCommand.updateDrivetrain();}}
	
	@Override public void testPeriodic(){}
	@Override public void disabledInit(){}
	@Override public void robotPeriodic(){}
	@Override public void disabledPeriodic(){}}
