package com.liuxsen.mp;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.liuxsen.mp.domain.po.area.*;
import com.liuxsen.mp.service.area.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.net.URL;
import java.util.List;

/**
 * @author liuxsen 2023/12/21
 */
@SpringBootTest(args = "--mpw.key=5bb0ec6afbff6dc9")
public class InsertAreaDataTest {
    @Resource
    ProvinceService provinceService;
    @Resource
    CityService cityService;
    @Resource
    AreaService areaService;
    @Resource
    VillageService villageService;
    @Resource
    StreetService streetService;

    @Test
    void initProvince(){
        List<Province> provinces = this.getProvince();
        boolean b = this.provinceService.saveBatch(provinces);
        System.out.println(b);
    }

    @Test
    void initCity(){
        List<City> citys = this.getCity();
        this.cityService.saveBatch(citys);
    }

    @Test
    void  initArea(){
        List<Area> area = this.getArea();
        this.areaService.saveBatch(area);
    }

//    乡镇
    @Test
    void initStreet(){
        List<Street> streets = this.getStreet();
        this.streetService.saveBatch(streets);
    }

//    村
    @Test
    void initVillage(){
        List<Village> villages = this.getVillages();
        this.villageService.saveBatch(villages);
    }


    private String readFile(String resourcePath){
        URL resource = this.getClass().getClassLoader().getResource(resourcePath);
        if (resource != null) {
            String path = resource.getPath();
            System.out.println(path);
            FileReader fileReader = new FileReader(path);
            return fileReader.readString();
        }
        return "";
    }

    private List<Province> getProvince(){
        String json = this.readFile("area/provinces.json");
        JSONArray array = JSONUtil.parseArray(json);
        List<Province> list = JSONUtil.toList(array, Province.class);
        return list;
    }

    private List<City> getCity(){
        String json = this.readFile("area/cities.json");
        JSONArray array = JSONUtil.parseArray(json);
        List<City> list = JSONUtil.toList(array, City.class);
        return list;
    }

    private List<Area> getArea(){
        String json = this.readFile("area/areas.json");
        JSONArray array = JSONUtil.parseArray(json);
        List<Area> list = JSONUtil.toList(array, Area.class);
        return list;
    }

    private List<Street> getStreet(){
        String json = this.readFile("area/streets.json");
        JSONArray array = JSONUtil.parseArray(json);
        List<Street> list = JSONUtil.toList(array, Street.class);
        return list;
    }

    private List<Village> getVillages(){
        String json = this.readFile("area/villages.json");
        JSONArray array = JSONUtil.parseArray(json);
        List<Village> list = JSONUtil.toList(array, Village.class);
        return list;
    }


}























