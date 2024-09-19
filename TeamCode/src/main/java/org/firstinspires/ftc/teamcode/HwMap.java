package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Tools.Chassis.Chassis;
import org.firstinspires.ftc.teamcode.Tools.Chassis.MecanumChassis;
import org.firstinspires.ftc.teamcode.Tools.Chassis.NormalChassis;
import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;
import org.firstinspires.ftc.teamcode.Tools.FieldNavigation;
import org.firstinspires.ftc.teamcode.Tools.Robot;

public class HwMap {
    // robot
    public Robot robot;
    public FieldNavigation navi;
    public Chassis chassis;

    /* PLACE YOUR HARDWARE INTERFACES DOWN BELOW */
    public DcMotor erster_winkwl;
    public DcMotor zweiter_winkel;
    /* END SECTION */

    /* PLACE YOUR CONSTANT VALUES DOWN BELOW*/
    // driving speeds
    public final double speed_full = 0.55;
    public final double speed_sneak = 0.3;

    // a
    /* END SECTION */

    /**
     * initialize the hardware
     * @param hardwareMap just put the object "hardwareMap" in here
     */
    public void initialize(HardwareMap hardwareMap) {
        // get chassis
        chassis = new NormalChassis(); // most likely your chassis is a mecanumwheel driven chassis
        chassis.setRotationAxis(3); /* (1=x,2=y,3=z,4=disabled) change this if needed : the value can be obtained with OpModes.Testing.GyroTest */
        chassis.populateMotorArray(hardwareMap); // uses hardwareMap.get(...) to get motor interfaces as defined in the used chassis class
        chassis.setRotation(0.0f); // start rotation is 0 degrees

        // get field navigator
        navi = new FieldNavigation(new Position2D(0.0, 0.0)); // start position is (0|0)

        // get robot api object
        robot = new Robot(navi, chassis);

        /* INITIALIZE YOUR HARDWARE DOWN BELOW */
        erster_winkwl = hardwareMap.get(DcMotor.class,"erster_winkel");
        erster_winkwl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        erster_winkwl.setPower(1.0);
        erster_winkwl.setTargetPosition(erster_winkwl.getCurrentPosition());
        zweiter_winkel = hardwareMap.get(DcMotor.class, "zweiter_winkel");
        zweiter_winkel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        zweiter_winkel.setPower(1.0);
        zweiter_winkel.setTargetPosition(zweiter_winkel.getCurrentPosition());

        /* END SECTION */
    }
}