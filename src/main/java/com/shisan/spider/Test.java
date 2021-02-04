package com.shisan.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class Test implements PageProcessor {

    /**
     * 解析页面
     *
     * @param page page
     */
    @Override
    public void process(Page page) {
        // 解析返回的数据page,并且把解析的结果放到ResultItems中

        // Css选择器                         css和$效果一样
        page.putField("div", page.getHtml().css("div.mt h2").all());
        page.putField("div2", page.getHtml().$("div.mt h2").all());

        // XPath
        page.putField("div3", page.getHtml().xpath("//div[@id=new_div]/ul/li/div/a"));

        // 正则表达式
        page.putField("div4", page.getHtml().css("div#news_div a").regex(".*省.*").all());

        // 处理结果API
        page.putField("div5", page.getHtml().css("div#news_div a").regex(".*省.*").get());
        page.putField("div6", page.getHtml().css("div#news_div a").regex(".*省.*").toString());

        // 获取连接
        page.addTargetRequest((Request) page.getHtml().css("div#news_div").links().regex(".*9$").all());
        page.putField("div7", page.getHtml().css("div.mt h1").all());
    }


    /**
     * 设置请求信息
     */
    private Site site = Site.me()
            // 设置编码
            .setCharset("utf8")
            // 设置超时时间
            .setTimeOut(10 * 1000)
            // 设置重试间隔时间
            .setRetrySleepTime(3 * 1000)
            // 设置重试次数
            .setSleepTime(3)
            // 设置Cookie
            .addCookie("c", "c");

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new Test())
                // 设置爬取地址
                .addUrl("https://www.jd.com/")
                // 设置输出位置 (默认控制台输出)
                .addPipeline(new FilePipeline("C:\\Users\\Administrator\\Desktop"))
                // 设置多线程
                .thread(5)
                // 执行
                .run();
    }
}
