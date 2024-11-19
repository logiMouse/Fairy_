package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class Robot_Auto extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor Left_Motor = hardwareMap.get(DcMotor.class, "Left_Motor");
        DcMotor Right_Motor = hardwareMap.get(DcMotor.class, "Right_Motor");
        DcMotor Arm_Line = hardwareMap.get(DcMotor.class, "Arm_Line");
        DcMotor Arm_upL = hardwareMap.get(DcMotor.class,"Arm_upL");
        DcMotor Arm_upR = hardwareMap.get(DcMotor.class,"Arm_upR");
        Servo servo_one = hardwareMap.get(Servo.class,"servo_one");
        Arm_upL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Arm_upR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // reverse test
        Left_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        Right_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        Arm_upL.setDirection(DcMotorSimple.Direction.REVERSE);
        Arm_Line.setDirection(DcMotorSimple.Direction.REVERSE);
        //servo_one.setDirection(Servo.Direction.REVERSE);

        waitForStart();
    }
}
