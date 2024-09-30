package com.alfi.mowItNow.batchprocessing;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class MowerItemReader {
    @Bean
    public FlatFileItemReader<String> itemReader() {
        return new FlatFileItemReaderBuilder<String>()
                .name("itemReader")
                .resource(new ClassPathResource("input.txt"))
                .lineMapper((line, lineNumber) -> line)
                .build();
    }
}

