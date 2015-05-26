
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
object editForm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[Long,Form[Diagnoseoverzicht],List[Bepalendkenmerk_Diagnose],List[Nic_Diagnose],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, diagnoseForm: Form[Diagnoseoverzicht], bepalendkenmerk_diagnose: List[Bepalendkenmerk_Diagnose], nic: List[Nic_Diagnose]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.render) }};
Seq[Any](format.raw/*1.134*/("""

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

		"""),_display_(/*117.4*/if(nic.size() == 0)/*117.23*/ {_display_(Seq[Any](format.raw/*117.25*/("""
			
			"""),format.raw/*119.4*/("""<div class="well">
				<em>Geen interventies gevonden</em>
			</div>
			
		""")))}/*123.5*/else/*123.10*/{_display_(Seq[Any](format.raw/*123.11*/("""
			
			"""),format.raw/*125.4*/("""<table class="diagnoses zebra-striped">
				<thead>
					<tr>
						<th>Interventie omschrijving:</th>
						<th>Interventie definitie:</th>
						<th>Interventie actie:</th>
					</tr>
				</thead>
				<tbody>

					"""),_display_(/*135.7*/for(interventies <- nic) yield /*135.31*/ {_display_(Seq[Any](format.raw/*135.33*/("""
						"""),format.raw/*136.7*/("""<tr>
							<td>
								"""),_display_(/*138.10*/interventies/*138.22*/.nic.nicoverzicht.get(0).nicoverzicht_omschrijving),format.raw/*138.72*/("""
							"""),format.raw/*139.8*/("""</td>
							<td>
								"""),_display_(/*141.10*/interventies/*141.22*/.nic.nicoverzicht.get(0).nicoverzicht_definitie),format.raw/*141.69*/("""
							"""),format.raw/*142.8*/("""</td>
							<td>
								"""),_display_(/*144.10*/interventies/*144.22*/.nicactiviteit.nicactiviteit_omschrijving),format.raw/*144.63*/("""
							"""),format.raw/*145.8*/("""</td>
						</tr>
					""")))}),format.raw/*147.7*/("""

				"""),format.raw/*149.5*/("""</tbody>
			</table>
		""")))}),format.raw/*151.4*/("""
		"""),format.raw/*152.3*/("""<!--
		a """),_display_(/*153.6*/diagnoseForm("diagnoseoverzicht_omschrijving")/*153.52*/.value),format.raw/*153.58*/(""" """),format.raw/*153.59*/("""<br />
		a """),_display_(/*154.6*/diagnoseForm("diagnoseoverzicht_definitie")/*154.49*/.value),format.raw/*154.55*/(""" """),format.raw/*154.56*/("""<br />
		a """),_display_(/*155.6*/diagnoseForm("diagnose_code")/*155.35*/.value),format.raw/*155.41*/(""" """),format.raw/*155.42*/("""<br />
		a """),_display_(/*156.6*/diagnoseForm("diagnoseklasse_code")/*156.41*/.value),format.raw/*156.47*/(""" """),format.raw/*156.48*/("""<br />
		a """),_display_(/*157.6*/diagnoseForm("diagnosedomein_code")/*157.41*/.value),format.raw/*157.47*/(""" """),format.raw/*157.48*/("""<br />
		a """),_display_(/*158.6*/diagnoseForm("gezondheidspatroon_id")/*158.43*/.value),format.raw/*158.49*/(""" """),format.raw/*158.50*/("""<br />
		a """),_display_(/*159.6*/diagnoseForm("diagnoseklasse.diagnoseklasse_code")/*159.56*/.value),format.raw/*159.62*/("""
		
            """),_display_(/*161.14*/inputText(diagnoseForm("diagnoseoverzicht_omschrijving"), '_label -> "Diagnose naam", '_help -> "")),format.raw/*161.113*/("""
			"""),_display_(/*162.5*/inputText(diagnoseForm("diagnoseoverzicht_definitie"), '_label -> "Diagnose omschrijving", '_help -> "")),format.raw/*162.109*/("""
			"""),_display_(/*163.5*/inputText(diagnoseForm("diagnose_code"), '_label -> "Diagnose code", '_help -> "")),format.raw/*163.87*/("""
			"""),_display_(/*164.5*/inputText(diagnoseForm("diagnoseklasse.diagnoseklasse_code"), '_label -> "Diagnose klasse", '_help -> "")),format.raw/*164.110*/("""
			"""),_display_(/*165.5*/inputText(diagnoseForm("diagnoseklasse.diagnosedomein.diagnosedomein_code"), '_label -> "Diagnose domein", '_help -> "")),format.raw/*165.125*/("""
			
            """),_display_(/*167.14*/select(
                diagnoseForm("gezondheidspatroon.gezondheidspatroon_id"), 
                options(Gezondheidspatroon.options), 
                '_label -> "Patroon", '_default -> "-- Kies een patroon --",
                '_showConstraints -> false
            )),format.raw/*172.14*/("""
            
		"""),format.raw/*174.3*/("""-->
        </fieldset>
        
        <div class="actions">
            <!--<input type="submit" value="Save this diagnose" class="btn primary"> or -->
            <a href=""""),_display_(/*179.23*/routes/*179.29*/.Application.list()),format.raw/*179.48*/("""" class="btn">Terug</a> 
        </div>
        
    """)))}),format.raw/*182.6*/("""
    
    """),_display_(/*184.6*/form(routes.Application.delete(id), 'class -> "topRight")/*184.63*/ {_display_(Seq[Any](format.raw/*184.65*/("""
        """),format.raw/*185.9*/("""<input type="submit" value="Delete this diagnose" class="btn danger">
    """)))}),format.raw/*186.6*/("""
    
""")))}),format.raw/*188.2*/("""
"""))}
  }

  def render(id:Long,diagnoseForm:Form[Diagnoseoverzicht],bepalendkenmerk_diagnose:List[Bepalendkenmerk_Diagnose],nic:List[Nic_Diagnose]): play.twirl.api.HtmlFormat.Appendable = apply(id,diagnoseForm,bepalendkenmerk_diagnose,nic)

  def f:((Long,Form[Diagnoseoverzicht],List[Bepalendkenmerk_Diagnose],List[Nic_Diagnose]) => play.twirl.api.HtmlFormat.Appendable) = (id,diagnoseForm,bepalendkenmerk_diagnose,nic) => apply(id,diagnoseForm,bepalendkenmerk_diagnose,nic)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue May 26 14:27:23 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/editForm.scala.html
                  HASH: 9fe9925117007c45d0624c8ca849106f1e02fb9d
                  MATRIX: 798->1|1025->154|1057->178|1142->133|1170->152|1198->232|1227->236|1238->240|1276->242|1304->244|1374->287|1402->288|1433->292|1510->342|1533->344|1562->345|1638->394|1661->396|1690->397|1780->460|1803->462|1832->463|1910->513|1939->514|1971->519|2029->550|2057->551|2133->599|2162->600|2194->605|2253->637|2281->638|2367->696|2396->697|2428->702|2487->734|2515->735|2550->743|2578->744|2657->797|2701->832|2741->834|2786->852|3090->1130|3140->1171|3180->1173|3215->1181|3251->1200|3264->1204|3303->1205|3338->1214|3376->1243|3403->1249|3441->1257|3481->1270|3576->1339|3631->1385|3658->1391|3691->1397|3782->1462|3834->1505|3861->1511|3894->1517|3999->1596|4085->1673|4125->1675|4160->1683|4196->1702|4209->1706|4248->1707|4283->1716|4357->1781|4384->1787|4422->1795|4462->1808|4567->1887|4638->1949|4678->1951|4713->1959|4749->1978|4762->1982|4801->1983|4836->1992|4895->2042|4922->2048|4960->2056|5000->2069|5117->2160|5204->2238|5244->2240|5279->2248|5315->2267|5328->2271|5367->2272|5403->2281|5479->2347|5507->2353|5546->2361|5587->2374|5787->2547|5816->2566|5857->2568|5893->2576|5988->2653|6002->2658|6042->2659|6078->2667|6323->2885|6364->2909|6405->2911|6440->2918|6494->2944|6516->2956|6588->3006|6624->3014|6679->3041|6701->3053|6770->3100|6806->3108|6861->3135|6883->3147|6946->3188|6982->3196|7037->3220|7071->3226|7126->3250|7157->3253|7194->3263|7250->3309|7278->3315|7308->3316|7347->3328|7400->3371|7428->3377|7458->3378|7497->3390|7536->3419|7564->3425|7594->3426|7633->3438|7678->3473|7706->3479|7736->3480|7775->3492|7820->3527|7848->3533|7878->3534|7917->3546|7964->3583|7992->3589|8022->3590|8061->3602|8121->3652|8149->3658|8194->3675|8316->3774|8348->3779|8475->3883|8507->3888|8611->3970|8643->3975|8771->4080|8803->4085|8946->4205|8992->4223|9284->4493|9328->4509|9533->4686|9549->4692|9590->4711|9675->4765|9713->4776|9780->4833|9821->4835|9858->4844|9964->4919|10002->4926
                  LINES: 26->1|28->5|28->5|29->1|31->4|32->5|34->7|34->7|34->7|35->8|36->9|36->9|37->10|37->10|37->10|37->10|38->11|38->11|38->11|39->12|39->12|39->12|40->13|40->13|41->14|42->15|42->15|43->16|43->16|44->17|45->18|45->18|47->20|47->20|48->21|49->22|49->22|51->24|51->24|56->29|56->29|56->29|58->31|73->46|73->46|73->46|74->47|75->48|75->48|75->48|76->49|76->49|76->49|77->50|79->52|84->57|84->57|84->57|85->58|90->63|90->63|90->63|91->64|98->71|98->71|98->71|99->72|100->73|100->73|100->73|101->74|101->74|101->74|102->75|104->77|111->84|111->84|111->84|112->85|113->86|113->86|113->86|114->87|114->87|114->87|115->88|117->90|124->97|124->97|124->97|125->98|126->99|126->99|126->99|127->100|127->100|127->100|128->101|130->103|144->117|144->117|144->117|146->119|150->123|150->123|150->123|152->125|162->135|162->135|162->135|163->136|165->138|165->138|165->138|166->139|168->141|168->141|168->141|169->142|171->144|171->144|171->144|172->145|174->147|176->149|178->151|179->152|180->153|180->153|180->153|180->153|181->154|181->154|181->154|181->154|182->155|182->155|182->155|182->155|183->156|183->156|183->156|183->156|184->157|184->157|184->157|184->157|185->158|185->158|185->158|185->158|186->159|186->159|186->159|188->161|188->161|189->162|189->162|190->163|190->163|191->164|191->164|192->165|192->165|194->167|199->172|201->174|206->179|206->179|206->179|209->182|211->184|211->184|211->184|212->185|213->186|215->188
                  -- GENERATED --
              */
          