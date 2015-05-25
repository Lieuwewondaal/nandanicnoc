
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
object editForm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Long,Form[Diagnoseoverzicht],List[Bepalendkenmerk_Diagnose],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, diagnoseForm: Form[Diagnoseoverzicht], d: List[Bepalendkenmerk_Diagnose]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.render) }};
Seq[Any](format.raw/*1.86*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.80*/(""" 

"""),_display_(/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
	"""),format.raw/*8.2*/("""<script>
	  $( document ).ready(function() """),format.raw/*9.35*/("""{"""),format.raw/*9.36*/("""
	  """),format.raw/*10.4*/("""var linkBepalendkenmerk = "bepalendkenmerk?f=" + """),_display_(/*10.54*/id),format.raw/*10.56*/(""" """),format.raw/*10.57*/(""";
	  var linkRisicofactor = "risicofactor?f=" + """),_display_(/*11.48*/id),format.raw/*11.50*/(""" """),format.raw/*11.51*/(""";
	  var linkSamenhangendefactor = "samenhangendefactor?f=" + """),_display_(/*12.62*/id),format.raw/*12.64*/(""" """),format.raw/*12.65*/(""";
	  $.get( linkBepalendkenmerk, function( data ) """),format.raw/*13.49*/("""{"""),format.raw/*13.50*/("""
		  """),format.raw/*14.5*/("""$( ".form" ).append( data );
		"""),format.raw/*15.3*/("""}"""),format.raw/*15.4*/(""");
	  $.get( linkRisicofactor, function( data ) """),format.raw/*16.46*/("""{"""),format.raw/*16.47*/("""
		  """),format.raw/*17.5*/("""$( ".form" ).append( data );
	  """),format.raw/*18.4*/("""}"""),format.raw/*18.5*/(""");
		
	  $.get( linkSamenhangendefactor, function( data ) """),format.raw/*20.53*/("""{"""),format.raw/*20.54*/("""
		  """),format.raw/*21.5*/("""$( ".form" ).append( data );
	  """),format.raw/*22.4*/("""}"""),format.raw/*22.5*/(""");
	  
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
						"""),_display_(/*46.8*/if(diagnoseForm("diagnose_code") == null)/*46.49*/ {_display_(Seq[Any](format.raw/*46.51*/("""
							"""),format.raw/*47.8*/("""<em>-</em>
						""")))}/*48.9*/else/*48.13*/{_display_(Seq[Any](format.raw/*48.14*/("""
							"""),_display_(/*49.9*/diagnoseForm("diagnose_code")/*49.38*/.value),format.raw/*49.44*/("""
						""")))}),format.raw/*50.8*/("""
						
					"""),format.raw/*52.6*/("""</td>
				</tr>
				<tr>
					<td>Omschrijving</td>
					<td>
						"""),_display_(/*57.8*/diagnoseForm("diagnoseoverzicht_omschrijving")/*57.54*/.value),format.raw/*57.60*/("""
					"""),format.raw/*58.6*/("""</td>
				</tr>
				<tr>
					<td>Defintie</td>
					<td>
						"""),_display_(/*63.8*/diagnoseForm("diagnoseoverzicht_definitie")/*63.51*/.value),format.raw/*63.57*/("""
					"""),format.raw/*64.6*/("""</td>
				</tr>
				<tr>
				    <td>
						Domein
					</td>
					<td>
						"""),_display_(/*71.8*/if(diagnoseForm("diagnoseklasse.diagnosedomein.diagnosedomein_code") == null)/*71.85*/ {_display_(Seq[Any](format.raw/*71.87*/("""
							"""),format.raw/*72.8*/("""<em>-</em>
						""")))}/*73.9*/else/*73.13*/{_display_(Seq[Any](format.raw/*73.14*/("""
							"""),_display_(/*74.9*/diagnoseForm("diagnoseklasse.diagnosedomein.diagnosedomein_code")/*74.74*/.value),format.raw/*74.80*/("""
						""")))}),format.raw/*75.8*/("""
						
					"""),format.raw/*77.6*/("""</td>
				</tr>
				<tr>
				    <td>
						Klasse
					</td>
					<td>
						"""),_display_(/*84.8*/if(diagnoseForm("diagnoseklasse.diagnoseklasse_code") == null)/*84.70*/ {_display_(Seq[Any](format.raw/*84.72*/("""
							"""),format.raw/*85.8*/("""<em>-</em>
						""")))}/*86.9*/else/*86.13*/{_display_(Seq[Any](format.raw/*86.14*/("""
							"""),_display_(/*87.9*/diagnoseForm("diagnoseklasse.diagnoseklasse_code")/*87.59*/.value),format.raw/*87.65*/("""
						""")))}),format.raw/*88.8*/("""
						
					"""),format.raw/*90.6*/("""</td>
				</tr>
				<tr>
				    <td>
						Gezondheidspatroon
					</td>
					<td>
						"""),_display_(/*97.8*/if(diagnoseForm("gezondheidspatroon.gezondheidspatroon_omschrijving") == null)/*97.86*/ {_display_(Seq[Any](format.raw/*97.88*/("""
							"""),format.raw/*98.8*/("""<em>-</em>
						""")))}/*99.9*/else/*99.13*/{_display_(Seq[Any](format.raw/*99.14*/("""
							"""),_display_(/*100.9*/diagnoseForm("gezondheidspatroon.gezondheidspatroon_omschrijving")/*100.75*/.value),format.raw/*100.81*/("""
						""")))}),format.raw/*101.8*/("""
						
					"""),format.raw/*103.6*/("""</td>
				</tr>
				<tr>
				    <td>
						Query
					</td>
					<td>
						getBepalendeKenmerken(id)
						
					</td>
				</tr>
            </tbody>
        </table>
		<!--
		"""),_display_(/*117.4*/if(d.size() == 0)/*117.21*/ {_display_(Seq[Any](format.raw/*117.23*/("""
			
			"""),format.raw/*119.4*/("""<div class="well">
				<em>Geen bepalende kenmerken gevonden</em>
			</div>
			
		""")))}/*123.5*/else/*123.10*/{_display_(Seq[Any](format.raw/*123.11*/("""
			
			"""),format.raw/*125.4*/("""<table class="diagnoses zebra-striped">
				<thead>
					<tr>
						<th>Bepalende kenmerken:</th>
					</tr>
				</thead>
				<tbody>

					"""),_display_(/*133.7*/for(bepalendkenmerk_diagnose <- d) yield /*133.41*/ {_display_(Seq[Any](format.raw/*133.43*/("""
						"""),format.raw/*134.7*/("""<tr>
							<td>
								"""),_display_(/*136.10*/bepalendkenmerk_diagnose/*136.34*/.bepalendkenmerk.bepalendkenmerk_omschrijving),format.raw/*136.79*/("""
							"""),format.raw/*137.8*/("""</td>
						</tr>
					""")))}),format.raw/*139.7*/("""

				"""),format.raw/*141.5*/("""</tbody>
			</table>
		""")))}),format.raw/*143.4*/("""
		"""),format.raw/*144.3*/("""-->
		<!--
		a """),_display_(/*146.6*/diagnoseForm("diagnoseoverzicht_omschrijving")/*146.52*/.value),format.raw/*146.58*/(""" """),format.raw/*146.59*/("""<br />
		a """),_display_(/*147.6*/diagnoseForm("diagnoseoverzicht_definitie")/*147.49*/.value),format.raw/*147.55*/(""" """),format.raw/*147.56*/("""<br />
		a """),_display_(/*148.6*/diagnoseForm("diagnose_code")/*148.35*/.value),format.raw/*148.41*/(""" """),format.raw/*148.42*/("""<br />
		a """),_display_(/*149.6*/diagnoseForm("diagnoseklasse_code")/*149.41*/.value),format.raw/*149.47*/(""" """),format.raw/*149.48*/("""<br />
		a """),_display_(/*150.6*/diagnoseForm("diagnosedomein_code")/*150.41*/.value),format.raw/*150.47*/(""" """),format.raw/*150.48*/("""<br />
		a """),_display_(/*151.6*/diagnoseForm("gezondheidspatroon_id")/*151.43*/.value),format.raw/*151.49*/(""" """),format.raw/*151.50*/("""<br />
		a """),_display_(/*152.6*/diagnoseForm("diagnoseklasse.diagnoseklasse_code")/*152.56*/.value),format.raw/*152.62*/("""
		
            """),_display_(/*154.14*/inputText(diagnoseForm("diagnoseoverzicht_omschrijving"), '_label -> "Diagnose naam", '_help -> "")),format.raw/*154.113*/("""
			"""),_display_(/*155.5*/inputText(diagnoseForm("diagnoseoverzicht_definitie"), '_label -> "Diagnose omschrijving", '_help -> "")),format.raw/*155.109*/("""
			"""),_display_(/*156.5*/inputText(diagnoseForm("diagnose_code"), '_label -> "Diagnose code", '_help -> "")),format.raw/*156.87*/("""
			"""),_display_(/*157.5*/inputText(diagnoseForm("diagnoseklasse.diagnoseklasse_code"), '_label -> "Diagnose klasse", '_help -> "")),format.raw/*157.110*/("""
			"""),_display_(/*158.5*/inputText(diagnoseForm("diagnoseklasse.diagnosedomein.diagnosedomein_code"), '_label -> "Diagnose domein", '_help -> "")),format.raw/*158.125*/("""
			
            """),_display_(/*160.14*/select(
                diagnoseForm("gezondheidspatroon.gezondheidspatroon_id"), 
                options(Gezondheidspatroon.options), 
                '_label -> "Patroon", '_default -> "-- Kies een patroon --",
                '_showConstraints -> false
            )),format.raw/*165.14*/("""
            
		"""),format.raw/*167.3*/("""-->
        </fieldset>
        
        <div class="actions">
            <!--<input type="submit" value="Save this diagnose" class="btn primary"> or -->
            <a href=""""),_display_(/*172.23*/routes/*172.29*/.Application.list()),format.raw/*172.48*/("""" class="btn">Terug</a> 
        </div>
        
    """)))}),format.raw/*175.6*/("""
    
    """),_display_(/*177.6*/form(routes.Application.delete(id), 'class -> "topRight")/*177.63*/ {_display_(Seq[Any](format.raw/*177.65*/("""
        """),format.raw/*178.9*/("""<input type="submit" value="Delete this diagnose" class="btn danger">
    """)))}),format.raw/*179.6*/("""
    
""")))}),format.raw/*181.2*/("""
"""))}
  }

  def render(id:Long,diagnoseForm:Form[Diagnoseoverzicht],d:List[Bepalendkenmerk_Diagnose]): play.twirl.api.HtmlFormat.Appendable = apply(id,diagnoseForm,d)

  def f:((Long,Form[Diagnoseoverzicht],List[Bepalendkenmerk_Diagnose]) => play.twirl.api.HtmlFormat.Appendable) = (id,diagnoseForm,d) => apply(id,diagnoseForm,d)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 21 14:44:18 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/editForm.scala.html
                  HASH: c6b90bd6735d01dddd10f86ff3bb86de7bebd03b
                  MATRIX: 779->1|958->106|990->130|1074->85|1102->104|1130->184|1159->188|1170->192|1208->194|1236->196|1306->239|1334->240|1365->244|1442->294|1465->296|1494->297|1570->346|1593->348|1622->349|1712->412|1735->414|1764->415|1842->465|1871->466|1903->471|1961->502|1989->503|2065->551|2094->552|2126->557|2185->589|2213->590|2299->648|2328->649|2360->654|2419->686|2447->687|2482->695|2510->696|2589->749|2633->784|2673->786|2718->804|3022->1082|3072->1123|3112->1125|3147->1133|3183->1152|3196->1156|3235->1157|3270->1166|3308->1195|3335->1201|3373->1209|3413->1222|3508->1291|3563->1337|3590->1343|3623->1349|3714->1414|3766->1457|3793->1463|3826->1469|3931->1548|4017->1625|4057->1627|4092->1635|4128->1654|4141->1658|4180->1659|4215->1668|4289->1733|4316->1739|4354->1747|4394->1760|4499->1839|4570->1901|4610->1903|4645->1911|4681->1930|4694->1934|4733->1935|4768->1944|4827->1994|4854->2000|4892->2008|4932->2021|5049->2112|5136->2190|5176->2192|5211->2200|5247->2219|5260->2223|5299->2224|5335->2233|5411->2299|5439->2305|5478->2313|5519->2326|5725->2505|5752->2522|5793->2524|5829->2532|5931->2616|5945->2621|5985->2622|6021->2630|6189->2771|6240->2805|6281->2807|6316->2814|6370->2840|6404->2864|6471->2909|6507->2917|6562->2941|6596->2947|6651->2971|6682->2974|6725->2990|6781->3036|6809->3042|6839->3043|6878->3055|6931->3098|6959->3104|6989->3105|7028->3117|7067->3146|7095->3152|7125->3153|7164->3165|7209->3200|7237->3206|7267->3207|7306->3219|7351->3254|7379->3260|7409->3261|7448->3273|7495->3310|7523->3316|7553->3317|7592->3329|7652->3379|7680->3385|7725->3402|7847->3501|7879->3506|8006->3610|8038->3615|8142->3697|8174->3702|8302->3807|8334->3812|8477->3932|8523->3950|8815->4220|8859->4236|9064->4413|9080->4419|9121->4438|9206->4492|9244->4503|9311->4560|9352->4562|9389->4571|9495->4646|9533->4653
                  LINES: 26->1|28->5|28->5|29->1|31->4|32->5|34->7|34->7|34->7|35->8|36->9|36->9|37->10|37->10|37->10|37->10|38->11|38->11|38->11|39->12|39->12|39->12|40->13|40->13|41->14|42->15|42->15|43->16|43->16|44->17|45->18|45->18|47->20|47->20|48->21|49->22|49->22|51->24|51->24|56->29|56->29|56->29|58->31|73->46|73->46|73->46|74->47|75->48|75->48|75->48|76->49|76->49|76->49|77->50|79->52|84->57|84->57|84->57|85->58|90->63|90->63|90->63|91->64|98->71|98->71|98->71|99->72|100->73|100->73|100->73|101->74|101->74|101->74|102->75|104->77|111->84|111->84|111->84|112->85|113->86|113->86|113->86|114->87|114->87|114->87|115->88|117->90|124->97|124->97|124->97|125->98|126->99|126->99|126->99|127->100|127->100|127->100|128->101|130->103|144->117|144->117|144->117|146->119|150->123|150->123|150->123|152->125|160->133|160->133|160->133|161->134|163->136|163->136|163->136|164->137|166->139|168->141|170->143|171->144|173->146|173->146|173->146|173->146|174->147|174->147|174->147|174->147|175->148|175->148|175->148|175->148|176->149|176->149|176->149|176->149|177->150|177->150|177->150|177->150|178->151|178->151|178->151|178->151|179->152|179->152|179->152|181->154|181->154|182->155|182->155|183->156|183->156|184->157|184->157|185->158|185->158|187->160|192->165|194->167|199->172|199->172|199->172|202->175|204->177|204->177|204->177|205->178|206->179|208->181
                  -- GENERATED --
              */
          