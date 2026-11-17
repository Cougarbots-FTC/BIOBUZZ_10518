package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class LauncherLogic {
    public DcMotorEx outtake_motor;
    public DcMotor intake_motor_outer;
    public DcMotor intake_motor_inner;

    private ElapsedTime stateTimer = new ElapsedTime();

    private enum LauncherState {
        IDLE,
        SPIN_UP,
        LAUNCH
    }

    private LauncherState launcherState;

    //flywheel constant
    private double MIN_FLYWHEEL_RPM;
    private double FLYWHEEL_MAX_SPINUP_TIME = 5;
    private double MAX_LAUNCHING_TIME = 10;


    private void init(HardwareMap hardwareMap) {
        outtake_motor  = hardwareMap.get(DcMotorEx.class, "OuttakeMotor");
        intake_motor_outer = hardwareMap.get(DcMotor.class, "outerIntakeMotor");
        //this is the one that lets the balls in, it actually intakes

        intake_motor_inner = hardwareMap.get(DcMotor.class, "innerIntakeMotor");

        outtake_motor.setPower(0);

    }

    public void update() {
        switch (launcherState) {
            case IDLE:
                intake_motor_inner.setPower(0);
                intake_motor_outer.setPower(0);
                outtake_motor.setVelocity(0);

                stateTimer.reset();
                //some logic for when it should launch
//                launcherState = LauncherState.SPIN_UP;
                break;

            case SPIN_UP:
                //turn on pid or smth
                if(outtake_motor.getVelocity() > MIN_FLYWHEEL_RPM || stateTimer.seconds() > FLYWHEEL_MAX_SPINUP_TIME) {
                    //if too much time passed or velocity is ready
                    launcherState = LauncherState.LAUNCH;
                }
                break;
            case LAUNCH:
                //turn on intake to let balls into motor
                intake_motor_inner.setPower(0.5);
                intake_motor_inner.setPower(0.5);
                if(stateTimer.seconds() > MAX_LAUNCHING_TIME) {
                    //maybe change if to check for dips in velocity
                    intake_motor_inner.setPower(0);
                    intake_motor_outer.setPower(0);
                    launcherState = LauncherState.IDLE;
                }
        }



    }
    public boolean isBusy(){
        return launcherState != LauncherState.IDLE;
    }
}
