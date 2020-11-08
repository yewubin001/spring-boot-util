package com.example.springboot.quartz;

import cn.magfin.quartz.QuartzSchedulerProperties;
import cn.magfin.quartz.QuartzUtils;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * @author yewub
 * @describe 定时任务 ScheduleConfig
 * @create 2018-10-26 11:00
 */
@Configuration
@EnableConfigurationProperties(QuartzSchedulerProperties.class)
@ConditionalOnClass(Scheduler.class)
public class ScheduleConfig {

    @Bean
    public JobDetailFactoryBean jobDetailCredit() {
        return QuartzUtils.createJobDetail(CreditJob.class, "执行定时任务");
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerCredit(JobDetail jobDetailCredit) {
        return QuartzUtils.createCronTrigger(jobDetailCredit, "0/5 * * * * ?");
    }
}
