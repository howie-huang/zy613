package com.exam.zy613.util;

import lombok.Data;

import java.util.List;

/**
 * layUI使用的数据
 * @author 31515
 */
@Data
public class LayUIData {
    private int code;
    private String message;
    private Integer count;
    private List<?> data;
}
