package com.exam.zy613.util;

import lombok.Data;

import java.util.List;

/**
 * 树实体
 * @author 31515
 */
@Data
public class LayUiTree {
    private String title;
    private int id;
    private String filed;
    private boolean checked;
    private boolean spread;
    private String url;
    private List<LayUiTree> children;
}
