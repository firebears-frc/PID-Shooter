package frc.robot;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {

    private final PIDController controller;

    private final ShooterSubsystem shooterSubsystem;

    public RobotContainer() {
        controller = new PIDController(1.0, 0.0, 0.0);
        shooterSubsystem = new ShooterSubsystem(controller);
        shooterSubsystem.setDefaultCommand(getAutonomousCommand());
    }

    public Command getAutonomousCommand() {
        return new PIDCommand(
            controller,
            () -> 0.5,
            0.1,
            (output, setpoint) -> shooterSubsystem.useOutput(output, setpoint),
            shooterSubsystem
        );
    }
}