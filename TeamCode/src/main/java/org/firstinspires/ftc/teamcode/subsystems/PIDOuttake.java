package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;


//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;


public class PIDOuttake {
    public DcMotorEx outtake;
    private Gamepad driver1, driver2;
    private double outtakeVelocity;
    private double lowVelocity;
    private double currentVelocity;

    private double F = 20.3; //says how much power the motor should have, -1
    private double P = 17.1;//dampener 82.9
    double [] stepSizes = {10, 1, 0.1, 0.01, 0.001, 0.0001};
    int stepIndex = 1;

    public PIDOuttake(OpMode opmode) {
        driver1 = opmode.gamepad1;
        driver2 = opmode.gamepad2;

        outtakeVelocity = 1350;
        currentVelocity = outtakeVelocity;

        HardwareMap hardwareMap = opmode.hardwareMap;

        outtake = hardwareMap.get(DcMotorEx.class, "OuttakeMotor");
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        outtake.setDirection(DcMotorSimple.Direction.REVERSE);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        outtake.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

//        opmode.telemetry.addData("Init Complete")
    }

    public void teleOp(OpMode opmode) {
        if (driver2.left_trigger > 0.1) {
//            outtake.setPower(-driver2.left_trigger);
//            outtake.setVelocity(outtakeVelocity);

            PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
            outtake.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

            outtake.setVelocity(outtakeVelocity);
        } else if(driver2.left_bumper){
            outtake.setVelocity(-1350);
            //outtake.setPower(0);
        }else{
            outtake.setVelocity(0);
        }
        //outtake.setPower(driver2.left_stick_y);


//        if (driver2.bWasPressed()) {
//            stepIndex = (stepIndex + 1) % stepSizes.length;
//            //outtakeVelocity += 5;
//        }
//
//        if (driver2.dpadLeftWasPressed()) {
//            F -= stepSizes[stepIndex];
//        }
//        if (driver2.dpadRightWasPressed()) {
//            F += stepSizes[stepIndex];
//        }
//        if (driver2.dpadUpWasPressed()) {
//            P += stepSizes[stepIndex];
//        }
//        if (driver2.dpadDownWasPressed()) {
//            P -= stepSizes[stepIndex];
//        }


// For tuning the PID Controller
        //PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        //outtake.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

//        outtake.setVelocity(currentVelocity);


    }

    public void turnOnOuttake(int velocity) {
        //outtake.setVelocity(velocity);

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        outtake.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        outtake.setVelocity(velocity);
    }
    public void turnOffOuttake() {
        outtake.setVelocity(0);
    }

    public void addTelemetry(OpMode opMode) {

        double currVelocity = outtake.getVelocity();
        double error = outtakeVelocity - currVelocity;

        opMode.telemetry.addData("Target Velocity: ", outtakeVelocity);
        opMode.telemetry.addData("Current Velocity: ", currVelocity);
        opMode.telemetry.addData("Error: ", error);
        opMode.telemetry.addLine("----------------------------------------------------");
        opMode.telemetry.addData("Tuning P: ", P);
        opMode.telemetry.addData("Tuning F: ", F);
        opMode.telemetry.addData("Step Size: ", stepSizes[stepIndex]);

        opMode.telemetry.update();
    }
}
