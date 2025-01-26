package Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import Robot.Robot;

@Autonomous(name = "1SpecimenAuton", group = "Auton")
public class SpecimenOne extends LinearOpMode {

    Robot robot = new Robot();



    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        robot.driveTrain.mecDrive(1,1,1,1);
        sleep(2000);
    }

}
