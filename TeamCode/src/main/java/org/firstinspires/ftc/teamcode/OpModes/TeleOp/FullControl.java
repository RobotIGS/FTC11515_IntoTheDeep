package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "FullControl", group = "FTC")
public class FullControl extends BaseTeleOp {
    /* ADD VARIABLES ONLY USED IN FULL CONTROL */
    protected boolean drive_sneak = false; // flag for storing the current speed mode
    protected boolean erste_achse_ausgefahren = false;
    protected boolean zweite_achse_ausgefahren = false;
    protected boolean kralle_zu = false;
    protected boolean hochziehen = false;
    protected boolean kralle_drehen_vorne = true;

    /* END SECTION */

    /**
     *  Gamepad 1: Fahren
     *  Gamepad 2: Arm & Kralle:
     *      a: Erste Achse ganz auf/ganz zu
     *      b: Zweite Achse ganz auf/ ganz zu
     *      x: Kralle auf/zu
     *      y: kralle nach ganz vorne/ganz hinten
     *      bumper: hochziehen (feste Position)
     *
     *      left_stick_y: erste Achse
     *      right_stick_y: zweite Achse
     *      dpad up/down: Kralle
     *      dpad left/right: Kralle drehen
     *      trigger: hochziehen (manuell)
     */

    @Override
    public void initialize() {
        super.initialize();
        /* ADD CODE WHICH IS RUN ONCE WHEN INIT IS PRESSED */

        /* END SECTION */
    }

    @Override
    public void runOnce() {
        /* ADD CODE WHICH IS RUN ONCE WHEN PLAY IS PRESSED */

        hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_eingefahren);
        hwMap.servo_kralle.setPosition(hwMap.kralle_offen);
        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_kralle_drehen.setPosition(hwMap.kralle_drehen_vorne);

        /* END SECTION */
    }

    @Override
    public void runLoop() {
        /* ADD OTHER HARDWARE CONTROLS DOWN BELOW */
        if (gamepad2.a){
            if(!erste_achse_ausgefahren){
                hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_oben);
            }
            else {
                hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
            }
            erste_achse_ausgefahren = !erste_achse_ausgefahren;
            while (gamepad2.a){}
        }
        if (gamepad2.b){
            if(!zweite_achse_ausgefahren){
                hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_ausgefahren);
            }
            else {
                hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse_eingefahren);
            }
            zweite_achse_ausgefahren = !zweite_achse_ausgefahren;
            while (gamepad2.b){}
        }
        if (gamepad2.x){
            if(kralle_zu){
                hwMap.servo_kralle.setPosition(hwMap.kralle_offen);
            }
            else {
                hwMap.servo_kralle.setPosition(hwMap.kralle_zu);
            }
            kralle_zu = !kralle_zu;
            while (gamepad2.x){}
        }
        if (gamepad2.y){
            if (kralle_drehen_vorne) {
                hwMap.servo_kralle_drehen.setPosition(hwMap.kralle_drehen_hinten);
            }
            else{
                hwMap.servo_kralle_drehen.setPosition(hwMap.kralle_drehen_vorne);
            }
            kralle_drehen_vorne = !kralle_drehen_vorne;
            while (gamepad2.y) {}
        }

        if (gamepad2.left_stick_y < 0) {
            hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse.getCurrentPosition()+25);
        }
        else if (gamepad2.left_stick_y > 0) {
            hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse.getCurrentPosition()-25);
        }

        if (gamepad2.right_stick_y < 0) {
            hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse.getPosition()+0.01);
        }
        else if (gamepad2.right_stick_y > 0) {
            hwMap.servo_zweite_achse.setPosition(hwMap.servo_zweite_achse.getPosition()-0.01);
        }

        if (gamepad2.dpad_up){
            hwMap.servo_kralle.setPosition(hwMap.servo_kralle.getPosition()-0.05);
        }
        else if (gamepad2.dpad_down) {
            hwMap.servo_kralle.setPosition(hwMap.servo_kralle.getPosition()+0.05);
        }

        if (gamepad2.dpad_left){
            hwMap.servo_kralle_drehen.setPosition(hwMap.servo_kralle_drehen.getPosition()-0.05);
        }
        else if (gamepad2.dpad_right) {
            hwMap.servo_kralle_drehen.setPosition(hwMap.servo_kralle_drehen.getPosition()+0.05);
        }

        // HOCHZIEHEN
        if (gamepad2.right_bumper || gamepad2.left_bumper){
            if(!hochziehen){
                hwMap.motor_hochziehen_links.setPower(0.5);
            }
            else {
                hwMap.motor_hochziehen_rechts.setPower(0.0);
            }
            hochziehen = !hochziehen;
            while (gamepad2.right_bumper || gamepad2.left_bumper){}
        }
        hwMap.motor_hochziehen_links.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
        hwMap.motor_hochziehen_rechts.setPower(gamepad2.left_trigger - gamepad2.right_trigger);


        /* END SECTION */

        /* DRIVING */
        if (gamepad1.left_bumper || gamepad1.right_bumper) {
            drive_sneak = !drive_sneak;
            while ((gamepad1.left_bumper || gamepad1.right_bumper) && opModeIsActive()) {
            }
        }
        hwMap.robot.setSpeed(
                -gamepad1.left_stick_y * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                -gamepad1.right_stick_x * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                (gamepad1.left_trigger - gamepad1.right_trigger) *
                        (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full));


        /* UPDATE THE ROBOT */
        hwMap.robot.step();

        /* ADD TELEMETRY FOR DRIVER DOWN BELOW */
        telemetry.addData("kralle", hwMap.servo_kralle.getPosition());
        telemetry.addData("kralle_drehen", hwMap.servo_kralle_drehen.getPosition());
        telemetry.addData("erste Achse", hwMap.motor_erste_achse.getCurrentPosition());
        telemetry.addData("zweite Achse", hwMap.servo_zweite_achse.getPosition());
        telemetry.addData("motor_hochziehen", hwMap.motor_hochziehen_links.getPower());
        telemetry.addData("erste_achse_ausgefahren", erste_achse_ausgefahren);
        telemetry.addData("zweite_achse_ausgefahren", zweite_achse_ausgefahren);
        telemetry.addData("kralle_zu", kralle_zu);
        telemetry.addData("kralle_drehen_vorne", kralle_drehen_vorne);
        telemetry.addData("hochziehen", hochziehen);

        telemetry.addLine();
        telemetry.addData("SNEAK", drive_sneak);

        /* END SECTION */
        telemetry.addLine();
        telemetry.addLine(hwMap.chassis.debug());
        telemetry.addLine(hwMap.navi.debug());
        telemetry.update();
    }
}