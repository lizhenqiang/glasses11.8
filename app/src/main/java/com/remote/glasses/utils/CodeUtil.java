package com.remote.glasses.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 加解密工具类
 * 
 * @author OSWorks-XC
 * @since 2013-07-13
 */
public class CodeUtil {

	public final static String APPKEY = "E214880f60Db693e96be";

	// 创建Base64对象,用于加密和解密;
	private final static Base64 base64 = new Base64();

	private final static String encoding = "UTF-8";
	
	/*app敏感参数传递加密方法*/
	public static String appEncryMethod(String strSRc) {
		return encryptBase64(strSRc,CodeUtil.APPKEY);
	}
	
	public static String appDecryptMethod(String strSRc) {
		return decryptBase64(strSRc, CodeUtil.APPKEY);
	}

	/**
	 * 用Base64对加密好的byte数组进行编码，返回字符串
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @param sKey
	 *            加密密钥
	 * @return 经过加密的字符串
	 */
	public static String encryptBase64(String str, String sKey) {
		// 声明加密后的结果字符串变量
		String result = str;
		if (str != null && str.length() > 0 && sKey != null
				&& sKey.length() >= 8) {
			try {
				// 调用DES 加密数组的 encrypt方法，返回加密后的byte数组;
				byte[] encodeByte = encryptBasedDes(str.getBytes(encoding), sKey);
				// 调用base64的编码方法，返回加密后的字符串;
				result = android.util.Base64.encodeToString(encodeByte, android.util.Base64.DEFAULT).trim();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

	/**
	 * 用Base64对字符串进行编码，返回byte数组
	 * 
	 * @param str
	 *            需要解密的字符串
	 * @param sKey
	 *            解密密钥
	 * @return 经过解密的字符串
	 */
	public static String decryptBase64(String str, String sKey) {
		String result = str;
		if (str != null && str.length() > 0 && sKey != null
				&& sKey.length() >= 8) {
			try {
				// 用base64进行编码，返回byte数组
				byte[] encodeByte = base64.decode(str);
				// 调用DES 解密数组的decrypte方法，返回解密后的数组;
				byte[] decoder = decryptBasedDes(encodeByte, sKey);
				// 对解密后的数组转化成字符串
				result = new String(decoder, encoding).trim();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 先用DES算法对byte[]数组加密
	 * 
	 * @param byteSource
	 *            需要加密的数据
	 * @param sKey
	 *            加密密钥
	 * @return 经过加密的数据
	 * @throws Exception
	 */
	private static byte[] encryptBasedDes(byte[] byteSource, String sKey)
			throws Exception {
		try {
			// 声明加密模式;
			int mode = Cipher.ENCRYPT_MODE;
			// 创建密码工厂对象;
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 把字符串格式的密钥转成字节数组;
			byte[] keyData = sKey.getBytes();
			// 以密钥数组为参数，创建密码规则
			DESKeySpec keySpec = new DESKeySpec(keyData);
			// 以密码规则为参数，用密码工厂生成密码
			Key key = keyFactory.generateSecret(keySpec);
			// 创建密码对象
			Cipher cipher = Cipher.getInstance("DES");
			// 以加密模式和密码为参数对密码对象 进行初始化
			cipher.init(mode, key);
			// 完成最终加密
			byte[] result = cipher.doFinal(byteSource);
			// 返回加密后的数组
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 先用DES算法对byte[]数组解密
	 * 
	 * @param byteSource
	 *            需要解密的数据
	 * @param sKey
	 *            解密密钥
	 * @return 经过解密的数据
	 * @throws Exception
	 */
	private static byte[] decryptBasedDes(byte[] byteSource, String sKey)
			throws Exception {
		try {
			// 声明解密模式;
			int mode = Cipher.DECRYPT_MODE;
			// 创建密码工厂对象;
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 把字符串格式的密钥转成字节数组;
			byte[] keyData = sKey.getBytes();
			// 以密钥数组为参数，创建密码规则
			DESKeySpec keySpec = new DESKeySpec(keyData);
			// 以密码规则为参数，用密码工厂生成密码
			Key key = keyFactory.generateSecret(keySpec);
			// 创建密码对象
			Cipher cipher = Cipher.getInstance("DES");
			// 以加密模式和密码为参数对密码对象 进行初始化
			cipher.init(mode, key);
			// 完成最终解密
			byte[] result = cipher.doFinal(byteSource);
			// 返回解密后的数组
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	public static String getAuthstr(String uname,String upas){
		String s = "username="+uname+"&passwd="+upas;
		return appEncryMethod(s);
	}



}
