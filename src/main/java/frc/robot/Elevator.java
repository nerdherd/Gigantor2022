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

public class Elevator extends SubsystemBase {
    private TalonSRX vertical;

    public Elevator() {
        vertical = new TalonSRX(6);
    }

    public void setPower(double Speed) {
        vertical.set(ControlMode.PercentOutput, Speed);
    }

    public double getElevatorOutputPercent() {
        return vertical.getMotorOutputPercent();
    }

    public void setNeutralModes() {
        vertical.setNeutralMode(NeutralMode.Coast);
    }
    public double getVoltage() {
        return vertical.getBusVoltage();
    }
    public double getPosition() {
        return vertical.getSelectedSensorPosition();
    }
}
