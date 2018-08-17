package com.jaymiaosha.pojo;

import java.util.ArrayList;
import java.util.List;

public class GoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIsNull() {
            addCriterion("goodsName is null");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIsNotNull() {
            addCriterion("goodsName is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsnameEqualTo(String value) {
            addCriterion("goodsName =", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotEqualTo(String value) {
            addCriterion("goodsName <>", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameGreaterThan(String value) {
            addCriterion("goodsName >", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameGreaterThanOrEqualTo(String value) {
            addCriterion("goodsName >=", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLessThan(String value) {
            addCriterion("goodsName <", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLessThanOrEqualTo(String value) {
            addCriterion("goodsName <=", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLike(String value) {
            addCriterion("goodsName like", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotLike(String value) {
            addCriterion("goodsName not like", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIn(List<String> values) {
            addCriterion("goodsName in", values, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotIn(List<String> values) {
            addCriterion("goodsName not in", values, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameBetween(String value1, String value2) {
            addCriterion("goodsName between", value1, value2, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotBetween(String value1, String value2) {
            addCriterion("goodsName not between", value1, value2, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodstitleIsNull() {
            addCriterion("goodsTitle is null");
            return (Criteria) this;
        }

        public Criteria andGoodstitleIsNotNull() {
            addCriterion("goodsTitle is not null");
            return (Criteria) this;
        }

        public Criteria andGoodstitleEqualTo(String value) {
            addCriterion("goodsTitle =", value, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleNotEqualTo(String value) {
            addCriterion("goodsTitle <>", value, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleGreaterThan(String value) {
            addCriterion("goodsTitle >", value, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleGreaterThanOrEqualTo(String value) {
            addCriterion("goodsTitle >=", value, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleLessThan(String value) {
            addCriterion("goodsTitle <", value, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleLessThanOrEqualTo(String value) {
            addCriterion("goodsTitle <=", value, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleLike(String value) {
            addCriterion("goodsTitle like", value, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleNotLike(String value) {
            addCriterion("goodsTitle not like", value, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleIn(List<String> values) {
            addCriterion("goodsTitle in", values, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleNotIn(List<String> values) {
            addCriterion("goodsTitle not in", values, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleBetween(String value1, String value2) {
            addCriterion("goodsTitle between", value1, value2, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodstitleNotBetween(String value1, String value2) {
            addCriterion("goodsTitle not between", value1, value2, "goodstitle");
            return (Criteria) this;
        }

        public Criteria andGoodsimgIsNull() {
            addCriterion("goodsImg is null");
            return (Criteria) this;
        }

        public Criteria andGoodsimgIsNotNull() {
            addCriterion("goodsImg is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsimgEqualTo(String value) {
            addCriterion("goodsImg =", value, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgNotEqualTo(String value) {
            addCriterion("goodsImg <>", value, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgGreaterThan(String value) {
            addCriterion("goodsImg >", value, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgGreaterThanOrEqualTo(String value) {
            addCriterion("goodsImg >=", value, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgLessThan(String value) {
            addCriterion("goodsImg <", value, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgLessThanOrEqualTo(String value) {
            addCriterion("goodsImg <=", value, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgLike(String value) {
            addCriterion("goodsImg like", value, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgNotLike(String value) {
            addCriterion("goodsImg not like", value, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgIn(List<String> values) {
            addCriterion("goodsImg in", values, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgNotIn(List<String> values) {
            addCriterion("goodsImg not in", values, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgBetween(String value1, String value2) {
            addCriterion("goodsImg between", value1, value2, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsimgNotBetween(String value1, String value2) {
            addCriterion("goodsImg not between", value1, value2, "goodsimg");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailIsNull() {
            addCriterion("goodsDetail is null");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailIsNotNull() {
            addCriterion("goodsDetail is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailEqualTo(String value) {
            addCriterion("goodsDetail =", value, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailNotEqualTo(String value) {
            addCriterion("goodsDetail <>", value, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailGreaterThan(String value) {
            addCriterion("goodsDetail >", value, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailGreaterThanOrEqualTo(String value) {
            addCriterion("goodsDetail >=", value, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailLessThan(String value) {
            addCriterion("goodsDetail <", value, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailLessThanOrEqualTo(String value) {
            addCriterion("goodsDetail <=", value, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailLike(String value) {
            addCriterion("goodsDetail like", value, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailNotLike(String value) {
            addCriterion("goodsDetail not like", value, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailIn(List<String> values) {
            addCriterion("goodsDetail in", values, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailNotIn(List<String> values) {
            addCriterion("goodsDetail not in", values, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailBetween(String value1, String value2) {
            addCriterion("goodsDetail between", value1, value2, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailNotBetween(String value1, String value2) {
            addCriterion("goodsDetail not between", value1, value2, "goodsdetail");
            return (Criteria) this;
        }

        public Criteria andGoodspriceIsNull() {
            addCriterion("goodsPrice is null");
            return (Criteria) this;
        }

        public Criteria andGoodspriceIsNotNull() {
            addCriterion("goodsPrice is not null");
            return (Criteria) this;
        }

        public Criteria andGoodspriceEqualTo(Double value) {
            addCriterion("goodsPrice =", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceNotEqualTo(Double value) {
            addCriterion("goodsPrice <>", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceGreaterThan(Double value) {
            addCriterion("goodsPrice >", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceGreaterThanOrEqualTo(Double value) {
            addCriterion("goodsPrice >=", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceLessThan(Double value) {
            addCriterion("goodsPrice <", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceLessThanOrEqualTo(Double value) {
            addCriterion("goodsPrice <=", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceIn(List<Double> values) {
            addCriterion("goodsPrice in", values, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceNotIn(List<Double> values) {
            addCriterion("goodsPrice not in", values, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceBetween(Double value1, Double value2) {
            addCriterion("goodsPrice between", value1, value2, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceNotBetween(Double value1, Double value2) {
            addCriterion("goodsPrice not between", value1, value2, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodsstockIsNull() {
            addCriterion("goodsStock is null");
            return (Criteria) this;
        }

        public Criteria andGoodsstockIsNotNull() {
            addCriterion("goodsStock is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsstockEqualTo(Integer value) {
            addCriterion("goodsStock =", value, "goodsstock");
            return (Criteria) this;
        }

        public Criteria andGoodsstockNotEqualTo(Integer value) {
            addCriterion("goodsStock <>", value, "goodsstock");
            return (Criteria) this;
        }

        public Criteria andGoodsstockGreaterThan(Integer value) {
            addCriterion("goodsStock >", value, "goodsstock");
            return (Criteria) this;
        }

        public Criteria andGoodsstockGreaterThanOrEqualTo(Integer value) {
            addCriterion("goodsStock >=", value, "goodsstock");
            return (Criteria) this;
        }

        public Criteria andGoodsstockLessThan(Integer value) {
            addCriterion("goodsStock <", value, "goodsstock");
            return (Criteria) this;
        }

        public Criteria andGoodsstockLessThanOrEqualTo(Integer value) {
            addCriterion("goodsStock <=", value, "goodsstock");
            return (Criteria) this;
        }

        public Criteria andGoodsstockIn(List<Integer> values) {
            addCriterion("goodsStock in", values, "goodsstock");
            return (Criteria) this;
        }

        public Criteria andGoodsstockNotIn(List<Integer> values) {
            addCriterion("goodsStock not in", values, "goodsstock");
            return (Criteria) this;
        }

        public Criteria andGoodsstockBetween(Integer value1, Integer value2) {
            addCriterion("goodsStock between", value1, value2, "goodsstock");
            return (Criteria) this;
        }

        public Criteria andGoodsstockNotBetween(Integer value1, Integer value2) {
            addCriterion("goodsStock not between", value1, value2, "goodsstock");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}