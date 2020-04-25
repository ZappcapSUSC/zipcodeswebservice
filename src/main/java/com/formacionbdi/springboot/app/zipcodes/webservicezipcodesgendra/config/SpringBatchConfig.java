package com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity.Zipcode;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	private static final String QUERY_INSERT_STUDENT = "INSERT INTO zipcodes(d_codigo, d_asenta, d_tipo_asenta, d_mnpio, d_estado, d_ciudad, d_CP, c_estado, c_oficina, c_tipo_asenta, c_mnpio, id_asenta_cpcons, d_zona, c_cve_ciudad) VALUES (:zCode, :d_asenta, :d_tipo_asenta, :d_mnpio, :d_estado, :d_ciudad, :d_CP, :c_estado, :c_oficina, :c_tipo_asenta, :c_mnpio, :id_asenta_cpcons, :d_zona, :c_cve_ciudad)";
	@Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
    
    private Resource inputTxt = new ClassPathResource("CPMexico.txt");
    
    @Autowired
    DataSource datasource;
    
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    
    @Bean
    public ItemReader<Zipcode> itemReader()
      throws UnexpectedInputException, ParseException {
    	
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        String[] tokens = { "zCode", 
        		"d_asenta", 
        		"d_tipo_asenta", 
        		"D_mnpio", 
        		"d_estado", 
        		"d_ciudad", 
        		"d_CP", 
        		"c_estado", 
        		"c_oficina", 
        		"c_CP",
        		"c_tipo_asenta", 
        		"c_mnpio", 
        		"id_asenta_cpcons", 
        		"d_zona", 
        		"c_cve_ciudad"};
        tokenizer.setNames(tokens);
        tokenizer.setDelimiter("|");
        
        FlatFileItemReader<Zipcode> reader = new FlatFileItemReader<Zipcode>();
        reader.setResource(inputTxt);
        reader.setLinesToSkip(1);
        DefaultLineMapper<Zipcode> lineMapper = 
          new DefaultLineMapper<Zipcode>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new CustomZipcodeFieldSetMapper());
        reader.setLineMapper(lineMapper);
        
        return reader;
    }
    
    @Bean
    public ItemWriter<Zipcode> itemWriter(){
    	
        JdbcBatchItemWriter<Zipcode> databaseItemWriter = new JdbcBatchItemWriter<>();
        databaseItemWriter.setDataSource(datasource);
        databaseItemWriter.setJdbcTemplate(jdbcTemplate);
 
        databaseItemWriter.setSql(QUERY_INSERT_STUDENT);
 
        ItemSqlParameterSourceProvider<Zipcode> paramProvider = 
                new BeanPropertyItemSqlParameterSourceProvider<>();
        databaseItemWriter.setItemSqlParameterSourceProvider(paramProvider);
 
        return databaseItemWriter;

    }
    
    @Bean
    protected Step step1(ItemReader<Zipcode> reader,
    	      ItemWriter<Zipcode> writer) {
    	        return steps
    	        		.get("step1")
    	        		.<Zipcode, Zipcode> chunk(10)
    	        		.reader(reader)
    	        		.writer(writer)
    	        		.build();
    }
    
    @Bean(name = "populateZipcodes")
    public Job job(@Qualifier("step1") Step step1) {
    	
		return jobs
				  .get("createZipCodesJob")
				  .incrementer(new RunIdIncrementer())
				  .flow(step1)
				  .end()
				  .build();
    }
    

}
