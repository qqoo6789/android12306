package com.db;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {

    public static final int VERSION = 1;
    public static final String GREEN_DAO_CODE_PATH = "../my12306/app/src/main/java";
    public static final String DEAFULT_DAO_PACKAGE = "ruijie.com.my12306.db.dao";
    public static final String DEAFULT_ENTITY_PACKAGE = "ruijie.com.my12306.db.entity";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(VERSION,DEAFULT_DAO_PACKAGE);
        schema.setDefaultJavaPackageDao(DEAFULT_ENTITY_PACKAGE);

        Entity user = schema.addEntity("User");
        user.addIdProperty();
        user.addStringProperty("userName");
        user.addStringProperty("passWord");
        user.addStringProperty("email");
        user.addStringProperty("number");
        user.addStringProperty("cookie");
        user.addStringProperty("token");
        user.addStringProperty("registerTime");
        user.addStringProperty("location");
        user.addStringProperty("nickNameUrl");
        user.addStringProperty("uid");
        user.addIntProperty("sex");

        File f = new File(GREEN_DAO_CODE_PATH);
        if(!f.exists()){
            f.mkdirs();
        }
        new DaoGenerator().generateAll(schema, f.getAbsolutePath());
    }
}
