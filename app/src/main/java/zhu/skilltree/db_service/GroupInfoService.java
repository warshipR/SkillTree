package zhu.skilltree.db_service;

import android.util.Log;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import zhu.skilltree.bean.group;


/**
 * Created by Zhu on 2017.7.23.
 */
public class GroupInfoService implements dbServiceI {
    private Boolean flag = false;

    @Override
    public Boolean save(BmobObject bmobObject) {
        group group = (group) bmobObject;
        group.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    setFlag(Boolean.valueOf(true));
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    setFlag(Boolean.valueOf(false));
                }
            }
        });
        return flag;

    }

    @Override
    public Boolean update(BmobObject bmobObject, String property, String newValue) {
        group userInfo = new group();
        userInfo.setValue(property, newValue);
        userInfo.update(bmobObject.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    setFlag(Boolean.valueOf(true));
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    setFlag(Boolean.valueOf(false));
                }
            }
        });
        return flag;
    }


    @Override
    public Boolean delete(BmobObject bmobObject) {

        final String id = bmobObject.getObjectId();
        group userInfo = new group();
        userInfo.setObjectId(id);
        userInfo.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    setFlag(Boolean.valueOf(true));
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    setFlag(Boolean.valueOf(false));
                }
            }
        });
        return flag;
    }

    @Override
    public void setFlag(Boolean b) {
        flag = b;
    }
}
