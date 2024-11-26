package org.firstinspires.ftc.robotcontroller.subsy;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcontroller.constance;

public class arm_pivot {
    DcMotorEx Arm_upR;
    DcMotorEx Arm_upL;
    OpMode opMode;
    Telemetry telemetry;

    public arm_pivot(OpMode opMode, Telemetry telemetry) {

        Arm_upL = hardwareMap.get(DcMotorEx.class, "Arm_upL");
        Arm_upR = hardwareMap.get(DcMotorEx.class, "Arm_upR");


        this.opMode = opMode;
        this.telemetry = telemetry;
        // reset encoder
        Arm_upR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Arm_upL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Arm_upR.setTargetPosition(0);
        Arm_upL.setTargetPosition(0);
        Arm_upR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Arm_upL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // motor reverse
        Arm_upL.setDirection(DcMotorSimple.Direction.REVERSE);
        //Arm_upR.setDirection(DcMotorSimple.Direction.REVERSE);

        // motor brake ( stay in place )
        Arm_upL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Arm_upR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        update_pidf(constance.pivot_kP,constance.pivot_kI, constance.pivot_kD, constance.pivot_kF1);

    }


    public void Joysticks(double left) {
        update_pidf(constance.pivot_kP,constance.pivot_kI, constance.pivot_kD, constance.pivot_kF1);
        Arm_upL.setPower(left);
        Arm_upR.setPower(left);

    }

    public void update_pidf(double kP, double kI, double kD, double kF) {
        PIDFCoefficients pidf_vals = new PIDFCoefficients(kP, kI, kD, kF);
        Arm_upL.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);
        Arm_upR.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pidf_vals);

    }
}



































































