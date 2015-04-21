
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
object twitterBootstrapInput extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[helper.FieldElements,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(elements: helper.FieldElements):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.34*/("""

"""),format.raw/*5.53*/("""
"""),format.raw/*6.1*/("""<div class="clearfix """),_display_(/*6.23*/if(elements.hasErrors)/*6.45*/ {_display_(Seq[Any](format.raw/*6.47*/("""error""")))}),format.raw/*6.53*/("""">
    <label for=""""),_display_(/*7.18*/elements/*7.26*/.id),format.raw/*7.29*/("""">"""),_display_(/*7.32*/elements/*7.40*/.label),format.raw/*7.46*/("""</label>
    <div class="input">
        """),_display_(/*9.10*/elements/*9.18*/.input),format.raw/*9.24*/("""
        """),format.raw/*10.9*/("""<span class="help-inline">"""),_display_(/*10.36*/elements/*10.44*/.infos.mkString(", ")),format.raw/*10.65*/("""</span> 
    </div>
</div>
"""))}
  }

  def render(elements:helper.FieldElements): play.twirl.api.HtmlFormat.Appendable = apply(elements)

  def f:((helper.FieldElements) => play.twirl.api.HtmlFormat.Appendable) = (elements) => apply(elements)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Apr 20 08:49:45 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/twitterBootstrapInput.scala.html
                  HASH: 4d027f50d85147935109e27fc6a6135ae86880fa
                  MATRIX: 753->1|873->33|902->193|929->194|977->216|1007->238|1046->240|1082->246|1128->266|1144->274|1167->277|1196->280|1212->288|1238->294|1306->336|1322->344|1348->350|1384->359|1438->386|1455->394|1497->415
                  LINES: 26->1|29->1|31->5|32->6|32->6|32->6|32->6|32->6|33->7|33->7|33->7|33->7|33->7|33->7|35->9|35->9|35->9|36->10|36->10|36->10|36->10
                  -- GENERATED --
              */
          