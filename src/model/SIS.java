package model;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.security.InvalidParameterException;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class SIS 
{
	private StudentDAO studentDao;
	//private WebServiceClient wsc;
	
	public SIS() throws Exception
	{
		this.studentDao = new StudentDAO();
		//this.wsc = new WebServiceClient();
	
	}
	
	public List<StudentBean> retrieve(String namePrefix, String minGPA, String year, String scope) throws Exception
	{
		namePrefix = this.extractName(namePrefix) + "%";
		double gpa = this.extractGPA(minGPA);
		if (scope.equals("NONE"))
		{
			if(year.equals("ANY"))
			{
				return this.studentDao.retrieve2(namePrefix, gpa);
			}
			else
			{
				return this.studentDao.retrieve2(namePrefix, gpa , year);
			}
		}
		else
		{
			if(year.equals("ANY"))
			{
				return this.studentDao.retrieve(namePrefix, gpa, scope);
			}
			else
			{
				return this.studentDao.retrieve(namePrefix, gpa, year, scope);
			}
			
		}
	}
	
	private double extractGPA(String sGPA)
	{
		//System.out.println(sGPA);
		double result = Double.parseDouble(sGPA);
		//System.out.println("gpa=" + result);
		if (result < 0 || result >= 9.0) throw new InvalidParameterException("Invalid gpa...must be between 0 to 9");
		return result;
	}
	
	private String extractName(String sName)
	{		
		char[] chars = sName.toCharArray();
	    for (char c : chars) 
	    {
	        if(!Character.isLetter(c)) 
	        {
	            throw new InvalidParameterException("Invalid name prefix...must only contain letters");
	        }
	    }    
        return sName;
	}
	
	public void export(String namePrefix, String minGPA, String year, String scope, String filename) throws Exception
	{
		  		  
		  List<StudentBean> list = this.retrieve(namePrefix, minGPA, year,  scope);
		  double gpa = this.extractGPA(minGPA);
		  ListWrapper lw = new ListWrapper(namePrefix, gpa, year, scope, list);
		  JAXBContext jc = JAXBContext.newInstance(lw.getClass());
		  Marshaller marshaller = jc.createMarshaller();	
		  		  		  
		  //SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
		  //Schema schema = sf.newSchema(new File("cse4413/workplace/BB/WebContent/xml/SIS.xsd")); 
		  //marshaller.setSchema(schema);
		  
		  marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		  marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		  
		  StringWriter sw = new StringWriter();
		  sw.write("<?xml version=\"1.0\"?>\n");
	      sw.write("<?xml-stylesheet type=\"text/xsl\" href=\"SIS.xsl\"?>\n");
		  sw.write("\n");
		  marshaller.marshal(lw, new StreamResult(sw));
		  
		  System.out.println(sw.toString()); // for debugging
		  FileWriter fw = new FileWriter(filename);
		  fw.write(sw.toString());
		  fw.close();
	
	}
	
	public List<StudentBean> retrieveyear() throws Exception
	{
		
			return this.studentDao.retrieveyear();

	}
		

}
