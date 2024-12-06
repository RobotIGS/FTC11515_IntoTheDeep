/*package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;

@Autonomous(name = "AutonomeEckeParkenKurz")
public class AutonomeEckeParkenKurz extends BaseAutonomous {

    @Override
    public void run() {
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_eingefahren);
        hwMap.servo_kralle.setPosition(hwMap.kralle_zu);
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_kralle_drehen.setPosition(hwMap.kralle_drehen_hinten);

        hwMap.motor_erste_achse.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        hwMap.robot.navi.setKeepRotation(true);

        // START: Kante zwischen zwei und drei von links

        // fahren
        hwMap.robot.drive(new Position2D(0, -120));
        schleife();

    }

    void schleife() {
        while (opModeIsActive() && hwMap.navi.getDriving()) {
            hwMap.robot.step();
            telemetry.addLine(hwMap.navi.debug());
            telemetry.addLine(hwMap.chassis.debug());
            telemetry.update();
        }
    }
}*/
