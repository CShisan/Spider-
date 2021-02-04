package com.shisan.spider.utils;

import com.shisan.spider.dto.IndustryDTO;
import com.shisan.spider.service.IndustryService;
import com.shisan.spider.service.impl.IndustryServiceImpl;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Component
public class MagicToolkit implements PageProcessor {
    private IndustryService industryService;
    private List<String> templates;
    private Site site;
    private Integer id = 1;

    public MagicToolkit(IndustryService industryService) {
        init(industryService);
    }

    private void addTemplate() {
        // 按照此模版顺序爬取
        templates.add("div.job-menu div.menu-sub");
        templates.add("p");
        templates.add("li");
        templates.add("h4");
        templates.add("div a");
    }

    @Override
    public void process(Page page) {
        // 解析页面
        Integer num = 0;
        List<Selectable> list = page.getHtml().css(templates.get(num++)).nodes();
        process(list, 0, num, 0);
        page.putField("show", page.getHtml());
    }

    public void process(List<Selectable> list, Integer deep, Integer num, Integer pid) {
        Integer deepMax = 2;
        String name = "";
        for (Selectable item : list) {
            if (deep < deepMax) {
                name = item.css(templates.get(num), "text").toString();
                this.setIndustryInfo(id++, name, deep, pid);
                List<Selectable> temp = item.css(templates.get(num + 1)).nodes();
                this.process(temp, deep + 1, num + 2, id);

            } else {
                name = Jsoup.parse(item.toString()).text();
                this.setIndustryInfo(id++, name, deep, pid);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }


    private void init(IndustryService industryService) {
        this.industryService = industryService;
        this.site = Site.me();
        templates = new ArrayList<String>();
        this.addTemplate();
    }

    private void setIndustryInfo(Integer id, String name, Integer deep, Integer pid) {
        String time = String.valueOf(System.currentTimeMillis());
        IndustryDTO dto = new IndustryDTO();
        dto.setId(id);
        dto.setIndustryName(name);
        dto.setLevel(deep);
        dto.setPid(pid);
        dto.setCreateTime(time);
        dto.setUpdateTime(time);
        industryService.save(dto);
    }
}
