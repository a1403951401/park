package com.example.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MybatisPlusGenerator {

    public static void main(final String[] args) throws IOException {

        // 全局配置
        final GlobalConfig config = new GlobalConfig();
        config.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        config.setFileOverride(true);
        config.setBaseResultMap(true);
        config.setBaseColumnList(true);
        config.setAuthor("N.E");
        config.setEntityName("%sDO");
        config.setServiceName("I%sService");
        config.setServiceImplName("%sServiceImpl");

        // 数据源配置
        Properties props = new Properties();
        props.load(ClassLoader.getSystemResourceAsStream("db.properties"));
        final DataSourceConfig dataConfig = new DataSourceConfig();
        dataConfig.setDriverName((String) props.get("spring.datasource.driver-class-name"));
        dataConfig.setUrl((String) props.get("spring.datasource.url"));
        dataConfig.setUsername((String) props.get("spring.datasource.username"));
        dataConfig.setPassword((String) props.get("spring.datasource.password"));

        // 策略配置
        final StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setInclude(new String[]{"test"}); // 需要生成的表
        strategy.setRestControllerStyle(true);
        final List<TableFill> tableFillList = new ArrayList<>();
        strategy.setTableFillList(tableFillList);

        final PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.example.park");
        packageConfig.setEntity("repository.mysql.entity");
        packageConfig.setMapper("repository.mysql.dao");
        packageConfig.setXml("repository.mappers");
        packageConfig.setService("biz");
        packageConfig.setServiceImpl("biz.impl");
        packageConfig.setController("controller");

        final AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config);
        autoGenerator.setDataSource(dataConfig);
        autoGenerator.setStrategy(strategy);
        autoGenerator.setPackageInfo(packageConfig);

        autoGenerator.execute();
    }
}
