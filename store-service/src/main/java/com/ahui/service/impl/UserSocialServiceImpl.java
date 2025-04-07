package com.ahui.service.impl;

import com.ahui.service.UserSocialService;
import com.ahui.store.entity.UserSocial;
import com.ahui.store.mapper.UserSocialMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第三方用户关联表 服务实现类
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
@Service
public class UserSocialServiceImpl extends ServiceImpl<UserSocialMapper, UserSocial> implements UserSocialService {

}
