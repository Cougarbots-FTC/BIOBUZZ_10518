package org.firstinspires.ftc.teamcode.subsystems;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Outtake {
    private final Gamepad driver1, driver2;
    public DcMotorEx outtake_motor  = null;
    public Outtake(OpMode opmode) {
        driver1 = opmode.gamepad1;
        driver2 = opmode.gamepad2;

        HardwareMap hardwareMap = opmode.hardwareMap;

        outtake_motor  = hardwareMap.get(DcMotorEx.class, "OuttakeMotor");
        outtake_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void teleOp(OpMode opmode) {
        if(driver2.left_trigger > 0.1) {
            outtake_motor.setVelocity(1350);
        } else {
            outtake_motor.setVelocity(0);
        }
        opmode.telemetry.addData("Outtake Velocity: ", outtake_motor.getVelocity());
        opmode.telemetry.addData("Outtake Voltage: ", outtake_motor.getCurrent(CurrentUnit.AMPS));

    }
}