package com.loan.config;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.loan.exception.DBException;
import com.loan.model.LoanData;
import com.loan.model.User;
import com.loan.service.LoanService;

@Configuration
@EnableBatchProcessing
public class BatchConfigurationFile {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	EntityManagerFactory entityManager;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private  StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private LoanService loanService;
	
	//@Value("classpath:/usersinput.csv")
	//private Resource personInput;
	
	@Bean
	public FlatFileItemReader<User> fileReader(){
		
		FlatFileItemReader<User> reader=new FlatFileItemReader<User>();
		//reader.setResource(new ClassPathResource("usersinput.csv"));
		reader.setResource(new ClassPathResource("usersinput.csv"));
		DefaultLineMapper<User> lineMapper=new DefaultLineMapper<>();
		
		DelimitedLineTokenizer delimiter=new DelimitedLineTokenizer();
		String[] tokennames= {"uname","gender","age","pan","aadhar","salary"};
		
		delimiter.setNames(tokennames);
		delimiter.setDelimiter(",");
		
		lineMapper.setLineTokenizer(delimiter);
		
		BeanWrapperFieldSetMapper<User> fieldset=new BeanWrapperFieldSetMapper<>();
		fieldset.setTargetType(User.class);
		
		lineMapper.setFieldSetMapper(fieldset);
		
		reader.setLineMapper(lineMapper);
		reader.setLinesToSkip(1);
		return reader;
	}

	@Bean
	public JpaItemWriter<User> jpaFileWriter() {
		JpaItemWriter<User> jpa = new JpaItemWriter<>();
		jpa.setEntityManagerFactory(entityManager);
		//jpa.
		return jpa;
	}
	
	@Bean
	public void loanReader() throws DBException {
		Optional<List<LoanData>> loan = loanService.getAllLoanData();
		if (!loan.isPresent()) {
			throw new DBException("Loan Data Not Found");
		}
		List<LoanData> data = loan.get();
		data.forEach(loanData->{
				Double finalAmount		=loanData.getFinalamount();
				Integer pendingTenture =	loanData.getPendingtenture();
				Double loanAmount=loanData.getLoanamount();
				Double	emiWithInt=finalAmount/pendingTenture;
				Double	emiWithOutInt=-(loanAmount/pendingTenture);
				loanData.setFinalamount(finalAmount-emiWithInt);
				loanData.setLoanamount(loanAmount+emiWithOutInt);
				loanData.setPendingtenture(pendingTenture-1);
				loanService.updateLoanData(loanData);
				}
				);
		/*for (LoanData loanData : data) {
			Double finalAmount		=loanData.getFinalamount();
			Integer pendingTenture =	loanData.getPendingtenture();
			Double loanAmount=loanData.getLoanamount();
			Double	emiWithInt=finalAmount/pendingTenture;
			Double	emiWithOutInt=-(loanAmount/pendingTenture);
			loanData.setFinalamount(finalAmount-emiWithInt);
			loanData.setLoanamount(loanAmount+emiWithOutInt);
			loanData.setPendingtenture(pendingTenture-1);
			loanService.updateLoanData(loanData);
		}*/
		
		//return data;
	}
	
	@Bean
	public Job readCSVFile() {
		return jobBuilderFactory.get("addUserDataJob")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}
	
	
	@Bean
	public Step step1() {
		return 	stepBuilderFactory.get("adduserData")
		.<User,User>chunk(1)
		.reader(fileReader())
		.writer(jpaFileWriter())
		.build();
	}

	@Bean
	public Step step2() {
		return 	stepBuilderFactory.get("emiJob")
		.<User,User>chunk(1)
		//.reader(loanReader)
	//	.writer(jpaFileWriter())
		.build();
	}

}
