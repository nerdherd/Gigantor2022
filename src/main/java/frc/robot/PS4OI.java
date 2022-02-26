/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.nerdherd.lib.oi.AbstractOI;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.PS4Controller;

import com.nerdherd.lib.misc.NerdyMath;

// PS4 OI doesn't implement any trigger stuff cause we weren't using it (?)

public class PS4OI extends AbstractOI {

    public PS4Controller driverController;
    public PS4Controller operatorController;
    private double m_joystickDeadband;

    public final int CROSS = 1, CIRCLE = 2, SQUARE = 0, TRIANGLE = 3, BUTTON_LB = 4, BUTTON_RB = 5,
            SHARE = 8, OPTION = 9, BUTTON_LEFT_STICK = 10, BUTTON_RIGHT_STICK = 11;

    public PS4OI() {
        this(0);
    }

    public PS4OI(double deadband) {
        configJoystickDeadband(deadband);

        driverController = new PS4Controller(0);
        operatorController = new PS4Controller(1);
    }

    public void update() {
        // update the deadband from smartdashboard
        configJoystickDeadband(SmartDashboard.getNumber("deadband", getJoystickDeadband()));
    }

    public boolean getRawButton(int n) {
        return driverController.getRawButton(n);
    }

    @Override
    public double getDriveJoyLeftX() {
        return NerdyMath.handleDeadband(driverController.getLeftX(), m_joystickDeadband);
    }

    @Override
    public double getDriveJoyLeftY() {
        return NerdyMath.handleDeadband(-driverController.getLeftY(), m_joystickDeadband);
    }

    @Override
    public double getDriveJoyRightX() {
        return NerdyMath.handleDeadband(driverController.getRightX(), m_joystickDeadband);
    }

    @Override
    public double getDriveJoyRightY() {
        return NerdyMath.handleDeadband(-driverController.getRightY(), m_joystickDeadband);
    }

    // use left operator joystick

    @Override
    public double getOperatorJoyX() {
        return operatorController.getLeftX();
    }

    @Override
    public double getOperatorJoyY() {
        return -operatorController.getLeftY();
    } 

    public void configJoystickDeadband(double deadband) {
        m_joystickDeadband = deadband;
    }

    public double getJoystickDeadband() {
        return m_joystickDeadband;
    }

    @Override
    public void initLoggingData() {
        
    }
}