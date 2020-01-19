package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *
 */
public class PIDSubsystem1 extends PIDSubsystem {

    public WPI_TalonSRX talonSRX1;

    // Initialize your subsystem here
    public PIDSubsystem1() {
        super("PIDSubsystem1", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.2);
        getPIDController().setContinuous(false);
        getPIDController().setName("PID Subsystem 1", "PIDSubsystem Controller");
        addChild(getPIDController());

        talonSRX1 = new WPI_TalonSRX(12);

        // Use these to get going:
        // setSetpoint() - Sets where the PID controller should move the system
        // to
        // enable() - Enables the PID controller.
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new FirstCommand());
    }

    @Override
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0;
    }

    @Override
    protected void usePIDOutput(final double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        talonSRX1.pidWrite(0.5);
    }
}
