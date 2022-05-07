package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private TalonSRX rightMaster;
    private TalonSRX leftMaster;

    private VictorSPX rightSlave1;
    private VictorSPX rightSlave2;
    private VictorSPX leftSlave1;
    private VictorSPX leftSlave2;

    private Compressor compressor;
    private DoubleSolenoid shifter;

    public Drivetrain() {
        rightMaster = new TalonSRX(RobotMap.kRightMasterTalonID);
        leftMaster = new TalonSRX(RobotMap.kLeftMasterTalonID);

        rightSlave1 = new VictorSPX(RobotMap.kRightSlave1VictorID);
        rightSlave2 = new VictorSPX(RobotMap.kRightSlave2VictorID);
        leftSlave1 = new VictorSPX(RobotMap.kLeftSlave1VictorID);
        leftSlave2 = new VictorSPX(RobotMap.kLeftSlave2VictorID);

        rightSlave1.follow(rightMaster);
        rightSlave2.follow(rightMaster);
        leftSlave1.follow(leftMaster);
        leftSlave2.follow(leftMaster);

        rightSlave1.setInverted(InvertType.FollowMaster);
        rightSlave2.setInverted(InvertType.FollowMaster);
        leftSlave1.setInverted(InvertType.FollowMaster);
        leftSlave2.setInverted(InvertType.FollowMaster);

        compressor = new Compressor(3, PneumaticsModuleType.CTREPCM);
        compressor.enableDigital();

        shifter = new DoubleSolenoid(3, PneumaticsModuleType.CTREPCM, RobotMap.kDriveShifterForwardID, RobotMap.kDriveShifterReverseID);
    }

    public void setPower(double leftSpeed, double rightSpeed) {
        leftMaster.set(ControlMode.PercentOutput, leftSpeed);
        rightMaster.set(ControlMode.PercentOutput, rightSpeed);
    }

    public void setDriveShifterForward() {
        shifter.set(Value.kForward);
    }

    public void setDriveShifterReverse() {
        shifter.set(Value.kReverse);
    }

    public double getLeftMotorOutputPercent() {
        return leftMaster.getMotorOutputPercent();
    }

    public double getRightMotorOutputPercent() {
        return rightMaster.getMotorOutputPercent();
    }

    public void setNeutralModes() {
        leftMaster.setNeutralMode(NeutralMode.Coast);
        rightMaster.setNeutralMode(NeutralMode.Coast);

        leftSlave1.setNeutralMode(NeutralMode.Coast);
        leftSlave2.setNeutralMode(NeutralMode.Coast);
        rightSlave1.setNeutralMode(NeutralMode.Coast);
        rightSlave2.setNeutralMode(NeutralMode.Coast);
    }
}
