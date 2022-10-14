package com.liujiachen.mpdemo1010.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: User
 * @Description:
 * @date: 2022/9/28 21:00
 */

@Data
public class User {
    @TableId(type = IdType.ID_WORKER) // mp自带策略，生成19位值，数字类型使用这种策略，比如long
    // @TableId(type = IdType.ID_WORKER_STR) // mp自带策略，生成19位值，字符串类型使用这种策略
    private Long id;

    private String name;
    private Integer age;
    private String email;

    // create_time
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    // update_time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;// 版本号

    @TableLogic
    private Integer deleted;// 逻辑删除
}
