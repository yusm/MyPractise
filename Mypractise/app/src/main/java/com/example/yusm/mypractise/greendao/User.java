package com.example.yusm.mypractise.greendao;

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
public class User {
    @Id
    private Long id;

    @NotNull
    private String username;

    private int grade;

    @Generated(hash = 1113909868)
    public User(Long id, @NotNull String username, int grade) {
        this.id = id;
        this.username = username;
        this.grade = grade;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", grade=" + grade +
                '}';
    }
}
