package Robot;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;


public class Robot {

    public class DriveTrain {
        public DcMotor frontLeft, frontRight, backLeft, backRight;
        public void init(HardwareMap hwMap) {
            frontLeft = hwMap.dcMotor.get("frontLeft");    // Config 0
            frontRight = hwMap.dcMotor.get("frontRight");   // Config 1
            backLeft = hwMap.dcMotor.get("backLeft");     // Config 2
            backRight = hwMap.dcMotor.get("backRight");    // Config 3

            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);

            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }
        public void mecDrive(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }
    }


    public class Slides {

        public DcMotor LinearSlide, LinearPivot;

        public void init(HardwareMap hwMap) {
            LinearSlide = hwMap.dcMotor.get("LinearSlide");
            LinearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
            LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            LinearPivot = hwMap.dcMotor.get("LinearPivot");
            LinearPivot.setDirection(DcMotorSimple.Direction.FORWARD);
            LinearPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

    }
    public class Scoring {
        public Servo IntakeL, IntakeR;

        public void init(HardwareMap hwMap){
            IntakeL = hwMap.servo.get("IntakeL");
            IntakeL.setPosition(0.00);

            IntakeR = hwMap.servo.get("IntakeR");
            IntakeR.setPosition(0.00);
        }

    }

    public DriveTrain driveTrain = new DriveTrain();
    public Scoring scoring = new Scoring();
    public Slides slides = new Slides();

    public void init(HardwareMap hwMap) {
        driveTrain.init(hwMap);
        scoring.init(hwMap);
        slides.init(hwMap);
    }

}