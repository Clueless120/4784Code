package TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import Robot.Robot;

@TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOp4784 extends LinearOpMode {

   Robot robot = new Robot();

    boolean lastAButtonState = false;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Initialized! Activate Controller(s)...");

        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            double drive  = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn   = -gamepad1.right_stick_x;

            double theta = Math.atan2(drive, strafe);
            double power = Math.hypot(strafe, drive);
            double sin = Math.sin(theta - Math.PI / 4);
            double cos = Math.cos(theta - Math.PI / 4);
            double max = Math.max(Math.abs(sin), Math.abs(cos));

            double frontLeftPower  = power * cos / max - turn;
            double frontRightPower = power * sin / max + turn;
            double backLeftPower   = power * sin / max - turn;
            double backRightPower  = power * cos / max + turn;

            if (power + Math.abs(turn) > 1) {
                frontLeftPower  /= power + Math.abs(turn);
                frontRightPower /= power + Math.abs(turn);
                backLeftPower   /= power + Math.abs(turn);
                backRightPower  /= power + Math.abs(turn);
            }

            robot.driveTrain.mecDrive(frontLeftPower, frontRightPower, backLeftPower, backRightPower);

//            double verticalSlideExtensionPower = gamepad1.left_trigger - gamepad1.right_trigger;
//            int currentVerticalSlidePosition = (robot.slides.LinearSlide.getCurrentPosition()) / 2;
//            int verticalSlideExtensionMin  =  -75;  // Min Value
//            int verticalToleranceThreshold =   25;  // Threshold Value (Adjust)
//            double slowFactor = 0.75;


            final double HOLD = 0.0005;
            boolean Holding = false;

            if (gamepad1.right_trigger > 0 && robot.slides.LinearSlide.getCurrentPosition() < 4000) {
                robot.slides.LinearSlide.setPower(1.00);
            } else if (gamepad1.left_trigger > 0 && robot.slides.LinearSlide.getCurrentPosition() > 0) {
                robot.slides.LinearSlide.setPower(-1.00);
            } else {
                // Stop the slide and set the brake behavior
                robot.slides.LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                robot.slides.LinearSlide.setPower(0.00);
            }


            boolean LB = gamepad1.left_bumper;
            boolean RB = gamepad1.right_bumper;

            if(LB) {
                robot.scoring.IntakeL.setDirection(Servo.Direction.REVERSE);
                robot.scoring.IntakeL.setPosition(1);

                robot.scoring.IntakeR.setDirection(Servo.Direction.REVERSE);
                robot.scoring.IntakeR.setPosition(1);
            }
            else if (RB) {
                robot.scoring.IntakeL.setDirection(Servo.Direction.FORWARD);
                robot.scoring.IntakeL.setPosition(1);

                robot.scoring.IntakeR.setDirection(Servo.Direction.FORWARD);
                robot.scoring.IntakeR.setPosition(1);

            }
            else {
                robot.scoring.IntakeL.setPosition(0);
                robot.scoring.IntakeR.setPosition(0);
            }
        }

        if (gamepad1.dpad_up) {
            robot.slides.LinearPivot.setPower(1.0);
        } else if (gamepad1.dpad_down) {
            robot.slides.LinearPivot.setPower(-1.0);
        } else {
            robot.slides.LinearPivot.setPower(0);
        }
    }
}


