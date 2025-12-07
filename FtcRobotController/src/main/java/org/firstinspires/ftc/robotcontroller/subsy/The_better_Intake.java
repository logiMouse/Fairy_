package org.firstinspires.ftc.robotcontroller.subsy;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class The_better_Intake {

    DcMotorEx right_intake;



    public The_better_Intake(HardwareMap hardwareMap){

        right_intake = hardwareMap.get(DcMotorEx.class, "RI");




    }


    public void forward (){
        right_intake.setDirection(DcMotorSimple.Direction.REVERSE);
        right_intake.setPower(1);


    }

    public void backward (){
        right_intake.setPower(1);


    }
    public class IntakeJamController {
    // ===== Tunables (start conservative; tighten after logging) =====
    private static final double P_MIN = 0.55;            // min commanded power to consider
    private static final double V_MIN_SET = 0.40;        // fraction of nominal velocity to set jam
    private static final double V_MIN_CLEAR = 0.55;      // fraction of nominal to clear jam
    private static final long T_HOLD_MS = 150;           // sustain low velocity before jam
    private static final long T_CLEAR_MS = 150;          // sustain high velocity to clear jam
    private static final long STARTUP_BLANK_MS = 250;    // blank after power changes
    private static final int MAX_AUTO_RECOVERY = 3;      // attempts before requiring driver
    private static final long RECOVERY_WINDOW_MS = 8000; // rate-limit window
    private static final double REVERSE_POWER = -0.45;   // gentle reverse
    private static final long REVERSE_MS = 175;          // reverse pulse duration
    private static final long PAUSE_MS = 75;             // settle pause

    // ===== Wiring =====
    private final DcMotorEx intake;
    private final Supplier<Double> nominalVelocitySupplier; // measured at runtime (ticks/sec)
    private final Runnable driverAlert;                     // LEDs/rumble/telemetry
    private final Consumer<Double> setIntakePower;          // your motor power setter

    // ===== State =====
    private long lastPowerChangeMs = 0;
    private long lowVelStartMs = -1;
    private long highVelStartMs = -1;
    private boolean jamActive = false;
    private int autoRecoverCount = 0;
    private long recoverWindowStartMs = 0;
    private double lastCommandedPower = 0.0;

    public IntakeJamController(DcMotorEx intake,
                               Supplier<Double> nominalVelocitySupplier,
                               Consumer<Double> setIntakePower,
                               Runnable driverAlert) {
        this.intake = intake;
        this.nominalVelocitySupplier = nominalVelocitySupplier;
        this.setIntakePower = setIntakePower;
        this.driverAlert = driverAlert;
        this.recoverWindowStartMs = System.currentTimeMillis();
    }

    public void commandPower(double power) {
        double clipped = Math.max(-1.0, Math.min(1.0, power));
        setIntakePower.accept(clipped);
        lastCommandedPower = clipped;
        lastPowerChangeMs = System.currentTimeMillis();
        // Reset timing windows on significant change
        lowVelStartMs = -1;
        highVelStartMs = -1;
    }

    public void update(boolean downstreamBeamTriggered) {
        long now = System.currentTimeMillis();
        double cmd = lastCommandedPower;
        double vel = intake.getVelocity(); // ticks/sec
        double vNom = Math.max(1.0, nominalVelocitySupplier.get()); // avoid divide-by-zero

        boolean startupBlank = (now - lastPowerChangeMs) < STARTUP_BLANK_MS;
        boolean powerHigh = Math.abs(cmd) >= P_MIN;

        // Normalize velocity to fraction of nominal
        double vFrac = Math.min(1.5, Math.abs(vel) / vNom); // clip for stability
        boolean velLow = vFrac <= V_MIN_SET;
        boolean velHigh = vFrac >= V_MIN_CLEAR;

        // Track low-velocity and high-velocity durations
        if (velLow && powerHigh && !startupBlank) {
            if (lowVelStartMs < 0) lowVelStartMs = now;
        } else {
            lowVelStartMs = -1;
        }
        if (velHigh && powerHigh && !startupBlank) {
            if (highVelStartMs < 0) highVelStartMs = now;
        } else {
            highVelStartMs = -1;
        }

        // Optional flow confirmation to reduce false trips (if you have a downstream beam-break)
        boolean flowBlocked = powerHigh && !downstreamBeamTriggered;

        // Trigger jam if low velocity sustained
        boolean holdMet = (lowVelStartMs > 0) && ((now - lowVelStartMs) >= T_HOLD_MS);
        if (!jamActive && holdMet /* && flowBlocked */) {
            triggerJam(now);
        }

        // Clear jam if high velocity sustained
        boolean clearMet = (jamActive && (highVelStartMs > 0) && ((now - highVelStartMs) >= T_CLEAR_MS));
        if (clearMet) {
            clearJam();
        }
    }

    private void triggerJam(long now) {
        jamActive = true;

        // Rate-limit auto recover attempts
        if ((now - recoverWindowStartMs) > RECOVERY_WINDOW_MS) {
            recoverWindowStartMs = now;
            autoRecoverCount = 0;
        }

        if (autoRecoverCount < MAX_AUTO_RECOVERY) {
            autoRecoverCount++;
            // Auto-unjam routine
            double forwardResume = Math.copySign(0.65, lastCommandedPower);
            setIntakePower.accept(REVERSE_POWER);
            sleep(REVERSE_MS);
            setIntakePower.accept(0.0);
            sleep(PAUSE_MS);
            setIntakePower.accept(forwardResume);
        } else {
            // Stop and alert driver
            setIntakePower.accept(0.0);
            driverAlert.run();
        }
    }

    private void clearJam() {
        jamActive = false;
        // Do not reset autoRecoverCount; let rate-limit logic handle windowing
    }

    public boolean isJammed() {
        return jamActive;
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
//Use a nominal velocity measured at your typical intake power (e.g., 0.7). Start with V_MIN_SET ≈ 0.40 and V_MIN_CLEAR ≈ 0.55, then tighten after collecting data.


}
