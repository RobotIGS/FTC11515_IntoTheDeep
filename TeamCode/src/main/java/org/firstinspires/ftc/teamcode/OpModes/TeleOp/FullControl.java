package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "FullControl", group = "FTC")
public class FullControl extends BaseTeleOp {
    /* ADD VARIABLES ONLY USED IN FULL CONTROL */
    protected boolean drive_sneak = false; // flag for storing the current speed mode
    protected boolean erste_achse_ausgefahren = false;
    protected boolean seilzug_oben = false;
    protected boolean intake_drehen_vorne = true;
    protected boolean korb_arm_vorne = true;

    /* END SECTION */

    /**
     *  Gamepad 1: Fahren
     *      Zusatz:
     *          a: Speed vorwärts
     *          b: Speed rückwärts
     *  Gamepad 2: Arm & Kralle:
     *      Vordefiniert:
     *          a: erste Achse auf/ zu
     *          b: korb arm vorne/hinten
     *          x:
     *          y: kralle nach vorne/ hinten
     *          left or right bumper: hochziehen
     *          trigger left: intake aufnehem
     *          trigger right: intake ablegen
     *
     *      Manuell:
     *          left_stick_y: erste Achse
     *          right_stick_y: intake Arm bewegen
     *          dpad up/down: korb arm bewegen
     *          dpad left/right: Kralle drehen
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

        hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse_unten);
        hwMap.servo_intake_drehen.setPosition(hwMap.kralle_drehen_vorne);

        /* END SECTION */
    }

    @Override
    public void runLoop() {
        /* ADD OTHER HARDWARE CONTROLS DOWN BELOW */
//intake Stein aufnehmen
        if (gamepad2.left_trigger > 0){
            hwMap.servo_intake_rechts.setPower(0.1);
            hwMap.servo_intake_links.setPower(-0.1);
        }
//intake Stein ablegen
        else if (gamepad2.right_trigger > 0){
            hwMap.servo_intake_rechts.setPower(-0.1);
            hwMap.servo_intake_links.setPower((0.1));
        }
        else {
            hwMap.servo_intake_rechts.setPower(0);
            hwMap.servo_intake_links.setPower(0);
        }

//intake drehen feste Werte
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
//intake drehen manuell
        if (gamepad2.dpad_left){
            hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen.getPosition()-0.05);
        }
        else if (gamepad2.dpad_right) {
            hwMap.servo_intake_drehen.setPosition(hwMap.servo_intake_drehen.getPosition()+0.05);
        }
//korb arm drehen feste werte
        if(gamepad2.b){
            if (korb_arm_vorne) {
                hwMap.servo_korb_drehen.setPosition(hwMap.korb_arm_vorne);
            }
            else{
                hwMap.servo_korb_drehen.setPosition(hwMap.korb_arm_hinten);
            }
            korb_arm_vorne = !korb_arm_vorne;
            while (gamepad2.b) {}
        }
//korb arm drehen manuell
        if (gamepad2.dpad_up){
            hwMap.servo_korb_drehen.setPosition(hwMap.servo_korb_drehen.getPosition() + 0.05);
        }
        else if (gamepad2.dpad_down){
            hwMap.servo_korb_drehen.setPosition(hwMap.servo_korb_drehen.getPosition() - 0.05);
        }
//erste achse manuell
        if (gamepad2.left_stick_y < 0) {
            hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse.getCurrentPosition() + 1);
        }
        else if (gamepad2.left_stick_y > 0) {
            hwMap.motor_erste_achse.setTargetPosition(hwMap.motor_erste_achse.getCurrentPosition() - 1);
        }

//erste Achse feste Werte
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
//intake arm drehen manuell
        if(gamepad2.right_stick_y < 0){
            hwMap.motor_intake_arm_drehen.setTargetPosition(hwMap.motor_intake_arm_drehen.getCurrentPosition() + 1);
        }
        else if (gamepad2.right_stick_y > 0) {
            hwMap.motor_intake_arm_drehen.setTargetPosition(hwMap.motor_intake_arm_drehen.getCurrentPosition() - 1 );
        }


// seilzug
       if (gamepad2.right_bumper || gamepad2.left_bumper){
           if(!seilzug_oben){
                hwMap.motor_seilzug.setPower(0.5);
            }

            seilzug_oben = !seilzug_oben;
            while (gamepad2.right_bumper || gamepad2.left_bumper){}
        }
        hwMap.motor_seilzug.setPower(gamepad2.left_trigger - gamepad2.right_trigger);


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
        telemetry.addData("intake_drehen", hwMap.servo_intake_drehen.getPosition());
        telemetry.addData("erste Achse", hwMap.motor_erste_achse.getCurrentPosition());
        telemetry.addData("intake_arm_drehen", hwMap.motor_intake_arm_drehen.getCurrentPosition());
        //telemetry.addData("motor_hochziehen", hwMap.motor_hochziehen_links.getPower());
        telemetry.addData("korb_drehen", hwMap.servo_korb_drehen.getPosition());
        telemetry.addData("erste_achse_ausgefahren", erste_achse_ausgefahren);
        telemetry.addData("kralle_drehen_vorne", intake_drehen_vorne);
        telemetry.addData("hochziehen", seilzug_oben);

        telemetry.addLine();
        telemetry.addData("SNEAK", drive_sneak);

        /* END SECTION */
        telemetry.addLine();
        telemetry.addLine(hwMap.chassis.debug());
        telemetry.addLine(hwMap.navi.debug());
        telemetry.update();
    }
}
