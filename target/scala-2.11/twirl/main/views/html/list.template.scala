
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
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[com.avaje.ebean.Page[Computer],String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: com.avaje.ebean.Page[Computer], currentSortBy: String, currentOrder: String, currentFilter: String):play.twirl.api.HtmlFormat.Appendable = {
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
    
    """),format.raw/*40.5*/("""<h1 id="homeTitle">"""),_display_(/*40.25*/Messages("computers.list.title", currentPage.getTotalRowCount)),format.raw/*40.87*/("""</h1>

    """),_display_(/*42.6*/if(flash.containsKey("success"))/*42.38*/ {_display_(Seq[Any](format.raw/*42.40*/("""
        """),format.raw/*43.9*/("""<div class="alert-message warning">
            <strong>Done!</strong> """),_display_(/*44.37*/flash/*44.42*/.get("success")),format.raw/*44.57*/("""
        """),format.raw/*45.9*/("""</div>
    """)))}),format.raw/*46.6*/(""" 

    """),format.raw/*48.5*/("""<div id="actions">
        
        <form action=""""),_display_(/*50.24*/link(0, "name")),format.raw/*50.39*/("""" method="GET">
            <input type="search" id="searchbox" name="f" value=""""),_display_(/*51.66*/currentFilter),format.raw/*51.79*/("""" placeholder="Filter by computer name...">
            <input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">
        </form>
        
        <a class="btn success" id="add" href=""""),_display_(/*55.48*/routes/*55.54*/.Application.create()),format.raw/*55.75*/("""">Add a new computer</a>
        
    </div>
    
    """),_display_(/*59.6*/if(currentPage.getTotalRowCount == 0)/*59.43*/ {_display_(Seq[Any](format.raw/*59.45*/("""
        
        """),format.raw/*61.9*/("""<div class="well">
            <em>Nothing to display</em>
        </div>
        
    """)))}/*65.7*/else/*65.12*/{_display_(Seq[Any](format.raw/*65.13*/("""
        
        """),format.raw/*67.9*/("""<table class="computers zebra-striped">
            <thead>
                <tr>
                    """),_display_(/*70.22*/header("name", "Computer name")),format.raw/*70.53*/("""
                    """),_display_(/*71.22*/header("introduced", "Introduced")),format.raw/*71.56*/("""
                    """),_display_(/*72.22*/header("discontinued", "Discontinued")),format.raw/*72.60*/("""
                    """),_display_(/*73.22*/header("company.name", "Company")),format.raw/*73.55*/("""
                """),format.raw/*74.17*/("""</tr>
            </thead>
            <tbody>

                """),_display_(/*78.18*/for(computer <- currentPage.getList) yield /*78.54*/ {_display_(Seq[Any](format.raw/*78.56*/("""
                    """),format.raw/*79.21*/("""<tr>
                        <td><a href=""""),_display_(/*80.39*/routes/*80.45*/.Application.edit(computer.id)),format.raw/*80.75*/("""">"""),_display_(/*80.78*/computer/*80.86*/.name),format.raw/*80.91*/("""</a></td>
                        <td>
                            """),_display_(/*82.30*/if(computer.introduced == null)/*82.61*/ {_display_(Seq[Any](format.raw/*82.63*/("""
                                """),format.raw/*83.33*/("""<em>-</em>
                            """)))}/*84.31*/else/*84.36*/{_display_(Seq[Any](format.raw/*84.37*/("""
                                """),_display_(/*85.34*/computer/*85.42*/.introduced.format("dd MMM yyyy")),format.raw/*85.75*/("""
                            """)))}),format.raw/*86.30*/("""
                        """),format.raw/*87.25*/("""</td>
                        <td>
                            """),_display_(/*89.30*/if(computer.discontinued == null)/*89.63*/ {_display_(Seq[Any](format.raw/*89.65*/("""
                                """),format.raw/*90.33*/("""<em>-</em>
                            """)))}/*91.31*/else/*91.36*/{_display_(Seq[Any](format.raw/*91.37*/("""
                                """),_display_(/*92.34*/computer/*92.42*/.discontinued.format("dd MMM yyyy")),format.raw/*92.77*/("""
                            """)))}),format.raw/*93.30*/("""
                        """),format.raw/*94.25*/("""</td>
                        <td>
                            """),_display_(/*96.30*/if(computer.company == null)/*96.58*/ {_display_(Seq[Any](format.raw/*96.60*/("""
                                """),format.raw/*97.33*/("""<em>-</em>
                            """)))}/*98.31*/else/*98.36*/{_display_(Seq[Any](format.raw/*98.37*/("""
                                """),_display_(/*99.34*/computer/*99.42*/.company.name),format.raw/*99.55*/("""
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

  def render(currentPage:com.avaje.ebean.Page[Computer],currentSortBy:String,currentOrder:String,currentFilter:String): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentSortBy,currentOrder,currentFilter)

  def f:((com.avaje.ebean.Page[Computer],String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentSortBy,currentOrder,currentFilter) => apply(currentPage,currentSortBy,currentOrder,currentFilter)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Apr 20 08:49:45 CEST 2015
                  SOURCE: C:/Users/Vincent/workspace/verpleegkunde-app/app/views/list.scala.html
                  HASH: dbd8dd5bb33184466872023ba2c6e0343250f1a7
                  MATRIX: 767->1|952->860|966->866|1073->896|1105->901|1144->913|1156->916|1194->933|1223->934|1258->942|1292->968|1379->1032|1427->1053|1460->1065|1490->1068|1516->1073|1554->243|1565->247|2097->114|2126->241|2154->748|2184->858|2212->1089|2241->1092|2253->1096|2292->1098|2329->1108|2376->1128|2459->1190|2497->1202|2538->1234|2578->1236|2614->1245|2713->1317|2727->1322|2763->1337|2799->1346|2841->1358|2875->1365|2953->1416|2989->1431|3097->1512|3131->1525|3369->1736|3384->1742|3426->1763|3507->1818|3553->1855|3593->1857|3638->1875|3744->1964|3757->1969|3796->1970|3841->1988|3970->2090|4022->2121|4071->2143|4126->2177|4175->2199|4234->2237|4283->2259|4337->2292|4382->2309|4474->2374|4526->2410|4566->2412|4615->2433|4685->2476|4700->2482|4751->2512|4781->2515|4798->2523|4824->2528|4919->2596|4959->2627|4999->2629|5060->2662|5119->2703|5132->2708|5171->2709|5232->2743|5249->2751|5303->2784|5364->2814|5417->2839|5508->2903|5550->2936|5590->2938|5651->2971|5710->3012|5723->3017|5762->3018|5823->3052|5840->3060|5896->3095|5957->3125|6010->3150|6101->3214|6138->3242|6178->3244|6239->3277|6298->3318|6311->3323|6350->3324|6411->3358|6428->3366|6462->3379|6524->3409|6578->3434|6659->3483|6702->3497|6840->3607|6873->3630|6914->3632|6964->3653|7044->3705|7106->3745|7191->3811|7205->3816|7245->3817|7295->3838|7444->3955|7490->3972|7574->4028|7595->4039|7650->4072|7722->4116|7755->4139|7796->4141|7846->4162|7926->4214|7988->4254|8069->4316|8083->4321|8123->4322|8173->4343|8318->4456|8360->4469|8426->4504|8468->4515
                  LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->6|34->6|56->1|58->5|59->27|61->31|62->36|64->38|64->38|64->38|66->40|66->40|66->40|68->42|68->42|68->42|69->43|70->44|70->44|70->44|71->45|72->46|74->48|76->50|76->50|77->51|77->51|81->55|81->55|81->55|85->59|85->59|85->59|87->61|91->65|91->65|91->65|93->67|96->70|96->70|97->71|97->71|98->72|98->72|99->73|99->73|100->74|104->78|104->78|104->78|105->79|106->80|106->80|106->80|106->80|106->80|106->80|108->82|108->82|108->82|109->83|110->84|110->84|110->84|111->85|111->85|111->85|112->86|113->87|115->89|115->89|115->89|116->90|117->91|117->91|117->91|118->92|118->92|118->92|119->93|120->94|122->96|122->96|122->96|123->97|124->98|124->98|124->98|125->99|125->99|125->99|126->100|127->101|129->103|131->105|136->110|136->110|136->110|137->111|138->112|138->112|140->114|140->114|140->114|141->115|144->118|145->119|146->120|146->120|146->120|148->122|148->122|148->122|149->123|150->124|150->124|152->126|152->126|152->126|153->127|156->130|157->131|160->134|162->136
                  -- GENERATED --
              */
          