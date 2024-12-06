/*
package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name = "MiaFullControl", group = "FTC")
public class MiaFullControl extends BaseTeleOp {
    protected boolean drive_sneak = false; // flag for storing the current speed mode
    protected boolean erste_achse_ausgefahren = false;
    protected boolean zweite_achse_ausgefahren = false;
    protected boolean kralle_zu = false;
    protected boolean hochziehen = false;
    protected boolean kralle_drehen_vorne = true;

    @Override
    public void runOnce() {
        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_eingefahren);
        hwMap.servo_kralle.setPosition(hwMap.kralle_offen);
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_intake_drehen.setPosition(hwMap.kralle_drehen_vorne);

    }

    @Override
    public void runLoop() {
        if (gamepad1.left_bumper) {
            drive_sneak = !drive_sneak;
            while (gamepad1.left_bumper) {

            }
        } // FAHREN
        if (gamepad1.left_stick_y < 0) {
            hwMap.robot.setSpeed(
                    gamepad1.left_stick_y * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    0 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    0 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full));
        } else if (gamepad1.left_stick_y > 0) {
            hwMap.robot.setSpeed(
                    gamepad1.left_stick_y * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    0 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    0 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full));
        } else {
            hwMap.robot.setSpeed(
                    0 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    0 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    0 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full));

        }
        if (gamepad1.left_stick_x < 0 || gamepad1.left_stick_y > 0) {
            hwMap.robot.setSpeed(
                    gamepad1.left_stick_y * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    0 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    0 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full));
        } else {
            hwMap.robot.setSpeed(
                    0,
                    0,
                    0);

        } // KRALLE DREHEN
        if (gamepad2.dpad_up) {
            hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen.getPosition() + 0.05);
        }
        if (gamepad2.dpad_down) {
            hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen.getPosition() - 0.05);
        } // KRALLE ÖFFNEN/ SCHLIEẞEN:
        if (gamepad2.dpad_left) {
            hwMap.servo_kralle.setPosition(hwMap.servo_kralle.getPosition() + 0.025);
        }
        if (gamepad2.dpad_right) {
            hwMap.servo_kralle.setPosition(hwMap.servo_kralle.getPosition() - 0.025);

        } // BEWEGUNG DER ERSTEN ACHSE
        if (gamepad2.left_stick_y > 0) {
            hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse.getCurrentPosition() + 25);
        } else if (gamepad2.left_stick_y < 0) {
            hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse.getCurrentPosition() - 25);
        } // BEWEGUNG DER ZWEITE ACHSE
        if (gamepad2.right_stick_y > 0) {
            hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse.getCurrentPosition() + 25);
        } else if (gamepad2.right_stick_y < 0) {
            hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse.getPosition() - 0.01);
        } while (gamepad2.dpad_right) {

        }
    }
}*/