package config;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.core.env.Environment;

/**
 * @author David
 *
 */
public class ZookeeperBase {
    private static CuratorFramework client;

    private static Environment environment;
}