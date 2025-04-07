package com.ahui.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName 菜单节点DTO$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@Getter
@Setter
public class MenuNodeDTO {
    private Long id;
    private String name;
    private String path;      // 前端路由路径（如 "/user"）
    private String icon;      // 图标
    private Integer orderNum; // 排序号
    private List<MenuNodeDTO> children; // 子菜单
}
