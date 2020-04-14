package com.jxk.sqmy.config.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jxk.sqmy.realm.UserRealm;

@Configuration
public class ShiroConfig {
	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager Filter Chain定义说明
	 * 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过 3、部分过滤器可指定参数，如perms，roles
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//配置静态资源允许访问
		filterChainDefinitionMap.put("/frontend/denglu", "anon");
		filterChainDefinitionMap.put("/frontend/zhuce", "anon");
		filterChainDefinitionMap.put("/frontend/getjob", "anon");
		filterChainDefinitionMap.put("/frontend/register", "anon");
		filterChainDefinitionMap.put("/backend/**","roles[admin]");
// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/frontend/**", "authc");
// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/frontend/login");
// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		Map<String, Filter> filters = new HashMap<String, Filter>();
		shiroFilterFactoryBean.setFilters(filters);
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}

	// 开启Controller中的shiro注解
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	/**
	 * 配置org.apache.shiro.web.session.mgt.DefaultWebSessionManager
	 * 
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager getDefaultWebSessionManager() {
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		defaultWebSessionManager.setSessionDAO(getMemorySessionDAO());
		defaultWebSessionManager.setGlobalSessionTimeout(4200000);
		defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
		defaultWebSessionManager.setSessionIdCookieEnabled(true);
		defaultWebSessionManager.setSessionIdCookie(getSimpleCookie());
		return defaultWebSessionManager;
	}

	/**
	 * 配置org.apache.shiro.session.mgt.eis.MemorySessionDAO
	 * 
	 * @return
	 */
	@Bean
	public MemorySessionDAO getMemorySessionDAO() {
		MemorySessionDAO memorySessionDAO = new MemorySessionDAO();
		memorySessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator());
		return memorySessionDAO;
	}

	@Bean
	public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}

	/**
	 * session自定义cookie名
	 * 
	 * @return
	 */
	@Bean
	public SimpleCookie getSimpleCookie() {
		SimpleCookie simpleCookie = new SimpleCookie();
		simpleCookie.setName("security.session.id");
		simpleCookie.setPath("/");
		return simpleCookie;
	}

	@Bean
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(userRealm);
		// <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		dwsm.setCacheManager(getEhCacheManager());
		dwsm.setSessionManager(getDefaultWebSessionManager());
		return dwsm;
	}

	@Bean
	public UserRealm userRealm(EhCacheManager cacheManager,@Qualifier("credentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher ) {
		UserRealm userRealm = new UserRealm();
		userRealm.setAuthorizationCachingEnabled(false);
		userRealm.setCachingEnabled(true);
		userRealm.setAuthenticationCachingEnabled(true);
		//缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
		userRealm.setAuthenticationCacheName("authenticationCache");
		//启用授权缓存，即缓存AuthorizationInfo信息，默认false
		userRealm.setAuthorizationCachingEnabled(true);
		//缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
		userRealm.setAuthorizationCacheName("authorizationCache");
		// 告诉realm,使用credentialsMatcher加密算法类来验证密文
		userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		userRealm.setCacheManager(cacheManager);
		return userRealm;
	}

	/**
	 * 开启shrio注解支持
	 * 
	 * @param userRealm
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(UserRealm userRealm) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(getDefaultWebSecurityManager(userRealm));
		return aasa;
	}
	/**
	 * 加密配置
	 * @return
	 */
	@Bean(name = "credentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		// 散列的次数，比如散列两次，相当于 md5(md5(""));
		hashedCredentialsMatcher.setHashIterations(1024);
		// storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}
}
