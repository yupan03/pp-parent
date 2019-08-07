package com.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class AutoCode {
	private static String url = "jdbc:mysql://localhost:3306/project?characterEncoding=utf8&useSSL=false&verifyServerCertificate=false&serverTimezone=UTC";
	private static String driverName = "com.mysql.cj.jdbc.Driver";
	private static String username = "root";
	private static String password = "123456";
	private static String basePackage = "com.project";
	// 需要生成代码的数据库表
	private static String[] includeTables = new String[] { "user" };
	// 不需要生成代码的数据库表
	private static String[] excludeTables = new String[] {};

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");// 输出文件路径
		gc.setOpen(false);
		gc.setFileOverride(true); // 文件是否覆盖
		gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("David");// 作者
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		gc.setSwagger2(true);

		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setUrl(url);
		dsc.setDriverName(driverName);
		dsc.setUsername(username);
		dsc.setPassword(password);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(basePackage);
		pc.setController("controller");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setMapper("dao");
		pc.setEntity("entity.table");
		pc.setXml(null);
		mpg.setPackageInfo(pc);
		mpg.setTemplate(new TemplateConfig().setXml(null));// 关闭默认 xml 生成，调整生成 至 根目录

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
						+ StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);// 列名生成策略
		strategy.setInclude(includeTables); // 需要生成的表
		strategy.setExclude(excludeTables);
		strategy.setEntityLombokModel(true);// 是否系列化类
		strategy.setRestControllerStyle(true);
		strategy.setSuperControllerClass("com.project.controller.BaseController");
		strategy.setControllerMappingHyphenStyle(true);

		mpg.setStrategy(strategy);

		mpg.execute();
	}

}