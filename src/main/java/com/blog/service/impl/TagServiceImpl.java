package com.blog.service.impl;

import com.blog.entity.Tag;
import com.blog.mapper.TagDao;
import com.blog.service.TagService;
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
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements TagService {

}
