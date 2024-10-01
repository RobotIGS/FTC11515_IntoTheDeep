package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;

@Autonomous(name = "TestAutonome")
public class TestAutonome extends BaseAutonomous {

    double yTravel = 335;

    @Override
    public void run() {
        hwMap.robot.navi.setKeepRotation(true);

        hwMap.robot.drive(new Position2D(20, 0));

        while (opModeIsActive() && hwMap.navi.getDriving()) {
            hwMap.robot.step();
            telemetry.addLine(hwMap.navi.debug());
            telemetry.addLine(hwMap.chassis.debug());
            telemetry.update();

        }

        hwMap.robot.rotate(90);

        while (opModeIsActive() && hwMap.navi.getDriving()) {
            hwMap.robot.step();
            telemetry.addLine(hwMap.navi.debug());
            telemetry.addLine(hwMap.chassis.debug());
            telemetry.update();
        }
        hwMap.robot.drive(new Position2D(60, 0));

        while (opModeIsActive() && hwMap.navi.getDriving()) {
            hwMap.robot.step();
            telemetry.addLine(hwMap.navi.debug());
            telemetry.addLine(hwMap.chassis.debug());
            telemetry.update();
        }

        hwMap.robot.rotate(45);
        while (opModeIsActive() && hwMap.navi.getDriving()) {
            hwMap.robot.step();
            telemetry.addLine(hwMap.navi.debug());
            telemetry.addLine(hwMap.chassis.debug());
            telemetry.update();
        }


//        for (int i = 0; i<4; i++) {
//            hwMap.robot.drive(new Position2D(-100, yTravel));
//            while (opModeIsActive() && hwMap.navi.getDriving()) {
//                hwMap.robot.step();
//                telemetry.addLine(hwMap.navi.debug());
//                telemetry.addLine(hwMap.chassis.debug());
//                telemetry.update();
//            }
//            yTravel = yTravel + 20;
//            hwMap.robot.drive(new Position2D(100, -yTravel));
//            while (opModeIsActive() && hwMap.navi.getDriving()) {
//                hwMap.robot.step();
//                telemetry.addLine(hwMap.navi.debug());
//                telemetry.addLine(hwMap.chassis.debug());
//                telemetry.update();
//            }
//        }
    }
}
