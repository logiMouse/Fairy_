package org.firstinspires.ftc.robotcontroller.subsy;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class arm_line {
    DcMotor Arm_Line;

    public arm_line(HardwareMap hardwareMap) {
        Arm_Line = hardwareMap.get(DcMotor.class, "Arm_Line");

    }

}
