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
        // motor def
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

        //set postion
        // liner
        int pos1l = 1000;
        // 0000000
        int pos2l = -1000;
        // up and down
        int pos1up = 1100;
        int posxButton = 500;
        // servo
        double closed = 0.0;
        double open = 1.0;
        servo_one.setPosition(closed);

        waitForStart();

        while (opModeIsActive()) {
            // motor to joystick ( def )
            double leftY = gamepad1.left_stick_y;
            double RightX = gamepad1.right_stick_x;
            // motor to joystick according to values
            Left_Motor.setPower(RightX - leftY);
            Right_Motor.setPower(leftY + RightX);

            //arm code virtical
            // up
            if (gamepad1.dpad_up){
                Arm_upR.setTargetPosition(pos1up);
                Arm_upL.setTargetPosition(pos1up);
                Arm_upL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_upR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_upL.setPower(1);
                Arm_upR.setPower(1);

            }else{
                Arm_upL.setPower(0);
                Arm_upR.setPower(0);
                // down
                if (gamepad1.dpad_down){
                    Arm_upR.setTargetPosition(pos2l);
                    Arm_upL.setTargetPosition(pos2l);
                    Arm_upL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Arm_upR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Arm_upL.setPower(4);
                    Arm_upR.setPower(4);
                }else{
                    Arm_upL.setPower(0);
                    Arm_upR.setPower(0);
                }


                // arm code liner
                // A button
                while  (gamepad1.a) {
                    if (gamepad1.a){
                        Arm_Line.setTargetPosition(pos1l);
                        Arm_Line.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        Arm_Line.setPower(-3);
                    }else{
                        Arm_Line.setPower(0);
                    }
                }


                // y button
                while ( gamepad1.y){
                    if (gamepad1.y){
                        Arm_Line.setTargetPosition(pos2l);
                        Arm_Line.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        Arm_Line.setPower(3);
                    }else{
                        Arm_Line.setPower(0);
                    }
                }

                // set pos x and b code
                if ( gamepad1.right_bumper){
                    Arm_upL.setTargetPosition(posxButton);
                    Arm_upR.setTargetPosition(posxButton);
                    Arm_upL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Arm_upR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Arm_upL.setPower(3);
                    Arm_upR.setPower(3);
                }
                //Servo

                if (gamepad1.x){
                    servo_one.setPosition(open);

                }else if (gamepad1.b){
                    servo_one.setPosition(closed);

                }

            }
        }
    }}