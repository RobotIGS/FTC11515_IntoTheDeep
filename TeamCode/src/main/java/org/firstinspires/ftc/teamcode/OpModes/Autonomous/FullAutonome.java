package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;

@Autonomous(name = "FullAutonome")
public class FullAutonome extends BaseAutonomous {

    @Override
    public void run() {
        hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_achse_unten);
        hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen_abgeben);

        hwMap.motor_intake_achse.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        hwMap.robot.navi.setKeepRotation(true);

        // START: 2. Kachel an Kante zur 3. Kachel

        // nach vorne fahren
        hwMap.robot.drive(new Position2D(20, 0));
        schleife();

        // nach links drehen
        hwMap.robot.rotate(-90);
        schleife();

        //Seilzug ausfahren
        hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_oben);

        //zum Korb fahren
        hwMap.robot.drive(new Position2D(-70, 0));
        schleife();

        // zum Korb drehen
        hwMap.robot.rotate(45);
        schleife();

        // Korb nach hinten drehen und Stein ablegen
        hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_unten);
        sleep(100);
        hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_oben);

        // Seilzug einfahren
        hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_unten);


        // Vorbereitung zum Greifen vom Boden
        hwMap.robot.rotate(45);
        schleife();
        hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_achse_unten);

        // Fahren
        hwMap.robot.drive(new Position2D(45, 25));
        schleife();

        // Stein aufnehmen

        hwMap.motor_intake_achse.setPower(-0.5);
        while (! hwMap.touchSensor.isPressed()) {}
        hwMap.motor_intake_achse.setPower(0);
        hwMap.servo_intake_links.setPower(0.1);
        hwMap.servo_intake_rechts.setPower(-0.1);
        sleep(200);
        hwMap.servo_intake_links.setPower(0);
        hwMap.servo_intake_rechts.setPower(0);

        sleep(10);
        hwMap.robot.rotate(-45);
        schleife();

        // zum Korb fahren
        hwMap.robot.drive(new Position2D(-50, 25));
        schleife();

        //Seilzug ausfahren
        hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_oben);


        //Korb nach hinten drehen und stein ablegen
        hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_unten);
        sleep(100);
        hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_oben);

        //Seilzug einfahren
        hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_unten);

        // Parken
        hwMap.robot.rotate(-45);
        schleife();

        hwMap.robot.drive(new Position2D(210, -95));
        schleife();

        // Stange berühren
        hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_achse_stange);
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