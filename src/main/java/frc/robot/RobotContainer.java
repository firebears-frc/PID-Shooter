package frc.robot;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {

    private final PIDController controller;

    private final ShooterSubsystem shooterSubsystem;

    public RobotContainer() {
        controller = new PIDController(1.0, 0.0, 0.0);
        shooterSubsystem = new ShooterSubsystem(controller);
        shooterSubsystem.setSetpoint(0.5);
        shooterSubsystem.setDefaultCommand(getAutonomousCommand());
    }

    public Command getAutonomousCommand() {
        return new PIDCommand(
            controller,
            () -> 0.2,
            0.1,
            (x) -> shooterSubsystem.output(x),
            shooterSubsystem
        );
    }
}
