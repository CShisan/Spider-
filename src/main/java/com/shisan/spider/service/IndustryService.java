package com.shisan.spider.service;

import com.shisan.spider.dto.IndustryDTO;
import com.shisan.spider.vo.IndustryVO;

import java.util.List;

/**
 * @author Administrator
 */

public interface IndustryService {

    /**
     * 查找
     * @return list
     */
    List<IndustryVO> findAll();

    /**
     * 保存
     * @param dto 行业dto
     * @return 状态
     */
    int save(IndustryDTO dto);


}
