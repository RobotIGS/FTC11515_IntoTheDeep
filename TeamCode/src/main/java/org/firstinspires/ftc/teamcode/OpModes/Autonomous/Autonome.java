package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;

@Autonomous(name = "Autonome")
public class Autonome extends BaseAutonomous {

    @Override
    public void run() {
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_eingefahren);
        hwMap.servo_kralle.setPosition(hwMap.kralle_zu);
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_kralle_drehen.setPosition(hwMap.kralle_drehen_hinten);

        hwMap.motor_erste_achse.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        hwMap.robot.navi.setKeepRotation(true);

        // START

        hwMap.robot.drive(new Position2D(35, 0));
        schleife();

        hwMap.robot.rotate(-90);
        schleife();

        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_oben);
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_ausgefahren);
        hwMap.robot.drive(new Position2D(-85, 0));
        schleife();

        hwMap.robot.rotate(45);
        schleife();

        hwMap.servo_kralle.setPosition(hwMap.kralle_offen);
        sleep(10);

        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_kralle_drehen.setPosition(hwMap.kralle_drehen_vorne);
        hwMap.robot.rotate(45);
        schleife();

        hwMap.robot.drive(new Position2D(45, 25));
        schleife();

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

        hwMap.robot.rotate(-45);
        schleife();

        hwMap.robot.drive(new Position2D(210, -95));
        schleife();

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
