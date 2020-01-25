package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import java.nio.ByteBuffer;

public class LidarLite {

    static private final byte LIDAR_ADDRESS = 0x62;

    private final I2C lidar = new I2C(I2C.Port.kMXP, LIDAR_ADDRESS);

    private final ByteBuffer buf = ByteBuffer.allocateDirect(2);

    public LidarLite() {

    }

    /** Acquisition mode control */
    static private final byte ACQ_CONFIG_REG = 0x04;

    static private final int ACQ_BIT_REF_PROCESS = 1 << 6;
    static private final int ACQ_BIT_MEASURE_DELAY = 1 << 5;
    static private final int ACQ_BIT_REF_FILTER = 1 << 4;
    static private final int ACQ_BIT_QUICK_TERM = 1 << 3;
    static private final int ACQ_BIT_DEFAULT_COUNT = 1 << 2;
    static private final int ACQ_BITS_MODE = 0x03;

    static private final byte OUTER_LOOP_COUNT = 0x11;

    static private final byte ACQ_COMMAND = 0x00;

    static private final byte ACQ_CMD_RESET = 0x00;
    static private final byte ACQ_CMD_MEASURE_NO_BIAS = 0x03;
    static private final byte ACQ_CMD_MEASURE = 0x04;

    static private final byte MEASUREMENT = (byte) 0x8F;

    public void start() {
        writeRegister(ACQ_CONFIG_REG,
            ACQ_BIT_QUICK_TERM | ACQ_BIT_MEASURE_DELAY);
        writeRegister(OUTER_LOOP_COUNT, 0xFF);   
        writeRegister(ACQ_COMMAND, ACQ_CMD_MEASURE);
    }

    public void stop() {
        writeRegister(OUTER_LOOP_COUNT, 0x00);
    }

    public short getDistance() {
        return readShort(MEASUREMENT);
    }

    private void writeRegister(int address, int value) {
        lidar.write((byte) address, (byte) value);
    }

    private short readShort(int address) {
        lidar.read((byte) address, 2, buf);
        return buf.getShort(0);
    }
}