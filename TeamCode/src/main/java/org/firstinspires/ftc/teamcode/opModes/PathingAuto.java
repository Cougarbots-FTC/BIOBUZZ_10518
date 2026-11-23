package org.firstinspires.ftc.teamcode.opModes;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import org.firstinspires.ftc.teamcode.pedro.Constants;


//here are the imports for our subsystems
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.PIDOuttake;


@Autonomous(name = "pedro auto", group = "autonomous")
public class PathingAuto extends OpMode {
    private Follower follower;

    private Intake intake;

    private PIDOuttake pidOuttake;


    private final PoseFactory poseFactory = PoseFactory.degrees();
    private Timer pathTimer, opModeTimer;

    public enum PathState {
        //start pos to end pos
        // drove state = movement state
        //shoot state = try to score

        DRIVE_STARTPOS_SHOOT_POS,
        SHOOT_PRELOAD

        //add another state for picking up those balls
        //then add another state DRIVE_PICKUPPOS_SHOOT_POS
    }

    PathState pathState;

    private final Pose startPose = poseFactory.of(56,8,90);
    private final Pose shootPose = poseFactory.of(56, 14.6, 90);

    //gonna use posefactory instead of new pose(), look above
//    private final Pose startPose = new Pose(56, 8, Math.toRadians(90));
//    //for sure gonna wanna change this shooting pose
//    private final Pose shootPose = new Pose(56, 14.6, Math.toRadians(90));

    //add some pose for picking up balls

//    private PathChain

    //example path

    //this is kinda how a path would look
//    private Path startToShoot() {
//        return line(startPose, shootPose).linear(startPose, shootPose);
//    }


    //these are really just supid test paths
    private Path startToShoot() {
        return line(startPose, shootPose).linear(startPose, shootPose);
    }

    private Path shootToStart() {
        return line(shootPose, startPose).linear(shootPose, startPose);
    }

    //stupid routine really just for test
    private Command autoRoutine() {
        return sequential(
                follow(follower, startToShoot()),
                follow(follower, shootToStart())
        );
    }
//this is to launch balls, ig i would need another to pick up balls
//    Command launchBalls = Command.build()
//            .setStart(() -> {
//                //it does this on start whatever that means
//                //i would spool up outtake i guess/set target velocity
//                //put some sort of pidOuttake.start() or smth to realize that
//                //also start a timer to check later if its taking too long
//                    })
//            .setExecute(()-> {
//                //maybe have an if to check if we have enough speed, if so then itll turn on the intake to let the balls in to get lanched
////                if(pidOuttake.getVelocity() > 1340) { intake.turnOn()}
//            })
//            .setDone(()-> {
//                //here check if too much time passed or 3 balls got launched somehow
//            })
//            .setEnd(() -> {
//                //turn off everything
//            })

    //would also have to change this if i wanted new states/paths

    @Override
    public void start() {
        //this is to schedule the auto routine so it actually runs
        schedule(autoRoutine());
    }

    @Override
    public void init() {
        pidOuttake = new PIDOuttake(this);
        intake = new Intake(this);
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        //setting what the starting point is
        follower.setPose(startPose);
        follower.update();
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
    }
}
