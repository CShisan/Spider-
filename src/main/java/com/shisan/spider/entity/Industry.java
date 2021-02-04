package com.shisan.spider.entity;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class Industry {
    private Integer id;
    private String industryName;
    private Integer level;
    private Integer pid;
    private String createTime;
    private String updateTime;
}
