package com.liuxsen.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuxsen.mp.domain.po.Address;
import com.liuxsen.mp.mapper.AddressMapper;
import com.liuxsen.mp.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * @author liuxsen 2023/12/19
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
}
