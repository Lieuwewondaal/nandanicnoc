
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
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.17*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>NANDA/NIC/NOC</title>
        
        <link rel='stylesheet' href='"""),_display_(/*8.39*/routes/*8.45*/.Assets.at("stylesheets/bootstrap.min.css")),format.raw/*8.88*/("""'>
        """),format.raw/*11.47*/("""
        """),format.raw/*12.9*/("""<script src=""""),_display_(/*12.23*/routes/*12.29*/.Assets.at("lib/jquery/jquery.js")),format.raw/*12.63*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*13.23*/routes/*13.29*/.Assets.at("lib/bootstrap/js/bootstrap.min.js")),format.raw/*13.76*/("""" type="text/javascript"></script>
		"""),format.raw/*16.47*/("""
        """),format.raw/*17.9*/("""<link rel="stylesheet" media="screen" href=""""),_display_(/*17.54*/routes/*17.60*/.Assets.at("stylesheets/main.css")),format.raw/*17.94*/(""""/> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <header class="topbar">
            <h1 class="fill">
                <a href=""""),_display_(/*24.27*/routes/*24.33*/.Application.index()),format.raw/*24.53*/("""">
                    NANDA/NIC/NOC
                </a>
            </h1>
        </header>

        <section id="main">
		"""),_display_(/*31.4*/content),format.raw/*31.11*/("""
		"""),format.raw/*32.3*/("""</section>
    </body>
</html>
"""))}
  }

  def render(content:Html): play.twirl.api.HtmlFormat.Appendable = apply(content)

  def f:((Html) => play.twirl.api.HtmlFormat.Appendable) = (content) => apply(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon May 11 10:29:38 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/main.scala.html
                  HASH: a957ddb6bd032563879a802893f6c769b33c9a6b
                  MATRIX: 720->1|823->16|851->18|995->136|1009->142|1072->185|1111->377|1147->386|1188->400|1203->406|1258->440|1342->497|1357->503|1425->550|1490->793|1526->802|1598->847|1613->853|1668->887|1883->1075|1898->1081|1939->1101|2091->1227|2119->1234|2149->1237
                  LINES: 26->1|29->1|31->3|36->8|36->8|36->8|37->11|38->12|38->12|38->12|38->12|39->13|39->13|39->13|40->16|41->17|41->17|41->17|41->17|48->24|48->24|48->24|55->31|55->31|56->32
                  -- GENERATED --
              */
          