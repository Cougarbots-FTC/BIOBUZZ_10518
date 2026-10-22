package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="driver centric teleop fsociety")
public class DriverCentricTeleOp extends OpMode {

    //    private Intake intake;
//    private Delivery delivery;
    private MecanumDrive mecanumDrive;
//    private Outtake outtake;
//    private Gate gate;

//    private Intake intake;

//    private PIDOuttake pidOuttake;

//    public double highVelocity = 1350;


//    public double lowVelocity = 900;

//    double curTargetVelocity = highVelocity;

//    double F = 0;
//    double P = 0;
//
//    double[] stepSizes = {10.0, 1.0, 0.1, 0.001, 0.0001};
//
//    int stepIndex = 1;

    @Override

    public void init() {
//        delivery = new Delivery(this);
        mecanumDrive = new MecanumDrive(this);
////        outtake = new Outtake(this);
//        pidOuttake = new PIDOuttake(this);
////        gate = new Gate(this);
//        intake = new Intake(this);

    }

    @Override
    public void loop() {
        mecanumDrive.teleOp();
//        pidOuttake.teleOp(this);
//        pidOuttake.addTelemetry(this);
////        gate.teleOp();
//        intake.teleOp(this);


    }

}