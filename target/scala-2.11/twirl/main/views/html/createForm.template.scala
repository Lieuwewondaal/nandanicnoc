
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
object createForm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[Diagnoseoverzicht],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(diagnoseForm: Form[Diagnoseoverzicht]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.render) }};
Seq[Any](format.raw/*1.41*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.80*/(""" 

"""),_display_(/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
	"""),format.raw/*8.2*/("""<script>
	  $( document ).ready(function() """),format.raw/*9.35*/("""{"""),format.raw/*9.36*/("""
		"""),format.raw/*10.3*/("""console.log( "ready!" );
		//$( ".modal-body" ).load( "../" );
	"""),format.raw/*12.2*/("""}"""),format.raw/*12.3*/(""");
	</script>

	<!-- Modal 
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">Modal title</h4>
		  </div>
		  <div class="modal-body">
			...
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		</div>
	  </div>
	</div>-->
    <h1>Add a diagnose</h1>
	
	"""),_display_(/*35.3*/helper/*35.9*/.form(action = routes.Application.upload(), 'enctype -> "multipart/form-data")/*35.87*/ {_display_(Seq[Any](format.raw/*35.89*/("""
	"""),format.raw/*36.2*/("""<input type="file" name="picture">
	<p>
	  <input type="submit">
    </p>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
	  Launch demo modal
	</button>
	""")))}),format.raw/*44.3*/("""
	
	"""),_display_(/*46.3*/form(routes.Application.save())/*46.34*/ {_display_(Seq[Any](format.raw/*46.36*/("""
        
        """),format.raw/*48.9*/("""<fieldset>
        
            """),_display_(/*50.14*/inputText(diagnoseForm("diagnoseoverzicht_omschrijving"), '_label -> "Diagnose naam", '_help -> "")),format.raw/*50.113*/("""
			"""),_display_(/*51.5*/inputText(diagnoseForm("diagnoseoverzicht_definitie"), '_label -> "Diagnose omschrijving", '_help -> "")),format.raw/*51.109*/("""
			"""),_display_(/*52.5*/inputText(diagnoseForm("diagnose_code"), '_label -> "Diagnose code", '_help -> "")),format.raw/*52.87*/("""
			"""),_display_(/*53.5*/inputText(diagnoseForm("diagnoseklasse.diagnoseklasse_klasse"), '_label -> "Diagnose klasse", '_help -> "")),format.raw/*53.112*/("""
			"""),_display_(/*54.5*/inputText(diagnoseForm("diagnosedomein.diagnosedomein_domein"), '_label -> "Diagnose domein", '_help -> "")),format.raw/*54.112*/("""
			
            """),_display_(/*56.14*/select(
                diagnoseForm("gezondheidspatroon.gezondheidspatroon_id"), 
                options(Gezondheidspatroon.options), 
                '_label -> "Patroon", '_default -> "-- Kies een patroon --",
                '_showConstraints -> false
            )),format.raw/*61.14*/("""
            

        """),format.raw/*64.9*/("""</fieldset>
        
        <div class="actions">
            <input type="submit" value="Create this diagnose" class="btn primary"> or 
            <a href=""""),_display_(/*68.23*/routes/*68.29*/.Application.list()),format.raw/*68.48*/("""" class="btn">Cancel</a> 
        </div>
        
    """)))}),format.raw/*71.6*/("""
    
""")))}))}
  }

  def render(diagnoseForm:Form[Diagnoseoverzicht]): play.twirl.api.HtmlFormat.Appendable = apply(diagnoseForm)

  def f:((Form[Diagnoseoverzicht]) => play.twirl.api.HtmlFormat.Appendable) = (diagnoseForm) => apply(diagnoseForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed May 20 15:13:48 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/createForm.scala.html
                  HASH: 199571a5b9ca4134a86854ccddc02bbc2e45cfdc
                  MATRIX: 745->1|879->61|911->85|995->40|1023->59|1051->139|1080->143|1091->147|1129->149|1157->151|1227->194|1255->195|1285->198|1376->262|1404->263|2174->1007|2188->1013|2275->1091|2315->1093|2344->1095|2611->1332|2642->1337|2682->1368|2722->1370|2767->1388|2827->1421|2948->1520|2979->1525|3105->1629|3136->1634|3239->1716|3270->1721|3399->1828|3430->1833|3559->1940|3604->1958|3895->2228|3945->2251|4132->2411|4147->2417|4187->2436|4272->2491
                  LINES: 26->1|28->5|28->5|29->1|31->4|32->5|34->7|34->7|34->7|35->8|36->9|36->9|37->10|39->12|39->12|62->35|62->35|62->35|62->35|63->36|71->44|73->46|73->46|73->46|75->48|77->50|77->50|78->51|78->51|79->52|79->52|80->53|80->53|81->54|81->54|83->56|88->61|91->64|95->68|95->68|95->68|98->71
                  -- GENERATED --
              */
          