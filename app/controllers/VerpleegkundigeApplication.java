package controllers;

import models.Casus;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.casuslist;

public class VerpleegkundigeApplication extends Controller  {
    /**
     * Display the paginated list of cases
     * @param page
     * @param sortBy
     * @param order
     * @param filter
     * @return
     */
    public static Result listCasusVerpleegkundige() {
    	return Application.GO_HOME;
    }
}
