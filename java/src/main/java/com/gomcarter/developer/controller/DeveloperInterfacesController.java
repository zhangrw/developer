package com.gomcarter.developer.controller;

import com.gomcarter.developer.dto.InterfacesDetailDto;
import com.gomcarter.developer.dto.JavaDto;
import com.gomcarter.developer.entity.Interfaces;
import com.gomcarter.developer.entity.Java;
import com.gomcarter.developer.params.InterfacesQueryParam;
import com.gomcarter.developer.service.InterfacesService;
import com.gomcarter.developer.service.JavaService;
import com.gomcarter.frameworks.base.common.CollectionUtils;
import com.gomcarter.frameworks.base.common.CustomStringUtils;
import com.gomcarter.frameworks.base.pager.DefaultPager;
import com.gomcarter.frameworks.interfaces.annotation.Notes;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gomcarter
 */
@RestController
@RequestMapping("developer/interfaces")
public class DeveloperInterfacesController {

    @Resource
    InterfacesService interfacesService;

    @Resource
    JavaService javaService;

    @DeleteMapping(value = "{id}", name = "删除接口")
    void delete(@PathVariable("id") Long id) {
        this.interfacesService.delete(id);
    }

    @GetMapping(value = "simple/list", name = "获取接口地址列表")
    List<InterfacesDetailDto> simpleList(@Notes("查询参数") InterfacesQueryParam params, @Notes("分页器") DefaultPager pager) {
        Long id = CustomStringUtils.parseLong(params.getName());
        if (id != null) {
            params.setName(null)
                    .setId(id);
        }

        return interfacesService.list(params, pager);
    }

    @GetMapping(value = "list", name = "获取接口地址列表")
    List<InterfacesDetailDto> list(@Notes("查询参数") InterfacesQueryParam params, @Notes("分页器") DefaultPager pager) {
        return this.interfacesService.list(params, pager);
    }

    @GetMapping(value = "{id}", name = "获取接口详情")
    InterfacesDetailDto detail(@Notes("查询参数") @PathVariable Long id) {
        return this.interfacesService.detail(id);
    }

    @GetMapping(value = "count", name = "获取接口地址列表总数")
    Integer count(@Notes("查询参数") InterfacesQueryParam params) {
        return interfacesService.count(params);
    }
}