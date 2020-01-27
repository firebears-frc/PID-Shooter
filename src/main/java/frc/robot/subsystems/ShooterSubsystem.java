package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    static private final int PID_LOOP_IDX = 0;
    static private final int TIMEOUT_MS = 30;
    static private final double P = 1.0;
    static private final double I = 0.0;
    static private final double D = 0.0;
    static private final double F = 0.0;
    static private final double RPM = 350.0 * 5.0;
    static private final double SENSOR_UNITS_PER_REV = 4096;
    static private final double GEAR_RATIO = 13.56;
    static private final double PER_MINUTE_100_MS = 600.0;

    private final TalonSRX srx;
    private int distance = 0;
    private int status = 0;

    /** Thread for I2C communication */
    private final Thread i2c_thread = new Thread() {
        @Override
        public void run() {
            LidarLite lidar = new LidarLite();
            lidar.startContinuous();
            while (true) {
                for (int i = 0; i < 10; i++) {
                    int d = lidar.getDistance();
                    if (d >= 0)
                        distance = d;
                    else {
                        System.err.println("Error reading distance");
                        break;
                    }
                    Timer.sleep(0.1);
                }
                status = lidar.getStatus();
            }
            lidar.stopContinuous();
        }
    }

    public ShooterSubsystem() {
        super();
        srx = new TalonSRX(12);
        i2c_thread.start();
        /*
        srx.configFactoryDefault();
        srx.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
                                         PID_LOOP_IDX,
                                         TIMEOUT_MS);
        srx.setSensorPhase(true);
        // Configure nominal / peak outputs
        srx.configNominalOutputForward(0, TIMEOUT_MS);
        srx.configNominalOutputReverse(0, TIMEOUT_MS);
        srx.configPeakOutputForward(1, TIMEOUT_MS);
        srx.configPeakOutputReverse(1, TIMEOUT_MS);
        // Config PIDF
        srx.config_kP(PID_LOOP_IDX, P, TIMEOUT_MS);
        srx.config_kI(PID_LOOP_IDX, I, TIMEOUT_MS);
        srx.config_kD(PID_LOOP_IDX, D, TIMEOUT_MS);
        srx.config_kF(PID_LOOP_IDX, F, TIMEOUT_MS);
*/
    }

    public void periodic() {
        SmartDashboard.putNumber("Distance", distance);
        SmartDashboard.putNumber("Status", status);
/*        double output = srx.getMotorOutputPercent();
        SmartDashboard.putNumber("Output", output);
        int velocity = srx.getSelectedSensorVelocity(PID_LOOP_IDX);
        SmartDashboard.putNumber("Velocity", velocity);
        // velocity in units per 100 ms
        double targetVelocity = RPM * SENSOR_UNITS_PER_REV
            / (PER_MINUTE_100_MS * GEAR_RATIO);
        srx.set(ControlMode.Velocity, targetVelocity);
    */
    }
}
