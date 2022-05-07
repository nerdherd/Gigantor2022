package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriveConstants;

public class RobotContainer {
    public Drivetrain drivetrain;
    public PS4Controller ps4Controller;

    public JoystickButton dCross;
    public JoystickButton dCircle;

    public RobotContainer() {
        drivetrain = new Drivetrain();
        ps4Controller = new PS4Controller(0);

        dCross = new JoystickButton(ps4Controller, Button.kCross.value);
        dCircle = new JoystickButton(ps4Controller, Button.kCircle.value);
    }

    public void configureButtonBindings() {
        dCross.whenPressed(new InstantCommand(() ->
            drivetrain.setDriveShifterForward()));
        
        dCircle.whenPressed(new InstantCommand(() ->
            drivetrain.setDriveShifterReverse()));
    }

    public void joystickControls() {
        double leftInput = ps4Controller.getLeftY();
        double rightInput = ps4Controller.getRightY();
        double prevLeftOutput = drivetrain.getLeftMotorOutputPercent();
        double prevRightOutput = drivetrain.getRightMotorOutputPercent();

        double leftOutput = (DriveConstants.kDriveAlpha * leftInput) + (DriveConstants.kDriveOneMinusAlpha * prevLeftOutput);
        double rightOutput = (DriveConstants.kDriveAlpha * rightInput) + (DriveConstants.kDriveOneMinusAlpha * prevRightOutput);

        drivetrain.setPower(leftOutput, rightOutput);
    }    

    public void setNeutralModes() {
        drivetrain.setNeutralModes();
    }
}
