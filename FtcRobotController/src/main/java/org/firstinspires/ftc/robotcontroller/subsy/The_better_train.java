package org.firstinspires.ftc.robotcontroller.subsy;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.hardware.motors.RevRobotics20HdHexMotor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class The_better_train {
    
    DcMotorEx back_left;
    DcMotorEx front_left;
    DcMotorEx back_right;
    DcMotorEx front_right;

    IMU imu;
    IMU.Parameters imU_peramiters;


     public The_better_train (HardwareMap hardwareMap) {


         back_left = hardwareMap.get(DcMotorEx.class, "LMB");
         back_right = hardwareMap.get(DcMotorEx.class, "RMB");
         front_right = hardwareMap.get(DcMotorEx.class, "RMF");
         front_left = hardwareMap.get(DcMotorEx.class, "LMF");
         imu = hardwareMap.get(IMU.class, "imu");

         front_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         front_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


         imU_peramiters = new IMU.Parameters(
                 new RevHubOrientationOnRobot(
                         RevHubOrientationOnRobot.LogoFacingDirection.UP,
                         RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                 )
         );

         imu.initialize(imU_peramiters);
     }




     // the three axis will be defined here
     public void strife_calc(double turn, double strife, double straight){



         double [] speed = {
                 (turn + strife + straight),
                 (turn - strife - straight),
                 (turn - strife + straight),
                 (turn + strife - straight)
         };

         double max = Math.abs(speed[0]);

         for (int i = 0; i < speed.length; i++) {
             if ( max < Math.abs(speed[i]) ) max = Math.abs(speed[i]);
         }


         if (max > 1) {
             for (int i = 0; i < speed.length; i++) speed[i] /= max;
         }


         front_left.setPower(speed[0]);
         front_right.setPower(speed[1]);
         back_left.setPower(speed[2]);
         back_right.setPower(speed[3]);





     }

    public void driveInches(double inches, double power) {
    int ticksPerRev = 560; // GoBilda/REV HD Hex motor ticks
    double wheelDiameter = 4.0; // change if not using 4" wheels
    double ticksPerInch = ticksPerRev / (Math.PI * wheelDiameter);

    int moveTicks = (int) (inches * ticksPerInch);

    // Reset encoders
    setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    // Set target positions
    front_left.setTargetPosition(moveTicks);
    front_right.setTargetPosition(moveTicks);
    back_left.setTargetPosition(moveTicks);
    back_right.setTargetPosition(moveTicks);

    setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

    // Apply power
    front_left.setPower(power);
    front_right.setPower(power);
    back_left.setPower(power);
    back_right.setPower(power);

    // Wait until done
    while (front_left.isBusy() && front_right.isBusy() && back_left.isBusy() && back_right.isBusy()) {
        // You can add telemetry if you want
    }

    // Stop motors
    front_left.setPower(0);
    front_right.setPower(0);
    back_left.setPower(0);
    back_right.setPower(0);

    setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
}



}
