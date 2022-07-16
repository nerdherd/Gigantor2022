// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.io.IOException;

import java.util.Random;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  public RobotContainer robotContainer;
  public static Path fileName;

  @Override
  public void robotInit() {
    CommandScheduler.getInstance().cancelAll();
    robotContainer = new RobotContainer();

    robotContainer.setNeutralModes();

    RobotContainer.initLog();
  }

  

  @Override
  public void autonomousPeriodic() {
    robotContainer.configureButtonBindings();
  }

  @Override
  public void teleopPeriodic() {
    robotContainer.joystickControls();
    robotContainer.configureButtonBindings();


  }
}