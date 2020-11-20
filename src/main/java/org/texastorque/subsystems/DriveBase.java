package org.texastorque.subsystems;

import org.texastorque.constants.Input;
import org.texastorque.torquelib.util.GenericController;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase {
    private static volatile DriveBase instance;
    private double leftSpeed = 0.0;
    private double rightSpeed = 0.0;

    GenericController controller = new GenericController(0, 0.1);
    PWMSparkMax left_1 = new PWMSparkMax(Input.DB_LEFT_1);
    PWMSparkMax left_2 = new PWMSparkMax(Input.DB_LEFT_2);
    PWMSparkMax left_3 = new PWMSparkMax(Input.DB_LEFT_3);
    PWMSparkMax right_1 = new PWMSparkMax(Input.DB_RIGHT_1);
    PWMSparkMax right_2 = new PWMSparkMax(Input.DB_RIGHT_2);
    PWMSparkMax right_3 = new PWMSparkMax(Input.DB_RIGHT_3);
    
    public DriveBase(){
        SmartDashboard.putString("test", "Instantiated Drivebase");        
    }

    public void run() {
        double leftRight = -controller.getRightXAxis();
        leftSpeed =.8*(-controller.getLeftYAxis() - 0.8 * Math.pow(leftRight, 4) * Math.signum(leftRight));
        rightSpeed = .8*(controller.getLeftYAxis() - 0.8 * Math.pow(leftRight, 4) * Math.signum(leftRight));
        left_1.set(leftSpeed);
        left_2.set(leftSpeed);
        left_3.set(leftSpeed);
        right_1.set(rightSpeed);
        right_2.set(rightSpeed);
        right_3.set(rightSpeed);
    }

    public static DriveBase getInstance(){
        if (instance == null){
            synchronized(DriveBase.class){
                if (instance == null)
                    instance = new DriveBase();
            }
        }
        return instance;
    }
}