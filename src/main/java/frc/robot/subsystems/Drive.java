// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Logging.Loggable;
import frc.robot.Logging.NerdyBadLog;

public class Drive extends SubsystemBase implements Loggable{
  public TalonSRX rightMaster;
  public TalonSRX leftMaster;
  
  public VictorSPX rightSlave1;
  public VictorSPX rightSlave2;

  public VictorSPX leftSlave1;
  public VictorSPX leftSlave2;

  
  /** Creates a new Drive. */
  public Drive() {
    rightMaster = new TalonSRX(2);
    leftMaster = new TalonSRX(1);
    
    rightSlave1 = new VictorSPX(3);
    rightSlave2 = new VictorSPX(4);
  
    leftSlave1 = new VictorSPX(19);
    leftSlave2 = new VictorSPX(20);

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
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getVoltageTalon(TalonSRX motor){
    return motor.getMotorOutputVoltage();
  }

  public double getVoltageVictor(VictorSPX motor){
    return motor.getMotorOutputVoltage();
  }

  public void initLoggingData(){
    NerdyBadLog.createTopic("RightMaster" + "/Voltage", () -> getVoltageTalon(rightMaster));
    NerdyBadLog.createTopic("LeftMaster" + "/Voltage", () -> getVoltageTalon(leftMaster));
    NerdyBadLog.createTopic("RightFollower1" + "/Voltage", () -> getVoltageVictor(rightSlave1));
    NerdyBadLog.createTopic("RightFollower2" + "/Voltage", () -> getVoltageVictor(rightSlave2));
    NerdyBadLog.createTopic("LeftFollower1" + "/Voltage", () -> getVoltageVictor(leftSlave1));
    NerdyBadLog.createTopic("LeftFollower2" + "/Voltage", () -> getVoltageVictor(leftSlave2));
  }
  
}
