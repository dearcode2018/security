/**
 * 描述: 
 * XSSTest.java
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

import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * XSSTest
 */
public final class XSSTest extends BaseTest {


	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testXSS() {
		try {
			String bodyHtml = "<img src=\"http://a/b1.png\" onerror=alert(1) />";
			bodyHtml = "<img src=\"/a/b1.png\" onerror=alert(1) />";
		//	bodyHtml = "<img src=\"/a/b1.png\" />";
			String value = stripXSS(bodyHtml);
			
			value = cleanXSS(bodyHtml);
			
			System.out.println(value);
		} catch (Exception e) {
			log.error("testXSS =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testXSS2() {
		try {
			String bodyHtml = "<img src=\"http://a/b1.png\" onerror=alert(1) />";
			bodyHtml = "<img src=\"/a/b1.png\" onerror=alert(1) />";
			bodyHtml = "<img src=\"/a/b1.png\" />";
			bodyHtml = "<img src=\"/o2oWeb/a/b1.png\"  onerror=alert(1) />";
			String value = null;
			
			value =  HtmlUtils.htmlEscape(bodyHtml);
			
			System.out.println(value);
		} catch (Exception e) {
			log.error("testXSS2 =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testXSSOfSpring() {
		try {
			String bodyHtml = "<img src=\"http://a/b1.png\" onerror=alert(1) />";
			bodyHtml = "<img src=\"/a/b1.png\" onerror=alert(1) />";
			bodyHtml = "<img src=\"/a/b1.png\" />";
			bodyHtml = "<img src=\"/o2oWeb/a/b1.png\"  onerror=alert(1) />";
			String value = null;
			
			value =  HtmlUtils.htmlEscape(bodyHtml);
			
			System.out.println(value);
		} catch (Exception e) {
			log.error("testXSSOfSpring =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testXSSOfSpring2() {
		try {
			String bodyHtml = "<img src=\"http://a/b1.png\" onerror=alert(1) />";
			bodyHtml = "<img src=\"/a/b1.png\" onerror=alert(1) />";
			bodyHtml = "<img src=\"/a/b1.png\" />";
			bodyHtml = "<img src=\"/o2oWeb/a/b1.png\"  onerror=alert(1) />";
			String value = null;
			
			// 转义，
			value =  HtmlUtils.htmlEscape("<a href=\"http://www.baidu.com\" target=\"_self\" title=\"ddd\">百度</a>");
			
			System.out.println(value);
		} catch (Exception e) {
			log.error("testXSSOfSpring =====> ", e);
		}
	}
	
	private String cleanXSS(String value) {
        //You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}	

	/**
	 * 
	 * 描述: 移除掉不安全的标签
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCleanXSS2() {
		try {
			String bodyHtml = "<img src=\"http://a/b1.png\" onerror=alert(1) />";
			bodyHtml = "<img src=\"/a/b1.png\" onerror=alert(1) onclick=\"aafdas\" onload=11 /> <script>alert('aa');</script>";
		//	bodyHtml = "<img src=\"/a/b1.png\" />";
			String value = null;
			
			value = stripXSS(bodyHtml);
			
			System.out.println(value);
		} catch (Exception e) {
			log.error("testCleanXSS2 =====> ", e);
		}
	}	
	
	/**
	 * 
	 * @description 过滤掉不安全的标签
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	private String stripXSS(String value) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);
            // Avoid null characters
            value = value.replaceAll("", "");
            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid anything in a src="http://www.yihaomen.com/article/java/..." type of e­xpression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            //scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            //value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid vbscript:... e­xpressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid on开头的= e­xpressions
            scriptPattern = Pattern.compile("on(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid onload= e­xpressions
           // scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
           // value = scriptPattern.matcher(value).replaceAll("");
            // Avoid onload= e­xpressions
            //scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            //value = scriptPattern.matcher(value).replaceAll("");
            
        }
        return value;
    }	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCleanXSS() {
		try {
			String value = null;
			value = "<img src=\"http://a/b1.png\" onerror=alert(1) />";
			value = "<img src=\"/a/b1.png\" onerror=alert(1) />";
			value = "<img src=\"/a/b1.png\" />";
			value = "<img src=\"/o2oWeb/a/b1.png\"  onerror=alert(1) />";
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);
            // Avoid null characters
            value = value.replaceAll("", "");
            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid anything in a src="http://www.yihaomen.com/article/java/..." type of e­xpression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid vbscript:... e­xpressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid on开头的= e­xpressions
            scriptPattern = Pattern.compile("^on(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid onload= e­xpressions
           // scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
           // value = scriptPattern.matcher(value).replaceAll("");
            // Avoid onload= e­xpressions
            //scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            //value = scriptPattern.matcher(value).replaceAll("");
			
            System.out.println(value);
            
		} catch (Exception e) {
			log.error("testCleanXSS =====> ", e);
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
			System.out.println(HtmlUtils.htmlUnescape("&lt;a href=&quot;http://www.baidu.com&quot;&gt;"));
			
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
