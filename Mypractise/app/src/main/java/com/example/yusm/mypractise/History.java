package com.example.yusm.mypractise;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/*
 *
 * Date: 2019/11/21
 * Desc：
 */
@Entity
public class History {

    @Id
    private Long id;

    @NotNull
    private String name;

    private String desc;

    private int type;

    @Generated(hash = 1235211756)
    public History(Long id, @NotNull String name, String desc, int type) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.type = type;
    }

    @Generated(hash = 869423138)
    public History() {
    }

    public synchronized Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
