package com.shisan.spider.service.impl;

import com.shisan.spider.dto.IndustryDTO;
import com.shisan.spider.entity.Industry;
import com.shisan.spider.mapper.IndustryMapper;
import com.shisan.spider.service.IndustryService;
import com.shisan.spider.vo.IndustryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author Administrator
 */
@Service
public class IndustryServiceImpl implements IndustryService {
    @Resource
    private IndustryMapper industryMapper;

    @Override
    public List<IndustryVO> findAll() {
        return industryMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(IndustryDTO dto) {
        Industry industry = new Industry();
        BeanUtils.copyProperties(dto,industry);
        return industryMapper.save(industry);
    }
}
