/**
 * 描述: 
 * GUIDTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.safety;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.safety.GUID;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * GUIDTest
 */
public final class GUIDTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetGUID() {
		try {
			// 32位
			log.info("testGetGUID =====> " + GUID.getGUID(true).length());
			log.info("testGetGUID =====> " + GUID.getGUID(true));
			log.info("testGetGUID =====> " + GUID.getGUID(false));
			
		} catch (Exception e) {
			log.error("testGetGUID =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testIntToHexString() {
		try {
			/**
			 * 小于等于 15 输出1位16进制字符串，在多数情况下为了保持2位16进制数的
			 * 形态，需要补0
			 * 例如:   if (b < 0x10) sb.append('0');
			 */
			/*
		 	    for (int j = 0; j < array.length; ++j) {
		 	    // 取8位 转成 int类型
                int b = array[j] & 0xFF;
                if (b < 0x10) sb.append('0');
                sb.append(Integer.toHexString(b));
	            }
			 */
			int i = 120;
			log.info("testIntToHexString =====> " + Integer.toHexString(i));
			
			 i = 14;
			log.info("testIntToHexString =====> " + Integer.toHexString(i));
			
			 i = 15;
			log.info("testIntToHexString =====> " + Integer.toHexString(i));
			
			 i = 16;
			log.info("testIntToHexString =====> " + Integer.toHexString(i));
			
		} catch (Exception e) {
			log.error("testIntToHexString =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBitAnd() {
		try {
			/**
			 * 位与: &
			 */
			byte bt = 127;
			int i = bt;
			
			int t = 0XFF;
			log.info("testBitAnd =====> i = " + i);
			
			bt = -127;
			/**
			 * 字节 和 2位16进制数进行 & 运算，
			 * 目的是保证字节值始终为正数.
			 */
			i = bt & 0XFF;
			log.info("testBitAnd =====> i = " + i);
			
		} catch (Exception e) {
			log.error("testBitAnd =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			log.info("testTemp =====> " +  InetAddress.getLocalHost().toString());
			String sql = "SELECT `driverClass` ,  `jdbcUrl` ,  `user` ,  `password` ,  `maxPoolSize` ,  `minPoolSize` ,  `initialPoolSize` ,  `acquireIncrement` ,  `maxStatements` ,  `maxIdleTime` ,  `checkoutTimeout` , `charactorEncoding` "+ 
					" FROM  `core_cmp_db` where cre_cmp_id=?";
			
			System.out.println(sql);
			System.out.println(File.separator);
			System.out.println(System.getProperty("file.separator"));
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss.ms");
			log.info("testTemp =====> " + df.format(new Date()));
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
