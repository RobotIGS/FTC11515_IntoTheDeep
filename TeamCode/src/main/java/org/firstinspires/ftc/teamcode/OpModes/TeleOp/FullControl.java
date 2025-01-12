package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "FullControl", group = "FTC")
public class FullControl extends BaseTeleOp {
    /* ADD VARIABLES ONLY USED IN FULL CONTROL */
    protected boolean drive_sneak = false; // flag for storing the current speed mode
    protected boolean erste_achse_ausgefahren = false;
    protected boolean intake_drehen_vorne = true;
    protected boolean korb_arm_unten = true;

    /* END SECTION */

    /**
     *  Gamepad 1: Fahren
     *      Zusatz:
     *          a: Speed vorwärts
     *          b: Speed rückwärts
     *  Gamepad 2: Arm & Kralle:
     *      Vordefiniert:
     *          a: intake achse hoch/runter
     *          b: korb arm hoch/ runter
     *          x: nope
     *          y: kralle nach vorne/ hinten
     *          left or right bumper: hochziehen
     *          trigger left: intake aufnehem
     *          trigger right: intake ablegen
     *
     *      Manuell:
     *          left_stick_y: erste Achse
     *          left_stick_x: intake Arm bewegen
     *          dpad up/down: korb arm hoch/ runter
     *          dpad left/right: intake drehen
     *          trigger: hochziehen (manuell)
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
        hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_intake_drehen.setPosition(hwMap.kralle_drehen_vorne);
        hwMap.servo_korb_hoch_runter.setPosition(hwMap.korb_arm_unten);

        /* END SECTION */
    }

    @Override
    public void runLoop() {
        /* ADD OTHER HARDWARE CONTROLS DOWN BELOW */

        // INTAKE aufnehmen/ abgeben
        if (gamepad2.left_trigger > 0){
            hwMap.servo_intake_rechts.setPower(0.5);
            hwMap.servo_intake_links.setPower(-0.5);
        }
        else if (gamepad2.right_trigger > 0){
            hwMap.servo_intake_rechts.setPower(-0.5);
            hwMap.servo_intake_links.setPower(0.5);
        }
        else {
            hwMap.servo_intake_rechts.setPower(0);
            hwMap.servo_intake_links.setPower(0);
        }

        // INTAKE KOPF DREHEN feste Werte
        if (gamepad2.y){
            if (intake_drehen_vorne) {
                hwMap.servo_intake_drehen.setPosition(hwMap.kralle_drehen_hinten);
            }
            else{
                hwMap.servo_intake_drehen.setPosition(hwMap.kralle_drehen_vorne);
            }
            intake_drehen_vorne = !intake_drehen_vorne;
            while (gamepad2.y) {}
        }
        // INTAKE KOPF DREHEN manuell
        if (gamepad2.dpad_left){
            hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen.getPosition()-0.05);
        }
        else if (gamepad2.dpad_right) {
            hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen.getPosition()+0.05);
        }

        // INTAKE ACHSE HOCH RUNTER feste Werte
        if (gamepad2.a){
            if(erste_achse_ausgefahren){
                hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
            }
            else {
                hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_erste_achse_oben);
            }
            erste_achse_ausgefahren = !erste_achse_ausgefahren;
            while (gamepad2.a){}
        }

        // INTAKE ACHSE HOCH RUNTER manuell
        if(gamepad2.left_stick_y != 0){
            if(hwMap.motor_intake_achse.getMode()== DcMotor.RunMode.RUN_TO_POSITION){
                hwMap.motor_intake_achse.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            hwMap.motor_intake_achse.setPower(gamepad2.left_stick_y * 0.15);
        }
        else if(hwMap.motor_intake_achse.getMode() == DcMotor.RunMode.RUN_USING_ENCODER){
            hwMap.motor_intake_achse.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hwMap.motor_intake_achse.setTargetPosition(hwMap.motor_intake_achse.getCurrentPosition());
        }

        // INTAKE PLATTE DREHEN manuell
        if(gamepad2.left_stick_x != 0){
            if(hwMap.motor_intake_arm_drehen.getMode()== DcMotor.RunMode.RUN_TO_POSITION){
                hwMap.motor_intake_arm_drehen.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            hwMap.motor_intake_arm_drehen.setPower(gamepad2.left_stick_x * 0.05);
        }
        else if(hwMap.motor_intake_arm_drehen.getMode() == DcMotor.RunMode.RUN_USING_ENCODER){
            hwMap.motor_intake_arm_drehen.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hwMap.motor_intake_arm_drehen.setTargetPosition(hwMap.motor_intake_arm_drehen.getCurrentPosition());
        }

        // -----------------------------------------------------------------

        // KORB ARM HOCH RUNTER feste werte
        if(gamepad2.b){
            if (korb_arm_unten) {
                hwMap.servo_korb_hoch_runter.setPosition(hwMap.korb_arm_oben);
            }
            else{
                hwMap.servo_korb_hoch_runter.setPosition(hwMap.korb_arm_unten);
            }
            korb_arm_unten = !korb_arm_unten;
            while (gamepad2.b) {}
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
        if (gamepad2.right_bumper){
            hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_oben);
            while (gamepad2.right_bumper){}
        } else if (gamepad2.left_bumper) {
           hwMap.motor_aufzug.setTargetPosition(hwMap.motor_aufzug_unten);
           while (gamepad2.left_bumper){}
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
        if (gamepad2.x && gamepad2.y && gamepad2.a && gamepad2.b && gamepad2.dpad_left){
            int deltaLimit = hwMap.motor_aufzug_oben - hwMap.motor_aufzug_unten;
            hwMap.motor_aufzug_unten = hwMap.motor_aufzug.getCurrentPosition();
            hwMap.motor_aufzug_oben = hwMap.motor_aufzug.getCurrentPosition() + deltaLimit;
        }
        if (gamepad2.a && gamepad2.b && gamepad2.x && gamepad2.y && gamepad2.dpad_right){
            double mittelLimit = (hwMap.servo_haken_dehen_zuklappen + hwMap.servo_haken_drehen_aufklappen)/2;
            double position = mittelLimit - hwMap.servo_haken_drehen.getPosition();
            if (position < 0){
                hwMap.servo_haken_drehen.setPosition(hwMap.servo_haken_drehen_aufklappen);
            }
            else {
                hwMap.servo_haken_drehen.setPosition(hwMap.servo_haken_drehen_aufklappen);
            }

        }

        // pull up Motor manuel
        if( gamepad2.right_stick_x != 0 ) {
            if (hwMap.motor_pull_up.getMode() == DcMotor.RunMode.RUN_TO_POSITION)
                hwMap.motor_pull_up.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hwMap.motor_pull_up.setPower(-gamepad2.right_stick_x);
        } else if (hwMap.motor_pull_up.getMode() == DcMotor.RunMode.RUN_USING_ENCODER) {
            if (hwMap.motor_pull_up.getCurrentPosition() < hwMap.motor_pull_up_unten)
                hwMap.motor_pull_up.setTargetPosition(hwMap.motor_pull_up_unten);
            if (hwMap.motor_pull_up.getCurrentPosition() > hwMap.motor_pull_up_oben)
                hwMap.motor_pull_up.setTargetPosition(hwMap.motor_pull_up_oben);
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
                    -1 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    -gamepad1.right_stick_x * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    (gamepad1.left_trigger - gamepad1.right_trigger) *
                            (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full));
        } else if (gamepad1.b) { // vorwärts
            hwMap.robot.setSpeed(
                    1 * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    -gamepad1.right_stick_x * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    (gamepad1.left_trigger - gamepad1.right_trigger) *
                            (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full));
        } else {
            hwMap.robot.setSpeed(
                    -gamepad1.left_stick_y * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    -gamepad1.right_stick_x * (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full),
                    (gamepad1.left_trigger - gamepad1.right_trigger) *
                            (drive_sneak ? hwMap.speed_sneak : hwMap.speed_full));
        }

        /* UPDATE THE ROBOT */
        hwMap.robot.step();

        /* ADD TELEMETRY FOR DRIVER DOWN BELOW */
        telemetry.addData("motor_intake_achse", hwMap.motor_intake_achse.getCurrentPosition());
        telemetry.addData("motor_intake_arm_drehen", hwMap.motor_intake_arm_drehen.getCurrentPosition());
        telemetry.addData("motor_aufzug", hwMap.motor_aufzug.getCurrentPosition());
        telemetry.addData("motor_pull_up", hwMap.motor_pull_up.getCurrentPosition());
        telemetry.addLine();
        telemetry.addData("servo_intake_rechts", hwMap.servo_intake_rechts.getPower());
        telemetry.addData("servo_intake_links", hwMap.servo_intake_links.getPower());
        telemetry.addData("servo_intake_drehen", hwMap.servo_intake_drehen.getPosition());
        telemetry.addData("servo_korb_hoch_runter", hwMap.servo_korb_hoch_runter.getPosition());
        telemetry.addLine();
        telemetry.addData("erste_achse_ausgefahren", erste_achse_ausgefahren);
        telemetry.addData("intake_drehen_vorne", intake_drehen_vorne);

        telemetry.addLine();
        telemetry.addData("SNEAK", drive_sneak);

        /* END SECTION */
        telemetry.addLine();
        telemetry.addLine(hwMap.chassis.debug());
        telemetry.addLine(hwMap.navi.debug());
        telemetry.update();
    }
}
