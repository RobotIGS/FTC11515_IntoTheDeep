package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Tools.Chassis.Chassis;
import org.firstinspires.ftc.teamcode.Tools.Chassis.MecanumChassis;
import org.firstinspires.ftc.teamcode.Tools.DTypes.Position2D;
import org.firstinspires.ftc.teamcode.Tools.FieldNavigation;
import org.firstinspires.ftc.teamcode.Tools.Robot;

public class HwMap {
    // robot
    public Robot robot;
    public FieldNavigation navi;
    public Chassis chassis;

    /* PLACE YOUR HARDWARE INTERFACES DOWN BELOW */
    public DcMotor motor_erste_achse;
    public DcMotor motor_hochziehen_links;
    public DcMotor motor_hochziehen_rechts;

    public Servo servo_zweite_achse;
    public Servo servo_kralle;
    /* END SECTION */

    /* PLACE YOUR CONSTANT VALUES DOWN BELOW*/
    // driving speeds
    public final double speed_full = 1.0;
    public final double speed_sneak = 0.3;

    public int motor_erste_achse_unten;
    public int motor_erste_achse_oben;

    public final double servo_zweite_achse_eingefahren = 0.85;
    public final double servo_zweite_achse_ausgefahren = 0.2;

    public final double kralle_offen = 0.62;
    public final double kralle_zu = 0.8;

    public int steps_motor_erste_achse = 0;

    /* END SECTION */

    /**
     * initialize the hardware
     * @param hardwareMap just put the object "hardwareMap" in here
     */
    public void initialize(HardwareMap hardwareMap) {
        // get chassis
        chassis = new MecanumChassis(); // most likely your chassis is a mecanumwheel driven chassis
        chassis.setRotationAxis(3); /* (1=x,2=y,3=z,4=disabled) change this if needed : the value can be obtained with OpModes.Testing.GyroTest */
        chassis.populateMotorArray(hardwareMap); // uses hardwareMap.get(...) to get motor interfaces as defined in the used chassis class
        chassis.setRotation(0.0f); // start rotation is 0 degrees

        // get field navigator
        navi = new FieldNavigation(new Position2D(0.0, 0.0)); // start position is (0|0)

        // get robot api object
        robot = new Robot(navi, chassis);

        /* INITIALIZE YOUR HARDWARE DOWN BELOW */
        motor_erste_achse = hardwareMap.get(DcMotor.class,"erste_achse");
        motor_erste_achse.setTargetPosition(motor_erste_achse.getCurrentPosition());
        motor_erste_achse.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_erste_achse.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor_erste_achse.setPower(1.0);
        motor_erste_achse.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor_erste_achse_unten = motor_erste_achse.getCurrentPosition() + 150;
        motor_erste_achse_oben = motor_erste_achse.getCurrentPosition() + 650;


        servo_zweite_achse = hardwareMap.get(Servo.class, "zweite_achse");
        servo_kralle = hardwareMap.get(Servo.class,"kralle");

        motor_hochziehen_links = hardwareMap.get(DcMotor.class,"hochziehen_links");
        motor_hochziehen_rechts = hardwareMap.get(DcMotor.class,"hochziehen_rechts");

        /* END SECTION */
    }
}