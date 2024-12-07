package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcontroller.subsy.arm_line;
import org.firstinspires.ftc.robotcontroller.subsy.arm_pivot;
import org.firstinspires.ftc.robotcontroller.subsy.drivetrain;
import org.firstinspires.ftc.robotcontroller.subsy.intake;

@Autonomous
public class Auto extends LinearOpMode {

    DcMotorEx Left_motor;
    DcMotorEx Right_motor;

    @Override
    public void runOpMode(){

        drivetrain drivetrain = new drivetrain(hardwareMap);
        intake intake = new intake(hardwareMap);
        arm_line arm_line = new arm_line(hardwareMap, telemetry);
        arm_pivot arm_pivot = new arm_pivot(hardwareMap, telemetry);

        Left_motor = hardwareMap.get(DcMotorEx.class, "LM");
        Right_motor = hardwareMap.get(DcMotorEx.class,"RM");

        waitForStart();


        drivetrain.move_ticks(1000,1);





    }
}
