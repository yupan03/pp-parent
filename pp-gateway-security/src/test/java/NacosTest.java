import java.util.Properties;
import java.util.concurrent.Executor;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

public class NacosTest {

    public static void main(String[] args) {
        String dataId = "gateway-rounts";

        String group = "DEFAULT_GROUP";

        String serverAddr = "localhost";
        try {
            Properties properties = new Properties();

            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);

            ConfigService configService = NacosFactory.createConfigService(properties);

            System.out.println(configService.getConfig(dataId, group, 5000));

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