package protobuf;

import tank.GameCenter;
import tank.Tank;
import tank.TankWorld;
import wingman.game.PlayerShip;

import java.util.ArrayList;
import java.util.ListIterator;

public class ReceiveMsg implements Runnable{

    private ClientProtocolProto.ClientProtocol req;
    private ArrayList<TankWorld> tankWorldArrayList;
    public ReceiveMsg(ClientProtocolProto.ClientProtocol req){
        this.req=req;
    }

    public void receiveMsg(ClientProtocolProto.ClientProtocol req){
        int groupNum=req.getGroupNum();
        String sign = req.getSign();
        String playerId =req.getPlayerId();
        String order = req.getOrder();
        ListIterator<PlayerShip> players=null;
        tankWorldArrayList=GameCenter.getInstance().tankWorldArrayList;
        for(int i = 0; i<tankWorldArrayList.size(); i++){//遍历房间
            if(tankWorldArrayList.get(i).getGroupNum()==groupNum){
                players = tankWorldArrayList.get(i).getPlayers();
                receiveMsgHandler(sign,playerId,order,players);
                break;
            }
        }
    }
    @Override
    public void run() {
        receiveMsg(req);
    }

    public void receiveMsgHandler(String sign,String playerId,String order,ListIterator<PlayerShip> players){
        // 处理从客户端接收的指令
        if (sign.equals("1")) {
            while (players.hasNext()) {
                Tank player = (Tank) players.next();
                if (player.getName().equals(playerId)) {
                    // 如果是left，right，up,down指令
                    if (order.equals("left")) {
                        player.left = 1;
                    } else if (order.equals("right")) {
                        player.right = 1;
                    } else if (order.equals("up")) {
                        player.up = 1;
                    } else if (order.equals("down")) {
                        player.down = 1;
                    }
                }
            }
        } else if (sign.equals("0")) {
            while (players.hasNext()) {
                Tank player = (Tank) players.next();
                if (player.getName().equals(playerId)) {
                    if (order.equals("left")) {
                        player.left = 0;
                    } else if (order.equals("right")) {
                        player.right = 0;
                    } else if (order.equals("up")) {
                        player.up = 0;
                    } else if (order.equals("down")) {
                        player.down = 0;
                    }
                }
            }
        } else if (sign.equals("*")) {// 处理*%指令
            while (players.hasNext()) {
                Tank player = (Tank) players.next();
                if (player.getName().equals(playerId)) {
                    // 如果是开火
                    if (order.equals("isFiring")) {
                        player.startFiring();
                    }
                }
            }
        }else if (sign.equals("%")) {//stop fire
            while (players.hasNext()) {
                Tank player = (Tank) players.next();
                if (player.getName().equals(playerId)) {
                    if (order.equals("isFiring")) {
                        player.stopFiring();
                    }
                }
            }
        }
    }
}
