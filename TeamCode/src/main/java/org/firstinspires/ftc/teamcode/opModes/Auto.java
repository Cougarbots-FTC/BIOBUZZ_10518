package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//import org.firstinspires.ftc.teamcode.subsystems.Gate;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
//import org.firstinspires.ftc.teamcode.subsystems.PIDOuttake;

@Autonomous(name="notAgainstWall auto", group="Autonomous")
public class Auto extends LinearOpMode {



    @Override
    public void runOpMode() {
        waitForStart();
        MecanumDrive mecanumDrive = new MecanumDrive(this);
//        PIDOuttake outtake = new PIDOuttake(this);
//        Gate gate = new Gate(this);

        if (opModeIsActive()) {
            mecanumDrive.front_left.setPower(-0.25);
            mecanumDrive.front_right.setPower(-0.25);
            mecanumDrive.back_left.setPower(-0.25);
            mecanumDrive.back_right.setPower(-0.25);
            sleep(1000);
            mecanumDrive.front_left.setPower(0);
            mecanumDrive.front_right.setPower(0);
            mecanumDrive.back_left.setPower(0);
            mecanumDrive.back_right.setPower(0);

        }
    }


}