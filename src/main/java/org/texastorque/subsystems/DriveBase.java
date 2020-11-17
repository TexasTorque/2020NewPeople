package org.texastorque.subsystems;

import org.texastorque.torquelib.component.TorqueSparkMax;
import org.texastorque.torquelib.util.GenericController;

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
    TorqueSparkMax left = new TorqueSparkMax(port_left_1);
    TorqueSparkMax right = new TorqueSparkMax(port_right_1);
    
    public DriveBase(){
        SmartDashboard.putString("test", "Instantiated Drivebase");        
        left.addFollower(port_left_2);
        left.addFollower(port_left_3);
        right.addFollower(port_right_2);
        right.addFollower(port_right_3);
    }

    public void run() {
        double leftRight = controller.getRightXAxis();
        leftSpeed =.2*(controller.getLeftYAxis() - 0.4 * Math.pow(leftRight, 4) * Math.signum(leftRight));
        rightSpeed = .2*(-controller.getLeftYAxis() - 0.4 * Math.pow(leftRight, 4) * Math.signum(leftRight));
        left.set(leftSpeed);
        right.set(rightSpeed);
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
