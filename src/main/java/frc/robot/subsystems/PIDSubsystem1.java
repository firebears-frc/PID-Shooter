package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *
 */
public class PIDSubsystem1 extends PIDSubsystem {

    public WPI_TalonSRX talonSRX1;

    // Initialize your subsystem here
    public PIDSubsystem1(PIDController c) {
        super(c);
        c.setTolerance(0.2);
        c.disableContinuousInput();

        talonSRX1 = new WPI_TalonSRX(12);

        // Use these to get going:
        // setSetpoint() - Sets where the PID controller should move the system
        // to
        // enable() - Enables the PID controller.
    }

    @Override
    protected double getMeasurement() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0;
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        talonSRX1.pidWrite(0.5);
    }
}
