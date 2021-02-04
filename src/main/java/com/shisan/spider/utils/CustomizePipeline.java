package com.shisan.spider.utils;


import com.shisan.spider.dto.IndustryDTO;
import com.shisan.spider.service.IndustryService;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@Component
public class CustomizePipeline implements Pipeline {
    @Resource
    IndustryService industryService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        IndustryDTO dto = resultItems.get("dto");
        if(dto != null){
            industryService.save(dto);
        }
    }
}
