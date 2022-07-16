
package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriveConstants;
import frc.robot.Elevator;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class RobotContainer {
    public Drivetrain drivetrain;
    public Elevator elevator;
    public PS4Controller ps4Controller;
    public PS4Controller ps4Controller2;

    public JoystickButton dCross;
    public JoystickButton dCircle;

    
    
    public RobotContainer() {
        drivetrain = new Drivetrain();
        elevator = new Elevator();
        ps4Controller = new PS4Controller(0);
        ps4Controller2 = new PS4Controller(1);

        dCross = new JoystickButton(ps4Controller2, Button.kCross.value);
        dCircle = new JoystickButton(ps4Controller2, Button.kCircle.value);

    }

    public void configureButtonBindings() {
        if (ps4Controller2.getCrossButtonPressed()){
            SmartDashboard.putString("Cross Button Status", "Got Pressed");
            drivetrain.setDriveShifterForward();
        }
        // dCross.whenPressed(new InstantCommand(() ->
        //     drivetrain.setDriveShifterForward()));
        
        if(ps4Controller2.getCircleButtonPressed()){
            SmartDashboard.putString("Circle Button Status", "Got Pressed");
            drivetrain.setDriveShifterReverse();
        }
        
        // dCircle.whenPressed(new InstantCommand(() ->
        //     drivetrain.setDriveShifterReverse()));
    }
    public static FileWriter myWriter;
    public static void initLog(){
    try {
        myWriter = new FileWriter("/home/lvuser/LOG3.txt");
    }
    catch(Exception e){}
    

}

    public void joystickControls() {
        double leftInput = ps4Controller.getLeftY();
        double rightInput = ps4Controller.getRightY();
        double prevLeftOutput = drivetrain.getLeftMotorOutputPercent();
        double prevRightOutput = drivetrain.getRightMotorOutputPercent();

        double leftOutput = (DriveConstants.kDriveAlpha * leftInput) + (DriveConstants.kDriveOneMinusAlpha * prevLeftOutput);
        double rightOutput = (DriveConstants.kDriveAlpha * rightInput) + (DriveConstants.kDriveOneMinusAlpha * prevRightOutput);

        drivetrain.setPower(leftOutput, rightOutput);

        double elevatorInput = -1 * ps4Controller2.getLeftY();
        double prevElevatorOutput = elevator.getElevatorOutputPercent();

        double verticalOutput = (DriveConstants.kDriveAlpha * elevatorInput) + (DriveConstants.kDriveOneMinusAlpha * prevElevatorOutput);

        elevator.setPower(verticalOutput);
        String line = "blank" + elevator.getVoltage() + "," + elevator.getPosition();

        
        if (ps4Controller2.getCrossButtonPressed()){
            SmartDashboard.putString("Cross Button Status", "Got Pressed");
            drivetrain.setDriveShifterForward();
        }
        if(ps4Controller2.getCircleButtonPressed()){
            SmartDashboard.putString("Circle Button Status", "Got Pressed");
            drivetrain.setDriveShifterReverse();
        }
        try{
            myWriter.write(line);
        }
        catch (Exception e) {
        }
        

    }    

    public void setNeutralModes() {
        drivetrain.setNeutralModes();
    }
}
