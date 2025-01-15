
package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;

@Autonomous(name = "AutonomeAbwerfenMitteParken")
public class AutonomeAbwerfenMitteParken extends BaseAutonomous {

    @Override
    public void run() {
        hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_achse_unten);
        hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen_vorne);
        hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_unten);

        hwMap.robot.navi.setKeepRotation(true);


        // STARTPOSITION: 2. Kachel an Kante zur 3. Kachel

        // nach vorne fahren
        hwMap.robot.drive(new Position2D(20, 0));
        schleife();

        // nach links drehen
        hwMap.robot.rotate(-90);
        schleife();

        // Aufzug ausfahren
        hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_oben);

        // fahren
        hwMap.robot.drive(new Position2D(-50, 0));
        schleife();

        // zur Box drehen
        hwMap.robot.rotate(45);
        schleife();

        // fahren
        hwMap.robot.drive(new Position2D(-30, 0));
        schleife();
        sleep(300);

        // Stein abladen und Korb anschließend einfahren
        hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_oben);
        sleep(800);
        hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_unten);
        sleep(250);


        // Aufzug einfahren
        hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_unten);
        sleep(100);

        // fahren
        hwMap.robot.drive(new Position2D(25, 0));
        schleife();
        sleep(100);

        // nach vorne fahren
        hwMap.robot.drive(new Position2D(110, 45));
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