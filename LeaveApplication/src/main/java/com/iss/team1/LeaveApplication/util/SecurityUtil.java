package com.iss.team1.LeaveApplication.util;

public class SecurityUtil {
	
	private static final int ROUNDS = 12;

	public static String hashPassword(String password)
    {
		return BCrypt.hashpw(password, BCrypt.gensalt(ROUNDS));
    }
	
	public static boolean isValidPassword(String password, String hashPwd) {
		return BCrypt.checkpw(password, hashPwd);
	}
	
}
