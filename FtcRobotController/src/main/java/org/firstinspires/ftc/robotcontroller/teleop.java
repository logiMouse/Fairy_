package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class teleop extends LinearOpMode{

    @Override
    public void runOpMode(){
        // defining motors for wheels ( left (L) and right (R) ) pg 1 ( for arcade )
        DcMotor LMotor = hardwareMap.get( DcMotor.class, "LMotor");
        DcMotor RMotor = hardwareMap.get(DcMotor.class, "RMotor");
        // motor reverse code ( to see what to reverse comment for test) pg2
        //LMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //dRMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()){
            // joystick def L = left R= right y= y value x = x value
            double LY = gamepad1.left_stick_y;
            double RX = gamepad1.right_stick_x;

            // motor speed according to joystick values
            LMotor.setPower(LY + RX);
            RMotor.setPower(LY - RX);



      }



    }


}

