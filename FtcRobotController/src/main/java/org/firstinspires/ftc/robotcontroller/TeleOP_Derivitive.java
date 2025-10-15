package org.firstinspires.ftc.robotcontroller;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.subsy.The_better_Intake;
import org.firstinspires.ftc.robotcontroller.subsy.The_better_train;

@TeleOp
public class TeleOP_Derivitive extends LinearOpMode {

    @Override
    public void runOpMode(){
        The_better_train The_better_train =  new The_better_train(hardwareMap);
        The_better_Intake The_better_intake = new The_better_Intake(hardwareMap);

        // joysticks (driver)
        double left_joystick_y;
        double left_joystick_x;
        double right_joystick_x;
        
        // buttons (operator)
        boolean button_x = gamepad2.x;
        boolean button_a = gamepad2.a;

        waitForStart();

        while (opModeIsActive()){
            left_joystick_y = gamepad1.right_stick_x;
            left_joystick_x = gamepad1.right_stick_y;
            right_joystick_x = gamepad1.left_stick_x;

            The_better_train.strife_calc(left_joystick_y ,left_joystick_x, right_joystick_x );

            if (button_a){
                The_better_intake.forward();

            }
            if (button_x){
                The_better_intake.backward();

            }



        }


    }
}
