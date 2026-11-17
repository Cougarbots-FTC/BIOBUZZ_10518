package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//import org.firstinspires.ftc.teamcode.subsystems.Gate;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
//import org.firstinspires.ftc.teamcode.subsystems.PIDOuttake;

@Autonomous(name="only goes forward", group="Autonomous")
public class forwardAuto extends LinearOpMode {



    @Override
    public void runOpMode() {
        waitForStart();
        MecanumDrive mecanumDrive = new MecanumDrive(this);
        Outtake outtake = new Outtake(this);
        Intake intake = new Intake(this);
//        PIDOuttake outtake = new PIDOuttake(this);
//        Gate gate = new Gate(this);

        if (opModeIsActive()) {
//            outtake.turnOn();
//            sleep(3500);
//            intake.turnOn();
//            sleep(2000);
//            intake.turnOff();
//            outtake.turnOff();

//            mecanumDrive.front_left.setPower(0.3);
//            mecanumDrive.front_right.setPower(0.3);
//            mecanumDrive.back_left.setPower(0.3);
//            mecanumDrive.back_right.setPower(0.3);
            mecanumDrive.forward(0.3);
            sleep(30000);
//            mecanumDrive.forward(0);

        }
    }


}