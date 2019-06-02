package com.briup.crm.dao.mappleInterface;

import com.briup.crm.common.bean.SalPlan;
import com.briup.crm.common.bean.SalPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SalPlanMapper {
    int countByExample(SalPlanExample example);

    int deleteByExample(SalPlanExample example);

    int deleteByPrimaryKey(Long plaId);

    int insert(SalPlan record);

    int insertSelective(SalPlan record);

    List<SalPlan> selectByExample(SalPlanExample example);

    SalPlan selectByPrimaryKey(Long plaId);

    int updateByExampleSelective(@Param("record") SalPlan record, @Param("example") SalPlanExample example);

    int updateByExample(@Param("record") SalPlan record, @Param("example") SalPlanExample example);

    int updateByPrimaryKeySelective(SalPlan record);

    int updateByPrimaryKey(SalPlan record);
}