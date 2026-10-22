package org.firstinspires.ftc.teamcode.subsystems;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Intake {
    private final Gamepad driver1, driver2;
    public DcMotor intake_motor  = null;

    private DcMotor intake_motor_outer, intake_motor_inner;
    public Intake(OpMode opmode) {
        driver1 = opmode.gamepad1;
        driver2 = opmode.gamepad2;

        HardwareMap hardwareMap = opmode.hardwareMap;

        intake_motor_outer  = hardwareMap.get(DcMotor.class, "outerIntakeMotor");
        //this is the one that lets the balls in, it actually intakes

        intake_motor_inner  = hardwareMap.get(DcMotor.class, "innerIntakeMotor");
        //this is the one that actually pushes the balls into the outtake
//        intake_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void teleOp(OpMode opmode) {
        if(driver2.right_trigger > 0.1) {
//            intake_motor.setVelocity(1350);
            intake_motor_inner.setPower(-1);
        } else if (driver2.right_bumper) {
            intake_motor_inner.setPower(1);
        } else {
            intake_motor_inner.setPower(0);
        }

        if(driver2.left_trigger > 0.1) {
            intake_motor_outer.setPower(-1);

        } else if (driver2.left_bumper) {
            intake_motor_outer.setPower(1);
        } else {
            intake_motor_outer.setPower(0);
        }


//        opmode.telemetry.addData("Intake Velocity: ", intake_motor.getVelocity());
//        opmode.telemetry.addData("Intake Voltage: ", intake_motor.getCurrent(CurrentUnit.AMPS));

    }
}