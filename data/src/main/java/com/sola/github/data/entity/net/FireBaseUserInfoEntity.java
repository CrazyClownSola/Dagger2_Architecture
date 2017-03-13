package com.sola.github.data.entity.net;

import com.sola.github.data.entity.BaseResultEntity;

/**
 * Created by Sola
 * 2017/3/13.
 */
@SuppressWarnings("unused")
public class FireBaseUserInfoEntity extends BaseResultEntity {

    private int id;

    private String userId;

    private String name;

    private String pic;

    private String gender;

    private int age;

    private String mobile;

    public int getId() {
        return id;
    }

    @Override
    public boolean isSuccess() {
        return true; // 假处理
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getMobile() {
        return mobile;
    }
}
