package frc.robot.subsystems;

import com.nerdherd.lib.drivetrain.singlespeed.Drivetrain;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Drive extends Drivetrain {
    private static VictorSPX[] leftFollowers = new VictorSPX[] {
        new VictorSPX(19), new VictorSPX(20)
    };
    private static VictorSPX[] rightFollowers = new VictorSPX[] {
        new VictorSPX(3), new VictorSPX(4)
    };

    public Drive() {
        super(1, 2, leftFollowers, rightFollowers, true, false);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        super.reportToSmartDashboard();
    }
}
