package cn.drp.tests.domain;

import cn.drp.testlibrary.domain.TestLibrary;

public class Test {
    private String sid;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String result;
    private int score;
    private String tid;
    private int hotspot;
    private String uid;
    private String time;
    @Override
    public String toString() {
        return "  问题：" + question + '\'' +
                " 选项1：" + answer1 + '\'' +
                " 选项2：" + answer2 + '\'' +
                " 选项3：" + answer3 + '\'' +
                " 选项4：" + answer4 + '\'' +
                "  答案：" + result + '\'' +
                "  分值：" + score +
                "   热度：" + hotspot;
    }

    public Test(String sid, String question, String answer1, String answer2, String answer3, String answer4, String result, int score, String tid, int hotspot) {
        this.sid = sid;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.result = result;
        this.score = score;
        this.tid = tid;
        this.hotspot = hotspot;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getHotspot() {
        return hotspot;
    }

    public void setHotspot(int hotspot) {
        this.hotspot = hotspot;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Test() {
    }
}
