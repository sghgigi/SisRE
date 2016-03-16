package tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class Greet2 extends SimpleTagSupport
{
        private String party;

        public void doTag() throws JspException, IOException
        {
                JspWriter os = this.getJspContext().getOut();
                
                StringWriter sw = new StringWriter();
                getJspBody().invoke(sw);
                os.write("<i>Attribute = "+this.party+"</i><br/>");
                os.write("<i>Body = "+sw.toString()+"</i><br/>");
                
        }
        public void setParty(String party)
        {
                this.party = party;
        }
        
}
