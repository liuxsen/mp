package com.liuxsen.mp.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuxsen.mp.domain.po.area.City;
import com.liuxsen.mp.domain.po.area.Province;
import com.liuxsen.mp.mapper.area.CityMapper;
import com.liuxsen.mp.mapper.area.ProvinceMapper;
import com.liuxsen.mp.service.area.CityService;
import com.liuxsen.mp.service.area.ProvinceService;
import org.springframework.stereotype.Service;

/**
 * @author liuxsen 2023/12/21
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {
}
