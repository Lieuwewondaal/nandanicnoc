
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object createForm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[Computer],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(computerForm: Form[Computer]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.render) }};
Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.80*/(""" 

"""),_display_(/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
    
    """),format.raw/*9.5*/("""<h1>Add a computer</h1>
	
	"""),_display_(/*11.3*/helper/*11.9*/.form(action = routes.Application.upload(), 'enctype -> "multipart/form-data")/*11.87*/ {_display_(Seq[Any](format.raw/*11.89*/("""
	"""),format.raw/*12.2*/("""<input type="file" name="picture">
	<p>
	  <input type="submit">
    </p>
    
	""")))}),format.raw/*17.3*/("""
	
	"""),_display_(/*19.3*/form(routes.Application.save())/*19.34*/ {_display_(Seq[Any](format.raw/*19.36*/("""
        
        """),format.raw/*21.9*/("""<fieldset>
        
            """),_display_(/*23.14*/inputText(computerForm("name"), '_label -> "Computer name", '_help -> "")),format.raw/*23.87*/("""
            """),_display_(/*24.14*/inputDate(computerForm("introduced"), '_label -> "Introduced date", '_help -> "")),format.raw/*24.95*/("""
            """),_display_(/*25.14*/inputDate(computerForm("discontinued"), '_label -> "Discontinued date", '_help -> "")),format.raw/*25.99*/("""

            """),_display_(/*27.14*/select(
                computerForm("company.id"), 
                options(Company.options), 
                '_label -> "Company", '_default -> "-- Choose a company --",
                '_showConstraints -> false
            )),format.raw/*32.14*/("""
            

        """),format.raw/*35.9*/("""</fieldset>
        
        <div class="actions">
            <input type="submit" value="Create this computer" class="btn primary"> or 
            <a href=""""),_display_(/*39.23*/routes/*39.29*/.Application.list()),format.raw/*39.48*/("""" class="btn">Cancel</a> 
        </div>
        
    """)))}),format.raw/*42.6*/("""
    
""")))}))}
  }

  def render(computerForm:Form[Computer]): play.twirl.api.HtmlFormat.Appendable = apply(computerForm)

  def f:((Form[Computer]) => play.twirl.api.HtmlFormat.Appendable) = (computerForm) => apply(computerForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Apr 21 13:41:36 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/createForm.scala.html
                  HASH: 0bde3281647f1d02192ac2c6e4818e87ab131a78
                  MATRIX: 736->1|861->52|893->76|977->31|1005->50|1033->130|1062->134|1073->138|1111->140|1147->150|1201->178|1215->184|1302->262|1342->264|1371->266|1482->347|1513->352|1553->383|1593->385|1638->403|1698->436|1792->509|1833->523|1935->604|1976->618|2082->703|2124->718|2374->947|2424->970|2611->1130|2626->1136|2666->1155|2751->1210
                  LINES: 26->1|28->5|28->5|29->1|31->4|32->5|34->7|34->7|34->7|36->9|38->11|38->11|38->11|38->11|39->12|44->17|46->19|46->19|46->19|48->21|50->23|50->23|51->24|51->24|52->25|52->25|54->27|59->32|62->35|66->39|66->39|66->39|69->42
                  -- GENERATED --
              */
          