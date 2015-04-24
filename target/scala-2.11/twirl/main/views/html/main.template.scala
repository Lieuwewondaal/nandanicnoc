
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
        <title>Verpleegkunde applicatie</title>
        """),format.raw/*13.47*/("""
        """),format.raw/*14.9*/("""<link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(/*14.70*/routes/*14.76*/.Assets.at("stylesheets/bootstrap.min.css")),format.raw/*14.119*/("""">
        
        <link rel="stylesheet" media="screen" href=""""),_display_(/*16.54*/routes/*16.60*/.Assets.at("stylesheets/main.css")),format.raw/*16.94*/(""""/> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        
        <header class="topbar">
            <h1 class="fill">
                <a href=""""),_display_(/*23.27*/routes/*23.33*/.Application.index()),format.raw/*23.53*/("""">
                    Verpleegkunde applicatie
                </a>
            </h1>
        </header>
        
        <section id="main">
            """),_display_(/*30.14*/content),format.raw/*30.21*/("""
        """),format.raw/*31.9*/("""</section>
        
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
                  DATE: Thu Apr 23 14:03:49 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/main.scala.html
                  HASH: 6de23d21b92204d75fbc737324ebde57f6fd6076
                  MATRIX: 720->1|823->16|851->18|969->607|1005->616|1093->677|1108->683|1173->726|1265->791|1280->797|1335->831|1558->1027|1573->1033|1614->1053|1796->1208|1824->1215|1860->1224
                  LINES: 26->1|29->1|31->3|35->13|36->14|36->14|36->14|36->14|38->16|38->16|38->16|45->23|45->23|45->23|52->30|52->30|53->31
                  -- GENERATED --
              */
          