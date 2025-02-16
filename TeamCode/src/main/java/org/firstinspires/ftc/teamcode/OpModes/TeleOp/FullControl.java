package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "FullControl", group = "FTC")
public class FullControl extends BaseTeleOp {
    /* ADD VARIABLES ONLY USED IN FULL CONTROL */
    protected boolean drive_sneak = false; // flag for storing the current speed mode

    private int lift_reset = 0;
    private int arm_reset = 0;

    /* END SECTION */

    /**
     *  Gamepad 1: Fahren
     *      Zusatz:
     *          a: Speed vorwärts
     *          b: Speed rückwärts
     *  Gamepad 2:
     *      Vordefiniert:
     *          x: OVERRIDE LIMITS
     *          a: intake kopf drehen vorne/ hinten
     *          b: intake achse hoch/ runter (ZU GEFÄHRLICH)
     *          y: Korb hoch/ runter
     *          dpad left: intake Halterung drehen
     *          dpad right: intake Halterung drehen
     *          dpad up: Korb hoch
     *          dpad down: Korb runter
     *          bumper left: aufzug hoch/ runter
     *          bumper right: Haken klappen
     *          trigger left: intake pixel aufnehmen
     *          trigger right: intake pixel ablegen
     *
     *          gamepad1.dpad_left: intake arm drehen limits
     *          gamepad1.dpad_right: aufzug limits
     *
     *      Manuell:
     *          left stick x: intake Achse drehen
     *          left stick y: intake Achse
     *          right stick x: hochziehen
     *          right stick y: Aufzug
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

        /* END SECTION */
    }

    @Override
    public void runLoop() {
        /* ADD OTHER HARDWARE CONTROLS DOWN BELOW */

        // INTAKE aufnehmen/ abgeben
        if (gamepad2.left_trigger > 0 && gamepad2.right_trigger <= 0){
            hwMap.servo_intake_rechts.setPower(0.5);
            hwMap.servo_intake_links.setPower(-0.5);
        }
        else if (gamepad2.right_trigger > 0 && gamepad2.left_trigger <= 0){
            hwMap.servo_intake_rechts.setPower(-0.5);
            hwMap.servo_intake_links.setPower(0.5);
        }
        else {
            hwMap.servo_intake_rechts.setPower(0);
            hwMap.servo_intake_links.setPower(0);
        }

        // INTAKE KOPF DREHEN feste Werte
        if (gamepad2.a){
            double position = (Math.abs(hwMap.servo_intake_drehen_abgeben + hwMap.servo_intake_drehen_aufnehmen)/2) - hwMap.servo_intake_drehen.getPosition();
            if (position < 0) {
                hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen_abgeben);
            }
            else{
                hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen_aufnehmen);
            }
            while (gamepad2.a && opModeIsActive()){}
        }
        // INTAKE KOPF DREHEN manuell
        if (gamepad2.dpad_left){
            hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen.getPosition()-0.05);
        }
        else if (gamepad2.dpad_right) {
            hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen.getPosition()+0.05);
        }

        // INTAKE ACHSE HOCH RUNTER feste Werte
        // ZU GEFÄHRLICH
        /* if (gamepad2.b){
            double position = ((float) Math.abs(hwMap.motor_achse_unten + hwMap.motor_achse_ueber_box)/2) - hwMap.motor_intake_achse.getCurrentPosition();
            if(position < 0){
                hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_achse_unten);
            }
            else {
                hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_achse_ueber_box);
            }
            while (gamepad2.b && opModeIsActive()){}
        } */

        // INTAKE ACHSE HOCH RUNTER manuell
        if(gamepad2.left_stick_y != 0){
            if(hwMap.motor_intake_achse.getMode()== DcMotor.RunMode.RUN_TO_POSITION){
                hwMap.motor_intake_achse.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            if (hwMap.touchSensor.isPressed() && gamepad2.left_stick_y > 0) {

            } else {
                hwMap.motor_intake_achse.setPower(gamepad2.left_stick_y * 0.15);
            }
        }
        else if(hwMap.motor_intake_achse.getMode() == DcMotor.RunMode.RUN_USING_ENCODER){
            hwMap.motor_intake_achse.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_intake_achse.getCurrentPosition());
        }

        // INTAKE PLATTE DREHEN manuell
        if( gamepad2.left_stick_x != 0 && (((hwMap.motor_intake_arm_drehen.getCurrentPosition() > hwMap.motor_intake_arm_drehen_rechts || gamepad2.left_stick_x > 0) && (hwMap.motor_intake_arm_drehen.getCurrentPosition() < hwMap.motor_intake_arm_drehen_links || gamepad2.left_stick_x < 0)) || gamepad2.x)) {
            if (hwMap.motor_intake_arm_drehen.getMode() == DcMotor.RunMode.RUN_TO_POSITION)
                hwMap.motor_intake_arm_drehen.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hwMap.motor_intake_arm_drehen.setPower(gamepad2.left_stick_x * 0.2);
        } else if (hwMap.motor_intake_arm_drehen.getMode() == DcMotor.RunMode.RUN_USING_ENCODER) {
            if (hwMap.motor_intake_arm_drehen.getCurrentPosition() < hwMap.motor_intake_arm_drehen_rechts)
                hwMap.motor_intake_arm_drehen.setTargetPosition(hwMap.motor_intake_arm_drehen_rechts);
            if (hwMap.motor_intake_arm_drehen.getCurrentPosition() > hwMap.motor_intake_arm_drehen_links)
                hwMap.motor_intake_arm_drehen.setTargetPosition(hwMap.motor_intake_arm_drehen_links);
            hwMap.motor_intake_arm_drehen.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hwMap.motor_intake_arm_drehen.setTargetPosition(hwMap.motor_intake_arm_drehen.getCurrentPosition());
        }
        // arm limit reset
        if (gamepad1.dpad_left) {
            int halbesDeltaLimit = (hwMap.motor_intake_arm_drehen_links - hwMap.motor_intake_arm_drehen_rechts) / 2;
            hwMap.motor_intake_arm_drehen_rechts = hwMap.motor_intake_arm_drehen.getCurrentPosition() - halbesDeltaLimit;
            hwMap.motor_intake_arm_drehen_links = hwMap.motor_intake_arm_drehen.getCurrentPosition() + halbesDeltaLimit;
            arm_reset++;
            while (gamepad1.dpad_left && opModeIsActive()) {}
        }

        // -----------------------------------------------------------------

        // KORB ARM HOCH RUNTER feste werte
        if(gamepad2.y){
            double position = (Math.abs(hwMap.servo_korb_arm_oben + hwMap.servo_korb_arm_unten)/2) - hwMap.servo_korb_hoch_runter.getPosition();
            if (position < 0) {
                hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_oben);
            }
            else{
                hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_arm_unten);
            }
            while (gamepad2.y && opModeIsActive()){}
        }

        // KORB ARM HOCH RUNTER manuell
        if (gamepad2.dpad_down){
            hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_hoch_runter.getPosition() + 0.01);
        }
        else if (gamepad2.dpad_up){
            hwMap.servo_korb_hoch_runter.setPosition(hwMap.servo_korb_hoch_runter.getPosition() - 0.01);
        }

        // -----------------------------------------------------------------

        // AUFZUG feste Werte
        if (gamepad2.left_bumper) {
            double position = (float) (Math.abs(hwMap.motor_aufzug_oben + hwMap.motor_aufzug_unten) / 2) - hwMap.motor_aufzug.getCurrentPosition();
            if (position > 0) {
                hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_oben);
            } else {
                hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_unten);
            }
            while (gamepad2.left_bumper && opModeIsActive()){}
        }

        // Aufzug hoch und runter manuell
        if( gamepad2.right_stick_y != 0 && (((hwMap.motor_aufzug.getCurrentPosition() > hwMap.motor_aufzug_unten || gamepad2.right_stick_y < 0) && (hwMap.motor_aufzug.getCurrentPosition() < hwMap.motor_aufzug_oben || gamepad2.right_stick_y > 0)) || gamepad2.x)) {
            if (hwMap.motor_aufzug.getMode() == DcMotor.RunMode.RUN_TO_POSITION)
                hwMap.motor_aufzug.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hwMap.motor_aufzug.setPower(-gamepad2.right_stick_y);
        } else if (hwMap.motor_aufzug.getMode() == DcMotor.RunMode.RUN_USING_ENCODER) {
            if (hwMap.motor_aufzug.getCurrentPosition() < hwMap.motor_aufzug_unten)
                hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_unten);
            if (hwMap.motor_aufzug.getCurrentPosition() > hwMap.motor_aufzug_oben)
                hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_oben);
            hwMap.motor_aufzug.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug.getCurrentPosition());
        }
        // aufzug limit reset
        if (gamepad1.dpad_right) {
            int deltaLimit = hwMap.motor_aufzug_oben - hwMap.motor_aufzug_unten;
            hwMap.motor_aufzug_unten = hwMap.motor_aufzug.getCurrentPosition();
            hwMap.motor_aufzug_oben = hwMap.motor_aufzug.getCurrentPosition() + deltaLimit;
            lift_reset++;
            while (gamepad1.dpad_right && opModeIsActive()) {}
        }

        // Haken kippen
        if (gamepad2.right_bumper){
            double position = (Math.abs(hwMap.servo_haken_drehen_zuklappen + hwMap.servo_haken_drehen_aufklappen)/2) - hwMap.servo_haken_drehen.getPosition();
            if (position > 0){
                hwMap.servo_haken_drehen.setPosition(hwMap.servo_haken_drehen_zuklappen);
            }
            else {
                hwMap.servo_haken_drehen.setPosition(hwMap.servo_haken_drehen_aufklappen);
            }
            while (gamepad2.right_bumper && opModeIsActive()){}
        }

        // pull up Motor manuel
        if( gamepad2.right_stick_x != 0 ) {
            if (hwMap.motor_pull_up.getMode() == DcMotor.RunMode.RUN_TO_POSITION)
                hwMap.motor_pull_up.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            hwMap.motor_pull_up.setPower(-gamepad2.right_stick_x);
        } else if (hwMap.motor_pull_up.getMode() == DcMotor.RunMode.RUN_USING_ENCODER) {
            hwMap.motor_pull_up.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hwMap.motor_pull_up.setTargetPosition(hwMap.motor_pull_up.getCurrentPosition());
        }

        /* END SECTION */

        /* DRIVING */
        if (gamepad1.left_bumper || gamepad1.right_bumper) {
            drive_sneak = !drive_sneak;
            while ((gamepad1.left_bumper || gamepad1.right_bumper) && opModeIsActive()) {
            }
        }

        if (gamepad1.a) { // rückwarts
            hwMap.robot.setSpeed(
                    -1 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_normal),
                    -gamepad1.left_stick_x * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_normal),
                    (gamepad1.left_trigger - gamepad1.right_trigger) *
                            (drive_sneak ? hwMap.speed_sneak : hwMap.speed_normal));
        } else if (gamepad1.b) { // vorwärts
            hwMap.robot.setSpeed(
                    1 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_normal),
                    -gamepad1.left_stick_x * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_normal),
                    (gamepad1.left_trigger - gamepad1.right_trigger) *
                            (drive_sneak ? hwMap.speed_sneak : hwMap.speed_normal));
        } else {
            hwMap.robot.setSpeed(
                    -gamepad1.left_stick_y * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_normal),
                    -gamepad1.left_stick_x * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_normal),
                    (gamepad1.left_trigger - gamepad1.right_trigger) *
                            (drive_sneak ? hwMap.speed_sneak : hwMap.speed_normal));
        }

        /* UPDATE THE ROBOT */
        hwMap.robot.step();

        /* ADD TELEMETRY FOR DRIVER DOWN BELOW */
        telemetry.addData("lift_reset", lift_reset);
        telemetry.addData("arm_reset", arm_reset);
        telemetry.addLine();
        // telemetry.addData("touch_sensor", hwMap.touchSensor.getValue());
        // telemetry.addData("touch_sensor", hwMap.touchSensor.isPressed());
        // telemetry.addLine();
        telemetry.addData("motor_intake_achse", hwMap.motor_intake_achse.getCurrentPosition());
        telemetry.addData("motor_intake_arm_drehen", hwMap.motor_intake_arm_drehen.getCurrentPosition());
        telemetry.addData("motor_aufzug", hwMap.motor_aufzug.getCurrentPosition());
        telemetry.addData("motor_pull_up", hwMap.motor_pull_up.getCurrentPosition());
        telemetry.addLine();
        telemetry.addData("servo_intake_rechts", hwMap.servo_intake_rechts.getPower());
        telemetry.addData("servo_intake_links", hwMap.servo_intake_links.getPower());
        telemetry.addData("servo_intake_drehen", hwMap.servo_intake_drehen.getPosition());
        telemetry.addData("servo_korb_hoch_runter", hwMap.servo_korb_hoch_runter.getPosition());
        telemetry.addData("servo_haken_drehen", hwMap.servo_haken_drehen.getPosition());
        telemetry.addLine();
        telemetry.addData("SNEAK", drive_sneak);

        /* END SECTION */
        telemetry.addLine();
        telemetry.addLine(hwMap.chassis.debug());
        telemetry.addLine(hwMap.navi.debug());
        telemetry.update();
    }
}
