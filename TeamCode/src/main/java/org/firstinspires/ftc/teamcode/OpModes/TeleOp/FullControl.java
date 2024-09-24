package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "FullControl", group = "FTC")
public class FullControl extends BaseTeleOp {
    /* ADD VARIABLES ONLY USED IN FULL CONTROL */
    protected boolean drive_sneak = false; // flag for storing the current speed mode
    protected boolean arm_oben = false;
    protected boolean kralle_offen = false;
    protected boolean zweite_achse_ausgefahren = false;
    /* END SECTION */

    /**
     *  Gamepad1: Fahren
     *  Gamepad2: Arm & Kralle:
     *      a: Arm ganz auf/ganz zu
     *      b: Kralle auf/zu
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
        if (gamepad2.a){
            if(!arm_oben){
                hwMap.motor_erste_achse_1.setTargetPosition(hwMap.motor_erste_achse_1_oben);
                hwMap.motor_erste_achse_2.setTargetPosition(hwMap.motor_erste_achse_2_oben);
                hwMap.motor_zweite_achse.setTargetPosition(hwMap.motor_zweite_achse_oben);
            }
            else {
                hwMap.motor_erste_achse_1.setTargetPosition(hwMap.motor_erste_achse_1_unten);
                hwMap.motor_erste_achse_2.setTargetPosition(hwMap.motor_erste_achse_2_unten);
                hwMap.motor_zweite_achse.setTargetPosition(hwMap.motor_zweite_achse_unten);
            }
            arm_oben = !arm_oben;
            while (gamepad2.a){}
        }
        if (gamepad2.b){
            if(kralle_offen){
                hwMap.kralle.setPosition(hwMap.kralle_auf);
            }
            else {
                hwMap.kralle.setPosition(hwMap.kralle_zu);
            }
            kralle_offen = !kralle_offen;
            while (gamepad2.b){}
        }
        if (gamepad2.x){
            if(!zweite_achse_ausgefahren){
                hwMap.motor_zweite_achse.setTargetPosition(hwMap.motor_zweite_achse_ausgefahren);
            }
            else {
                hwMap.motor_zweite_achse.setTargetPosition(hwMap.motor_zweite_achse_unten);
            }
            zweite_achse_ausgefahren = !zweite_achse_ausgefahren;
            while (gamepad2.x){}
        }

        if (gamepad2.left_stick_y < 0) {
            hwMap.motor_erste_achse_1.setTargetPosition(hwMap.motor_erste_achse_1.getCurrentPosition()-100);
            hwMap.motor_erste_achse_2.setTargetPosition(hwMap.motor_erste_achse_2.getCurrentPosition()-100);

        }
        else if (gamepad2.left_stick_y > 0) {
            hwMap.motor_erste_achse_1.setTargetPosition(hwMap.motor_erste_achse_1.getCurrentPosition()+100);
            hwMap.motor_erste_achse_2.setTargetPosition(hwMap.motor_erste_achse_2.getCurrentPosition()+100);
        }

        if (gamepad2.right_stick_y < 0) {
            hwMap.motor_zweite_achse.setTargetPosition(hwMap.motor_zweite_achse.getCurrentPosition()-100);
        }
        else if (gamepad2.right_stick_y > 0) {
            hwMap.motor_zweite_achse.setTargetPosition(hwMap.motor_zweite_achse.getCurrentPosition()+100);
        }

        if (gamepad2.dpad_up){
            hwMap.kralle.setPosition(hwMap.kralle.getPosition()-1);
        }
        else if (gamepad2.dpad_down) {
            hwMap.kralle.setPosition(hwMap.kralle.getPosition()+1);
        }

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
        telemetry.addData("SNEAK", drive_sneak);

        /* END SECTION */
        telemetry.addLine();
        telemetry.addLine(hwMap.chassis.debug());
        telemetry.addLine(hwMap.navi.debug());
        telemetry.update();
    }
}