package com.alfi.mowItNow.config;

import com.alfi.mowItNow.batchprocessing.MowerItemProcessor;
import com.alfi.mowItNow.batchprocessing.MowerItemWriter;
import com.alfi.mowItNow.model.Mower;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job mowerJob(JobRepository jobRepository, Step mowerStep) {
        return new JobBuilder("mowerJob", jobRepository)
                .start(mowerStep)
                .build();
    }

    @Bean
    public Step mowerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                          FlatFileItemReader<String> itemReader,
                          ItemProcessor<String, List<Mower>> processor,
                          ItemWriter<List<Mower>> writer) {
        return new StepBuilder("mowerStep", jobRepository)
                .<String, List<Mower>>chunk(1, transactionManager)
                .reader(itemReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemProcessor<String, List<Mower>> itemProcessor() {
        return new MowerItemProcessor();
    }

    @Bean
    public MowerItemWriter itemWriter() {
        return new MowerItemWriter();
    }

}
