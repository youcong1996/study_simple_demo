package com.blog.datasource;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * Druid数据源
 * @update 
 * @updateDate 
 */
public class DruidDynamicDataSource extends AbstractDynamicDataSource<DruidDataSource> {

    private boolean testWhileIdle = true;
    private boolean testOnBorrow = false;
    private boolean testOnReturn = false;

    // 是否打开连接泄露自动检测
    private boolean removeAbandoned = false;
    // 连接长时间没有使用，被认为发生泄露时长
    private long removeAbandonedTimeoutMillis = 300 * 1000;
    // 发生泄露时是否需要输出 log，建议在开启连接泄露检测时开启，方便排错
    private boolean logAbandoned = false;

    // 只要maxPoolPreparedStatementPerConnectionSize>0,poolPreparedStatements就会被自动设定为true，使用oracle时可以设定此值。
    //    private int maxPoolPreparedStatementPerConnectionSize = -1;

    // 配置监控统计拦截的filters
    private String filters; // 监控统计："stat" 防SQL注入："wall" 组合使用： "stat,wall"
    private List<Filter> filterList;

    /*
     * 创建数据源
     * @see com.cdelabcare.pubservice.datasource.IDynamicDataSource#createDataSource(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public DruidDataSource createDataSource(String driverClassName, String url, String username,
                                            String password) {
        DruidDataSource parent = (DruidDataSource) super.getApplicationContext().getBean(
            DEFAULT_DATASOURCE_KEY);
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName);
        ds.setInitialSize(parent.getInitialSize());
        ds.setMinIdle(parent.getMinIdle());
        ds.setMaxActive(parent.getMaxActive());
        ds.setMaxWait(parent.getMaxWait());
        ds.setTimeBetweenConnectErrorMillis(parent.getTimeBetweenConnectErrorMillis());
        ds.setTimeBetweenEvictionRunsMillis(parent.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(parent.getMinEvictableIdleTimeMillis());

        ds.setValidationQuery(parent.getValidationQuery());
        ds.setTestWhileIdle(testWhileIdle);
        ds.setTestOnBorrow(testOnBorrow);
        ds.setTestOnReturn(testOnReturn);

        ds.setRemoveAbandoned(removeAbandoned);
        ds.setRemoveAbandonedTimeoutMillis(removeAbandonedTimeoutMillis);
        ds.setLogAbandoned(logAbandoned);

        // 只要maxPoolPreparedStatementPerConnectionSize>0,poolPreparedStatements就会被自动设定为true，参照druid的源码
        ds.setMaxPoolPreparedStatementPerConnectionSize(parent
            .getMaxPoolPreparedStatementPerConnectionSize());

        if (StringUtils.isNotBlank(filters))
            try {
                ds.setFilters(filters);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        addFilterList(ds);
        return ds;
    }

    private void addFilterList(DruidDataSource ds) {
        if (filterList != null) {
            List<Filter> targetList = ds.getProxyFilters();
            for (Filter add : filterList) {
                boolean found = false;
                for (Filter target : targetList) {
                    if (add.getClass().equals(target.getClass())) {
                        found = true;
                        break;
                    }
                }
                if (!found)
                    targetList.add(add);
            }
        }
    }
}