package org.firstinspires.ftc.robotcontroller.subsy;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcontroller.constance;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class arm_line {
    DcMotorEx Arm_Line;
    Telemetry telemetry;
    OpMode opMode;
    double Right;
    public arm_line(HardwareMap hardwareMap, OpMode opMode, Telemetry telemetry) {
        Arm_Line = hardwareMap.get(DcMotorEx.class, "Arm_Line");
        this.opMode = opMode;
        this.telemetry = telemetry;

        //Reset encoders
        Arm_Line.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Arm_Line.setTargetPosition(0);
        Arm_Line.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        update_pidf(constance.arm_kP, constance.arm_kI, constance.arm_kD, constance.arm_kF1);

    }
    public void arm_Line(){
        update_pidf(constance.arm_kP, constance.arm_kI, constance.arm_kD, constance.arm_kF1);
        Arm_Line.setPower(Right);
    }

    public void update_pidf(double kP, double kI, double kD, double kF) {
        PIDFCoefficients pidf_vals = new PIDFCoefficients(kP, kI, kD, kF);
        Arm_Line.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);

    }

}
