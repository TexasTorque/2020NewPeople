package org.texastorque.subsystems;

import org.texastorque.torquelib.util.GenericController;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase {
    private static volatile DriveBase instance;
    private final int port_left_1 = 0;
    private final int port_left_2 = 1;
    private final int port_left_3 = 2;
    private final int port_right_1 = 3;
    private final int port_right_2 = 4;
    private final int port_right_3 = 5;
    private double leftSpeed = 0.0;
    private double rightSpeed = 0.0;

    GenericController controller = new GenericController(0, 0.1);
    PWMSparkMax left_1 = new PWMSparkMax(port_left_1);
    PWMSparkMax left_2 = new PWMSparkMax(port_left_2);
    PWMSparkMax left_3 = new PWMSparkMax(port_left_3);
    PWMSparkMax right_1 = new PWMSparkMax(port_right_1);
    PWMSparkMax right_2 = new PWMSparkMax(port_right_2);
    PWMSparkMax right_3 = new PWMSparkMax(port_right_3);
    
    public DriveBase(){
        SmartDashboard.putString("test", "Instantiated Drivebase");        
    }

    public void run() {
        double leftRight = controller.getRightXAxis();
        leftSpeed =.2*(controller.getLeftYAxis() - 0.4 * Math.pow(leftRight, 4) * Math.signum(leftRight));
        rightSpeed = .2*(-controller.getLeftYAxis() - 0.4 * Math.pow(leftRight, 4) * Math.signum(leftRight));
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
