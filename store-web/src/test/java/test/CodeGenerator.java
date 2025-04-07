package test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @ClassName $
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/store_sale_managerment?" +
                "useSSL=false&" +
                "allowPublicKeyRetrieval=true&" +  // MySQL 8.0+必需
                "serverTimezone=Asia/Shanghai&" +  // 必须明确时区
                "useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "peng1989127";
        String moduleName = "system";  // 模块名（如system、shop等）
        String[] tableNames = {"sys_permission", "sys_role", "sys_role_permission", "sys_user", "sys_user_role", "sys_user_social"}; // 要生成的表

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("ahui") // 作者名
                            .outputDir(System.getProperty("user.dir") + "/store-dao/src/main/java") // 输出目录
                            .disableOpenDir(); // 生成后不打开文件夹
                })
                .packageConfig(builder -> {
                    builder.parent("com.ahui.store") // 父包名
                            .moduleName(moduleName) // 模块包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir") + "/store-dao/src/main/resources/mapper/" + moduleName)); // XML位置
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames) // 要生成的表
                            .addTablePrefix("sys_") // 表前缀过滤
                            .entityBuilder()
                            .enableLombok() // 使用Lombok
                            .enableTableFieldAnnotation() // 字段注解
                            .mapperBuilder()
                            .enableMapperAnnotation() // @Mapper
                            .enableBaseResultMap() // 生成resultMap
                            .serviceBuilder()
                            .formatServiceFileName("%sService") // 服务接口名
                            .controllerBuilder()
                            .enableRestStyle(); // @RestController
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎
                .execute();
    }
}
