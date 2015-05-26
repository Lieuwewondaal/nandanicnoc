
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
object editForm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[Long,Form[Diagnoseoverzicht],List[Bepalendkenmerk_Diagnose],List[Nic],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, diagnoseForm: Form[Diagnoseoverzicht], bepalendkenmerk_diagnose: List[Bepalendkenmerk_Diagnose], nic: List[Nic]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.render) }};
Seq[Any](format.raw/*1.125*/("""

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
						<th>Interventie definitie:
					</tr>
				</thead>
				<tbody>

					"""),_display_(/*134.7*/for(interventies <- nic) yield /*134.31*/ {_display_(Seq[Any](format.raw/*134.33*/("""
						"""),format.raw/*135.7*/("""<tr>
							<td>
								"""),_display_(/*137.10*/interventies/*137.22*/.nicoverzicht.get(0).nicoverzicht_omschrijving),format.raw/*137.68*/("""
							"""),format.raw/*138.8*/("""</td>
							<td>
								"""),_display_(/*140.10*/interventies/*140.22*/.nicoverzicht.get(0).nicoverzicht_definitie),format.raw/*140.65*/("""
							"""),format.raw/*141.8*/("""</td>
						</tr>
					""")))}),format.raw/*143.7*/("""

				"""),format.raw/*145.5*/("""</tbody>
			</table>
		""")))}),format.raw/*147.4*/("""
		"""),format.raw/*148.3*/("""<!--
		a """),_display_(/*149.6*/diagnoseForm("diagnoseoverzicht_omschrijving")/*149.52*/.value),format.raw/*149.58*/(""" """),format.raw/*149.59*/("""<br />
		a """),_display_(/*150.6*/diagnoseForm("diagnoseoverzicht_definitie")/*150.49*/.value),format.raw/*150.55*/(""" """),format.raw/*150.56*/("""<br />
		a """),_display_(/*151.6*/diagnoseForm("diagnose_code")/*151.35*/.value),format.raw/*151.41*/(""" """),format.raw/*151.42*/("""<br />
		a """),_display_(/*152.6*/diagnoseForm("diagnoseklasse_code")/*152.41*/.value),format.raw/*152.47*/(""" """),format.raw/*152.48*/("""<br />
		a """),_display_(/*153.6*/diagnoseForm("diagnosedomein_code")/*153.41*/.value),format.raw/*153.47*/(""" """),format.raw/*153.48*/("""<br />
		a """),_display_(/*154.6*/diagnoseForm("gezondheidspatroon_id")/*154.43*/.value),format.raw/*154.49*/(""" """),format.raw/*154.50*/("""<br />
		a """),_display_(/*155.6*/diagnoseForm("diagnoseklasse.diagnoseklasse_code")/*155.56*/.value),format.raw/*155.62*/("""
		
            """),_display_(/*157.14*/inputText(diagnoseForm("diagnoseoverzicht_omschrijving"), '_label -> "Diagnose naam", '_help -> "")),format.raw/*157.113*/("""
			"""),_display_(/*158.5*/inputText(diagnoseForm("diagnoseoverzicht_definitie"), '_label -> "Diagnose omschrijving", '_help -> "")),format.raw/*158.109*/("""
			"""),_display_(/*159.5*/inputText(diagnoseForm("diagnose_code"), '_label -> "Diagnose code", '_help -> "")),format.raw/*159.87*/("""
			"""),_display_(/*160.5*/inputText(diagnoseForm("diagnoseklasse.diagnoseklasse_code"), '_label -> "Diagnose klasse", '_help -> "")),format.raw/*160.110*/("""
			"""),_display_(/*161.5*/inputText(diagnoseForm("diagnoseklasse.diagnosedomein.diagnosedomein_code"), '_label -> "Diagnose domein", '_help -> "")),format.raw/*161.125*/("""
			
            """),_display_(/*163.14*/select(
                diagnoseForm("gezondheidspatroon.gezondheidspatroon_id"), 
                options(Gezondheidspatroon.options), 
                '_label -> "Patroon", '_default -> "-- Kies een patroon --",
                '_showConstraints -> false
            )),format.raw/*168.14*/("""
            
		"""),format.raw/*170.3*/("""-->
        </fieldset>
        
        <div class="actions">
            <!--<input type="submit" value="Save this diagnose" class="btn primary"> or -->
            <a href=""""),_display_(/*175.23*/routes/*175.29*/.Application.list()),format.raw/*175.48*/("""" class="btn">Terug</a> 
        </div>
        
    """)))}),format.raw/*178.6*/("""
    
    """),_display_(/*180.6*/form(routes.Application.delete(id), 'class -> "topRight")/*180.63*/ {_display_(Seq[Any](format.raw/*180.65*/("""
        """),format.raw/*181.9*/("""<input type="submit" value="Delete this diagnose" class="btn danger">
    """)))}),format.raw/*182.6*/("""
    
""")))}),format.raw/*184.2*/("""
"""))}
  }

  def render(id:Long,diagnoseForm:Form[Diagnoseoverzicht],bepalendkenmerk_diagnose:List[Bepalendkenmerk_Diagnose],nic:List[Nic]): play.twirl.api.HtmlFormat.Appendable = apply(id,diagnoseForm,bepalendkenmerk_diagnose,nic)

  def f:((Long,Form[Diagnoseoverzicht],List[Bepalendkenmerk_Diagnose],List[Nic]) => play.twirl.api.HtmlFormat.Appendable) = (id,diagnoseForm,bepalendkenmerk_diagnose,nic) => apply(id,diagnoseForm,bepalendkenmerk_diagnose,nic)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue May 26 13:51:12 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/editForm.scala.html
                  HASH: 205db14a1a935dd241180a50a437552ef762de1f
                  MATRIX: 789->1|1007->145|1039->169|1124->124|1152->143|1180->223|1209->227|1220->231|1258->233|1286->235|1356->278|1384->279|1415->283|1492->333|1515->335|1544->336|1620->385|1643->387|1672->388|1762->451|1785->453|1814->454|1892->504|1921->505|1953->510|2011->541|2039->542|2115->590|2144->591|2176->596|2235->628|2263->629|2349->687|2378->688|2410->693|2469->725|2497->726|2532->734|2560->735|2639->788|2683->823|2723->825|2768->843|3072->1121|3122->1162|3162->1164|3197->1172|3233->1191|3246->1195|3285->1196|3320->1205|3358->1234|3385->1240|3423->1248|3463->1261|3558->1330|3613->1376|3640->1382|3673->1388|3764->1453|3816->1496|3843->1502|3876->1508|3981->1587|4067->1664|4107->1666|4142->1674|4178->1693|4191->1697|4230->1698|4265->1707|4339->1772|4366->1778|4404->1786|4444->1799|4549->1878|4620->1940|4660->1942|4695->1950|4731->1969|4744->1973|4783->1974|4818->1983|4877->2033|4904->2039|4942->2047|4982->2060|5099->2151|5186->2229|5226->2231|5261->2239|5297->2258|5310->2262|5349->2263|5385->2272|5461->2338|5489->2344|5528->2352|5569->2365|5769->2538|5798->2557|5839->2559|5875->2567|5970->2644|5984->2649|6024->2650|6060->2658|6266->2837|6307->2861|6348->2863|6383->2870|6437->2896|6459->2908|6527->2954|6563->2962|6618->2989|6640->3001|6705->3044|6741->3052|6796->3076|6830->3082|6885->3106|6916->3109|6953->3119|7009->3165|7037->3171|7067->3172|7106->3184|7159->3227|7187->3233|7217->3234|7256->3246|7295->3275|7323->3281|7353->3282|7392->3294|7437->3329|7465->3335|7495->3336|7534->3348|7579->3383|7607->3389|7637->3390|7676->3402|7723->3439|7751->3445|7781->3446|7820->3458|7880->3508|7908->3514|7953->3531|8075->3630|8107->3635|8234->3739|8266->3744|8370->3826|8402->3831|8530->3936|8562->3941|8705->4061|8751->4079|9043->4349|9087->4365|9292->4542|9308->4548|9349->4567|9434->4621|9472->4632|9539->4689|9580->4691|9617->4700|9723->4775|9761->4782
                  LINES: 26->1|28->5|28->5|29->1|31->4|32->5|34->7|34->7|34->7|35->8|36->9|36->9|37->10|37->10|37->10|37->10|38->11|38->11|38->11|39->12|39->12|39->12|40->13|40->13|41->14|42->15|42->15|43->16|43->16|44->17|45->18|45->18|47->20|47->20|48->21|49->22|49->22|51->24|51->24|56->29|56->29|56->29|58->31|73->46|73->46|73->46|74->47|75->48|75->48|75->48|76->49|76->49|76->49|77->50|79->52|84->57|84->57|84->57|85->58|90->63|90->63|90->63|91->64|98->71|98->71|98->71|99->72|100->73|100->73|100->73|101->74|101->74|101->74|102->75|104->77|111->84|111->84|111->84|112->85|113->86|113->86|113->86|114->87|114->87|114->87|115->88|117->90|124->97|124->97|124->97|125->98|126->99|126->99|126->99|127->100|127->100|127->100|128->101|130->103|144->117|144->117|144->117|146->119|150->123|150->123|150->123|152->125|161->134|161->134|161->134|162->135|164->137|164->137|164->137|165->138|167->140|167->140|167->140|168->141|170->143|172->145|174->147|175->148|176->149|176->149|176->149|176->149|177->150|177->150|177->150|177->150|178->151|178->151|178->151|178->151|179->152|179->152|179->152|179->152|180->153|180->153|180->153|180->153|181->154|181->154|181->154|181->154|182->155|182->155|182->155|184->157|184->157|185->158|185->158|186->159|186->159|187->160|187->160|188->161|188->161|190->163|195->168|197->170|202->175|202->175|202->175|205->178|207->180|207->180|207->180|208->181|209->182|211->184
                  -- GENERATED --
              */
          