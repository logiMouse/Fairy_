package org.firstinspires.ftc.robotcontroller.subsy;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class intake {
    Servo Servo;
    OpMode opMode;
    public intake(HardwareMap hardwareMap){

        Servo = hardwareMap.get(Servo.class,"Servo");

    }

    public void open(){

        Servo.setPosition(0.2);
    }

    public void closed(){

        Servo.setPosition(0.0);
    }
}
