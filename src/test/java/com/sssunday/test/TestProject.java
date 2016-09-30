package com.sssunday.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sssunday.common.utils.ResourcesUtils;
import com.sssunday.model.User;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring.xml")*/
public class TestProject {

	
	/*@Before
	public void before(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:config/spring.xml");
		context.start();
	}*/
	//@Test
	public void testResources() {
		ResourcesUtils resourcesUtils = ResourcesUtils.getResouce("properties/constant");
		Logger log = Logger.getLogger(TestProject.class);
		try {
			String s = resourcesUtils.getValue("test1","mike","中国");
			System.out.println(s);
		} catch (UnsupportedEncodingException uee) {
			log.error(uee.getMessage(),uee);
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
	}
	
	//@Test
	public void testRedis() throws Exception{
		
		List<User> uList = new ArrayList<User>();
		
		User u2 = new User();
		u2.setId(1L);
		u2.setAge(20L);
		u2.setName("json");
		User u23 = new User();
		u23.setId(1L);
		u23.setAge(20L);
		u23.setName("json");
		uList.add(u2);
		uList.add(u23);
		
		User u = new User();
		u.setId(1L);
		u.setAge(20L);
		u.setName("json");
		u.setU(uList);
		
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(u);
		System.out.println(str);
		
		Map<String,Object> map = mapper.readValue(str, Map.class);
		System.out.println(map.get("u"));
	}
	
	@Test
	public void test2() throws JsonProcessingException{
		 String str="{buyer_id=2088502993562915, trade_no=2016092321001004910202574398, body=1, use_coupon=N, notify_time=2016-09-23 11:48:30, subject=9倍极速闪电解冻板 快速化冰创意厨房用品, sign_type=RSA, is_total_fee_adjust=N, notify_type=trade_status_sync, out_trade_no=14746022787771751_1474602279, gmt_payment=2016-09-23 11:44:52, trade_status=TRADE_SUCCESS, discount=0.00, sign=EL0fVfyHgWgIX00+vE9lyH3+tLM8m3hTXJ3qBaPYL93mWF9yddazhKCv1tOsNndkrFhcuaqQ4JRc3ZKGhwa0DWrGP8fE3hc3nJa5RAGLTrbP7XB9sE1+0y1HuR1XhCpbBrLmF6xObf/im5+3bv4oa9bLm38unAhA5i8BZbB+X4c=, gmt_create=2016-09-23 11:44:51, buyer_email=843458091@qq.com, price=78.00, total_fee=78.00, seller_id=2088121772091220, quantity=1, seller_email=winfor-lefu@winforit.com, notify_id=e92f4d9aca3139dab87b404ea0ef355n0u, payment_type=1}";
		 Map<String,String> map=mapStringToMap(str);
		 ObjectMapper mapper = new ObjectMapper();
			String str1 = mapper.writeValueAsString(map);
			System.out.println(str1);
		System.out.println(map);
	}

	public static Map<String,String> mapStringToMap(String str)
	{str=str.substring(1, str.length()-1);
	String[] strs=str.split(",");
	Map<String,String> map = new HashMap<String, String>();
	for (String string : strs) 
	{String key=string.split("=")[0];
	String value=string.split("=")[1];
	map.put(key, value);
	}return map;
	}
}
