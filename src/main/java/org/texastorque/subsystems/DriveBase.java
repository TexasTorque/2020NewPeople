package org.texastorque.subsystems;

import org.texastorque.torquelib.component.TorqueSparkMax;
import org.texastorque.torquelib.util.GenericController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase {
    private static volatile DriveBase instance;
    private final int port_left = 0;
    private final int port_right = 1;

    GenericController controller = new GenericController(0, 0.1);
    TorqueSparkMax left = new TorqueSparkMax(port_left);
    TorqueSparkMax right = new TorqueSparkMax(port_right);
    
    public DriveBase(){
        SmartDashboard.putString("test", "Instantiated Drivebase");        
    }

    public void run() {
        System.out.println(controller.getLeftYAxis());
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
