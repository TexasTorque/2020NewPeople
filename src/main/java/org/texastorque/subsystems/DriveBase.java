package org.texastorque.subsystems;

import org.texastorque.torquelib.util.GenericController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase {
    GenericController controller = new GenericController(0, 0.1);

    public DriveBase(){
        SmartDashboard.putString("test", "Instantiated Drivebase");        
        run();
    }

    public void run() {
        try {
            while(true) {
                System.out.println(controller.getLeftYAxis());
                Thread.sleep(100);
            }
        } catch(InterruptedException e) {
            System.out.println("Thread interrupted: "+e);
        }
    }
}
