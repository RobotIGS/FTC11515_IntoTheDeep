package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;

@Autonomous(name = "TestAutonome")
public class TestAutonome extends BaseAutonomous {
    @Override
    public void run() {
        hwMap.robot.drive(new Position2D(0, 100));
        while (opModeIsActive() && hwMap.navi.getDriving()) {
            hwMap.robot.step();
        }
        while (opModeIsActive()) {}



        telemetry.addLine(hwMap.navi.debug());
        telemetry.addLine(hwMap.chassis.debug());
        telemetry.update();
    }
}
