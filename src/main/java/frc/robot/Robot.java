// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.rmi.dgc.VMID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.OI;
import frc.robot.Logging.NerdyBadLog;
import frc.robot.subsystems.Drive;

public class Robot extends TimedRobot {
  private static OI oi;
  private static Drive drive;
  
  

  @Override
  public void robotInit() {

    oi = new OI();

    drive = new Drive();

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    
    
    
    
    NerdyBadLog.initAndLog("/home/lvuser/logs/", "Test5", 0.02, drive);
  }


  @Override
  public void teleopPeriodic() {
    
    double leftInput = oi.ps4Controller.getLeftY();
    double rightInput = oi.ps4Controller.getRightY();

    drive.rightMaster.set(ControlMode.PercentOutput, rightInput);
    drive.leftMaster.set(ControlMode.PercentOutput, leftInput);
  }


  
}