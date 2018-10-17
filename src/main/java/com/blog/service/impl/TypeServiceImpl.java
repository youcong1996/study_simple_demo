package com.blog.service.impl;

import com.blog.entity.Type;
import com.blog.mapper.TypeDao;
import com.blog.service.TypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youcong123
 * @since 2018-07-22
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeDao, Type> implements TypeService {

}
