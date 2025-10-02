package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.subsy.Auto_drivetrain;

@Autonomous
public class BIG_AUTO extends LinearOpMode {

    @Override
    public void runOpMode(){

        Auto_drivetrain drivetrain = new Auto_drivetrain(hardwareMap);
        drivetrain.Reset_Encoder();


        waitForStart();


        drivetrain.move_ticks(-500,0.5  );

        while (opModeIsActive() && (drivetrain.LM_busy() || drivetrain.RM_busy())); {
        }

        drivetrain.rotate_degrees(45);

        while (opModeIsActive() && (drivetrain.LM_busy() || drivetrain.RM_busy())); {
        }
         drivetrain.move_ticks(-500,0.5);



    }



}
