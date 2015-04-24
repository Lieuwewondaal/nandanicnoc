
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
object editForm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Long,Form[Diagnose],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, diagnoseForm: Form[Diagnose]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.render) }};
Seq[Any](format.raw/*1.42*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.80*/(""" 

"""),_display_(/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
    
    """),format.raw/*9.5*/("""<h1>Edit diagnose</h1>
    
    """),_display_(/*11.6*/form(routes.Application.update(id))/*11.41*/ {_display_(Seq[Any](format.raw/*11.43*/("""
        
        """),format.raw/*13.9*/("""<fieldset>
        
            """),_display_(/*15.14*/inputText(diagnoseForm("name"), '_label -> "Diagnose name", '_help -> "")),format.raw/*15.87*/("""
            """),_display_(/*16.14*/inputDate(diagnoseForm("introduced"), '_label -> "Introduced date", '_help -> "")),format.raw/*16.95*/("""
            """),_display_(/*17.14*/inputDate(diagnoseForm("discontinued"), '_label -> "Discontinued date", '_help -> "")),format.raw/*17.99*/("""
            
            """),_display_(/*19.14*/select(
                diagnoseForm("company.id"), 
                options(Company.options), 
                '_label -> "Company", '_default -> "-- Choose a company --",
                '_showConstraints -> false
            )),format.raw/*24.14*/("""
        
        """),format.raw/*26.9*/("""</fieldset>
        
        <div class="actions">
            <input type="submit" value="Save this diagnose" class="btn primary"> or 
            <a href=""""),_display_(/*30.23*/routes/*30.29*/.Application.list()),format.raw/*30.48*/("""" class="btn">Cancel</a> 
        </div>
        
    """)))}),format.raw/*33.6*/("""
    
    """),_display_(/*35.6*/form(routes.Application.delete(id), 'class -> "topRight")/*35.63*/ {_display_(Seq[Any](format.raw/*35.65*/("""
        """),format.raw/*36.9*/("""<input type="submit" value="Delete this diagnose" class="btn danger">
    """)))}),format.raw/*37.6*/("""
    
""")))}),format.raw/*39.2*/("""
"""))}
  }

  def render(id:Long,diagnoseForm:Form[Diagnose]): play.twirl.api.HtmlFormat.Appendable = apply(id,diagnoseForm)

  def f:((Long,Form[Diagnose]) => play.twirl.api.HtmlFormat.Appendable) = (id,diagnoseForm) => apply(id,diagnoseForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Apr 21 17:02:04 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/editForm.scala.html
                  HASH: b30ea1e5558cf81d4421ddb7c8f54b87b891d851
                  MATRIX: 739->1|874->62|906->86|990->41|1018->60|1046->140|1075->144|1086->148|1124->150|1160->160|1219->193|1263->228|1303->230|1348->248|1408->281|1502->354|1543->368|1645->449|1686->463|1792->548|1846->575|2096->804|2141->822|2326->980|2341->986|2381->1005|2466->1060|2503->1071|2569->1128|2609->1130|2645->1139|2750->1214|2787->1221
                  LINES: 26->1|28->5|28->5|29->1|31->4|32->5|34->7|34->7|34->7|36->9|38->11|38->11|38->11|40->13|42->15|42->15|43->16|43->16|44->17|44->17|46->19|51->24|53->26|57->30|57->30|57->30|60->33|62->35|62->35|62->35|63->36|64->37|66->39
                  -- GENERATED --
              */
          