package com.liuxsen.mp.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuxsen.mp.domain.po.area.Area;
import com.liuxsen.mp.domain.po.area.City;
import com.liuxsen.mp.mapper.area.AreaMapper;
import com.liuxsen.mp.mapper.area.CityMapper;
import com.liuxsen.mp.service.area.AreaService;
import com.liuxsen.mp.service.area.CityService;
import org.springframework.stereotype.Service;

/**
 * @author liuxsen 2023/12/21
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {
}
