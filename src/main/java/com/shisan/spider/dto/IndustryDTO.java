package com.shisan.spider.dto;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class IndustryDTO {
    private Integer id;
    private String industryName;
    private Integer level;
    private Integer pid;
    private String createTime;
    private String updateTime;
}
