package org.firstinspires.ftc.robotcontroller.subsy;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.hardware.motors.RevRobotics20HdHexMotor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class The_better_train {
    
    DcMotorEx back_left;
    DcMotorEx front_left;
    DcMotorEx back_right;
    DcMotorEx front_right;

    IMU imu;
    IMU.Parameters imU_peramiters;


     public The_better_train (HardwareMap hardwareMap) {


         back_left = hardwareMap.get(DcMotorEx.class, "LMB");
         back_right = hardwareMap.get(DcMotorEx.class, "RMB");
         front_right = hardwareMap.get(DcMotorEx.class, "RMF");
         front_left = hardwareMap.get(DcMotorEx.class, "LMF");
         imu = hardwareMap.get(IMU.class, "imu");

         front_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         front_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


         imU_peramiters = new IMU.Parameters(
                 new RevHubOrientationOnRobot(
                         RevHubOrientationOnRobot.LogoFacingDirection.UP,
                         RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                 )
         );

         imu.initialize(imU_peramiters);
     }




     // the three axis will be defined here
     public void strife_calc(double turn, double strife, double straight){



         double [] speed = {
                 (turn + strife + straight),
                 (turn - strife - straight),
                 (turn - strife + straight),
                 (turn + strife - straight)
         };

         double max = Math.abs(speed[0]);

         for (int i = 0; i < speed.length; i++) {
             if ( max < Math.abs(speed[i]) ) max = Math.abs(speed[i]);
         }


         if (max > 1) {
             for (int i = 0; i < speed.length; i++) speed[i] /= max;
         }


         front_left.setPower(speed[0]);
         front_right.setPower(speed[1]);
         back_left.setPower(speed[2]);
         back_right.setPower(speed[3]);


     }

    public void turn( double degrees){
        turn_ticks = degrees;
        
                   
    }
    //Thread.sleep(mili)
    public void driveInches(double inches, double power) {
    int ticksPerRev = 560; 
    double wheelDiameter = 4.0; // 4" wheels
    double ticksPerInch = (double) ticksPerRev / (Math.PI * wheelDiameter);
    //double Aj_Power = Math.abs(power) * Math.signum(inches);  

    int moveTicks = (int) (inches * ticksPerInch);

    // zero power behaviour 
     front_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     front_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     back_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     back_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    // Reset encoders FIRST
    front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


     // Set target positions
    front_left.setTargetPosition(moveTicks);
    front_right.setTargetPosition(moveTicks);
    back_left.setTargetPosition(moveTicks);
    back_right.setTargetPosition(moveTicks);


      // Switch to RUN_TO_POSITION
    front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

   
    // start
    front_left.setPower(power);
    front_right.setPower(power);
    back_left.setPower(power);
    back_right.setPower(power);

    // Wait until done
    long startTime = System.currentTimeMillis();
    long timeout = 30000; // 30 seconds timeout
        
    while (front_left.isBusy() || front_right.isBusy() || back_left.isBusy() || back_right.isBusy()) {
        && (System.currentTimeMillis() - startTime < timeout)) //Thread.sleep(10);//idle(); 
    }Thread.sleep(10);
 
    // Stop motors
    front_left.setPower(0);
    front_right.setPower(0);
    back_left.setPower(0);
    back_right.setPower(0);

    // switch back to encoder mode
    front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    front_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    back_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    back_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

   // setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//telemetry.addData("Moving...", "Time: %d ms", System.currentTimeMillis() - startTime);
//telemetry.update();

        //long startTime = System.currentTimeMillis();
//long waitTime = 2000;  2 seconds - note : safter wait thing 
//while (System.currentTimeMillis() - startTime < waitTime) {
   // idle(); // lets the system update
    //telemetry.addData("Waiting", "Time left: %.1f s", (waitTime - (System.currentTimeMillis() - startTime))/1000.0);
  //  telemetry.update();

    }



}
