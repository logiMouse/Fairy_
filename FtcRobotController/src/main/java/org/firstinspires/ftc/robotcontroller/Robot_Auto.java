package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.subsy.drivetrain;

@Autonomous
public class Robot_Auto extends LinearOpMode {
    @Override
    public void runOpMode() {
        drivetrain drivetrain = new drivetrain( HardwareMap );



        drivetrain.Reset_encoder();
        waitForStart();
    }
}
