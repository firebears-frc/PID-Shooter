package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class ShooterSubsystem extends PIDSubsystem {

    private final WPI_TalonSRX srx;

    public ShooterSubsystem(PIDController c) {
        super(c);
        srx = new WPI_TalonSRX(12);
    }

    @Override
    protected double getMeasurement() {
        return 0.2;
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        srx.pidWrite(output);
    }

    public void output(double output) {
        srx.pidWrite(output);
    }
}
