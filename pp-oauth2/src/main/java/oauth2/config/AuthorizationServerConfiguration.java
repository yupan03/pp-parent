package oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import oauth2.service.ClientDetailsServiceImpl;

/**
 * 开放平台网关只进行客户端模式认证
 * 
 * @author David
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

    /**
     * 配置客户端详情服务 客户端详细信息在这里进行初始化， 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     * 
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /* 配置token获取合验证时的策略 */
        security.tokenKeyAccess("permitAll()")

                .checkTokenAccess("isAuthenticated()")

                .allowFormAuthenticationForClients()

//                .accessDeniedHandler(null)

        ;
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.pathMapping("/oauth/token", "/auth/token");// 令牌端点
//        endpoints.pathMapping("/oauth/authorize", "/auth/token");// 授权端点
//        endpoints.pathMapping("/oauth/confirm_access", "/auth/token");// 用户确认授权提交端点
//        endpoints.pathMapping("/oauth/error", "/auth/token");// 授权服务错误信息端点
//        endpoints.pathMapping("/oauth/check_token", "/auth/token");// 用于资源服务访问的令牌解析端点
//        endpoints.pathMapping("/oauth/token_key", "/auth/token");// 提供公有密匙的端点，如果你使用JWT令牌的话。
        super.configure(endpoints);
    }
}