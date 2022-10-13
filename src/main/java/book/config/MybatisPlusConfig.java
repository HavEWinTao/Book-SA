package book.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangwei
 * @version 1.0.0
 * 2022/09/03 16:07
 * @description: TODO
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /**
         * overflow	boolean	false	溢出总页数后是否进行处理(默认不处理,参见 插件#continuePage 方法)
         * maxLimit	Long		单页分页条数限制(默认无限制,参见 插件#handlerLimit 方法)
         * dbType	DbType		数据库类型(根据类型获取应使用的分页方言,参见 插件#findIDialect 方法)
         * dialect	IDialect		方言实现类(参见 插件#findIDialect 方法)
         */
        // interceptor.setDbType();
        // interceptor.setDbType();
        // interceptor.setDbType();
        // interceptor.setDbType();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
