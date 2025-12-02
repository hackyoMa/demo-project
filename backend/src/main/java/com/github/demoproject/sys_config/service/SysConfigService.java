package com.github.demoproject.sys_config.service;

import com.github.demoproject.common.HttpException;
import com.github.demoproject.constant.SysConfigKey;
import com.github.demoproject.sys_config.entity.SysConfig;
import com.github.demoproject.sys_config.repository.SysConfigRepository;
import com.github.demoproject.util.I18n;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * SysConfigService
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Service
@RequiredArgsConstructor
public class SysConfigService {

    private final SysConfigRepository sysConfigRepository;

    public SysConfig get(SysConfigKey configKey) {
        return sysConfigRepository.findFirstByConfigKey(configKey.name()).orElseThrow();
    }

    public SysConfig update(SysConfig sysConfig) {
        if (!StringUtils.hasLength(sysConfig.getConfigKey())) {
            throw new HttpException(I18n.get("configKeyNotExist"));
        }
        if (!StringUtils.hasLength(sysConfig.getConfigValue())) {
            throw new HttpException(I18n.get("configValueCannotNull"));
        }
        SysConfigKey sysConfigKey = SysConfigKey.valueOf(sysConfig.getConfigKey());
        SysConfig oldSysConfig = get(sysConfigKey);
        oldSysConfig.setConfigValue(sysConfig.getConfigValue());
        return sysConfigRepository.save(oldSysConfig);
    }

}
