
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
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[com.avaje.ebean.Page[Diagnose],String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: com.avaje.ebean.Page[Diagnose], currentSortBy: String, currentOrder: String, currentFilter: String):play.twirl.api.HtmlFormat.Appendable = {
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
Seq[Any](format.raw/*1.115*/("""

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
                    """),_display_(/*70.22*/header("name", "Diagnose name")),format.raw/*70.53*/("""
                    """),_display_(/*71.22*/header("introduced", "Introduced")),format.raw/*71.56*/("""
                    """),_display_(/*72.22*/header("discontinued", "Discontinued")),format.raw/*72.60*/("""
                    """),_display_(/*73.22*/header("company.name", "Company")),format.raw/*73.55*/("""
                """),format.raw/*74.17*/("""</tr>
            </thead>
            <tbody>

                """),_display_(/*78.18*/for(diagnose <- currentPage.getList) yield /*78.54*/ {_display_(Seq[Any](format.raw/*78.56*/("""
                    """),format.raw/*79.21*/("""<tr>
                        <td><a href=""""),_display_(/*80.39*/routes/*80.45*/.Application.edit(diagnose.id)),format.raw/*80.75*/("""">"""),_display_(/*80.78*/diagnose/*80.86*/.name),format.raw/*80.91*/("""</a></td>
                        <td>
                            """),_display_(/*82.30*/if(diagnose.introduced == null)/*82.61*/ {_display_(Seq[Any](format.raw/*82.63*/("""
                                """),format.raw/*83.33*/("""<em>-</em>
                            """)))}/*84.31*/else/*84.36*/{_display_(Seq[Any](format.raw/*84.37*/("""
                                """),_display_(/*85.34*/diagnose/*85.42*/.introduced.format("dd MMM yyyy")),format.raw/*85.75*/("""
                            """)))}),format.raw/*86.30*/("""
                        """),format.raw/*87.25*/("""</td>
                        <td>
                            """),_display_(/*89.30*/if(diagnose.discontinued == null)/*89.63*/ {_display_(Seq[Any](format.raw/*89.65*/("""
                                """),format.raw/*90.33*/("""<em>-</em>
                            """)))}/*91.31*/else/*91.36*/{_display_(Seq[Any](format.raw/*91.37*/("""
                                """),_display_(/*92.34*/diagnose/*92.42*/.discontinued.format("dd MMM yyyy")),format.raw/*92.77*/("""
                            """)))}),format.raw/*93.30*/("""
                        """),format.raw/*94.25*/("""</td>
                        <td>
                            """),_display_(/*96.30*/if(diagnose.company == null)/*96.58*/ {_display_(Seq[Any](format.raw/*96.60*/("""
                                """),format.raw/*97.33*/("""<em>-</em>
                            """)))}/*98.31*/else/*98.36*/{_display_(Seq[Any](format.raw/*98.37*/("""
                                """),_display_(/*99.34*/diagnose/*99.42*/.company.name),format.raw/*99.55*/("""
                            """)))}),format.raw/*100.30*/("""
                        """),format.raw/*101.25*/("""</td>
                    </tr>
                """)))}),format.raw/*103.18*/("""

            """),format.raw/*105.13*/("""</tbody>
        </table>

        <div id="pagination" class="pagination">
            <ul>
                """),_display_(/*110.18*/if(currentPage.hasPrev)/*110.41*/ {_display_(Seq[Any](format.raw/*110.43*/("""
                    """),format.raw/*111.21*/("""<li class="prev">
                        <a href=""""),_display_(/*112.35*/link(currentPage.getPageIndex - 1, null)),format.raw/*112.75*/("""">&larr; Previous</a>
                    </li>
                """)))}/*114.19*/else/*114.24*/{_display_(Seq[Any](format.raw/*114.25*/("""
                    """),format.raw/*115.21*/("""<li class="prev disabled">
                        <a>&larr; Previous</a>
                    </li>
                """)))}),format.raw/*118.18*/("""
                """),format.raw/*119.17*/("""<li class="current">
                    <a>Displaying """),_display_(/*120.36*/currentPage/*120.47*/.getDisplayXtoYofZ(" to "," of ")),format.raw/*120.80*/("""</a>
                </li>
                """),_display_(/*122.18*/if(currentPage.hasNext)/*122.41*/ {_display_(Seq[Any](format.raw/*122.43*/("""
                    """),format.raw/*123.21*/("""<li class="next">
                        <a href=""""),_display_(/*124.35*/link(currentPage.getPageIndex + 1, null)),format.raw/*124.75*/("""">Next &rarr;</a>
                    </li>
                """)))}/*126.19*/else/*126.24*/{_display_(Seq[Any](format.raw/*126.25*/("""
                    """),format.raw/*127.21*/("""<li class="next disabled">
                        <a>Next &rarr;</a>
                    </li>
                """)))}),format.raw/*130.18*/("""
            """),format.raw/*131.13*/("""</ul>
        </div>
        
    """)))}),format.raw/*134.6*/("""
        
""")))}),format.raw/*136.2*/("""

            """))}
  }

  def render(currentPage:com.avaje.ebean.Page[Diagnose],currentSortBy:String,currentOrder:String,currentFilter:String): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentSortBy,currentOrder,currentFilter)

  def f:((com.avaje.ebean.Page[Diagnose],String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentSortBy,currentOrder,currentFilter) => apply(currentPage,currentSortBy,currentOrder,currentFilter)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Apr 23 14:07:20 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/list.scala.html
                  HASH: 38afd20eaa1c1cd18dd61f69519c7dc5d67d2029
                  MATRIX: 767->1|952->860|966->866|1073->896|1105->901|1144->913|1156->916|1194->933|1223->934|1258->942|1292->968|1379->1032|1427->1053|1460->1065|1490->1068|1516->1073|1554->243|1565->247|2097->114|2126->241|2154->748|2184->858|2212->1089|2241->1092|2253->1096|2292->1098|2329->1108|2376->1128|2458->1189|2496->1201|2537->1233|2577->1235|2613->1244|2712->1316|2726->1321|2762->1336|2798->1345|2840->1357|2874->1364|2952->1415|2988->1430|3096->1511|3130->1524|3347->1714|3362->1720|3404->1741|3482->1793|3528->1830|3568->1832|3613->1850|3719->1939|3732->1944|3771->1945|3816->1963|3945->2065|3997->2096|4046->2118|4101->2152|4150->2174|4209->2212|4258->2234|4312->2267|4357->2284|4449->2349|4501->2385|4541->2387|4590->2408|4660->2451|4675->2457|4726->2487|4756->2490|4773->2498|4799->2503|4894->2571|4934->2602|4974->2604|5035->2637|5094->2678|5107->2683|5146->2684|5207->2718|5224->2726|5278->2759|5339->2789|5392->2814|5483->2878|5525->2911|5565->2913|5626->2946|5685->2987|5698->2992|5737->2993|5798->3027|5815->3035|5871->3070|5932->3100|5985->3125|6076->3189|6113->3217|6153->3219|6214->3252|6273->3293|6286->3298|6325->3299|6386->3333|6403->3341|6437->3354|6499->3384|6553->3409|6634->3458|6677->3472|6815->3582|6848->3605|6889->3607|6939->3628|7019->3680|7081->3720|7166->3786|7180->3791|7220->3792|7270->3813|7419->3930|7465->3947|7549->4003|7570->4014|7625->4047|7697->4091|7730->4114|7771->4116|7821->4137|7901->4189|7963->4229|8044->4291|8058->4296|8098->4297|8148->4318|8293->4431|8335->4444|8401->4479|8443->4490
                  LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->6|34->6|56->1|58->5|59->27|61->31|62->36|64->38|64->38|64->38|66->40|66->40|66->40|68->42|68->42|68->42|69->43|70->44|70->44|70->44|71->45|72->46|74->48|76->50|76->50|77->51|77->51|81->55|81->55|81->55|85->59|85->59|85->59|87->61|91->65|91->65|91->65|93->67|96->70|96->70|97->71|97->71|98->72|98->72|99->73|99->73|100->74|104->78|104->78|104->78|105->79|106->80|106->80|106->80|106->80|106->80|106->80|108->82|108->82|108->82|109->83|110->84|110->84|110->84|111->85|111->85|111->85|112->86|113->87|115->89|115->89|115->89|116->90|117->91|117->91|117->91|118->92|118->92|118->92|119->93|120->94|122->96|122->96|122->96|123->97|124->98|124->98|124->98|125->99|125->99|125->99|126->100|127->101|129->103|131->105|136->110|136->110|136->110|137->111|138->112|138->112|140->114|140->114|140->114|141->115|144->118|145->119|146->120|146->120|146->120|148->122|148->122|148->122|149->123|150->124|150->124|152->126|152->126|152->126|153->127|156->130|157->131|160->134|162->136
                  -- GENERATED --
              */
          