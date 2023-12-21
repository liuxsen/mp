package com.liuxsen.mp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuxsen.mp.domain.po.area.*;
import com.liuxsen.mp.service.area.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuxsen 2023/12/21
 */

@Api(value = "areaController", tags = "地区请求")
@RestController
@RequestMapping("area")
public class AreaController {
    @Resource
    ProvinceService provinceService;
    @Resource
    CityService cityService;
    @Resource
    AreaService areaService;
    @Resource
    StreetService streetService;
    @Resource
    VillageService villageService;

    @ApiOperation("请求省份数据")
    @GetMapping("province")
    List<Province> getProvince(){
        return this.provinceService.list();
    }

    @ApiOperation("获取市数据")
    @GetMapping("province/{provinceCode}/city")
    List<City> getCitys(@PathVariable("provinceCode") String provinceCode){
        LambdaQueryWrapper<City> wrapper = new LambdaQueryWrapper<City>()
                .eq(City::getProvinceCode, provinceCode);
        return this.cityService.list(wrapper);
    }

    @ApiOperation("获取县数据")
    @GetMapping("city/{cityCode}/area")
    List<Area> getArea(@PathVariable("cityCode") String cityCode){
        LambdaQueryWrapper<Area> wrapper = new LambdaQueryWrapper<Area>()
                .eq(Area::getCityCode, cityCode)
                ;
        return this.areaService.list(wrapper);
    }

    @ApiOperation("获取乡镇街道数据")
    @GetMapping("area/{areaCode}/street")
    List<Street> getStreet(@PathVariable("areaCode") String areaCode){
        LambdaQueryWrapper<Street> wrapper = new LambdaQueryWrapper<Street>()
                .eq(Street::getAreaCode, areaCode)
                ;
        return this.streetService.list(wrapper);
    }

    @ApiOperation("获取村数据")
    @GetMapping("street/{streetCode}/village")
    List<Village> getVillage(@PathVariable("streetCode") String streetCode){
        LambdaQueryWrapper<Village> wrapper = new LambdaQueryWrapper<Village>()
                .eq(Village::getStreetCode, streetCode)
                ;
        return this.villageService.list(wrapper);
    }
}
