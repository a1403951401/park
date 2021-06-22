package com.example.park.biz.impl;

import com.example.park.repository.mysql.entity.CommentDO;
import com.example.park.repository.mysql.dao.CommentMapper;
import com.example.park.biz.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author N.E
 * @since 2021-06-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentDO> implements ICommentService {

}
