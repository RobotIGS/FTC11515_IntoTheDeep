package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
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
    public DcMotor motor_intake_achse;
    public DcMotor motor_intake_arm_drehen;
    public DcMotor motor_aufzug;

    public CRServo servo_intake_rechts;
    public CRServo servo_intake_links;
    public Servo servo_intake_drehen;
    public Servo servo_korb_hoch_runter;

    /* END SECTION */

    /* PLACE YOUR CONSTANT VALUES DOWN BELOW*/
    // driving speeds
    public final double speed_full = 1.0;
    public final double speed_normal = 0.8;
    public final double speed_sneak = 0.5;

    // autonomous values
    public final double driving_accuracy = 1.5;
    public final float rotation_accuracy = 4.0f;

    // values
    public int motor_erste_achse_ganz_unten = 0; //ganz unten um Stein aufzuheben
    public int motor_erste_achse_stange = 0; //arm nur zur hälfe ausfahren damit wir die Stange berühren
    public int motor_erste_achse_unten = 0; //unten aber so das der Arm noch über die Steine kann
    public int motor_erste_achse_oben = 0;

    public int motor_seilzug_unten = 0;
    public int motor_seilzug_oben = 100;

    public final double kralle_drehen_vorne = 0.0;
    public final double kralle_drehen_hinten = 0.0;

    public final double korb_arm_oben = 0.05;
    public final double korb_arm_unten = 0.5;

    /* END SECTION */

    /**
     * initialize the hardware
     * @param hardwareMap just put the object "hardwareMap" in here
     */
    public void initialize(HardwareMap hardwareMap) {
        // get chassis
        chassis = new MecanumChassis(); // most likely your chassis is a mecanumwheel driven chassis
        chassis.setRotationAxis(2); /* (1=x,2=y,3=z,4=disabled) change this if needed : the value can be obtained with OpModes.Testing.GyroTest */
        chassis.populateMotorArray(hardwareMap); // uses hardwareMap.get(...) to get motor interfaces as defined in the used chassis class
        chassis.setRotation(0.0f); // start rotation is 0 degrees

        // get field navigator
        navi = new FieldNavigation(new Position2D(0.0, 0.0)); // start position is (0|0)

        // get robot api object
        robot = new Robot(navi, chassis);

        /* INITIALIZE YOUR HARDWARE DOWN BELOW */
        motor_intake_achse = hardwareMap.get(DcMotor.class,"motor_intake_achse");
        motor_intake_achse.setTargetPosition(motor_intake_achse.getCurrentPosition());
        motor_intake_achse.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_intake_achse.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor_intake_achse.setPower(1);
        motor_intake_achse.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor_intake_arm_drehen = hardwareMap.get(DcMotor.class,"motor_intake_arm_drehen");
        motor_intake_arm_drehen.setTargetPosition(motor_intake_arm_drehen.getCurrentPosition());
        motor_intake_arm_drehen.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_intake_arm_drehen.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor_intake_arm_drehen.setPower(1);
        motor_intake_arm_drehen.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor_erste_achse_unten = motor_intake_achse.getCurrentPosition() + 150;
        motor_erste_achse_oben = motor_intake_achse.getCurrentPosition() + 650;

        servo_intake_links = hardwareMap.get(CRServo.class, "crservo_intake_links");
        servo_intake_rechts = hardwareMap.get(CRServo.class, "crservo_intake_rechts");
        servo_intake_drehen = hardwareMap.get(Servo.class,"servo_intake_drehen");
        servo_korb_hoch_runter = hardwareMap.get(Servo.class, "servo_korb_drehen");

        motor_aufzug = hardwareMap.get(DcMotor.class,"motor_aufzug");
        motor_aufzug.setTargetPosition(motor_aufzug.getCurrentPosition());
        motor_aufzug.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_aufzug.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor_aufzug.setPower(1);
        motor_aufzug.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        /* END SECTION */
    }
}