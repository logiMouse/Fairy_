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
         back_left = hardwareMap.get(DcMotorEx.class, "lmb");
         back_right = hardwareMap.get(DcMotorEx.class, "rmb");
         front_right = hardwareMap.get(DcMotorEx.class, "bld");
         front_left = hardwareMap.get(DcMotorEx.class, "b0d");
         imu = hardwareMap.get(IMU.class, "imu");

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



}
