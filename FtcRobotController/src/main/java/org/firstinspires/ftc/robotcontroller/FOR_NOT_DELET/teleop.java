/**package org.firstinspires.ftc.robotcontroller.FOR_NOT_DELET;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.FOR_NOT_DELET.arm_line;
import org.firstinspires.ftc.robotcontroller.FOR_NOT_DELET.arm_pivot;
import org.firstinspires.ftc.robotcontroller.FOR_NOT_DELET.drivetrain;
import org.firstinspires.ftc.robotcontroller.FOR_NOT_DELET.intake;
@TeleOp
public class teleop extends LinearOpMode{

    @Override
    public void runOpMode(){

        // defining subsy files
        drivetrain drivetrain = new drivetrain(hardwareMap);
        intake intake = new intake(hardwareMap);
        arm_line arm_line = new arm_line(hardwareMap, telemetry);
        arm_pivot arm_pivot = new arm_pivot(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()){
            // joystick def L = left R= right y= y value x = x value d= drivetrain
            double LYD = gamepad1.left_stick_y;
            double RXD = gamepad1.right_stick_x;

            //double LYP = -gamepad2.left_stick_y;

            // A X Y B buttons
            boolean A = gamepad2.a;
            boolean Y = gamepad2.y;
            boolean X = gamepad2.x;
            boolean B = gamepad2.b;

            // D_pads
            boolean UP = gamepad2.dpad_up;
            boolean DOWN = gamepad2.dpad_down;
            boolean RIGHT = gamepad2.dpad_right;



            // drivetrain
            drivetrain.arcade(LYD,RXD);

            // liner extension
            if(A){
                arm_line.Line_POS_1();
            } else if (Y) {
                arm_line.Line_POS_2();
            } else if (RIGHT) {
                arm_line.Line_POS_3();
            }else {
               arm_line.stop();
            }

            // pivot
            if(DOWN){
                arm_pivot.ARM_UP();
            } else if (UP) {
                arm_pivot.ARM_DOWN();
            } else {
              arm_pivot.stop();
            }

            //arm_pivot.Joysticks(LYP-0.7);

            // intake
            if(X){
                intake.open();
            } else if (B) {
                intake.closed();
            }

            arm_line.addTelemetry();


        }



    }


}*/

