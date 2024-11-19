package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;

@Autonomous(name = "FullAutonome")
public class FullAutonome extends BaseAutonomous {

    @Override
    public void run() {
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_eingefahren);
        hwMap.servo_kralle.setPosition(hwMap.kralle_zu);
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_kralle_drehen.setPosition(hwMap.kralle_drehen_hinten);

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
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_ausgefahren);
        //geändert von -80 auf -70 da sich der roboter zu nah an den korb bewegt
        hwMap.robot.drive(new Position2D(-70, 0));
        schleife();

        // zur Box drehen
        hwMap.robot.rotate(45);
        schleife();

        // Stein abladen
        hwMap.servo_kralle.setPosition(hwMap.kralle_offen);
        sleep(10);

        // 2. Achse einfahren
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_mitte);
        sleep(100);

        // Vorbereitung zum Greifen vom Boden
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_kralle_drehen.setPosition(hwMap.kralle_drehen_vorne);
        hwMap.robot.rotate(45);
        schleife();

        // Fahren
        hwMap.robot.drive(new Position2D(45, 25));
        schleife();

        // Stein aufnehmen
        hwMap.servo_kralle.setPosition(hwMap.kralle_zu);
        sleep(10);
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_oben);
        hwMap.servo_kralle_drehen.setPosition(hwMap.kralle_drehen_hinten);
        hwMap.robot.rotate(-45);
        schleife();

        hwMap.robot.drive(new Position2D(-50, 25));
        schleife();

        hwMap.servo_kralle.setPosition(hwMap.kralle_offen);
        sleep(10);

        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_eingefahren);

        // Parken
        hwMap.robot.rotate(-45);
        schleife();

        hwMap.robot.drive(new Position2D(210, -95));
        schleife();

        // Stange berühren
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_oben-120);
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
