/**
 * Copyright (c) 2012-2024 https://www.eryansky.com
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.eryansky.modules.notice.dao;

import com.eryansky.common.orm._enum.StatusState;
import com.eryansky.common.orm.model.Parameter;
import com.eryansky.common.orm.mybatis.MyBatisDao;
import com.eryansky.common.orm.persistence.CrudDao;
import com.eryansky.modules.notice._enum.MessageMode;
import com.eryansky.modules.notice.mapper.MessageReceive;

import java.util.List;
import java.util.Map;


/**
 * @author Eryan
 * @date 2016-03-14
 */
@MyBatisDao
public interface MessageReceiveDao extends CrudDao<MessageReceive> {
    /**
     * 用户消息
     *
     * @param parameter status {@link StatusState}
     *                  mode {@link MessageMode}
     *                  userId 用户ID
     * @return
     */
    List<MessageReceive> findUserList(Parameter parameter);

    List<MessageReceive> findByMessageId(Parameter parameter);

    List<MessageReceive> findBySenderUserId(Parameter parameter);

    MessageReceive getUserMessageReceiveByMessageId(Parameter parameter);

    int updateByUserIdAndMessageId(MessageReceive messageReceive);

    int updateById(MessageReceive messageReceive);

    int setUserMessageRead(Parameter parameter);

    int deleteByMessageId(Parameter parameter);
}

