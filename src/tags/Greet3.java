package tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class Greet3 extends SimpleTagSupport
{
        private String name;
        private String major;
        private String courses;
        private String year;
        private double gpa;
        private int count;

        public void doTag() throws JspException, IOException
        {
                JspWriter os = this.getJspContext().getOut();
                
               // StringWriter sw = new StringWriter();
               // getJspBody().invoke(sw);
                
                os.write("<td align=\"center\" width=\"90\" ><i>" + this.name + "</i><br/></td>\n");
                os.write("<td align=\"center\" width=\"130\" ><i>" + this.major + "</i><br/></td>\n");
                os.write("<td align=\"center\" width=\"30\" ><i>" + this.courses + "</i><br/></td>\n");
                os.write("<td align=\"center\" width=\"35\" ><i>" + this.year + "</i><br/></td>\n");
                os.write("<td align=\"center\" width=\"35\" ><i>" + this.gpa + "</i><br/></td>\n");
                
                if (gpa > 5.0)
                {
                	os.write("<td align=\"left\" id=\"blue\"width=\"480\" ><i>");
                	count = (int) ((gpa - 5.0 + 0.001) * 10);
                	for (int i = 0; i < count; i++) 
                    {
                    	os.write("&#x25B7;");
    				}
                	os.write("&#x25CF;");
                	//os.write("" +count);
                }
                else
                {
                	os.write("<td align=\"left\" id=\"red\"width=\"480\" ><i>");
                	count = (int) ((gpa - 5.0 -0.001) * -10);
                	os.write("&#x25CF;");
                	//os.write("" + count);
                	for (int i = 0; i < count; i++) 
                    {
                    	os.write("&#x25C1;");
    				}
                }
                
                
                os.write("</i><br/></td>");
                
                //os.write("<i>Body = "+sw.toString()+"</i><br/>");
                
        }
        public void setName(String name)
        {
                this.name = name;
        }
        public void setMajor(String major)
        {
                this.major = major;
        }
        public void setCourses(String courses)
        {
                this.courses = courses;
        }
        public void setGpa(double gpa)
        {
                this.gpa = gpa;
        }
        public void setYear(String year)
        {
                this.year = year;
        }
        
}
