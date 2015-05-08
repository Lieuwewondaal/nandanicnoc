// @SOURCE:C:/Users/Vincent/workspace/verpleegkunde-app/conf/routes
// @HASH:6a5930e7c15344fa1c6540c36619230d75764df4
// @DATE:Fri May 08 13:38:39 CEST 2015


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
private[this] lazy val controllers_Application_create2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/new"))))
private[this] lazy val controllers_Application_create2_invoker = createInvoker(
controllers.Application.create(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "create", Nil,"GET", """ Add computer""", Routes.prefix + """diagnoses/new"""))
        

// @LINE:13
private[this] lazy val controllers_Application_save3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/now"))))
private[this] lazy val controllers_Application_save3_invoker = createInvoker(
controllers.Application.save(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "save", Nil,"GET", """""", Routes.prefix + """diagnoses/now"""))
        

// @LINE:14
private[this] lazy val controllers_Application_upload4_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses"))))
private[this] lazy val controllers_Application_upload4_invoker = createInvoker(
controllers.Application.upload(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "upload", Nil,"POST", """""", Routes.prefix + """diagnoses"""))
        

// @LINE:17
private[this] lazy val controllers_Application_edit5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_Application_edit5_invoker = createInvoker(
controllers.Application.edit(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "edit", Seq(classOf[Long]),"GET", """ Edit existing computer""", Routes.prefix + """diagnoses/$id<[^/]+>"""))
        

// @LINE:18
private[this] lazy val controllers_Application_update6_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_Application_update6_invoker = createInvoker(
controllers.Application.update(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """diagnoses/$id<[^/]+>"""))
        

// @LINE:21
private[this] lazy val controllers_Application_delete7_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diagnoses/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_Application_delete7_invoker = createInvoker(
controllers.Application.delete(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "delete", Seq(classOf[Long]),"POST", """ Delete a computer""", Routes.prefix + """diagnoses/$id<[^/]+>/delete"""))
        

// @LINE:24
private[this] lazy val controllers_Assets_at8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at8_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses""","""controllers.Application.list(p:Int ?= 0, s:String ?= "diagnoseoverzicht_omschrijving", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/new""","""controllers.Application.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/now""","""controllers.Application.save()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses""","""controllers.Application.upload()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/$id<[^/]+>""","""controllers.Application.edit(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/$id<[^/]+>""","""controllers.Application.update(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diagnoses/$id<[^/]+>/delete""","""controllers.Application.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
case controllers_Application_create2_route(params) => {
   call { 
        controllers_Application_create2_invoker.call(controllers.Application.create())
   }
}
        

// @LINE:13
case controllers_Application_save3_route(params) => {
   call { 
        controllers_Application_save3_invoker.call(controllers.Application.save())
   }
}
        

// @LINE:14
case controllers_Application_upload4_route(params) => {
   call { 
        controllers_Application_upload4_invoker.call(controllers.Application.upload())
   }
}
        

// @LINE:17
case controllers_Application_edit5_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_edit5_invoker.call(controllers.Application.edit(id))
   }
}
        

// @LINE:18
case controllers_Application_update6_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_update6_invoker.call(controllers.Application.update(id))
   }
}
        

// @LINE:21
case controllers_Application_delete7_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_delete7_invoker.call(controllers.Application.delete(id))
   }
}
        

// @LINE:24
case controllers_Assets_at8_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at8_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     