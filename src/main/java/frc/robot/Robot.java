// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.nerdherd.lib.drivetrain.teleop.TankDrive;
import com.nerdherd.lib.motor.motorcontrollers.NerdyTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Drive;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private static PS4OI ps4oi;
  public static Drive drive;

  private NerdyTalon elevator;

  @Override
  public void robotInit() {
    ps4oi = new PS4OI(0.2);
    drive = new Drive();

    elevator = new NerdyTalon(6);
  }

  @Override
  public void teleopPeriodic() {
    double leftPower = ps4oi.getDriveJoyLeftY();
    double rightPower = ps4oi.getDriveJoyRightY();

    SmartDashboard.putNumber("Left Drive Power", leftPower);
    SmartDashboard.putNumber("Right Drive Power", rightPower);
    drive.setPower(leftPower, rightPower);
    
    //CommandScheduler.getInstance().schedule(new TankDrive(Drive, ps4oi));

    double elevatorInput = ps4oi.getDriveJoyLeftX();

    SmartDashboard.putNumber("elevator Inp%ut", elevatorInput);
    elevator.set(ControlMode.PercentOutput, elevatorInput);

    //CommandScheduler.getInstance().run();
  }
}