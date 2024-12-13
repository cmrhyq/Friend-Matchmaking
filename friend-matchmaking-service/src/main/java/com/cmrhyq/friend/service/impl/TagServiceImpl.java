package com.cmrhyq.friend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmrhyq.friend.service.TagService;
import com.cmrhyq.friend.model.dto.tag.Tag;
import com.cmrhyq.friend.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author AlanHuang
* @description 针对表【tag(标签表)】的数据库操作Service实现
* @createDate 2024-12-13 16:02:57
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




