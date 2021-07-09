package com.example.test.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SHA265Util {
	private static final Logger logger = LoggerFactory.getLogger(SHA265Util.class);
	public static String sha256(String id, String pw) throws NoSuchAlgorithmException { //
		 byte[] hashValue = null;
	        
	        MessageDigest md = MessageDigest.getInstance("SHA-512");	//인스턴스 생성 암호화 알고리즘 설정
	        	
	        md.reset();		//객체초기화
	        md.update(id.getBytes());	//바이트 데이터사용을 위해 갱신
	        
	        hashValue = md.digest(pw.getBytes());	//해싱값 생성
	        //String.format -> 암호화를 위해 문자열 변환	%064x ->sha-256으로 해싱된 값 16진수 64바이트로 변환
	        pw = String.format("%064x", new BigInteger(1, hashValue));
	        return pw;
	}
}
