
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
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[com.avaje.ebean.Page[Diagnoseoverzicht],String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: com.avaje.ebean.Page[Diagnoseoverzicht], currentSortBy: String, currentOrder: String, currentFilter: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {
def /*32.2*/header/*32.8*/(key:String, title:String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*32.38*/("""
    """),format.raw/*33.5*/("""<th class=""""),_display_(/*33.17*/key/*33.20*/.replace(".","_")),format.raw/*33.37*/(""" """),format.raw/*33.38*/("""header """),_display_(/*33.46*/if(currentSortBy == key){/*33.72*/{if(currentOrder == "asc") "headerSortDown" else "headerSortUp"}}),format.raw/*33.136*/("""">
        <a href=""""),_display_(/*34.19*/link(0, key)),format.raw/*34.31*/("""">"""),_display_(/*34.34*/title),format.raw/*34.39*/("""</a>
    </th>
""")))};def /*6.2*/link/*6.6*/(newPage:Int, newSortBy:String) = {{
    
    var sortBy = currentSortBy
    var order = currentOrder
    
    if(newSortBy != null) {
        sortBy = newSortBy
        if(currentSortBy == newSortBy) {
            if(currentOrder == "asc") {
                order = "desc"
            } else {
                order = "asc"
            }
        } else {
            order = "asc"
        }
    }
    
    // Generate the link
    routes.Application.list(newPage, sortBy, order, currentFilter)
    
}};
Seq[Any](format.raw/*1.124*/("""

"""),format.raw/*5.42*/("""
"""),format.raw/*27.2*/("""

"""),format.raw/*31.37*/("""
"""),format.raw/*36.2*/("""

"""),_display_(/*38.2*/main/*38.6*/ {_display_(Seq[Any](format.raw/*38.8*/("""
    
    """),format.raw/*40.5*/("""<h1 id="homeTitle">"""),_display_(/*40.25*/Messages("diagnose.list.title", currentPage.getTotalRowCount)),format.raw/*40.86*/("""</h1>

    """),_display_(/*42.6*/if(flash.containsKey("success"))/*42.38*/ {_display_(Seq[Any](format.raw/*42.40*/("""
        """),format.raw/*43.9*/("""<div class="alert-message warning">
            <strong>Done!</strong> """),_display_(/*44.37*/flash/*44.42*/.get("success")),format.raw/*44.57*/("""
        """),format.raw/*45.9*/("""</div>
    """)))}),format.raw/*46.6*/(""" 

    """),format.raw/*48.5*/("""<div id="actions">
        
        <form action=""""),_display_(/*50.24*/link(0, "name")),format.raw/*50.39*/("""" method="GET">
            <input type="search" id="searchbox" name="f" value=""""),_display_(/*51.66*/currentFilter),format.raw/*51.79*/("""" placeholder="Diagnose naam">
            <input type="submit" id="searchsubmit" value="Zoeken" class="btn primary">
        </form>
        
        <a class="btn success" id="add" href=""""),_display_(/*55.48*/routes/*55.54*/.Application.create()),format.raw/*55.75*/("""">Nieuwe Diagnose</a>
        
    </div>
    
    """),_display_(/*59.6*/if(currentPage.getTotalRowCount == 0)/*59.43*/ {_display_(Seq[Any](format.raw/*59.45*/("""
        
        """),format.raw/*61.9*/("""<div class="well">
            <em>Nothing to display</em>
        </div>
        
    """)))}/*65.7*/else/*65.12*/{_display_(Seq[Any](format.raw/*65.13*/("""
        
        """),format.raw/*67.9*/("""<table class="diagnoses zebra-striped">
            <thead>
                <tr>
                    """),_display_(/*70.22*/header("diagnoseoverzicht_omschrijving", "Diagnose name")),format.raw/*70.79*/("""
                    """),_display_(/*71.22*/header("diagnose_code", "Code")),format.raw/*71.53*/("""
                    """),_display_(/*72.22*/header("gezondheidspatroon.gezondheidspatroon_omschrijving", "Patroon")),format.raw/*72.93*/("""
                    """),_display_(/*73.22*/header("diagnose.diagnose_id", "DiagnoseID")),format.raw/*73.66*/("""
                """),format.raw/*74.17*/("""</tr>
            </thead>
            <tbody>

                """),_display_(/*78.18*/for(diagnoseoverzicht <- currentPage.getList) yield /*78.63*/ {_display_(Seq[Any](format.raw/*78.65*/("""
                    """),format.raw/*79.21*/("""<tr>
                        <td><a href=""""),_display_(/*80.39*/routes/*80.45*/.Application.edit(diagnoseoverzicht.diagnose.diagnose_id )),format.raw/*80.103*/("""">"""),_display_(/*80.106*/diagnoseoverzicht/*80.123*/.diagnoseoverzicht_omschrijving),format.raw/*80.154*/("""</a></td>
                        <td>
                            """),_display_(/*82.30*/if(diagnoseoverzicht.diagnose_code == null)/*82.73*/ {_display_(Seq[Any](format.raw/*82.75*/("""
                                """),format.raw/*83.33*/("""<em>-</em>
                            """)))}/*84.31*/else/*84.35*/{_display_(Seq[Any](format.raw/*84.36*/("""
								"""),_display_(/*85.10*/diagnoseoverzicht/*85.27*/.diagnose_code),format.raw/*85.41*/("""
							""")))}),format.raw/*86.9*/("""
							
                        """),format.raw/*88.25*/("""</td>
                        <td>
                            """),_display_(/*90.30*/if(diagnoseoverzicht.gezondheidspatroon.gezondheidspatroon_omschrijving == null)/*90.110*/ {_display_(Seq[Any](format.raw/*90.112*/("""
                                """),format.raw/*91.33*/("""<em>-</em>
                            """)))}/*92.31*/else/*92.36*/{_display_(Seq[Any](format.raw/*92.37*/("""
                                """),_display_(/*93.34*/diagnoseoverzicht/*93.51*/.gezondheidspatroon.gezondheidspatroon_omschrijving),format.raw/*93.102*/("""
                            """)))}),format.raw/*94.30*/("""
                        """),format.raw/*95.25*/("""</td>
                        <td>
                                """),_display_(/*97.34*/diagnoseoverzicht/*97.51*/.diagnose.diagnose_id),format.raw/*97.72*/("""
                        """),format.raw/*98.25*/("""</td>
                    </tr>
                """)))}),format.raw/*100.18*/("""

            """),format.raw/*102.13*/("""</tbody>
        </table>

        <div id="pagination" class="pagination">
            <ul>
                """),_display_(/*107.18*/if(currentPage.hasPrev)/*107.41*/ {_display_(Seq[Any](format.raw/*107.43*/("""
                    """),format.raw/*108.21*/("""<li class="prev">
                        <a href=""""),_display_(/*109.35*/link(currentPage.getPageIndex - 1, null)),format.raw/*109.75*/("""">&larr; Previous</a>
                    </li>
                """)))}/*111.19*/else/*111.24*/{_display_(Seq[Any](format.raw/*111.25*/("""
                    """),format.raw/*112.21*/("""<li class="prev disabled">
                        <a>&larr; Previous</a>
                    </li>
                """)))}),format.raw/*115.18*/("""
                """),format.raw/*116.17*/("""<li class="current">
                    <a>Displaying """),_display_(/*117.36*/currentPage/*117.47*/.getDisplayXtoYofZ(" to "," of ")),format.raw/*117.80*/("""</a>
                </li>
                """),_display_(/*119.18*/if(currentPage.hasNext)/*119.41*/ {_display_(Seq[Any](format.raw/*119.43*/("""
                    """),format.raw/*120.21*/("""<li class="next">
                        <a href=""""),_display_(/*121.35*/link(currentPage.getPageIndex + 1, null)),format.raw/*121.75*/("""">Next &rarr;</a>
                    </li>
                """)))}/*123.19*/else/*123.24*/{_display_(Seq[Any](format.raw/*123.25*/("""
                    """),format.raw/*124.21*/("""<li class="next disabled">
                        <a>Next &rarr;</a>
                    </li>
                """)))}),format.raw/*127.18*/("""
            """),format.raw/*128.13*/("""</ul>
        </div>
        
    """)))}),format.raw/*131.6*/("""
        
""")))}),format.raw/*133.2*/("""

            """))}
  }

  def render(currentPage:com.avaje.ebean.Page[Diagnoseoverzicht],currentSortBy:String,currentOrder:String,currentFilter:String): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentSortBy,currentOrder,currentFilter)

  def f:((com.avaje.ebean.Page[Diagnoseoverzicht],String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentSortBy,currentOrder,currentFilter) => apply(currentPage,currentSortBy,currentOrder,currentFilter)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Fri May 08 15:39:25 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/list.scala.html
                  HASH: 00dcd99b175c75cad9605c4b47ea1211b8a160db
                  MATRIX: 776->1|970->869|984->875|1091->905|1123->910|1162->922|1174->925|1212->942|1241->943|1276->951|1310->977|1397->1041|1445->1062|1478->1074|1508->1077|1534->1082|1572->252|1583->256|2115->123|2144->250|2172->757|2202->867|2230->1098|2259->1101|2271->1105|2310->1107|2347->1117|2394->1137|2476->1198|2514->1210|2555->1242|2595->1244|2631->1253|2730->1325|2744->1330|2780->1345|2816->1354|2858->1366|2892->1373|2970->1424|3006->1439|3114->1520|3148->1533|3365->1723|3380->1729|3422->1750|3500->1802|3546->1839|3586->1841|3631->1859|3737->1948|3750->1953|3789->1954|3834->1972|3963->2074|4041->2131|4090->2153|4142->2184|4191->2206|4283->2277|4332->2299|4397->2343|4442->2360|4534->2425|4595->2470|4635->2472|4684->2493|4754->2536|4769->2542|4849->2600|4880->2603|4907->2620|4960->2651|5055->2719|5107->2762|5147->2764|5208->2797|5267->2838|5280->2842|5319->2843|5356->2853|5382->2870|5417->2884|5456->2893|5517->2926|5608->2990|5698->3070|5739->3072|5800->3105|5859->3146|5872->3151|5911->3152|5972->3186|5998->3203|6071->3254|6132->3284|6185->3309|6280->3377|6306->3394|6348->3415|6401->3440|6482->3489|6525->3503|6663->3613|6696->3636|6737->3638|6787->3659|6867->3711|6929->3751|7014->3817|7028->3822|7068->3823|7118->3844|7267->3961|7313->3978|7397->4034|7418->4045|7473->4078|7545->4122|7578->4145|7619->4147|7669->4168|7749->4220|7811->4260|7892->4322|7906->4327|7946->4328|7996->4349|8141->4462|8183->4475|8249->4510|8291->4521
                  LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->6|34->6|56->1|58->5|59->27|61->31|62->36|64->38|64->38|64->38|66->40|66->40|66->40|68->42|68->42|68->42|69->43|70->44|70->44|70->44|71->45|72->46|74->48|76->50|76->50|77->51|77->51|81->55|81->55|81->55|85->59|85->59|85->59|87->61|91->65|91->65|91->65|93->67|96->70|96->70|97->71|97->71|98->72|98->72|99->73|99->73|100->74|104->78|104->78|104->78|105->79|106->80|106->80|106->80|106->80|106->80|106->80|108->82|108->82|108->82|109->83|110->84|110->84|110->84|111->85|111->85|111->85|112->86|114->88|116->90|116->90|116->90|117->91|118->92|118->92|118->92|119->93|119->93|119->93|120->94|121->95|123->97|123->97|123->97|124->98|126->100|128->102|133->107|133->107|133->107|134->108|135->109|135->109|137->111|137->111|137->111|138->112|141->115|142->116|143->117|143->117|143->117|145->119|145->119|145->119|146->120|147->121|147->121|149->123|149->123|149->123|150->124|153->127|154->128|157->131|159->133
                  -- GENERATED --
              */
          