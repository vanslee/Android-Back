package com.ldx.constants;

public class SystemConstant {
    /**
     * 七牛云API的外网域名
     */
    public static final String ENDPOINT = "lidengxiang.xyz";
    /**
     * 七牛云API的bucket名称
     * 在七牛云上自己创建一个bucket
     */
    public static final String BACKET_NAME = "lidengxiang";
    /**
     * 七牛云API的文件夹名称
     * 在七牛云上自己创建一个文件夹，方便分类管理图片
     */
    public static final String FOLDER = "img/";

    /**
     * 填写超级管理员的用户名
     */
    public static final String SITE_OWNER = "李图报";

    /**
     * 填写网站域名或ip地址(最好是域名，如果你还没有域名填ip也行吧)
     */
    public static final String SITE_OWNER_URL = "www.lidengxiang.xyz";
    /**
     * 普通用户
     */
    public static final int ROLE_USER = 1;
    public static final String REDIS_LOGIN = "android:login:";
    public static final int NOT_IMPLEMENTED = 501;
    public static final int SUCCESS = 200;

    /**
     * 亲朋好友
     */
    int ROLE_ADMIN = 2;

    /**
     * 我的权限
     */
    int ROLE_SUPERADMIN = 3;
    /**
     * 参照Unix命令行返回值 0代表成功，大于0代表不同的错误
     */
    public static final int DEFAULT_STATUS_SUCCESS = 0;
    public static final int DEFAULT_STATUS_FAIL = 1;

    public static final String DEFAULT_STATUS_KEY = "status";
    public static final String DEFAULT_MESSAGE_KEY = "message";
    public static final String DEFAULT_DATA_KEY = "data";
    /**
     * QQ
     */
    public static String QQ_ACCESS_APPID = "";
    public static String QQ_SECURITY_KEY = "";
    public static String QQ_REDIRECT = "";
    public static String QQ_GET_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=";
    public static String QQ_GET_USERINFO_URL = "https://graph.qq.com/user/get_user_info?access_token=";
    public static String QQ_GET_ACCESSTOKEN_URL = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code";
    public static String QQ_OAUTHCONSUMERKEY_URI = "&oauth_consumer_key=";
    public static String QQ_OPENID_URI = "&openid=";
    public static String QQ_CLIENTID_URI = "&client_id=";
    public static String QQ_CLIENTSECRET_URI = "&client_secret=";
    public static String QQ_REDIRECT_URI_URI = "&redirect_uri=";
    public static String QQ_CODE_URI = "&code=";
    public static String QQ_RESULT_FMT = "&fmt=json";
    /**
     * 七牛云
     */
    public static String QINIUYUN_ACCESS_KEY_ID = "";
    public static String QINIUYUN_ACCESS_KEY_SECRET = "";
}
