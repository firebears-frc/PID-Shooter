package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

    private RobotContainer container;

    private Command autonomousCommand;

    @Override
    public void robotInit() {
        container = new RobotContainer();
    //  CommandScheduler.getInstance().registerSubsystem(container.vision);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = container.getAutonomousCommand();
        if (autonomousCommand != null)
            autonomousCommand.schedule();
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null)
            autonomousCommand.cancel();
        autonomousCommand = null;
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
        autonomousCommand = null;
    }
}
