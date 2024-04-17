package com.example.resturentsilverspoons.util;

public class ConstantData {

    public static  final String SERVERADDRESS="http://192.168.145.132" + ":8000";
    public static final String BANNERURL="/api/get/getbanner";

        public static final String  COUNT_URL="/api/getCountOfCart";
        public static String  COUNT="0";

    public static final String TABLE_BOOKINGURL="/api/addtablebooking";

    public static final  String CONFIRM_ORDER_URL="/api/makePayment";

    public static  final  String REMOVE_ORDER_URL="/api/removeOrder";
    public static  final  String QUANTITY_ORDER_URL="/api/update/qty";
    public static  final  String GET_MENU_URL="/api/get/Menu/cat";
    public static  final  String APPLY_COUPON_URL="/api/getCouponFromCode";





    public static final String OrderUrl="/api/addOrders";
    public static final String GetOrderUrl="/api/getOrders";


    public  static  final  String CouponUrl="/api/get/GetCouponApi";

    public static final String REGISTERURL="/register_user";
    public static final String LOGIN_USERURL="/login_api";

    public static  final String CuisinUrl="/api/get/getcuisin";

    public  static  final  String Menuurl="/api/get/GetMenuApi";
    public  static  final  String EDIT_PROFILE_METHOD="/api/editProfile";


    public static final String KEY_USERNAME="key_username";
    public static final String KEY_PASS="key_pass";
    public static final String KEY_EMAIL="key_email";
    public static final String KEY_PHONE="key_phone";
    public static final String KEY_PIC="key_pic";

    public  static final  String KEY_ID="key_id";

    public static final  String LOGIN_REGISTRATION_PREFS="login_register_prefs";


    public static String MyOrderUrl="/order/history";
}
