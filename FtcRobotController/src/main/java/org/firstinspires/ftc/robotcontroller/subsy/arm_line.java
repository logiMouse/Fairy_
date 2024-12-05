package org.firstinspires.ftc.robotcontroller.subsy;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcontroller.constance;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.lang.Thread;
public class arm_line {
    DcMotorEx Arm_Line;
    Telemetry telemetry;
    OpMode opMode;
    double Right;
    public arm_line(HardwareMap hardwareMap,Telemetry telemetry) {
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
    public void Line_POS_1() {
        update_pidf(constance.arm_kP, constance.arm_kI, constance.arm_kD, constance.arm_kF1);
        Arm_Line.setTargetPosition(constance.arm_pos3);
        Arm_Line.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //Don't change -1
        Arm_Line.setPower(-1);

    }

    public void Line_POS_2() {
        update_pidf(constance.arm_kP, constance.arm_kI, constance.arm_kD, constance.arm_kF1);
        Arm_Line.setTargetPosition(constance.arm_pos1);
        Arm_Line.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //Don't Change -1
        Arm_Line.setPower(-1);

    }
    public void stop(){
        Arm_Line.setPower(0);
    }


    public void update_pidf(double kP, double kI, double kD, double kF) {
        PIDFCoefficients pidf_vals = new PIDFCoefficients(kP, kI, kD, kF);
        Arm_Line.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);

    }


    // AUTO/////////////////////////////////////////////////////////////////////////////

/*    public void ARM_AUTO(){
        Arm_Line.setPower(0.2);
        sleep(1000);
        Arm_Line.setPower(0);
    } */


}
