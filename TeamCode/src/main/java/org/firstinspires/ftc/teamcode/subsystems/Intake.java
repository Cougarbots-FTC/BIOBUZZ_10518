package org.firstinspires.ftc.teamcode.subsystems;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Intake {
    private final Gamepad driver1, driver2;
    public DcMotor intake_motor  = null;

    private final double OUTER_POWER = 0.8;
    private final double INNER_POWER = 0.7;

    private DcMotor intake_motor_outer, intake_motor_inner;
    public Intake(OpMode opmode) {
        driver1 = opmode.gamepad1;
        driver2 = opmode.gamepad2;

        HardwareMap hardwareMap = opmode.hardwareMap;
        //temporary stuff in case of disconnect
        try {

            intake_motor_outer = hardwareMap.get(DcMotor.class, "outerIntakeMotor");
            //this is the one that lets the balls in, it actually intakes

            intake_motor_inner = hardwareMap.get(DcMotor.class, "innerIntakeMotor");
            //this is the one that actually pushes the balls into the outtake
//        intake_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            intake_motor_inner.setDirection(DcMotorSimple.Direction.REVERSE);

        } catch (IllegalArgumentException e) {
            intake_motor_inner = null;
            intake_motor_outer = null;
        }
    }

    public void turnOn() {
        if(intake_motor_outer != null) {
            intake_motor_outer.setPower(OUTER_POWER);
            intake_motor_inner.setPower(-INNER_POWER);
        }
    }

    public void turnOff() {
        if(intake_motor_outer != null) {
            intake_motor_outer.setPower(0);
            intake_motor_inner.setPower(0);
        }
    }

    public void teleOp(OpMode opmode) {


        if(intake_motor_outer != null) {
            if (driver2.right_trigger > 0.1) {
//            intake_motor.setVelocity(1350);
                //negative and forward
                intake_motor_inner.setPower(-INNER_POWER);
            } else if (driver2.right_bumper) {
                //pos
                intake_motor_inner.setPower(INNER_POWER);
            } else {
                intake_motor_inner.setPower(0);
            }

            if (driver2.left_trigger > 0.1) {
                //pos
                intake_motor_outer.setPower(OUTER_POWER);

            } else if (driver2.left_bumper) {

                //neg
                intake_motor_outer.setPower(-OUTER_POWER);
            } else {
                intake_motor_outer.setPower(0);
            }

            if (driver2.a) {
                intake_motor_inner.setPower(-0.65);
                intake_motor_outer.setPower(0.8);
            }
        }
        if(intake_motor_outer == null) {
            opmode.telemetry.addData("Outer Intake: ", "DISCONNECTED");
        }
        if(intake_motor_inner == null) {
            opmode.telemetry.addData("Inner Intake: ", "DISCONNECTED");
        }

//        opmode.telemetry.addData("Intake Velocity: ", intake_motor.getVelocity());
//        opmode.telemetry.addData("Intake Voltage: ", intake_motor.getCurrent(CurrentUnit.AMPS));

    }
}