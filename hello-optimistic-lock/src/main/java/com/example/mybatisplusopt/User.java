package com.example.mybatisplusopt;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/1/6
 * @since
 */
@Data // Lombok
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT) // 表示插入的时候自动填充
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE) // 更新的时候自动填充
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDeleted;
    @Version
    private Integer version;
}