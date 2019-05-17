package protobuf.messageManager.handler;

import io.netty.channel.ChannelHandlerContext;
import protobuf.message.messageClass.*;
import protobuf.messageManager.annotation.MessageHandler;
import protobuf.messageManager.annotation.MethodInvode;
import protobuf.user.User;
import protobuf.user.UserManager;
import tank.Tank;
import tank.TankWorld;
import wingman.game.PlayerShip;

import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author longzhu
 * version 1.0
 * 2019/5/17 16:39
 */
@MessageHandler
public class InputControllerHandler {

    @MethodInvode
    public void turnLeftMessageHandler(long userId, OutTurnLeftMessage.TurnLeftMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.left=1;
                    }
                }
            }
        }
    }

    @MethodInvode
    public void turnRightMessageHandler(long userId, OutTurnRightMessage.TurnRightMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.right=1;
                    }
                }
            }
        }
    }

    @MethodInvode
    public void turnUpMessageHandler(long userId, OutTurnUpMessage.TurnUpMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.up=1;
                    }
                }
            }
        }
    }

    @MethodInvode
    public void turnDownMessageHandler(long userId, OutTurnDownMessage.TurnDownMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.down=1;
                    }
                }
            }
        }
    }

    @MethodInvode
    public void turnFiringMessageHandler(long userId, OutTurnFiringMessage.TurnFiringMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.startFiring();
                    }
                }
            }
        }
    }

    @MethodInvode
    public void releaseLeftMessageHandler(long userId, OutReleaseLeftMessage.ReleaseLeftMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.left=0;
                    }
                }
            }
        }
    }

    @MethodInvode
    public void releaseRightMessageHandler(long userId, OutReleaseRightMessage.ReleaseRightMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.right=0;
                    }
                }
            }
        }
    }

    @MethodInvode
    public void releaseUpMessageHandler(long userId, OutReleaseUpMessage.ReleaseUpMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.up=0;
                    }
                }
            }
        }
    }

    @MethodInvode
    public void releaseDownMessageHanlder(long userId, OutReleaseDownMessage.ReleaseDownMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.down=0;
                    }
                }
            }
        }
    }

    @MethodInvode
    public void releaseFiringMessageHandler(long userId, OutReleaseFiringMessage.ReleaseFiringMessage message){
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        int groupNum = user.getGroupNum();
        CopyOnWriteArrayList<TankWorld> tankWorlds=NetWorkGameHandler.tankWorlds;
        String name=message.getName();
        for(TankWorld tankWorld:tankWorlds){
            if(tankWorld.getGroupNum()==groupNum){
                ListIterator<PlayerShip> players=tankWorld.getPlayers();
                while (players.hasNext()){
                    Tank player = (Tank) players.next();
                    if(player.getName().equals(name)){
                        player.stopFiring();
                    }
                }
            }
        }
    }
}
