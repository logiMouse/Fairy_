package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class TeleOp3 extends LinearOpMode {

    @Override
    public void runOpMode() {
        //Def Motors
        DcMotor Left_Motor = hardwareMap.get(DcMotor.class, "Left_Motor");
        DcMotor Right_Motor = hardwareMap.get(DcMotor.class, "Right_Motor");
        DcMotor Arm_Line = hardwareMap.get(DcMotor.class, "Arm_Line");
        DcMotor Arm_upL = hardwareMap.get(DcMotor.class,"Arm_upL");
        DcMotor Arm_upR = hardwareMap.get(DcMotor.class,"Arm_upR");
        Servo servo_one = hardwareMap.get(Servo.class,"servo_one");
        Arm_upL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Arm_upR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // reverse Motors
        //Left Motor Reverse
        Left_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        //Right Motor Reverse
        Right_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        //Arm Up left Motor Reverse
        Arm_upL.setDirection(DcMotorSimple.Direction.REVERSE);
        //Arm up right Motor Reverse
        //Arm_upR.setDirection(DcMotorSimple.Direction.REVERSE);
        //Arm Linear extension Reverse
        Arm_Line.setDirection(DcMotorSimple.Direction.REVERSE);
        //Servo Reverse
        //servo_one.setDirection(Servo.Direction.REVERSE);

        //Set Positions
        // linear Extension
        //Forwards
        int pos1l = 1000;
        //Backwards
        int pos2l = -1000;
        //Up and Down
        int pos1up = 1100;
        //Set Target
        int posxButton = 500;
        //Servo
        double closed = 0.0;
        double open = 0.2;
        //starting position for Servo
        servo_one.setPosition(closed);

        waitForStart();
        while (opModeIsActive()) {

            // motor to joystick ( def ) (Gamepad 1 [Movement])
            double leftY = gamepad1.left_stick_y;
            double RightX = gamepad1.right_stick_x;
            // motor to joystick according to values
            Left_Motor.setPower(RightX - leftY);
            Right_Motor.setPower(leftY + RightX);
            //Motor for Up and Down (Gamepad 2)
            double left = -gamepad2.left_stick_y;
            double Right = gamepad2.right_stick_x;
            Arm_upL.setPower(left);
            Arm_upR.setPower(left);
            Arm_Line.setPower(Right);
            //arm code for up and down (Gamepad 2)
            if (gamepad2.dpad_up){
                Arm_upR.setTargetPosition(pos1up);
                Arm_upL.setTargetPosition(pos1up);
                Arm_upL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_upR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_upL.setPower(1);
                Arm_upR.setPower(1);

            }else if (gamepad2.dpad_down){
                Arm_upR.setTargetPosition(pos2l);
                Arm_upL.setTargetPosition(pos2l);
                Arm_upL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_upR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_upL.setPower(1);
                Arm_upR.setPower(1);
            }else{
                Arm_upL.setPower(0);
                Arm_upR.setPower(0);
            }
            // arm code for liner
            if (gamepad2.a){
                Arm_Line.setTargetPosition(pos2l);
                Arm_Line.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_Line.setPower(1);
            }else if (gamepad2.y){
                Arm_Line.setTargetPosition(pos1l);
                Arm_Line.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_Line.setPower(-1);
            }else{
                Arm_Line.setPower(0);
            }

            //Servo
            if (gamepad2.x){
                servo_one.setPosition(open);


            }else if (gamepad2.b){
                servo_one.setPosition(closed);
            }
        }
    }
}