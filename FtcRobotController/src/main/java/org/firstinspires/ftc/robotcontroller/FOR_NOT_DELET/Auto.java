/**package org.firstinspires.ftc.robotcontroller.FOR_NOT_DELET;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.FOR_NOT_DELET.Auto_drivetrain;

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
}*/
