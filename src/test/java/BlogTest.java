


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.datasource.DBContextHolder;
import com.blog.entity.User;
import com.blog.mapper.PostDao;
import com.blog.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class BlogTest {


	@Autowired
	private PostDao postDao;
	@Autowired
	private UserService ud;
	
	@Test
	public void testName() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(DBContextHolder.DATASOURCE_KEY, "localhost");
		map.put(DBContextHolder.DATASOURCE_DRIVER, "com.mysql.jdbc.Driver");
		map.put(DBContextHolder.DATASOURCE_URL,
			"jdbc:mysql://127.0.0.1:3306/blog_test?useUnicode=true&characterEncoding=UTF-8");
		map.put(DBContextHolder.DATASOURCE_USERNAME, "root");
		map.put(DBContextHolder.DATASOURCE_PASSWORD, "1234");
		DBContextHolder.setDBType(map);
	    
		
		List<User> list = ud.selectList(null);
		for (User user : list) {
			System.out.println(user);
		}
	}


}
