package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.Math;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
@Autonomous(name="Pels Plors", group="Pushbot")
public class AutonomousQueFuncioniPlis extends LinearOpMode{
    private DcMotor frontLeftMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backRightMotor = null;



    static final double TicksPercm = 537.6/(10*3.141592654);
    int ticksParellX = 0;
    int ticksParellY = 0;
    double angle = 0;
    double distance = 60;

    public void runOpMode() {
        angle += 45;
        angle = Math.toRadians(angle);

    frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_drive");
    backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_drive");
    frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_drive");
    backRightMotor = hardwareMap.get(DcMotor.class, "back_right_drive");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);

        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ticksParellX = (int)(distance*TicksPercm*Math.cos(angle));
        ticksParellY = (int)(distance*TicksPercm*Math.sin(angle));
        do {
            sleep(10);
        }while(!opModeIsActive());
        if (opModeIsActive()) {
            backLeftMotor.setTargetPosition(ticksParellX);
            frontRightMotor.setTargetPosition(ticksParellX);
            backRightMotor.setTargetPosition(ticksParellY);
            frontLeftMotor.setTargetPosition(ticksParellY);

            frontLeftMotor.setPower(0.2);
            frontRightMotor.setPower(0.2);//0

            backRightMotor.setPower(0.2);
            backLeftMotor.setPower(0.2); //0

            //Connfigurem  encoders
            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(10000);
        }
    }
}

