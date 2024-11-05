package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleOp3 extends LinearOpMode {

    @Override
    public void runOpMode() {
        // motor def
        DcMotor Left_Motor = hardwareMap.get(DcMotor.class, "Left_Motor");
        DcMotor Right_Motor = hardwareMap.get(DcMotor.class, "Right_Motor");
        DcMotor Arm_Line = hardwareMap.get(DcMotor.class, "Arm_Line");
        DcMotor Arm_upL = hardwareMap.get(DcMotor.class,"Arm_upL");
        DcMotor Arm_upR = hardwareMap.get(DcMotor.class,"Arm_upR") ;
;
        // reverse test
        //LMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //dRMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //set postion
        int pos1 = 100;
        int pos2 = 0;

        waitForStart();
        while (opModeIsActive()) {
            // motor to joystick ( def )
            double leftY = gamepad1.left_stick_y;
            double RightX = gamepad1.right_stick_x;
            // motor to joystick according to values
            Left_Motor.setPower(leftY + RightX);
            Right_Motor.setPower(leftY - RightX);
            //set postion
            if (gamepad1.dpad_up){

            }


            // arm code
            // A button
            if (gamepad1.a) {
                Arm_Line.setTargetPosition(pos1);
                Arm_Line.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_Line.setPower(1);
            }else {
                Arm_Line.setPower(0);

            }
           // y button
            if ( gamepad1.y){
                Arm_Line.setTargetPosition(pos2);
                Arm_Line.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm_Line.setPower(-1);
            }else{
                Arm_Line.setPower(0);
            }

        }
    }
}
