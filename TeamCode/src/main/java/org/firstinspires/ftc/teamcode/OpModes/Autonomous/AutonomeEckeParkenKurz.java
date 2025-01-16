package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;

@Autonomous(name = "AutonomeEckeParkenKurz")
public class AutonomeEckeParkenKurz extends BaseAutonomous {

    @Override
    public void run() {
        hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_achse_unten);
        hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen_aufnehmen);
        hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_unten);


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
}
