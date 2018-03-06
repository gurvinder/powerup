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
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
public class RobotMap{
	//<INITIATING PWM CONSTANTS>
		private static final int LEFT_FRONT_MOTOR_ID = 2; 
		private static final int LEFT_REAR_MOTOR_ID = 3; 
		private static final int RIGHT_FRONT_MOTOR_ID = 1; 
		private static final int RIGHT_REAR_MOTOR_ID = 0; 
		private static final int LEFT_LIFT_MOTOR_ID = 4;
		private static final int RIGHT_LIFT_MOTOR_ID = 5;
		private static final int LEFT_INTAKE_MOTOR_ID = 6;
		private static final int RIGHT_INTAKE_MOTOR_ID = 7;
	//</INITIATING PWM CONSTANTS>
			
	//<INITIATING DRIVETRAIN>
		static Spark LEFT_FRONT_MOTOR_CONTROLLER = new Spark(LEFT_FRONT_MOTOR_ID);
		static Spark LEFT_REAR_MOTOR_CONTROLLER = new Spark(LEFT_REAR_MOTOR_ID);
		static Spark RIGHT_FRONT_MOTOR_CONTROLLER = new Spark(RIGHT_FRONT_MOTOR_ID);
		static Spark RIGHT_REAR_MOTOR_CONTROLLER = new Spark(RIGHT_REAR_MOTOR_ID);
		
		static SpeedControllerGroup LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(LEFT_FRONT_MOTOR_CONTROLLER, LEFT_REAR_MOTOR_CONTROLLER); 
		static SpeedControllerGroup RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(RIGHT_FRONT_MOTOR_CONTROLLER, RIGHT_REAR_MOTOR_CONTROLLER);
		
		static DifferentialDrive MAIN_DIFFERENTIAL_DRIVETRAIN = new DifferentialDrive(LEFT_DRIVETRAIN_SPEED_CONTROLLER_GROUP, RIGHT_DRIVETRAIN_SPEED_CONTROLLER_GROUP);
	//</INITIATING DRIVETRAIN>
		
	//<INITIATING LIFT SUBSYSTEM>
		static Spark LEFT_LIFT_MOTOR_CONTROLLER = new Spark(LEFT_LIFT_MOTOR_ID);
		static Spark RIGHT_LIFT_MOTOR_CONTROLLER = new Spark(RIGHT_LIFT_MOTOR_ID);
		
		static SpeedControllerGroup LIFT_GEARBOX_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(LEFT_LIFT_MOTOR_CONTROLLER, RIGHT_LIFT_MOTOR_CONTROLLER);
	//<INITIATING LIFT SUBSYSTEM>
		
	//<INITIATING INTAKE SUBSYSTEM>
		static Spark LEFT_INTAKE_MOTOR_CONTROLLER = new Spark(LEFT_INTAKE_MOTOR_ID);
		static Spark RIGHT_INTAKE_MOTOR_CONTROLLER = new Spark(RIGHT_INTAKE_MOTOR_ID);
		
		static SpeedControllerGroup INTAKE_SPEED_CONTROLLER_GROUP = new SpeedControllerGroup(LEFT_INTAKE_MOTOR_CONTROLLER, RIGHT_INTAKE_MOTOR_CONTROLLER);
	//<INITIATING INTAKE SUBSYSTEM>
		
	//<INITIATING SENSORS>
		static AHRS GYROSCOPE_YAW;
		static AHRS GYROSCOPE_EXPERIMENTAL;
		public static float TEMPORARY_GYRO_HEADING = 0;
		
		Accelerometer accelerometer = new BuiltInAccelerometer(Accelerometer.Range.k4G);
	//</INITIATING SENSORS>
}