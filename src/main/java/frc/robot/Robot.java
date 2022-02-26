// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import com.nerdherd.lib.drivetrain.teleop.TankDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drive;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private static OI oi;
  private static PS4OI ps4oi;
  public static Drive Drive;

  //private TalonSRX elevator;

  @Override
  public void robotInit() {
    ps4oi = new PS4OI(0.2);
    Drive = new Drive();

    //elevator = new TalonSRX(6);
  }

  @Override
  public void teleopPeriodic() {
    //Drive.setPower(ps4oi.getDriveJoyLeftY(), ps4oi.getDriveJoyRightY());
    CommandScheduler.getInstance().schedule(new TankDrive(Drive, ps4oi));

    //double elevatorInput = oi.xboxController.getLeftX();

    //SmartDashboard.putNumber("elevator Inp%ut", elevatorInput);
    //elevator.set(ControlMode.PercentOutput, elevatorInput);

    CommandScheduler.getInstance().run();
  }
}