package com.blog.service.impl;

import com.blog.entity.Comment;
import com.blog.mapper.CommentDao;
import com.blog.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

}
