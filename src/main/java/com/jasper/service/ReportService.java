package com.jasper.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.jasper.domain.User;
import com.jasper.repository.UserRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	
	@Autowired
	private UserRepository userRepository;
	
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		List<User> userList = (List<User>) userRepository.findAll();
		File file = ResourceUtils.getFile("classpath:users.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userList);
		Map<String,Object> parameters= new HashMap<>();
		parameters.put("CreatedBy", "Sampurna Shrestha");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,"E:\\"+"users.html");
		}
		if(reportFormat.equalsIgnoreCase("xls")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,"E:\\"+"users.xls");
		}
		
		return "report generated in path : "+"E:\\";
	}

}
