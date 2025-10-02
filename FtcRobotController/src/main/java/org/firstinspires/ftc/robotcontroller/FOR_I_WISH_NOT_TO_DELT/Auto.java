package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcontroller.subsy.Auto_drivetrain;
import org.firstinspires.ftc.robotcontroller.subsy.arm_line;
import org.firstinspires.ftc.robotcontroller.subsy.arm_pivot;
import org.firstinspires.ftc.robotcontroller.subsy.drivetrain;
import org.firstinspires.ftc.robotcontroller.subsy.intake;

@Autonomous
public class Auto extends LinearOpMode {



    @Override
    public void runOpMode(){

        Auto_drivetrain drivetrain = new Auto_drivetrain(hardwareMap);
        drivetrain.Reset_Encoder();


        waitForStart();


        drivetrain.move_ticks(-2550,0.45  );

        while (opModeIsActive() && (drivetrain.LM_busy() || drivetrain.RM_busy())); {
        }







    }
}
