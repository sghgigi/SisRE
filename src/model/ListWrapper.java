package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sisReport")
public class ListWrapper
{
   @XmlAttribute
   private String namePrefix;
   @XmlAttribute
   private double minGPA;
   @XmlAttribute
   private String year;
   @XmlAttribute
   private String orderBy;
   @XmlElement(name="student")
   private List<StudentBean> list;
   
   public ListWrapper()
	{
		
	}
   
   public ListWrapper(String namePrefix, double minGPA, String orderBy,
		List<StudentBean> list) {
	super();
	this.namePrefix = namePrefix;
	this.minGPA = minGPA;
	this.orderBy = orderBy;
	this.list = list;
}
   public ListWrapper(String namePrefix, double minGPA, String year, String orderBy,
			List<StudentBean> list) {
		super();
		this.namePrefix = namePrefix;
		this.minGPA = minGPA;
		this.orderBy = orderBy;
		this.year = year;
		this.list = list;
	}
   
   
   
}