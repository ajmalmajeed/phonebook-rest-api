package com.ajmal.phonebook.authentication;

import com.ajmal.phonebook.dao.UserDao;
import com.ajmal.phonebook.models.User;
import com.ajmal.phonebook.util.JsonServiceUtil;
import com.ajmal.phonebook.util.ResponseWrapper;
import com.google.inject.Inject;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Named;
import java.util.concurrent.CompletionStage;


public class UserAuthentication extends Action.Simple {

    @Inject
    @Named("userDao")
    private UserDao userDao;

    /**
     * Gets the token that user sent to the service which attached on header.
     In this case, User must be sent the X-AUTH_TOKEN key to authenticate
     @param ctx the http context in which to execute this action
     @return an array with all auth token headers
     **/

    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }

    public CompletionStage<Result> call(Http.Context ctx){
        String token = getTokenFromHeader(ctx);
        Result unauthorized = Results.unauthorized(JsonServiceUtil.toJsonNode(
                new ResponseWrapper<>("Unauthorized to access", "This request have no enough access details")));
        if (token != null ) {
            User user = userDao.findUserByToken(token);
            if (user == null){
                return F.Promise.pure(unauthorized);
            }
            if (user != null) {
                ctx.args.put("user",user);
                return delegate.call(ctx);
            }
        }

        return F.Promise.pure(unauthorized);
    }
}

