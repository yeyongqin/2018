package com.web.vo;

/**
 * 指标信息
 * @author yyq
 */
public class Index {
    private int id;                              //序号
    private String indexType;                  //指标类型
    private String indexName;                  //指标名称
    private String fineTerm;                   //细项
    private String score;                       //分值
    private String caliber;                     //取值口径
    private String challengeValue;            //挑战值
    private String completionTimeLimit;     //完成时限
    private String responsibleGroup;         //负责组
    private String responsiblePeople;         //负责人
    private Integer currentProgress;         //目前进展
    private String description;              //描述


    public int getId() {
        return id;
    }

    public String getIndexType() {
        return indexType;
    }

    public String getIndexName() {
        return indexName;
    }

    public String getFineTerm() {
        return fineTerm;
    }

    public String getScore() {
        return score;
    }

    public String getCaliber() {
        return caliber;
    }

    public String getChallengeValue() {
        return challengeValue;
    }

    public String getCompletionTimeLimit() {
        return completionTimeLimit;
    }

    public String getResponsibleGroup() {
        return responsibleGroup;
    }

    public String getResponsiblePeople() {
        return responsiblePeople;
    }

    public Integer getCurrentProgress() {
        return currentProgress;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setFineTerm(String fineTerm) {
        this.fineTerm = fineTerm;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public void setChallengeValue(String challengeValue) {
        this.challengeValue = challengeValue;
    }

    public void setCompletionTimeLimit(String completionTimeLimit) {
        this.completionTimeLimit = completionTimeLimit;
    }

    public void setResponsibleGroup(String responsibleGroup) {
        this.responsibleGroup = responsibleGroup;
    }

    public void setResponsiblePeople(String responsiblePeople) {
        this.responsiblePeople = responsiblePeople;
    }

    public void setCurrentProgress(Integer currentProgress) {
        this.currentProgress = currentProgress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //更新数据
    public void setIndex(Index index){
        this.id = index.getId();
        this.indexName = index.getIndexName();
        this.indexType = index.getIndexType();
        this.fineTerm = index.getFineTerm();
        this.score = index.getScore();
        this.caliber = index.getCaliber();
        this.challengeValue = index.getChallengeValue();
        this.completionTimeLimit = index.getCompletionTimeLimit();
        this.responsibleGroup = index.getResponsibleGroup();
        this.responsiblePeople = index.getResponsiblePeople();
        this.currentProgress = index.getCurrentProgress();
        this.description = index.getDescription();

    }
}
