package com.xiaoqiang.shardingjdbc.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.mysql.jdbc.Driver;
import com.xiaoqiang.shardingjdbc.common.ModuleDatabaseShardingAlgorithm;
import com.xiaoqiang.shardingjdbc.common.ModuleTableShardingAlgorithm;
import com.xiaoqiang.shardingjdbc.common.ModuleTableStringKeyShardingAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {


    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }



    private DataSource buildDataSource() throws SQLException {
        //设置分库映射
        Map<String,DataSource> dataSourceMap = new HashMap<>(2);
//        dataSourceMap.put("db0", createDataSource("db0"));
        dataSourceMap.put("demo0", createDataSource("demo0"));
        //设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);
        //如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
//        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "db0");
        //设置分表映射，将t_order_0和t_order_1两个实际的表映射到t_order逻辑表
        //0和1两个表是真实的表，t_order是个虚拟不存在的表，只是供使用。如查询所有数据就是select
        TableRule orderTableRule = TableRule.builder("t_order")
//                .actualTables(Arrays.asList("t_order2020-01","t_order2020-02"))
                .actualTables(Arrays.asList("t_order202001","t_order202002"))
                .dataSourceRule(dataSourceRule)
                .build();
        //具体分库分表策略，按什么规则来分
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(orderTableRule))
//                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id",new ModuleDatabaseShardingAlgorithm()))
//                .tableShardingStrategy(new TableShardingStrategy("order_id",new ModuleTableShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("period",new ModuleTableStringKeyShardingAlgorithm()))
                .build();
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
        return dataSource;
    }


    private static DataSource createDataSource(final String dataSourceName) {
        //使用druid连接数据库
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(Driver.class.getName());
        result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        result.setUsername("root");
        result.setPassword("root");
        return result;
    }


    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }

}
