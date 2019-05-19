package tank;

import protobuf.message.messageClass.*;
import wingman.game.Bullet;
import wingman.game.PlayerShip;

/**
 * @Author longzhu
 * version 1.0
 * 2019/5/1 16:42
 */
public class TankWorldHelper {

    public static OutResRandomMapMessage.ResRandomMapMessage getResRandomMapMessage(int mapNum,int groupId){
        OutResRandomMapMessage.ResRandomMapMessage.Builder builder= OutResRandomMapMessage.ResRandomMapMessage.newBuilder();
        builder.setMapNum(mapNum);
        builder.setGroupId(groupId);
        return builder.build();
    }
    public static OutUserTankMessage.UserTankMessage getUserTankMessage(PlayerShip playerShip){
        if(playerShip==null){
            return null;
        }
        OutUserTankMessage.UserTankMessage.Builder builder= OutUserTankMessage.UserTankMessage.newBuilder();
        String tankName=playerShip.getName();
        builder.setTankName(tankName);
        int x=playerShip.getLocation().x;
        builder.setLocationX(x);
        int y=playerShip.getLocation().y;
        builder.setLocationY(y);
        int direction=((Tank) playerShip).getDirection();
        builder.setDirection(direction);
        int tankLives=playerShip.getLives();
        builder.setTankLives(tankLives);
        int tankHealth=playerShip.getHealth();
        builder.setTankHealth(tankHealth);
        int tankScores=playerShip.getScore();
        builder.setTankScores(tankScores);
        int tankStrength=playerShip.getStrength();
        builder.setTankStrength(tankStrength);
        int respawnCounter=playerShip.respawnCounter;
        builder.setRespawnCounter(respawnCounter);
        return builder.build();
    }

    public static OutAiTankMessage.AiTankMessage getAiTankMessage(PlayerShip playerShip){
        if(playerShip==null){
            return null;
        }
        OutAiTankMessage.AiTankMessage.Builder builder= OutAiTankMessage.AiTankMessage.newBuilder();
        String tankName=playerShip.getName();
        builder.setTankName(tankName);
        int x=playerShip.getLocation().x;
        builder.setLocationX(x);
        int y=playerShip.getLocation().y;
        builder.setLocationY(y);
        int direction=((AiTank) playerShip).getDirection();
        builder.setDirection(direction);
        int tankLives=playerShip.getLives();
        builder.setTankLives(tankLives);
        int tankHealth=playerShip.getHealth();
        builder.setTankHealth(tankHealth);
        int tankScores=playerShip.getScore();
        builder.setTankScores(tankScores);
        int tankStrength=playerShip.getStrength();
        builder.setTankStrength(tankStrength);
        int respawnCounter=playerShip.respawnCounter;
        builder.setRespawnCounter(respawnCounter);
        return builder.build();
    }

    public static OutRemoveBulletMessage.RemoveBulletMessage getRemoveBulletMessage(int bulletId,int bulletLocationX,int bulletLocationY){
        OutRemoveBulletMessage.RemoveBulletMessage.Builder builder= OutRemoveBulletMessage.RemoveBulletMessage.newBuilder();
        builder.setBulletId(bulletId);
        builder.setBulletLocationX(bulletLocationX);
        builder.setBulletLocationY(bulletLocationY);
        return builder.build();
    }

    public static OutSmallExplosionMessage.SmallExplosionMessage getSmallExplosionMessage(int bulletId,int bulletLocationX,int bulletLocationY){
        OutSmallExplosionMessage.SmallExplosionMessage.Builder builder= OutSmallExplosionMessage.SmallExplosionMessage.newBuilder();
        builder.setBulletId(bulletId);
        builder.setBulletLocationX(bulletLocationX);
        builder.setBulletLocationY(bulletLocationY);
        return builder.build();
    }

    public static OutBulletInfoMessage.BulletInfoMessage getBulletInfoMessage(Bullet bullet){
        if(bullet==null){
            return null;
        }
        OutBulletInfoMessage.BulletInfoMessage.Builder builder= OutBulletInfoMessage.BulletInfoMessage.newBuilder();
        builder.setBulletId(bullet.getBulletID());
        builder.setBulletLocationX(bullet.getLocationPoint().x);
        builder.setBulletLocationY(bullet.getLocationPoint().y);
        return builder.build();
    }

    public static OutTankDisappearMessage.TankDisappearMessage getTankDisappearMessage(String name,int locationX,int locationY){
        OutTankDisappearMessage.TankDisappearMessage.Builder builder= OutTankDisappearMessage.TankDisappearMessage.newBuilder();
        builder.setTankName(name);
        builder.setLocationX(locationX);
        builder.setLocationY(locationY);
        return builder.build();
    }

    public static OutBigExplosionMessage.BigExplosionMessage getBIgExplosionMessage(int locationX,int locationY){
        OutBigExplosionMessage.BigExplosionMessage.Builder builder= OutBigExplosionMessage.BigExplosionMessage.newBuilder();
        builder.setBulletLocationX(locationX);
        builder.setBulletLocationY(locationY);
        return builder.build();
    }

    public static OutRemoveBreakableWallMessage.RemoveBreakableWallMessage getRemoveBreakableWallMessage(int locationX,int locationY){
        OutRemoveBreakableWallMessage.RemoveBreakableWallMessage.Builder builder= OutRemoveBreakableWallMessage.RemoveBreakableWallMessage.newBuilder();
        builder.setBulletLocationX(locationX);
        builder.setBulletLocationY(locationY);
        return builder.build();
    }

    public static OutBreakableWallMessage.BreakableWallMessage getBreakableWallMessage(int locationX,int locationY){
        OutBreakableWallMessage.BreakableWallMessage.Builder builder= OutBreakableWallMessage.BreakableWallMessage.newBuilder();
        builder.setBulletLocationX(locationX);
        builder.setBulletLocationY(locationY);
        return builder.build();
    }

    public static OutSmallAnimationMessage.SmallAnimationMessage getSmallAnimationMessage(int locationX,int locationY,int frame){
        OutSmallAnimationMessage.SmallAnimationMessage.Builder builder= OutSmallAnimationMessage.SmallAnimationMessage.newBuilder();
        builder.setLocationX(locationX);
        builder.setLocationY(locationY);
        builder.setFrame(frame);
        return builder.build();
    }

    public static OutRemoveSmallExplosionMessage.RemoveSmallExplosionMessage getRemoveSmallExplosionMessage(int locationX,int locationY){
        OutRemoveSmallExplosionMessage.RemoveSmallExplosionMessage.Builder builder= OutRemoveSmallExplosionMessage.RemoveSmallExplosionMessage.newBuilder();
        builder.setLocationX(locationX);
        builder.setLocationY(locationY);
        return builder.build();
    }

    public static OutBigAnimationMessage.BigAnimationMessage getBigAnimationMessage(int locationX,int locationY,int frame){
        OutBigAnimationMessage.BigAnimationMessage.Builder builder= OutBigAnimationMessage.BigAnimationMessage.newBuilder();
        builder.setLocationX(locationX);
        builder.setLocationY(locationY);
        builder.setFrame(frame);
        return builder.build();
    }

    public static OutRemoveBigExplosionMessage.RemoveBigExplosionMessage getRemoveBigExplosionMessage(int locationX,int locationY){
        OutRemoveBigExplosionMessage.RemoveBigExplosionMessage.Builder builder= OutRemoveBigExplosionMessage.RemoveBigExplosionMessage.newBuilder();
        builder.setBulletLocationX(locationX);
        builder.setBulletLocationY(locationY);
        return builder.build();
    }

    public static OutRemovePowerUpMessage.RemovePowerUpMessage getRemovePowerUpMessage(int locationX,int locationY){
        OutRemovePowerUpMessage.RemovePowerUpMessage.Builder builder= OutRemovePowerUpMessage.RemovePowerUpMessage.newBuilder();
        builder.setLocationX(locationX);
        builder.setLocationY(locationY);
        return builder.build();
    }

    public static OutCreateBulletMessage.CreateBulletMessage getCreateBulletMessage(String name,int locationX,int locationY,int direction,int bulletId){
        OutCreateBulletMessage.CreateBulletMessage.Builder builder= OutCreateBulletMessage.CreateBulletMessage.newBuilder();
        builder.setName(name);
        builder.setLocationX(locationX);
        builder.setLocationY(locationY);
        builder.setDirection(direction);
        builder.setBulletId(bulletId);
        return builder.build();
    }

    public static OutgameFinishedAndGameWonMessage.gameFinishedAndGameWonMessage getgameFinishedAndGameWonMessage(){
        OutgameFinishedAndGameWonMessage.gameFinishedAndGameWonMessage.Builder builder=OutgameFinishedAndGameWonMessage.gameFinishedAndGameWonMessage.newBuilder();
        return builder.build();
    }

    public static OutgameFinishedAndNotGameWonMessage.gameFinishedAndNotGameWonMessage getgameFinishedAndNotGameWonMessage(){
        OutgameFinishedAndNotGameWonMessage.gameFinishedAndNotGameWonMessage.Builder builder=OutgameFinishedAndNotGameWonMessage.gameFinishedAndNotGameWonMessage.newBuilder();
        return builder.build();
    }
}
