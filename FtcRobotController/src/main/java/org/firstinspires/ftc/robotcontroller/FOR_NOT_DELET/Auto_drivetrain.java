/*package org.firstinspires.ftc.robotcontroller.FOR_NOT_DELET;

import androidx.core.math.MathUtils;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcontroller.FOR_NOT_DELET.constance;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class Auto_drivetrain {

    DcMotorEx Left_motor;
    DcMotorEx Right_motor;
    IMU imu;
    IMU.Parameters imu_param;

    YawPitchRollAngles robot_orientation;
    public Auto_drivetrain(HardwareMap hardwareMap){
        Left_motor = hardwareMap.get(DcMotorEx.class, "LM");
        Right_motor = hardwareMap.get(DcMotorEx.class,"RM");
        //Left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
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

    public void arcadedrive (double RightX, Double leftY){
        Right_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Left_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Left_motor.setPower(leftY - RightX);
        Right_motor.setPower(RightX + leftY);
    }
    public void move_ticks(int ticks, double sleep) {
        //reset encoder first
        this.Reset_Encoder();
        Right_motor.setTargetPosition(ticks);
        Left_motor.setTargetPosition(ticks);
        Right_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Left_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Right_motor.setPower(sleep);
        Left_motor.setPower(sleep);
    }

    public void Reset_Encoder(){
        Right_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Left_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    // AUTO/////////////////////////////////////////////////////////////////////////////
    public double get_Yaw() {
        robot_orientation = imu.getRobotYawPitchRollAngles();
        return robot_orientation.getYaw(AngleUnit.DEGREES);

    }
    public void rotate_degrees(double target){
        while (this.get_Yaw() != target) {
            double distance = target - this.get_Yaw();

            Left_motor.setPower(MathUtils.clamp(distance * constance.rot_kP, -1,1));
            Right_motor.setPower(MathUtils.clamp(distance * constance.rot_kP, -1,1));
        }
    }


    public boolean RM_busy(){
        return Right_motor.isBusy();
    }
    public boolean LM_busy(){
        return Left_motor.isBusy();
    }

}*/
