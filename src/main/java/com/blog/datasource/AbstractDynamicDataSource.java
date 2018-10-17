package com.blog.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源父类
 * @create ll
 * @update 
 * @updateDate 
 */
public abstract class AbstractDynamicDataSource<T extends DataSource> extends AbstractRoutingDataSource
                                                                                                implements
                                                                                                ApplicationContextAware {

    /** 日志 */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    /** 默认的数据源KEY */
    protected static final String DEFAULT_DATASOURCE_KEY = "defaultDataSource";

    /** 数据源KEY-VALUE键值对 */
    public Map<Object, Object> targetDataSources;

    /** spring容器上下文 */
    private static ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public static Object getBean(String name) {
        return ctx.getBean(name);
    }

    /**
     * @param targetDataSources the targetDataSources to set
     */
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.targetDataSources = targetDataSources;
        super.setTargetDataSources(targetDataSources);
        // afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
        super.afterPropertiesSet();
    }

    /**
     * 创建数据源
     * @param driverClassName 数据库驱动名称
     * @param url 连接地址
     * @param username 用户名
     * @param password 密码
     * @return 数据源{@link T}
     * @Author : ll. create at 2017年3月27日 下午2:44:34
     */
    public abstract T createDataSource(String driverClassName, String url, String username,
                                       String password);

    /**
     * 设置系统当前使用的数据源
     * <p>数据源为空或者为0时，自动切换至默认数据源，即在配置文件中定义的默认数据源
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("【设置系统当前使用的数据源】");
        Map<String, Object> configMap = DBContextHolder.getDBType();
        logger.info("【当前数据源配置为：{}】", configMap);
        if (MapUtils.isEmpty(configMap)) {
            // 使用默认数据源
            return DEFAULT_DATASOURCE_KEY;
        }
        // 判断数据源是否需要初始化
        this.verifyAndInitDataSource();
        logger.info("【切换至数据源：{}】", configMap);
        return configMap.get(DBContextHolder.DATASOURCE_KEY);
    }

    /**
     * 判断数据源是否需要初始化
     * @Author : ll. create at 2017年3月27日 下午3:57:43
     */
    private void verifyAndInitDataSource() {
        Map<String, Object> configMap = DBContextHolder.getDBType();
        Object obj = this.targetDataSources.get(configMap.get(DBContextHolder.DATASOURCE_KEY));
        if (obj != null) {
            return;
        }
        logger.info("【初始化数据源】");
        T datasource = this.createDataSource(configMap.get(DBContextHolder.DATASOURCE_DRIVER)
            .toString(), configMap.get(DBContextHolder.DATASOURCE_URL).toString(),
            configMap.get(DBContextHolder.DATASOURCE_USERNAME).toString(),
            configMap.get(DBContextHolder.DATASOURCE_PASSWORD).toString());
        this.addTargetDataSource(configMap.get(DBContextHolder.DATASOURCE_KEY).toString(),
            datasource);
    }

    /**
     * 往数据源key-value键值对集合添加新的数据源
     * @param key 新的数据源键
     * @param dataSource 新的数据源
     * @Author : ll. create at 2017年3月27日 下午2:56:49
     */
    private void addTargetDataSource(String key, T dataSource) {
        this.targetDataSources.put(key, dataSource);
        super.setTargetDataSources(this.targetDataSources);
        // afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
        super.afterPropertiesSet();
    }

}