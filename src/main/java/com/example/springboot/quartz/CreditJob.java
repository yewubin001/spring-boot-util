package com.example.springboot.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

/**
 * @author yewub
 * @describe 调度处理个人征信过期
 * @create 2018-10-26 12:28
 */
public class CreditJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(CreditJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LOG.info("定时任务开始--{}", Instant.now());
        //todo

        LOG.info("定时任务结束--{}", Instant.now());
    }

}
