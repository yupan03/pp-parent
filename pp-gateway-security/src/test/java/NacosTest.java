import java.util.Properties;
import java.util.concurrent.Executor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { NacosTest.class })
public class NacosTest {
    private String dataId = "gateway-rounts";

    private String group = "DEFAULT_GROUP";

    private String serverAddr = "localhost:8848";

    @Test
    public void test1() {
        try {
            Properties properties = new Properties();

            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);

            ConfigService configService = NacosFactory.createConfigService(properties);

            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println(configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}