package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

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

        //here add motor reversals and stuff
    }

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

    //maybe add auto function here????
}
