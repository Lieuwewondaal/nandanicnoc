
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
	"""),format.raw/*8.2*/("""<script>
	  $( document ).ready(function() """),format.raw/*9.35*/("""{"""),format.raw/*9.36*/("""
	  """),format.raw/*10.4*/("""var linkBepalendkenmerk = "bepalendkenmerk?f=" + window.location.pathname.substring(11);
	  var linkRisicofactor = "risicofactor?f=" + window.location.pathname.substring(11);
	  var linkSamenhangendefactor = "samenhangendefactor?f=" + window.location.pathname.substring(11);
	  $.get( linkBepalendkenmerk, function( data ) """),format.raw/*13.49*/("""{"""),format.raw/*13.50*/("""
		  """),format.raw/*14.5*/("""$( ".form" ).append( data );
		"""),format.raw/*15.3*/("""}"""),format.raw/*15.4*/(""");
	  $.get( linkRisicofactor, function( data ) """),format.raw/*16.46*/("""{"""),format.raw/*16.47*/("""
		  """),format.raw/*17.5*/("""$( ".form" ).append( data );
	  """),format.raw/*18.4*/("""}"""),format.raw/*18.5*/(""");
		
	  $.get( linkSamenhangendefactor, function( data ) """),format.raw/*20.53*/("""{"""),format.raw/*20.54*/("""
		  """),format.raw/*21.5*/("""$( ".form" ).append( data );
	  """),format.raw/*22.4*/("""}"""),format.raw/*22.5*/(""");
		//$( ".bepalendkenmerk" ).load( "bepalendkenmerk?f=491322316502" );
	"""),format.raw/*24.2*/("""}"""),format.raw/*24.3*/(""");
	</script>
	
    <h1>Edit diagnose</h1>
    
    """),_display_(/*29.6*/form(routes.Application.update(id))/*29.41*/ {_display_(Seq[Any](format.raw/*29.43*/("""
        
        """),format.raw/*31.9*/("""<fieldset class="form">
		<table class="diagnoses zebra-striped">
            <thead>
                <tr>
				  <th>Detail</th>
				  <th>Waarde</th>
                </tr>
            </thead>
            <tbody>
				<tr>
				    <td>
						Code
					</td>
					<td>
						"""),_display_(/*45.8*/if(diagnoseForm("diagnose_code") == null)/*45.49*/ {_display_(Seq[Any](format.raw/*45.51*/("""
							"""),format.raw/*46.8*/("""<em>-</em>
						""")))}/*47.9*/else/*47.13*/{_display_(Seq[Any](format.raw/*47.14*/("""
							"""),_display_(/*48.9*/diagnoseForm("diagnose_code")/*48.38*/.value),format.raw/*48.44*/("""
						""")))}),format.raw/*49.8*/("""
						
					"""),format.raw/*51.6*/("""</td>
				</tr>
				<tr>
					<td>Omschrijving</td>
					<td>
						"""),_display_(/*56.8*/diagnoseForm("diagnoseoverzicht_omschrijving")/*56.54*/.value),format.raw/*56.60*/("""
					"""),format.raw/*57.6*/("""</td>
				</tr>
				<tr>
					<td>Defintie</td>
					<td>
						"""),_display_(/*62.8*/diagnoseForm("diagnoseoverzicht_definitie")/*62.51*/.value),format.raw/*62.57*/("""
					"""),format.raw/*63.6*/("""</td>
				</tr>
				<tr>
				    <td>
						Domein
					</td>
					<td>
						"""),_display_(/*70.8*/if(diagnoseForm("diagnoseklasse.diagnosedomein.diagnosedomein_code") == null)/*70.85*/ {_display_(Seq[Any](format.raw/*70.87*/("""
							"""),format.raw/*71.8*/("""<em>-</em>
						""")))}/*72.9*/else/*72.13*/{_display_(Seq[Any](format.raw/*72.14*/("""
							"""),_display_(/*73.9*/diagnoseForm("diagnoseklasse.diagnosedomein.diagnosedomein_code")/*73.74*/.value),format.raw/*73.80*/("""
						""")))}),format.raw/*74.8*/("""
						
					"""),format.raw/*76.6*/("""</td>
				</tr>
				<tr>
				    <td>
						Klasse
					</td>
					<td>
						"""),_display_(/*83.8*/if(diagnoseForm("diagnoseklasse.diagnoseklasse_code") == null)/*83.70*/ {_display_(Seq[Any](format.raw/*83.72*/("""
							"""),format.raw/*84.8*/("""<em>-</em>
						""")))}/*85.9*/else/*85.13*/{_display_(Seq[Any](format.raw/*85.14*/("""
							"""),_display_(/*86.9*/diagnoseForm("diagnoseklasse.diagnoseklasse_code")/*86.59*/.value),format.raw/*86.65*/("""
						""")))}),format.raw/*87.8*/("""
						
					"""),format.raw/*89.6*/("""</td>
				</tr>
				<tr>
				    <td>
						Gezondheidspatroon
					</td>
					<td>
						"""),_display_(/*96.8*/if(diagnoseForm("gezondheidspatroon.gezondheidspatroon_omschrijving") == null)/*96.86*/ {_display_(Seq[Any](format.raw/*96.88*/("""
							"""),format.raw/*97.8*/("""<em>-</em>
						""")))}/*98.9*/else/*98.13*/{_display_(Seq[Any](format.raw/*98.14*/("""
							"""),_display_(/*99.9*/diagnoseForm("gezondheidspatroon.gezondheidspatroon_omschrijving")/*99.75*/.value),format.raw/*99.81*/("""
						""")))}),format.raw/*100.8*/("""
						
					"""),format.raw/*102.6*/("""</td>
				</tr>
            </tbody>
        </table>
		<!--
		a """),_display_(/*107.6*/diagnoseForm("diagnoseoverzicht_omschrijving")/*107.52*/.value),format.raw/*107.58*/(""" """),format.raw/*107.59*/("""<br />
		a """),_display_(/*108.6*/diagnoseForm("diagnoseoverzicht_definitie")/*108.49*/.value),format.raw/*108.55*/(""" """),format.raw/*108.56*/("""<br />
		a """),_display_(/*109.6*/diagnoseForm("diagnose_code")/*109.35*/.value),format.raw/*109.41*/(""" """),format.raw/*109.42*/("""<br />
		a """),_display_(/*110.6*/diagnoseForm("diagnoseklasse_code")/*110.41*/.value),format.raw/*110.47*/(""" """),format.raw/*110.48*/("""<br />
		a """),_display_(/*111.6*/diagnoseForm("diagnosedomein_code")/*111.41*/.value),format.raw/*111.47*/(""" """),format.raw/*111.48*/("""<br />
		a """),_display_(/*112.6*/diagnoseForm("gezondheidspatroon_id")/*112.43*/.value),format.raw/*112.49*/(""" """),format.raw/*112.50*/("""<br />
		a """),_display_(/*113.6*/diagnoseForm("diagnoseklasse.diagnoseklasse_code")/*113.56*/.value),format.raw/*113.62*/("""
		
            """),_display_(/*115.14*/inputText(diagnoseForm("diagnoseoverzicht_omschrijving"), '_label -> "Diagnose naam", '_help -> "")),format.raw/*115.113*/("""
			"""),_display_(/*116.5*/inputText(diagnoseForm("diagnoseoverzicht_definitie"), '_label -> "Diagnose omschrijving", '_help -> "")),format.raw/*116.109*/("""
			"""),_display_(/*117.5*/inputText(diagnoseForm("diagnose_code"), '_label -> "Diagnose code", '_help -> "")),format.raw/*117.87*/("""
			"""),_display_(/*118.5*/inputText(diagnoseForm("diagnoseklasse.diagnoseklasse_code"), '_label -> "Diagnose klasse", '_help -> "")),format.raw/*118.110*/("""
			"""),_display_(/*119.5*/inputText(diagnoseForm("diagnoseklasse.diagnosedomein.diagnosedomein_code"), '_label -> "Diagnose domein", '_help -> "")),format.raw/*119.125*/("""
			
            """),_display_(/*121.14*/select(
                diagnoseForm("gezondheidspatroon.gezondheidspatroon_id"), 
                options(Gezondheidspatroon.options), 
                '_label -> "Patroon", '_default -> "-- Kies een patroon --",
                '_showConstraints -> false
            )),format.raw/*126.14*/("""
            
		"""),format.raw/*128.3*/("""-->
        </fieldset>
        
        <div class="actions">
            <!--<input type="submit" value="Save this diagnose" class="btn primary"> or -->
            <a href=""""),_display_(/*133.23*/routes/*133.29*/.Application.list()),format.raw/*133.48*/("""" class="btn">Terug</a> 
        </div>
        
    """)))}),format.raw/*136.6*/("""
    
    """),_display_(/*138.6*/form(routes.Application.delete(id), 'class -> "topRight")/*138.63*/ {_display_(Seq[Any](format.raw/*138.65*/("""
        """),format.raw/*139.9*/("""<input type="submit" value="Delete this diagnose" class="btn danger">
    """)))}),format.raw/*140.6*/("""
    
""")))}),format.raw/*142.2*/("""
"""))}
  }

  def render(id:Long,diagnoseForm:Form[Diagnoseoverzicht]): play.twirl.api.HtmlFormat.Appendable = apply(id,diagnoseForm)

  def f:((Long,Form[Diagnoseoverzicht]) => play.twirl.api.HtmlFormat.Appendable) = (id,diagnoseForm) => apply(id,diagnoseForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun May 17 23:03:04 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/editForm.scala.html
                  HASH: a7023359c701f197a4789809d747ff61b212138f
                  MATRIX: 748->1|892->71|924->95|1008->50|1036->69|1064->149|1093->153|1104->157|1142->159|1170->161|1240->204|1268->205|1299->209|1650->532|1679->533|1711->538|1769->569|1797->570|1873->618|1902->619|1934->624|1993->656|2021->657|2107->715|2136->716|2168->721|2227->753|2255->754|2356->828|2384->829|2463->882|2507->917|2547->919|2592->937|2893->1212|2943->1253|2983->1255|3018->1263|3054->1282|3067->1286|3106->1287|3141->1296|3179->1325|3206->1331|3244->1339|3284->1352|3379->1421|3434->1467|3461->1473|3494->1479|3585->1544|3637->1587|3664->1593|3697->1599|3802->1678|3888->1755|3928->1757|3963->1765|3999->1784|4012->1788|4051->1789|4086->1798|4160->1863|4187->1869|4225->1877|4265->1890|4370->1969|4441->2031|4481->2033|4516->2041|4552->2060|4565->2064|4604->2065|4639->2074|4698->2124|4725->2130|4763->2138|4803->2151|4920->2242|5007->2320|5047->2322|5082->2330|5118->2349|5131->2353|5170->2354|5205->2363|5280->2429|5307->2435|5346->2443|5387->2456|5480->2522|5536->2568|5564->2574|5594->2575|5633->2587|5686->2630|5714->2636|5744->2637|5783->2649|5822->2678|5850->2684|5880->2685|5919->2697|5964->2732|5992->2738|6022->2739|6061->2751|6106->2786|6134->2792|6164->2793|6203->2805|6250->2842|6278->2848|6308->2849|6347->2861|6407->2911|6435->2917|6480->2934|6602->3033|6634->3038|6761->3142|6793->3147|6897->3229|6929->3234|7057->3339|7089->3344|7232->3464|7278->3482|7570->3752|7614->3768|7819->3945|7835->3951|7876->3970|7961->4024|7999->4035|8066->4092|8107->4094|8144->4103|8250->4178|8288->4185
                  LINES: 26->1|28->5|28->5|29->1|31->4|32->5|34->7|34->7|34->7|35->8|36->9|36->9|37->10|40->13|40->13|41->14|42->15|42->15|43->16|43->16|44->17|45->18|45->18|47->20|47->20|48->21|49->22|49->22|51->24|51->24|56->29|56->29|56->29|58->31|72->45|72->45|72->45|73->46|74->47|74->47|74->47|75->48|75->48|75->48|76->49|78->51|83->56|83->56|83->56|84->57|89->62|89->62|89->62|90->63|97->70|97->70|97->70|98->71|99->72|99->72|99->72|100->73|100->73|100->73|101->74|103->76|110->83|110->83|110->83|111->84|112->85|112->85|112->85|113->86|113->86|113->86|114->87|116->89|123->96|123->96|123->96|124->97|125->98|125->98|125->98|126->99|126->99|126->99|127->100|129->102|134->107|134->107|134->107|134->107|135->108|135->108|135->108|135->108|136->109|136->109|136->109|136->109|137->110|137->110|137->110|137->110|138->111|138->111|138->111|138->111|139->112|139->112|139->112|139->112|140->113|140->113|140->113|142->115|142->115|143->116|143->116|144->117|144->117|145->118|145->118|146->119|146->119|148->121|153->126|155->128|160->133|160->133|160->133|163->136|165->138|165->138|165->138|166->139|167->140|169->142
                  -- GENERATED --
              */
          