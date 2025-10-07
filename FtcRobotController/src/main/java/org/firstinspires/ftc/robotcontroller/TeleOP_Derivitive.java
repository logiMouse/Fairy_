package org.firstinspires.ftc.robotcontroller;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.subsy.The_better_train;

@TeleOp
public class TeleOP_Derivitive extends LinearOpMode {

    @Override
    public void runOpMode(){
        The_better_train The_better_train =  new The_better_train(hardwareMap);
        double left = gamepad1.left_stick_y;

        double left_x = gamepad1.left_stick_x;

        double right = gamepad1.right_stick_x;

        waitForStart();

        while (opModeIsActive()){

            The_better_train.strife_calc(left ,left_x, right );


        }


    }
}
