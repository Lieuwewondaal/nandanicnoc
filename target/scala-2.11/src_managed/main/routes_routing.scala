// @SOURCE:C:/Users/Vincent/workspace/verpleegkunde-app/conf/routes
// @HASH:b2bed3060c0909d690396aaaf87401354297bc48
// @DATE:Wed May 20 16:54:37 CEST 2015


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String): Unit = {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Default path will just redirect to the computer list""", Routes.prefix + """"""))
        

// @LINE:9
private[this] lazy val controllers_Application_list1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses"))))
private[this] lazy val controllers_Application_list1_invoker = createInvoker(
controllers.Application.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """ Computers list (look at the default values for pagination parameters)""", Routes.prefix + """diagnoses"""))
        

// @LINE:12
private[this] lazy val controllers_Application_getBepalendkenmerk2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/bepalendkenmerk"))))
private[this] lazy val controllers_Application_getBepalendkenmerk2_invoker = createInvoker(
controllers.Application.getBepalendkenmerk(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getBepalendkenmerk", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """ Get diagnoseoverzicht information""", Routes.prefix + """diagnoses/bepalendkenmerk"""))
        

// @LINE:13
private[this] lazy val controllers_Application_getRisicofactor3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/risicofactor"))))
private[this] lazy val controllers_Application_getRisicofactor3_invoker = createInvoker(
controllers.Application.getRisicofactor(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getRisicofactor", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """diagnoses/risicofactor"""))
        

// @LINE:14
private[this] lazy val controllers_Application_getSamenhangendefactor4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/samenhangendefactor"))))
private[this] lazy val controllers_Application_getSamenhangendefactor4_invoker = createInvoker(
controllers.Application.getSamenhangendefactor(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getSamenhangendefactor", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """diagnoses/samenhangendefactor"""))
        

// @LINE:15
private[this] lazy val controllers_Application_getNicActiviteit5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/nic_diagnose"))))
private[this] lazy val controllers_Application_getNicActiviteit5_invoker = createInvoker(
controllers.Application.getNicActiviteit(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getNicActiviteit", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """diagnoses/nic_diagnose"""))
        

// @LINE:18
private[this] lazy val controllers_Application_create6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/new"))))
private[this] lazy val controllers_Application_create6_invoker = createInvoker(
controllers.Application.create(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "create", Nil,"GET", """ Add computer""", Routes.prefix + """diagnoses/new"""))
        

// @LINE:19
private[this] lazy val controllers_Application_save7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/now"))))
private[this] lazy val controllers_Application_save7_invoker = createInvoker(
controllers.Application.save(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "save", Nil,"GET", """""", Routes.prefix + """diagnoses/now"""))
        

// @LINE:20
private[this] lazy val controllers_Application_upload8_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses"))))
private[this] lazy val controllers_Application_upload8_invoker = createInvoker(
controllers.Application.upload(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "upload", Nil,"POST", """""", Routes.prefix + """diagnoses"""))
        

// @LINE:23
private[this] lazy val controllers_Application_edit9_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_Application_edit9_invoker = createInvoker(
controllers.Application.edit(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "edit", Seq(classOf[Long]),"GET", """ Edit existing computer""", Routes.prefix + """diagnoses/$id<[^/]+>"""))
        

// @LINE:24
private[this] lazy val controllers_Application_update10_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_Application_update10_invoker = createInvoker(
controllers.Application.update(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """diagnoses/$id<[^/]+>"""))
        

// @LINE:27
private[this] lazy val controllers_Application_delete11_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_Application_delete11_invoker = createInvoker(
controllers.Application.delete(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "delete", Seq(classOf[Long]),"POST", """ Delete a computer""", Routes.prefix + """diagnoses/$id<[^/]+>/delete"""))
        

// @LINE:30
private[this] lazy val controllers_Assets_at12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at12_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses""","""controllers.Application.list(p:Int ?= 0, s:String ?= "diagnoseoverzicht_omschrijving", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/bepalendkenmerk""","""controllers.Application.getBepalendkenmerk(p:Int ?= 0, s:String ?= "", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/risicofactor""","""controllers.Application.getRisicofactor(p:Int ?= 0, s:String ?= "", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/samenhangendefactor""","""controllers.Application.getSamenhangendefactor(p:Int ?= 0, s:String ?= "", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/nic_diagnose""","""controllers.Application.getNicActiviteit(p:Int ?= 0, s:String ?= "", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/new""","""controllers.Application.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/now""","""controllers.Application.save()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses""","""controllers.Application.upload()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/$id<[^/]+>""","""controllers.Application.edit(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/$id<[^/]+>""","""controllers.Application.update(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/$id<[^/]+>/delete""","""controllers.Application.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index())
   }
}
        

// @LINE:9
case controllers_Application_list1_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("diagnoseoverzicht_omschrijving")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_Application_list1_invoker.call(controllers.Application.list(p, s, o, f))
   }
}
        

// @LINE:12
case controllers_Application_getBepalendkenmerk2_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_Application_getBepalendkenmerk2_invoker.call(controllers.Application.getBepalendkenmerk(p, s, o, f))
   }
}
        

// @LINE:13
case controllers_Application_getRisicofactor3_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_Application_getRisicofactor3_invoker.call(controllers.Application.getRisicofactor(p, s, o, f))
   }
}
        

// @LINE:14
case controllers_Application_getSamenhangendefactor4_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_Application_getSamenhangendefactor4_invoker.call(controllers.Application.getSamenhangendefactor(p, s, o, f))
   }
}
        

// @LINE:15
case controllers_Application_getNicActiviteit5_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_Application_getNicActiviteit5_invoker.call(controllers.Application.getNicActiviteit(p, s, o, f))
   }
}
        

// @LINE:18
case controllers_Application_create6_route(params) => {
   call { 
        controllers_Application_create6_invoker.call(controllers.Application.create())
   }
}
        

// @LINE:19
case controllers_Application_save7_route(params) => {
   call { 
        controllers_Application_save7_invoker.call(controllers.Application.save())
   }
}
        

// @LINE:20
case controllers_Application_upload8_route(params) => {
   call { 
        controllers_Application_upload8_invoker.call(controllers.Application.upload())
   }
}
        

// @LINE:23
case controllers_Application_edit9_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_edit9_invoker.call(controllers.Application.edit(id))
   }
}
        

// @LINE:24
case controllers_Application_update10_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_update10_invoker.call(controllers.Application.update(id))
   }
}
        

// @LINE:27
case controllers_Application_delete11_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_delete11_invoker.call(controllers.Application.delete(id))
   }
}
        

// @LINE:30
case controllers_Assets_at12_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at12_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     