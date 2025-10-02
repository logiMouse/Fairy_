package org.firstinspires.ftc.robotcontroller.subsy;

import androidx.core.math.MathUtils;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcontroller.constance;
public class drivetrain {
    DcMotorEx Left_motor;
    DcMotorEx Right_motor;
    IMU imu;
    IMU.Parameters imu_param;

    YawPitchRollAngles robot_orientation;
    public drivetrain(HardwareMap hardwareMap){
       Left_motor = hardwareMap.get(DcMotorEx.class, "LM");
       Right_motor = hardwareMap.get(DcMotorEx.class,"RM");
       Left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
       Right_motor.setDirection(DcMotorSimple.Direction.REVERSE);
       imu = hardwareMap.get(IMU.class, "imu");

       imu_param = new IMU.Parameters(
               new RevHubOrientationOnRobot(
                       RevHubOrientationOnRobot.LogoFacingDirection.UP,
                       RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
               )
       );

       imu.initialize(imu_param);
    }

    public void arcade (double RightX, Double leftY){
        Right_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Left_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Left_motor.setPower(leftY - RightX);
        Right_motor.setPower(RightX + leftY);
    }

    public void Reset_encoder(){
        Right_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Left_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

}
