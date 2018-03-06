/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2018. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/* Credits: <Gurvinder Singh>                                                 */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team1228.robot;
import edu.wpi.first.wpilibj.Timer;
public class AutonomousCommand{
	
	//<DRIVERSTATION ALLIANCE DATA>
		public static String AUTONOMOUS_GAMEDATA;
	//</DRIVERSTATION ALLIANCE DATA>
			
	static double motorvaluesone = 0.0;
	static double motorvaluestwo = 0.0;
	
	public static void drive(){
		if(RobotMap.GYROSCOPE_YAW.getYaw() > RobotMap.TEMPORARY_GYRO_HEADING + 5){
			motorvaluesone = 0.6;
			motorvaluestwo = 0.5;
		}else if(RobotMap.GYROSCOPE_YAW.getYaw() > RobotMap.TEMPORARY_GYRO_HEADING){
			motorvaluesone = 0.60;
			motorvaluestwo = 0.53;}
		
		if(RobotMap.GYROSCOPE_YAW.getYaw() < RobotMap.TEMPORARY_GYRO_HEADING - 5){
			motorvaluesone = 0.5;
			motorvaluestwo = 0.6;
		}else if(RobotMap.GYROSCOPE_YAW.getYaw() < RobotMap.TEMPORARY_GYRO_HEADING){
			motorvaluesone = 0.53;
			motorvaluestwo = 0.60;}
		
		RobotMap.LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(motorvaluesone);
		RobotMap.RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(motorvaluestwo);
		Timer.delay(0.05);
		RobotMap.LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(motorvaluesone/1.5);
		RobotMap.RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP.set(motorvaluestwo/1.5);
	}
}