package org.firstinspires.ftc.robotcontroller.subsy;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class The_better_Intake {

    DcMotorEx right_intake;
    DcMotorEx left_intake;


    public The_better_Intake(HardwareMap hardwareMap){

        right_intake = hardwareMap.get(DcMotorEx.class, "RI");
        left_intake = hardwareMap.get(DcMotorEx.class, "LI");



    }


    public void forward (){
        right_intake.setDirection(DcMotorSimple.Direction.REVERSE);
        left_intake.setDirection(DcMotorSimple.Direction.REVERSE);
        right_intake.setPower(0.5);
        left_intake.setPower(0.5);

    }

    public void backward (){
        right_intake.setPower(0.5);
        left_intake.setPower(0.5);

    }


}
