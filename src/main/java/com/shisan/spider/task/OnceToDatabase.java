package com.shisan.spider.task;

import com.shisan.spider.config.SpiderConfig;
import com.shisan.spider.service.IndustryService;
import com.shisan.spider.utils.CustomizePipeline;
import com.shisan.spider.utils.MagicToolkit;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;


/**
 * @author Administrator
 */
@Component        //该注解表明该类被Spring容器管理
@Order(1)        //如果有多个自定义的ApplicationRunner，该注解用来表明执行的顺序
public class OnceToDatabase implements ApplicationRunner {
    @Resource
    private SpiderConfig spiderConfig;
    @Resource
    private IndustryService industryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MagicToolkit magicToolkit = new MagicToolkit(industryService);
        magicToolkit.setSite(Site.me()
                // 设置编码
                .setCharset(spiderConfig.getCharset())
                // 设置超时时间
                .setTimeOut(10 * 1000)
                // 设置重试间隔时间
                .setRetrySleepTime(3 * 1000)
                // 设置重试次数
                .setSleepTime(3)
                // 设置Cookie
                .addCookie(spiderConfig.getTokenName(), spiderConfig.getToken())
        );
        Spider.create(magicToolkit)
                .addUrl(spiderConfig.getUrl())
                .thread(spiderConfig.getThread())
                .run();
    }
}
