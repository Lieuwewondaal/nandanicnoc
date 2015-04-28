
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
object createForm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[Diagnose],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(diagnoseForm: Form[Diagnose]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.render) }};
Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.80*/(""" 

"""),_display_(/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
	"""),format.raw/*8.2*/("""<script>
	  $( document ).ready(function() """),format.raw/*9.35*/("""{"""),format.raw/*9.36*/("""
		"""),format.raw/*10.3*/("""console.log( "ready!" );
		$( ".modal-body" ).load( "../" );
	"""),format.raw/*12.2*/("""}"""),format.raw/*12.3*/(""");
	</script>

	<!-- Modal -->
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
	</div>
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
        
            """),_display_(/*50.14*/inputText(diagnoseForm("name"), '_label -> "Diagnose name", '_help -> "")),format.raw/*50.87*/("""
            """),_display_(/*51.14*/inputDate(diagnoseForm("introduced"), '_label -> "Introduced date", '_help -> "")),format.raw/*51.95*/("""
            """),_display_(/*52.14*/inputDate(diagnoseForm("discontinued"), '_label -> "Discontinued date", '_help -> "")),format.raw/*52.99*/("""

            """),_display_(/*54.14*/select(
                diagnoseForm("company.id"), 
                options(Company.options), 
                '_label -> "Company", '_default -> "-- Choose a company --",
                '_showConstraints -> false
            )),format.raw/*59.14*/("""
            

        """),format.raw/*62.9*/("""</fieldset>
        
        <div class="actions">
            <input type="submit" value="Create this diagnose" class="btn primary"> or 
            <a href=""""),_display_(/*66.23*/routes/*66.29*/.Application.list()),format.raw/*66.48*/("""" class="btn">Cancel</a> 
        </div>
        
    """)))}),format.raw/*69.6*/("""
    
""")))}))}
  }

  def render(diagnoseForm:Form[Diagnose]): play.twirl.api.HtmlFormat.Appendable = apply(diagnoseForm)

  def f:((Form[Diagnose]) => play.twirl.api.HtmlFormat.Appendable) = (diagnoseForm) => apply(diagnoseForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Apr 28 15:56:03 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/createForm.scala.html
                  HASH: bfd580f9e312fa0b613008091204b794fe2bd740
                  MATRIX: 736->1|861->52|893->76|977->31|1005->50|1033->130|1062->134|1073->138|1111->140|1139->142|1209->185|1237->186|1267->189|1356->251|1384->252|2154->996|2168->1002|2255->1080|2295->1082|2324->1084|2591->1321|2622->1326|2662->1357|2702->1359|2747->1377|2807->1410|2901->1483|2942->1497|3044->1578|3085->1592|3191->1677|3233->1692|3483->1921|3533->1944|3720->2104|3735->2110|3775->2129|3860->2184
                  LINES: 26->1|28->5|28->5|29->1|31->4|32->5|34->7|34->7|34->7|35->8|36->9|36->9|37->10|39->12|39->12|62->35|62->35|62->35|62->35|63->36|71->44|73->46|73->46|73->46|75->48|77->50|77->50|78->51|78->51|79->52|79->52|81->54|86->59|89->62|93->66|93->66|93->66|96->69
                  -- GENERATED --
              */
          