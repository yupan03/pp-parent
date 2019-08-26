package config;

import java.util.List;
import java.util.function.Consumer;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author David
 */
public class ZookeeperContext {
    private static CuratorFramework client;

    private static Environment environment;

    public static void setEnvironment(Environment environment) {
        Assert.notNull(environment, "environment不能为null");
        ZookeeperContext.environment = environment;
        initZookeeperClient();
    }

    public synchronized static void initZookeeperClient() {
        if (client != null) {
            return;
        }
        setClient(createClient());
    }

    public static CuratorFramework createClient() {
        String zookeeperServerAddr = environment.getProperty("zookeeper.connect-string");
        if (StringUtils.isEmpty(zookeeperServerAddr)) {
            throw new RuntimeException("未指定spring.cloud.zookeeper.connect-string参数");
        }
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zookeeperServerAddr)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

        client.start();
        return client;
    }

    public static CuratorFramework getClient() {
        return client;
    }

    public static void setClient(CuratorFramework client) {
        ZookeeperContext.client = client;
    }

    /**
     * 新建或保存节点
     */
    public static String createOrUpdateData(String path, String data) throws Exception {
        return getClient().create()
                // 如果节点存在则Curator将会使用给出的数据设置这个节点的值
                .orSetData()
                // 如果指定节点的父节点不存在，则Curator将会自动级联创建父节点
                .creatingParentContainersIfNeeded().forPath(path, data.getBytes());
    }

    /**
     * 更新节点
     * 
     */
    public static void updatePath(String path, String data) throws Exception {
        getClient().setData().forPath(path, data.getBytes());
    }

    /**
     * 创建path，如果path存在不报错，静默返回path名称
     */
    public static String createPath(String path, String data) throws Exception {
        if (isPathExist(path)) {
            return path;
        }
        return getClient().create()
                // 如果指定节点的父节点不存在，则Curator将会自动级联创建父节点
                .creatingParentContainersIfNeeded().forPath(path, data.getBytes());
    }

    public static boolean isPathExist(String path) {
        try {
            return client.checkExists().forPath(path) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 监听一个节点
     */
    public static String listenPath(String path, Consumer<NodeCache> onChange) throws Exception {
        String ret = createOrUpdateData(path, "{}");
        final NodeCache cache = new NodeCache(client, path, false);
        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                onChange.accept(cache);
            }
        });
        cache.start();
        return ret;
    }

    /**
     * 获取子节点信息并监听子节点
     */
    public static void getChildrenAndListen(String parentPath, Consumer<List<ChildData>> listConsumer,
            PathChildrenCacheListener listener) throws Exception {
        // 为子节点添加watcher
        // PathChildrenCache: 监听数据节点的增删改，可以设置触发的事件
        PathChildrenCache childrenCache = new PathChildrenCache(client, parentPath, true);

        /**
         * StartMode: 初始化方式 POST_INITIALIZED_EVENT：异步初始化，初始化之后会触发事件 NORMAL：异步初始化
         * BUILD_INITIAL_CACHE：同步初始化
         */
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

        // 列出子节点数据列表，需要使用BUILD_INITIAL_CACHE同步初始化模式才能获得，异步是获取不到的
        List<ChildData> childDataList = childrenCache.getCurrentData();
        listConsumer.accept(childDataList);
        // 监听根节点下面的子节点
        childrenCache.getListenable().addListener(listener);
    }

    /**
     * 获取子节点信息
     */
    public static void getChildrenData(String parentPath, Consumer<List<ChildData>> listConsumer) throws Exception {
        // 为子节点添加watcher
        // PathChildrenCache: 监听数据节点的增删改，可以设置触发的事件
        PathChildrenCache childrenCache = new PathChildrenCache(client, parentPath, true);

        /**
         * StartMode: 初始化方式 POST_INITIALIZED_EVENT：异步初始化，初始化之后会触发事件 NORMAL：异步初始化
         * BUILD_INITIAL_CACHE：同步初始化
         */
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

        // 列出子节点数据列表，需要使用BUILD_INITIAL_CACHE同步初始化模式才能获得，异步是获取不到的
        List<ChildData> childDataList = childrenCache.getCurrentData();
        listConsumer.accept(childDataList);
        childrenCache.close();
    }

    /**
     * 监听子节点的增删改
     */
    public static void listenChildren(String parentPath, PathChildrenCacheListener listener) throws Exception {
        // 为子节点添加watcher
        // PathChildrenCache: 监听数据节点的增删改，可以设置触发的事件
        PathChildrenCache childrenCache = new PathChildrenCache(client, parentPath, true);

        /**
         * StartMode: 初始化方式 POST_INITIALIZED_EVENT：异步初始化，初始化之后会触发事件 NORMAL：异步初始化
         * BUILD_INITIAL_CACHE：同步初始化
         */
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        // 监听根节点下面的子节点
        childrenCache.getListenable().addListener(listener);
    }

}
