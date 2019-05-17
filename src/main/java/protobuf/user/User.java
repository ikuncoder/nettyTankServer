package protobuf.user;

/**
 * @Author longzhu
 * version 1.0
 * 2019/4/28 14:58
 */
public class User {
    /**用户id*/
    private long userId;
    /**房间号*/
    private volatile int groupNum;

    public User(long userId,Integer...group){
        this.userId=userId;
        if(group!=null&&group.length!=0){
            this.groupNum=group[0];
        }
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
