package com.seosj.classicbook;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


public class JSONParser {

    //json으로 들어오는 string
    private String str;

    public String stat;//status 0 -> 로긴 실패 status 1 ->로긴 성공
    public String major;//학과
    public String stu_num;//학번
    public String stu_name;//이름

    public JsonArray stat_auth;//인증현황
    public String stat_auth_seo;//서양 -> ~권
    public String stat_auth_dong;//동양
    public String stat_auth_dongseo;//동서양
    public String stat_auth_science;//과학
    public String stat_auth_tot;//합계

    public JsonArray stat_test_auth;//시험인증현황
    //여기있는 data들 recyclerview로 getset해야죵~


    public JsonArray stat_alter_auth;//대체과목현황



    public JsonArray stat_challenge_auth;//대회인증현황



    public JSONParser(String str){
        this.str = str;
    }
    public void start() {
        JsonParser Parser = new JsonParser();
        JsonObject jsonObj = (JsonObject) Parser.parse(str);

        System.out.println("status is" + jsonObj);
        JsonPrimitive pri = (JsonPrimitive)jsonObj.get("status");


        //System.out.println("status is 1 " + pri);

        stat = jsonObj.get("status").getAsString();
        major = jsonObj.get("학과").getAsString();
        stu_num = jsonObj.get("학번").getAsString();
        stu_name = jsonObj.get("이름").getAsString();


        stat_auth = (JsonArray) jsonObj.get("인증현황");
        for(int i = 0;i<stat_auth.size();i++){
            JsonObject object = (JsonObject)stat_auth.get(i);
            stat_auth_seo = object.get("서양의 역사와 사상").getAsString();//서양 -> ~권
            stat_auth_dong = object.get("동양의 역사와 사상").getAsString();//동양
            stat_auth_dongseo = object.get("동서양의 문학").getAsString();//동서양
            stat_auth_science = object.get("과학 사상").getAsString();//과학
            stat_auth_tot = object.get("합계").getAsString();//합계
        }

        stat_alter_auth = (JsonArray) jsonObj.get("대체과목현황");

        stat_challenge_auth = (JsonArray) jsonObj.get("대회인증현황");








    }




}
