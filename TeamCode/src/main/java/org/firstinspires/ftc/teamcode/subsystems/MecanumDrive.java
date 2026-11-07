package org.firstinspires.ftc.teamcode.subsystems;
//import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MecanumDrive {
    public DcMotor front_left = null;
    public DcMotor front_right = null;
    public DcMotor back_left = null;
    public DcMotor back_right = null;

    private final Gamepad driver1, driver2;

    public MecanumDrive(OpMode opMode) {





        //make the controllers

        driver1 = opMode.gamepad1;
        driver2 = opMode.gamepad2;

        HardwareMap hardwareMap = opMode.hardwareMap;

        //name strings need to match config

        front_left = hardwareMap.get(DcMotor.class, "frontLeft");
        front_right = hardwareMap.get(DcMotor.class, "frontRight");
        back_right = hardwareMap.get(DcMotor.class, "backRight");
        back_left = hardwareMap.get(DcMotor.class, "backLeft");
        
//        front_right.setDirection(DcMotor.Direction.REVERSE);
//        back_right.setDirection(DcMotor.Direction.REVERSE);
        front_left.setDirection(DcMotorSimple.Direction.REVERSE);

        //here add motor reversals and stuff
    }

//    public void startWait(double seconds) {
//        timer.reset();
//        targetWaitTime = seconds;
//        isWaiting = true;
//    }
//
//    public boolean isWaitComplete() {
//        if (!isWaiting) return true;
//        if (timer.seconds() >= targetWaitTime) {
//            isWaiting = false;
//            return true;
//        }
//        return false;
//    }

    //teleop function that will actually be called in teleop

    public void teleOp() {
        double speed;
        if(driver1.right_bumper) {

            speed = 0.5;
        } else {
            speed = 1;
        }

        double vertical = driver1.left_stick_y;
        double horizontal = driver1.left_stick_x * -1.1; //to negate imperfect strafing
        double pivot = driver1.right_stick_x;

        //puts everything tg
        double denominator = Math.max(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(pivot), 1); //finds if any value is over the max of 1
        //if so itll divide everything by that value to keep the ratio but not go over 1
        double rightFrontPower = (vertical + horizontal + pivot) / denominator * speed;
        double rightBackPower = (vertical - horizontal + pivot) / denominator * speed;
        double leftFrontPower = (vertical - horizontal - pivot) / denominator * speed;
        double leftBackPower = (vertical + horizontal - pivot) / denominator * speed;

        front_left.setPower(leftFrontPower);
        front_right.setPower(rightFrontPower);
        back_left.setPower(leftBackPower);
        back_right.setPower(rightBackPower);

    }
//quality of life functions for auto may uncomment later
    public void forward(double power) {
        this.front_left.setPower(-power);
        this.front_right.setPower(-power);
        this.back_left.setPower(-power);
        this.back_right.setPower(-power);
        //figure out why its negative later
    }

    public void strafe_right(double power) {
        this.front_left.setPower(power);
        this.front_right.setPower(-power);
        this.back_left.setPower(-power);
        this.back_right.setPower(power);
    }

    public void strafe_left(double power) {
        this.front_left.setPower(-power);
        this.front_right.setPower(power);
        this.back_left.setPower(power);
        this.back_right.setPower(-power);
    }

    //maybe add auto function here????
}
