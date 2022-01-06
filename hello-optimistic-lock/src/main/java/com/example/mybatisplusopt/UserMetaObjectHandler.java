//package com.example.mybatisplusopt;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import org.apache.ibatis.reflection.MetaObject;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * @author <a href="mailto:410486047@qq.com">Grey</a>
// * @date 2022/1/6
// * @since
// */
//@Component
//public class UserMetaObjectHandler implements MetaObjectHandler {
//
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("isDeleted", false, metaObject);
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//    }
//}