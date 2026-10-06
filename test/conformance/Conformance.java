package com.anyoauth;
import com.google.gson.*;
import java.nio.charset.StandardCharsets;
public class Conformance {
 public static void main(String[] args){JsonObject out=new JsonObject();try{JsonObject input=JsonParser.parseString(new String(System.in.readAllBytes(),StandardCharsets.UTF_8)).getAsJsonObject();JsonObject p=input.has("params")?input.getAsJsonObject("params"):new JsonObject();AnyOAuth c=new AnyOAuth(value(input,"baseUrl"),value(input,"clientId"),value(input,"clientSecret"));JsonElement result=switch(value(input,"action")){
 case "exchangeCode" -> c.exchangeCode(p);
case "profile" -> c.profile(p);
case "revoke" -> c.revoke(p);
case "providers" -> c.providers(p);
 case "pkceChallenge" -> new JsonPrimitive(AnyOAuth.pkceChallenge(value(p,"codeVerifier")));
 case "createTransaction" -> AnyOAuth.createTransaction();
 case "authorizationUrl" -> new JsonPrimitive(c.authorizationUrl(p));
 case "validateCallback" -> AnyOAuth.validateCallback(p);
 default -> throw new AnyOAuth.SDKException(0,"invalid_argument","Unknown action");};out.addProperty("ok",true);out.add("result",result);
 }catch(Exception e){AnyOAuth.SDKException error=e instanceof AnyOAuth.SDKException?(AnyOAuth.SDKException)e:new AnyOAuth.SDKException(0,"invalid_argument","Invalid input");JsonObject detail=new JsonObject();detail.addProperty("status",error.status);detail.addProperty("code",error.code);detail.addProperty("message",error.message);out.addProperty("ok",false);out.add("error",detail);}System.out.println(out);}
 private static String value(JsonObject p,String key){return p.has(key)?p.get(key).getAsString():"";}
}
