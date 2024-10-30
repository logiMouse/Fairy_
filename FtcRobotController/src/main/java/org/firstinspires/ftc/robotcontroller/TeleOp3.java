package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class TeleOp3 extends LinearOpMode {

    @Override
    public void runOpMode() {
        // motor def
        DcMotor LeftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        DcMotor RightMotor = hardwareMap.get(DcMotor.class, "RightMotor");
        // reverse test
        //LMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //dRMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            // motor to joystick ( def )
            double leftY = gamepad1.left_stick_y;
            double RightX = gamepad1.right_stick_x;
            // motor to joystick according to values
            LeftMotor.setPower(leftY + RightX);
            RightMotor.setPower(leftY - RightX);
        }
    }
}
