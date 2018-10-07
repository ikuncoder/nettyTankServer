package tank;

import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Random;

public class GameLevelHandler{
    //这里只能处理单个房间的，后期有需求再修改支持多个房间
    //游戏一共有三个关卡,根据地图号码来辨别关卡
    private ArrayList<ChannelHandlerContext> channelHandlerContextArrayList;
    private static int groupNum;
    private int mapNum;
    //private ArrayList<ChannelHandlerContext> channelHandlerContextsArrayList;
    //单例
    private GameLevelHandler(){
        channelHandlerContextArrayList=new ArrayList<>();
        groupNum=100;
    }
    private static GameLevelHandler gameLevelHandler=null;
    public static GameLevelHandler getInstance(){
        if(gameLevelHandler==null){
            gameLevelHandler=new GameLevelHandler();
        }
        return gameLevelHandler;
    }

    //根据mapNum的位数来判断是第几关
    public void gameLevelHandler(int mapNum,ArrayList<ChannelHandlerContext> channelHandlerContextArrayList){
        Random random = new Random();
        if(mapNum>=0&&mapNum<10){//第一关完成了，进入第二关
            //生成第二关的随机地图
            int maxNum=12;
            int minNum=10;
            int randNumber =random.nextInt(maxNum - minNum + 1) + minNum;
            TankWorld tankWorld=new TankWorld(groupNum++,channelHandlerContextArrayList,randNumber);
            GameCenter.tankWorldArrayList.add(tankWorld);
            tankWorld.tankWorkStart(tankWorld);
        }else if(mapNum>=10&&mapNum<100){//第二关完成了，进入第三关
            //生成第二关的随机地图
            int maxNum=102;
            int minNum=100;
            int randNumber =random.nextInt(maxNum - minNum + 1) + minNum;
            TankWorld tankWorld=new TankWorld(groupNum++,channelHandlerContextArrayList,randNumber);
            GameCenter.tankWorldArrayList.add(tankWorld);
            tankWorld.tankWorkStart(tankWorld);
        } else if (mapNum>=100&&mapNum<1000){//第三关完成了，这时需要结束游戏

        }
    }

    public void setChannelHandlerContextArrayList(ArrayList<ChannelHandlerContext> channelHandlerContextArrayList){
        this.channelHandlerContextArrayList=channelHandlerContextArrayList;
    }
}
