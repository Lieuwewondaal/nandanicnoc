
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
object editForm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Long,Form[Diagnoseoverzicht],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, diagnoseForm: Form[Diagnoseoverzicht]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.render) }};
Seq[Any](format.raw/*1.51*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.80*/(""" 

"""),_display_(/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
    
    """),format.raw/*9.5*/("""<h1>Edit diagnose</h1>
    
    """),_display_(/*11.6*/form(routes.Application.update(id))/*11.41*/ {_display_(Seq[Any](format.raw/*11.43*/("""
        
        """),format.raw/*13.9*/("""<fieldset>
        
            """),_display_(/*15.14*/inputText(diagnoseForm("diagnoseoverzicht_omschrijving"), '_label -> "Diagnose naam", '_help -> "")),format.raw/*15.113*/("""
			"""),_display_(/*16.5*/inputText(diagnoseForm("diagnoseoverzicht_definitie"), '_label -> "Diagnose omschrijving", '_help -> "")),format.raw/*16.109*/("""
			"""),_display_(/*17.5*/inputText(diagnoseForm("diagnose_code"), '_label -> "Diagnose code", '_help -> "")),format.raw/*17.87*/("""
			"""),_display_(/*18.5*/inputText(diagnoseForm("diagnoseklasse.diagnoseklasse_klasse"), '_label -> "Diagnose klasse", '_help -> "")),format.raw/*18.112*/("""
			"""),_display_(/*19.5*/inputText(diagnoseForm("diagnosedomein.diagnosedomein_domein"), '_label -> "Diagnose domein", '_help -> "")),format.raw/*19.112*/("""
			
            """),_display_(/*21.14*/select(
                diagnoseForm("gezondheidspatroon.gezondheidspatroon_id"), 
                options(Gezondheidspatroon.options), 
                '_label -> "Patroon", '_default -> "-- Kies een patroon --",
                '_showConstraints -> false
            )),format.raw/*26.14*/("""
            

        """),format.raw/*29.9*/("""</fieldset>
        
        <div class="actions">
            <input type="submit" value="Save this diagnose" class="btn primary"> or 
            <a href=""""),_display_(/*33.23*/routes/*33.29*/.Application.list()),format.raw/*33.48*/("""" class="btn">Cancel</a> 
        </div>
        
    """)))}),format.raw/*36.6*/("""
    
    """),_display_(/*38.6*/form(routes.Application.delete(id), 'class -> "topRight")/*38.63*/ {_display_(Seq[Any](format.raw/*38.65*/("""
        """),format.raw/*39.9*/("""<input type="submit" value="Delete this diagnose" class="btn danger">
    """)))}),format.raw/*40.6*/("""
    
""")))}),format.raw/*42.2*/("""
"""))}
  }

  def render(id:Long,diagnoseForm:Form[Diagnoseoverzicht]): play.twirl.api.HtmlFormat.Appendable = apply(id,diagnoseForm)

  def f:((Long,Form[Diagnoseoverzicht]) => play.twirl.api.HtmlFormat.Appendable) = (id,diagnoseForm) => apply(id,diagnoseForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon May 11 09:54:27 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/editForm.scala.html
                  HASH: 6a4144bc97f04d26c6dc46488d67bf913ca7782d
                  MATRIX: 748->1|892->71|924->95|1008->50|1036->69|1064->149|1093->153|1104->157|1142->159|1178->169|1237->202|1281->237|1321->239|1366->257|1426->290|1547->389|1578->394|1704->498|1735->503|1838->585|1869->590|1998->697|2029->702|2158->809|2203->827|2494->1097|2544->1120|2729->1278|2744->1284|2784->1303|2869->1358|2906->1369|2972->1426|3012->1428|3048->1437|3153->1512|3190->1519
                  LINES: 26->1|28->5|28->5|29->1|31->4|32->5|34->7|34->7|34->7|36->9|38->11|38->11|38->11|40->13|42->15|42->15|43->16|43->16|44->17|44->17|45->18|45->18|46->19|46->19|48->21|53->26|56->29|60->33|60->33|60->33|63->36|65->38|65->38|65->38|66->39|67->40|69->42
                  -- GENERATED --
              */
          