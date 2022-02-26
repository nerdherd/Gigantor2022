// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.rmi.dgc.VMID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.OI;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private static OI oi;

  private Joystick leftStick;
  private Joystick rightStick;
  
  private TalonSRX rightMaster;
  private TalonSRX leftMaster;
  
  private VictorSPX rightSlave1;
  private VictorSPX rightSlave2;

  private VictorSPX leftSlave1;
  private VictorSPX leftSlave2;

  private TalonSRX elevator;

  public static double driveStraightkP = 2;
  public static double driveStraightkD = 0.7;

  public void drive(double leftSpeed, double rightSpeed, int waitTime) {
    leftMaster.set(ControlMode.Velocity, leftSpeed);
    rightMaster.set(ControlMode.Velocity, rightSpeed);

    Timer.delay(waitTime/1000);
  }

  public void PDDriveStraight(double target, double timeStep) {
      double error = 0;
      double oldError = 0;
      double threshold = 0.025;
      double speed = 0;

      while (error > threshold) {
          oldError = error;
          error = (ticksToRevolutions(rightMaster.getSelectedSensorPosition()) + ticksToRevolutions(leftMaster.getSelectedSensorPosition()))/2;

          speed = (driveStraightkP * error) + ((error - oldError) / (timeStep) * driveStraightkD);
          drive(speed, speed, 10);
      }
  }

  private double ticksToRevolutions(double ticks) {
      return (ticks / 2048);
  }

  @Override
  public void robotInit() {

    oi = new OI();

    rightMaster = new TalonSRX(2);
    leftMaster = new TalonSRX(1);
    
    rightSlave1 = new VictorSPX(3);
    rightSlave2 = new VictorSPX(4);
  
    leftSlave1 = new VictorSPX(19);
    leftSlave2 = new VictorSPX(20);

    elevator = new TalonSRX(6);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    rightMaster.setInverted(true);

    leftSlave1.follow(leftMaster);
    leftSlave2.follow(leftMaster);
    rightSlave1.follow(rightMaster);
    rightSlave2.follow(rightMaster);

    leftSlave1.setInverted(InvertType.FollowMaster);
    leftSlave2.setInverted(InvertType.FollowMaster);
    rightSlave1.setInverted(InvertType.FollowMaster);
    rightSlave2.setInverted(InvertType.FollowMaster);
  }


  @Override
  public void autonomousPeriodic() {
    drive(5, 5, 10);
  }

  @Override
  public void teleopPeriodic() {
    //m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
    
    double leftInput = oi.xboxController.getLeftY();
    double rightInput = oi.xboxController.getRightY();

    double elevatorInput = oi.xboxController.getLeftX();

    SmartDashboard.putNumber("elevator Input", elevatorInput);
    elevator.set(ControlMode.PercentOutput, elevatorInput);
    rightMaster.set(ControlMode.PercentOutput, rightInput);
    leftMaster.set(ControlMode.PercentOutput, leftInput);
  }
}