package com.liuxsen.mp.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuxsen.mp.domain.po.area.Street;
import com.liuxsen.mp.domain.po.area.Village;
import com.liuxsen.mp.mapper.area.StreetMapper;
import com.liuxsen.mp.mapper.area.VillageMapper;
import com.liuxsen.mp.service.area.StreetService;
import com.liuxsen.mp.service.area.VillageService;
import org.springframework.stereotype.Service;

/**
 * @author liuxsen 2023/12/21
 */
@Service
public class VillageServiceImpl extends ServiceImpl<VillageMapper, Village> implements VillageService {
}
