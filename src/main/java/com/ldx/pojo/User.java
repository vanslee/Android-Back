package com.ldx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
@NoArgsConstructor
public class User implements Serializable {
    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    @TableField(exist = false)
    private Set<String> roles;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
