package org.firstinspires.ftc.robotcontroller.subsy;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.hardware.motors.RevRobotics20HdHexMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class The_better_train {
    DcMotorEx lf_bk;
    DcMotorEx lf_fd;
    DcMotorEx rt_bk;
    DcMotorEx rt_fd;

     public void drivetrain_motors (HardwareMap hardwareMap){
         lf_bk = hardwareMap.get(DcMotorEx.class, "lmb");
         rt_bk = hardwareMap.get(DcMotorEx.class, "rmb");
         rt_fd = hardwareMap.get(DcMotorEx.class, "bld");
         lf_fd = hardwareMap.get(DcMotorEx.class, "bld");


     }

     // the three axis will be defined here
     public void strife_calc(){
         double turn = gamepad1.right_stick_x;
         double strife = gamepad1.left_stick_x;
         double straight = gamepad1.left_stick_y;

         double [] speed = {
                 (turn- strife - straight),
                 (turn- strife - straight),
                 (turn- strife - straight)
         };

     }

}
