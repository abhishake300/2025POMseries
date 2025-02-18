package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	public static final  String LOGIN_PAGE_TITLE="Account Login";
	public static final  String ACCOUNTS_PAGE_TITLE="My Account";
	public static final  String LOGIN_PAGE_FRACTION_URL="route=account/login";
	
	
	//Accounts Page
//	public static final  String ACCOUNTS_PAGE_HEADERS_LIST="route=account/login";
	public static final  int ACCOUNTS_PAGE_HEADERS_COUNT=4;	
	
	
	
	public static final  int SHORT_TIME_OUT=5;
	public static final  int MEDIUM_TIME_OUT=10;
	public static final  int LONG_TIME_OUT=20;
	
	public static final List<String> EXPECTED_ACCOUNT_HEADERS_LIST= List.of("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final String USER_REG_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "x";
	


}
