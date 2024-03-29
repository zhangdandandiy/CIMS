package com.ruoyi.workflow.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfoFlowable;
import com.ruoyi.flowable.core.domain.ProcessQuery;
import com.ruoyi.workflow.domain.vo.WfDeployVo;

import java.util.List;

/**
 * @author KonBAI
 * @createTime 2022/6/30 9:03
 */
public interface IWfDeployService {

    TableDataInfoFlowable<WfDeployVo> queryPageList(ProcessQuery processQuery, PageQuery pageQuery);

    TableDataInfoFlowable<WfDeployVo> queryPublishList(String processKey, PageQuery pageQuery);

    void updateState(String definitionId, String stateCode);

    String queryBpmnXmlById(String definitionId);

    void deleteByIds(List<String> deployIds);
}
