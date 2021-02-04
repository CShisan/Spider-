package com.shisan.spider.mapper;

import com.shisan.spider.entity.Industry;
import com.shisan.spider.vo.IndustryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface IndustryMapper {

    /**
     * 行业信息
     * @return list
     */
    List<IndustryVO> findAll();

    /**
     * 保存
     * @param industry 行业实体
     * @return 状态
     */
    int save(Industry industry);
}
