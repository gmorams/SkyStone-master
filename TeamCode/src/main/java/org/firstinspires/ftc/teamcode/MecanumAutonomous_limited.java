package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Mecanum Autonomous Limited", group="Pushbot")
public class MecanumAutonomous_limited extends LinearOpMode{
    private DcMotor frontLeftMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backRightMotor = null;



    static final double TicksPercm = 537.6/(10*3.141592654);
    int ticksParellX = 0;
    int ticksParellY = 0;
    double powerParellX=0;
    double powerParellY=0;

    public void runOpMode() {

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

        waitForStart();

        if (opModeIsActive()) {
            move("fl", 70,0.6);
            move("br", 70,0.6);
        }
    }

    public void move (String direction, double distance, double power) {

        if (direction == "fl") {
            ticksParellX = (int) (distance * TicksPercm);
            ticksParellY = 0;

            powerParellX = power;
            powerParellY = 0.1;

        } else if (direction == "f") {
            ticksParellX = (int) (distance * TicksPercm);
            ticksParellY = (int) (distance * TicksPercm);

            powerParellX = power;
            powerParellY = power;

        } else if (direction == "fr") {
            ticksParellX = 0;
            ticksParellY = (int) (distance * TicksPercm);powerParellX = power;

            powerParellX = 0.1;
            powerParellY = power;

        } else if (direction == "bl") {
            ticksParellX = 0;
            ticksParellY = (int) (distance * TicksPercm)*-1;

            powerParellX = 0.1;
            powerParellY = -power;

        } else if (direction == "b") {
            ticksParellX = (int) (distance * TicksPercm)*-1;
            ticksParellY = (int) (distance * TicksPercm)*-1;

            powerParellX = -power;
            powerParellY = -power;
        } else if (direction == "br") {
            ticksParellX = (int) (distance * TicksPercm)*-1;
            ticksParellY = 0;

            powerParellX = -power;
            powerParellY = 0.1;
        } else if (direction == "l") {
            ticksParellX = (int) (distance * TicksPercm);
            ticksParellY = (int) (distance * TicksPercm)*-1;

            powerParellX = power;
            powerParellY = -power;
        } else if (direction == "r") {
            ticksParellX = (int) (distance * TicksPercm)*-1;
            ticksParellY = (int) (distance * TicksPercm);

            powerParellX = -power;
            powerParellY = power;
        }

        if (opModeIsActive()) {

            backLeftMotor.setTargetPosition(backLeftMotor.getCurrentPosition()+ticksParellX);
            frontRightMotor.setTargetPosition(frontRightMotor.getCurrentPosition()+ticksParellX);
            backRightMotor.setTargetPosition(backRightMotor.getCurrentPosition()+ticksParellY);
            frontLeftMotor.setTargetPosition(frontLeftMotor.getCurrentPosition()+ticksParellY);

            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);//0
            backRightMotor.setPower(power);
            backLeftMotor.setPower(power); //0

            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            while (opModeIsActive() && frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy()){

                // Display it for the driver.
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        frontLeftMotor.getCurrentPosition(),
                        backRightMotor.getCurrentPosition());
                telemetry.update();
                /*
                //--> ! 03.01.20 Pendent de canviar i posar l'aturada dins del while
                // Stop all motion;
                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backRightMotor.setPower(0); */


            }

            // Turn off RUN_TO_POSITION
            frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);

        }

    }
}

