/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2018. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/* Credits: <Gurvinder Singh>                                                 */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team1228.robot;
import edu.wpi.first.wpilibj.Timer;
public class OperatorCommand{
	//<LIFT GEARBOX UPDATE>
		public static void updateLiftGearbox(){
			double LIFT_CONTROLLER_VALUE = 0;
			switch(OI.PS4_JOYSTICK_CONTROLLER.getPOV()){
				case 0: LIFT_CONTROLLER_VALUE = 0.5; break;
				case 90: LIFT_CONTROLLER_VALUE = 0.75; break;
				case 180: LIFT_CONTROLLER_VALUE = -0.5; break;
				case 270: LIFT_CONTROLLER_VALUE = -0.75; break;
				default: RobotMap.LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(0); break;}
			RobotMap.LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(LIFT_CONTROLLER_VALUE);
			LIFT_CONTROLLER_VALUE /= 1.5; Timer.delay(.05); 
			RobotMap.LIFT_GEARBOX_SPEED_CONTROLLER_GROUP.set(LIFT_CONTROLLER_VALUE);}
	//</LIFT GEARBOX UPDATE>
		
	//<INTAKE UPDATE>
		public static void updateIntakeGroup(){}
	//</INTAKE UPDATE>
		
	//<DRIVETRAIN UPDATE>
		public static void updateDrivetrain(){
			if(OI.PS4_JOYSTICK_CONTROLLER.getZ() > 0.05 || OI.PS4_JOYSTICK_CONTROLLER.getZ() < -0.05){
				RobotMap.MAIN_DIFFERENTIAL_DRIVETRAIN.arcadeDrive(-(OI.PS4_JOYSTICK_CONTROLLER.getY()), -(OI.PS4_JOYSTICK_CONTROLLER.getZ()));}}
	//</DRIVETRAIN UPDATE>
}
