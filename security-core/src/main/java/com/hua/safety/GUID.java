/**
  * @filename GUID.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.safety;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import com.hua.constant.Constant;
import com.hua.util.StringUtil;

 /**
 * @type GUID
 * @description 
 * @author qye.zheng
 */
public final class GUID {
	
	private static final Random random;
	
	private static final SecureRandom secureRandom;
	
	private static String sId;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	private GUID() {
	}
	
	static
	{
		secureRandom = new SecureRandom();
		final long secureInitializer = secureRandom.nextLong();
		random = new Random(secureInitializer);
		try {
			sId = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 获取 GUID 32位(0-f)
	 * @param secure
	 * @return
	 * @author qye.zheng
	 */
	public static final String getGUID(final boolean secure)
	{
		String valueBeforeMD5 = null;
		String valueAfterMD5 = null;
		MessageDigest md5 = null;
		final StringBuilder builderBeforeMD5 = StringUtil.getBuilder();
		try
		{
			md5 = MessageDigest.getInstance(Constant.EN_DECRY_MD5);
			final long time = System.currentTimeMillis();
			long rand = 0L;
			if (secure)
			{
				rand = secureRandom.nextLong();
			} else
			{
				rand = random.nextLong();
			}
			builderBeforeMD5.append(sId);
			builderBeforeMD5.append(":");
			builderBeforeMD5.append(time);
			builderBeforeMD5.append(":");
			builderBeforeMD5.append(rand);
			
			valueBeforeMD5 = builderBeforeMD5.toString();
			// 更新
			md5.update(valueBeforeMD5.getBytes());
			// 获取字节数组
			byte[] array = md5.digest();
			// 将1个字节转换为 2位 16进制字符串
			final StringBuilder encodeBuilder = StringUtil.getBuilder();
			int b = 0;
			// 16
			int n16 = 0X10;
			for (int i = 0; i < array.length; i++)
			{
				// 字节8位 转成 int类型
				b = array[i] & 0XFF;
				// 高位补0
				if (b < n16)
				{
					encodeBuilder.append(Constant.ZERO);
				}
				// 转成16进制字符串
				encodeBuilder.append(Integer.toHexString(b));
			}
			
			valueAfterMD5 = encodeBuilder.toString();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return valueAfterMD5;
	}
}
