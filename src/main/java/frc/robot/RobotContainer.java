package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.LidarLite;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;

public class RobotContainer {

    private final ShooterSubsystem shooterSubsystem;

    public final Vision vision;

    private final LidarLite lidarLite;

    public RobotContainer() {
        shooterSubsystem = new ShooterSubsystem();
        shooterSubsystem.setDefaultCommand(getAutonomousCommand());
        vision = new Vision();
        lidarLite = new LidarLite();
    }

    public Command getAutonomousCommand() {
        return new RunCommand(
            () -> shooterSubsystem.periodic(),
            shooterSubsystem
        );
    }
}