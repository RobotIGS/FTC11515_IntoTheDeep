package org.firstinspires.ftc.teamcode.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.OpModes.TeleOp.BaseTeleOp;


@TeleOp(name = "Motor & Servo Test", group = "TESTING")
public class MotorServoTest extends BaseTeleOp {
    /**
     * HW-Map: testing
     * Servo: Port 1
     * Servo 2: Port 3
     * CR-Servo (dreht sich unendlich): Port 2
     * Motor: Port 1
     * Motor 2: Port 2
     */


    // this document is being used for the preparing of the actual full control
            /*Controls:
            * a: take in
            * b: take out
            * x: reset of the arm to default [not yet implemented]
            * y: arm to the goal position [not yet implemented]
            * left-bumper:
            * right-bumper:
            * left-trigger: turning arm left
            * right-trigger: turning arm right
            * d-pad-up: turning arm up
            * d-pad-down: turning arm down
            * d-pad-left:
            * d-pad-right:
            * left-stick:
            * left-stick-button:
            * right-stick: turning the take-in
            * right-stick-button:
            */



    //the Motor and Servo activities are described in the void initialize


    public DcMotor motor1;

    //if we have a correct count of steps please fill in (default at 10) and then delete this comment or mark it as done also mark the corresponding motor with the count of steps
    int countOfStepsForRotation1 = 10; //not done
    int trackingOfRotation1 = 0;


    public DcMotor motor2;

    //if we have a correct count of steps please fill in (default at 10) and then delete this comment or mark it as done also mark the corresponding motor with the count of steps
    int countOfStepsForRotation2 = 10; //not done
    int trackingOfRotation2 = 0;



    public Servo servo0;
    public Servo servo1; //intake
    public Servo servo2; //intake
    public Servo servo3; //intakeRotation

    public Servo servo5;
    //public CRServo crservo;

    boolean zwei_motoren = false;

    @Override
    public void initialize() {

        //arm of take in right-left Motor
        motor1 = hardwareMap.get(DcMotor.class,"motor1");
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor1.setTargetPosition(0);

        //arm of take in up-down Motor
        motor2 = hardwareMap.get(DcMotor.class,"motor2");
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor2.setTargetPosition(0);

        //take in Servos
        servo1 = hardwareMap.get(Servo.class,"servo1");
        servo2 = hardwareMap.get(Servo.class,"servo2");

        //take in rotation Servo
        servo3 = hardwareMap.get(Servo.class,"servo3");


        //not used yet


        //crservo = hardwareMap.get(CRServo.class,"crservo");
    }

    @Override
    public void runOnce() {

        //sets default position of servos

        servo1.setPosition(servo1.getPosition() + 1);
        servo2.setPosition(servo2.getPosition() - 1);

        servo3.setPosition(servo3.getPosition() + 1);
        servo3.setPosition(servo3.getPosition() - 0.5);


    }

    @Override
    public void runLoop() {


        //take in

        if (gamepad1.a){
            servo1.setPosition(servo1.getPosition() - 1);
            servo2.setPosition(servo2.getPosition() + 1);
        }


        //take out

        if(gamepad1.b){
            servo1.setPosition(servo1.getPosition() + 1);
            servo2.setPosition(servo2.getPosition() - 1);
        }


        //turning of the take-in left

        if(gamepad1.right_stick_x < 0){
            //fast turning
            if(gamepad1.right_stick_x < -0.5){
                while(gamepad1.right_stick_x < 0.5){
                    servo3.setPosition(servo3.getPosition() - 0.05);
                }
            }
            //slow turning
            else if (gamepad1.right_stick_x > -0.5 && gamepad1.right_stick_x < 0) {
                while(gamepad1.right_stick_x > -0.5 && gamepad1.right_stick_x < 0){
                    servo3.setPosition(servo3.getPosition() - 0.025);
                }
            }
        }


        //turning of the take-in right

        if(gamepad1.right_stick_x > 0){
            //fast turning
            if(gamepad1.right_stick_x >= 0.5){
                while(gamepad1.right_stick_x >= 0.5){
                    servo3.setPosition(servo3.getPosition() + 0.05);
                }
            }
            //slow turning
            else if (gamepad1.right_stick_x < 0.5 && gamepad1.right_stick_x > 0) {
                while(gamepad1.right_stick_x < 0.5 && gamepad1.right_stick_x > 0){
                    servo3.setPosition(servo3.getPosition() + 0.025);
                }
            }
        }


        //turning arm left
        if(gamepad1.left_trigger > 0)
        {
            while(gamepad1.left_trigger > 0){
            motor1.setPower(-gamepad1.left_trigger);}
        }

        //turning arm right
        if(gamepad1.right_trigger > 0)
        {
            while(gamepad1.right_trigger > 0){
                motor1.setPower(gamepad1.right_trigger);}
        }

        //turning arm up
        if(gamepad1.left_trigger > 0)
        {
            while(gamepad1.left_trigger > 0){
                motor2.setPower(-gamepad1.left_trigger);}
        }

        //turning arm down
        if(gamepad1.dpad_down)
        {
            while(gamepad1.dpad_down){
                motor2.setPower(1);}
        }
        if(gamepad1.dpad_up)
        {
            while(gamepad1.dpad_up){
                motor2.setPower(1);}
        }



        /*motor.setPower(gamepad1.left_stick_y);

        if (zwei_motoren) {
            motor2.setPower(gamepad1.left_stick_y);
        }

        if (gamepad1.b) {
            zwei_motoren = !zwei_motoren;
            while (gamepad1.b) {}
        }

        //crservo.setPower(gamepad1.right_stick_y);

        if (gamepad1.dpad_down) {
            servo.setPosition(servo.getPosition() - 0.001);
            servo2.setPosition(servo.getPosition() - 0.001);
        }
        else if (gamepad1.dpad_up) {
            servo.setPosition(servo.getPosition() + 0.001);
            servo2.setPosition(servo.getPosition() + 0.001);
        }*/





        // motor information
        telemetry.addLine("motor information:");
        telemetry.addData("Speed", Math.abs(gamepad1.left_stick_y));
        telemetry.addData("Steps", motor1.getCurrentPosition());

        // servo information
        telemetry.addLine("servo information:");
        telemetry.addData("Value", servo1.getPosition());

        // cr-servo information
        telemetry.addLine("cr-servo information:");
        //telemetry.addData("Value", crservo.getPower());

        // update screen
        telemetry.update();
    }
}