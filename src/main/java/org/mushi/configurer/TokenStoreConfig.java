package org.mushi.configurer;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**
 * token 存储方式配置,支持jdbc，内存、jwt扩展
 *
 **/
@Configuration
public class TokenStoreConfig {

    /**
     * 前缀
     */
    private final static String OLYM_PREFIX = "olym_";

    /**
     * oauth 相关前缀
     */
    private final static String OAUTH_PREFIX = "oauth:";

    private RedisConnectionFactory factory;


    @Autowired(required = false)
    public TokenStoreConfig(RedisConnectionFactory factory) {
        this.factory = factory;
    }

    @Bean
    public TokenStore tokenStore() throws Exception {
        TokenStore store = null;
        if (factory == null) {
            throw new BeanCreationException("配置Redis存储Token需要redisConnectionFactory bean，未找到");
        }
        store = new RedisTokenStore(factory);
        ((RedisTokenStore) store).setPrefix(OLYM_PREFIX+OAUTH_PREFIX);
        return store;
    }

}
