package com.blog.service.impl;

import com.blog.entity.Post;
import com.blog.mapper.PostDao;
import com.blog.service.PostService;
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
public class PostServiceImpl extends ServiceImpl<PostDao, Post> implements PostService {

}
