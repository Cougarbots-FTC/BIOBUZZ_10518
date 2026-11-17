package org.firstinspires.ftc.teamcode.subsystems;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Outtake {
    private double OUTTAKE_VELOCITY = 1270;

    private boolean edit_mode = false;
    private final Gamepad driver1, driver2;


    public DcMotorEx outtake_motor  = null;
    public Outtake(OpMode opmode) {
        driver1 = opmode.gamepad1;
        driver2 = opmode.gamepad2;

        HardwareMap hardwareMap = opmode.hardwareMap;
        try{
            outtake_motor  = hardwareMap.get(DcMotorEx.class, "OuttakeMotor");
        } catch(IllegalArgumentException e) {
            outtake_motor = null;
        }

        if(outtake_motor != null) {
            outtake_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }


    }

    public void turnOn() {
        if (outtake_motor != null) {
            outtake_motor.setVelocity(OUTTAKE_VELOCITY);
        }
    }

    public void turnOff() {
        if (outtake_motor != null) {
            outtake_motor.setVelocity(0);
        }
    }

    public void teleOp(OpMode opmode) {
        if (outtake_motor != null) {
            if (driver2.b) {
                outtake_motor.setVelocity(OUTTAKE_VELOCITY);
            } else if (driver2.y) {
                outtake_motor.setVelocity(-1000);
            } else {
                outtake_motor.setVelocity(0);
            }
        }

//        if(driver2.x) {
//
//        }
        if(outtake_motor == null) {
            opmode.telemetry.addData("Outtake Motor: ", "DISCONNECTED");
        }
        opmode.telemetry.addData("Outtake Velocity: ", outtake_motor.getVelocity());
        opmode.telemetry.addData("Outtake Voltage: ", outtake_motor.getCurrent(CurrentUnit.AMPS));
        opmode.telemetry.addData("Velocity Variable", OUTTAKE_VELOCITY);
    }
}