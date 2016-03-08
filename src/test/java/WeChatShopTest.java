import com.bfl.kernel.entity.Account;
import com.bfl.kernel.entity.User;
import com.bfl.kernel.service.AccountService;
import com.bfl.kernel.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by apple on 16/2/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class WeChatShopTest {
    private static Logger logger = LoggerFactory.getLogger(WeChatShopTest.class);

    @Resource
    private IUserService userService;

    @Resource
    private AccountService accountService;

    @Test
    public void test1(){
        String user = userService.getUserById(1);
        System.out.println("姓名:" + user);
    }

    @Test
    public void test2(){
        Account account = accountService.getAccount();
        System.out.println("账号是:" + account.getAcc_num()+"," + "密码是:" + account.getAcc_psd());
    }
}
