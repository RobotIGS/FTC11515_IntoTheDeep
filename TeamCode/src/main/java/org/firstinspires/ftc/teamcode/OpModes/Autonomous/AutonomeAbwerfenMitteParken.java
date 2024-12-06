package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;

/*@Autonomous(name = "AutonomeAbwerfenMitteParken")
public class AutonomeAbwerfenMitteParken extends BaseAutonomous {

    @Override
    public void run() {
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_eingefahren);
        hwMap.servo_kralle.setPosition(hwMap.kralle_zu);
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_intake_drehen.setPosition(hwMap.kralle_drehen_hinten);

        hwMap.motor_erste_achse.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        hwMap.robot.navi.setKeepRotation(true);


        // START: 2. Kachel an Kante zur 3. Kachel

        // nach vorne fahren
        hwMap.robot.drive(new Position2D(20, 0));
        schleife();

        // nach links drehen
        hwMap.robot.rotate(-90);
        schleife();

        // Arm ausfahren
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_oben);
        sleep(250);
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_ausgefahren);

        // fahren
        hwMap.robot.drive(new Position2D(-50, 0));
        schleife();

        // zur Box drehen
        hwMap.robot.rotate(45);
        schleife();

        // fahren
        hwMap.robot.drive(new Position2D(-30, 0));
        schleife();
        sleep(250);

        // Stein abladen
        hwMap.servo_kralle.setPosition(hwMap.kralle_offen);
        sleep(250);
        hwMap.servo_intake_drehen.setPosition(hwMap.kralle_drehen_vorne);
        sleep(100);

        // fahren
        hwMap.robot.drive(new Position2D(25, 0));
        schleife();
        sleep(100);

        // 2. Achse einfahren
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_mitte);
        sleep(100);

        // 1. Achse einfahren
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
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
}*/
