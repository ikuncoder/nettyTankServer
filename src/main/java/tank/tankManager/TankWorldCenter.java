package tank.tankManager;

import tank.TankWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author longzhu
 * version 1.0
 * 2019/4/27 14:56
 */
public class TankWorldCenter {
    List<TankWorld> tankWorldList=new ArrayList<>();

    private TankWorldCenter(){}
    private static TankWorldCenter instance;
    public static TankWorldCenter getInstance(){
        if(instance==null){
            synchronized (TankWorldCenter.class){
                instance=new TankWorldCenter();
            }
        }
        return instance;
    }




}
