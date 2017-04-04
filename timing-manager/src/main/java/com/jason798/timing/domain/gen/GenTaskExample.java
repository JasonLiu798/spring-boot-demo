package com.jason798.timing.domain.gen;

import java.util.ArrayList;
import java.util.List;

public class GenTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GenTaskExample() {
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

        public Criteria andTidIsNull() {
            addCriterion("TID is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("TID is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Long value) {
            addCriterion("TID =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Long value) {
            addCriterion("TID <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Long value) {
            addCriterion("TID >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Long value) {
            addCriterion("TID >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Long value) {
            addCriterion("TID <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Long value) {
            addCriterion("TID <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Long> values) {
            addCriterion("TID in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Long> values) {
            addCriterion("TID not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Long value1, Long value2) {
            addCriterion("TID between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Long value1, Long value2) {
            addCriterion("TID not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTkeyIsNull() {
            addCriterion("TKEY is null");
            return (Criteria) this;
        }

        public Criteria andTkeyIsNotNull() {
            addCriterion("TKEY is not null");
            return (Criteria) this;
        }

        public Criteria andTkeyEqualTo(String value) {
            addCriterion("TKEY =", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotEqualTo(String value) {
            addCriterion("TKEY <>", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyGreaterThan(String value) {
            addCriterion("TKEY >", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyGreaterThanOrEqualTo(String value) {
            addCriterion("TKEY >=", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyLessThan(String value) {
            addCriterion("TKEY <", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyLessThanOrEqualTo(String value) {
            addCriterion("TKEY <=", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyLike(String value) {
            addCriterion("TKEY like", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotLike(String value) {
            addCriterion("TKEY not like", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyIn(List<String> values) {
            addCriterion("TKEY in", values, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotIn(List<String> values) {
            addCriterion("TKEY not in", values, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyBetween(String value1, String value2) {
            addCriterion("TKEY between", value1, value2, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotBetween(String value1, String value2) {
            addCriterion("TKEY not between", value1, value2, "tkey");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andConfTypeIsNull() {
            addCriterion("CONF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andConfTypeIsNotNull() {
            addCriterion("CONF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andConfTypeEqualTo(String value) {
            addCriterion("CONF_TYPE =", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotEqualTo(String value) {
            addCriterion("CONF_TYPE <>", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeGreaterThan(String value) {
            addCriterion("CONF_TYPE >", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_TYPE >=", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLessThan(String value) {
            addCriterion("CONF_TYPE <", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLessThanOrEqualTo(String value) {
            addCriterion("CONF_TYPE <=", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLike(String value) {
            addCriterion("CONF_TYPE like", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotLike(String value) {
            addCriterion("CONF_TYPE not like", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeIn(List<String> values) {
            addCriterion("CONF_TYPE in", values, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotIn(List<String> values) {
            addCriterion("CONF_TYPE not in", values, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeBetween(String value1, String value2) {
            addCriterion("CONF_TYPE between", value1, value2, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotBetween(String value1, String value2) {
            addCriterion("CONF_TYPE not between", value1, value2, "confType");
            return (Criteria) this;
        }

        public Criteria andConfStartTmIsNull() {
            addCriterion("CONF_START_TM is null");
            return (Criteria) this;
        }

        public Criteria andConfStartTmIsNotNull() {
            addCriterion("CONF_START_TM is not null");
            return (Criteria) this;
        }

        public Criteria andConfStartTmEqualTo(Long value) {
            addCriterion("CONF_START_TM =", value, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfStartTmNotEqualTo(Long value) {
            addCriterion("CONF_START_TM <>", value, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfStartTmGreaterThan(Long value) {
            addCriterion("CONF_START_TM >", value, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfStartTmGreaterThanOrEqualTo(Long value) {
            addCriterion("CONF_START_TM >=", value, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfStartTmLessThan(Long value) {
            addCriterion("CONF_START_TM <", value, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfStartTmLessThanOrEqualTo(Long value) {
            addCriterion("CONF_START_TM <=", value, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfStartTmIn(List<Long> values) {
            addCriterion("CONF_START_TM in", values, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfStartTmNotIn(List<Long> values) {
            addCriterion("CONF_START_TM not in", values, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfStartTmBetween(Long value1, Long value2) {
            addCriterion("CONF_START_TM between", value1, value2, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfStartTmNotBetween(Long value1, Long value2) {
            addCriterion("CONF_START_TM not between", value1, value2, "confStartTm");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesIsNull() {
            addCriterion("CONF_EXE_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesIsNotNull() {
            addCriterion("CONF_EXE_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES =", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesNotEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES <>", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesGreaterThan(Long value) {
            addCriterion("CONF_EXE_TIMES >", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesGreaterThanOrEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES >=", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesLessThan(Long value) {
            addCriterion("CONF_EXE_TIMES <", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesLessThanOrEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES <=", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesIn(List<Long> values) {
            addCriterion("CONF_EXE_TIMES in", values, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesNotIn(List<Long> values) {
            addCriterion("CONF_EXE_TIMES not in", values, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesBetween(Long value1, Long value2) {
            addCriterion("CONF_EXE_TIMES between", value1, value2, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesNotBetween(Long value1, Long value2) {
            addCriterion("CONF_EXE_TIMES not between", value1, value2, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitIsNull() {
            addCriterion("CONF_INTERVAL_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitIsNotNull() {
            addCriterion("CONF_INTERVAL_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitEqualTo(String value) {
            addCriterion("CONF_INTERVAL_UNIT =", value, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitNotEqualTo(String value) {
            addCriterion("CONF_INTERVAL_UNIT <>", value, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitGreaterThan(String value) {
            addCriterion("CONF_INTERVAL_UNIT >", value, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_INTERVAL_UNIT >=", value, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitLessThan(String value) {
            addCriterion("CONF_INTERVAL_UNIT <", value, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitLessThanOrEqualTo(String value) {
            addCriterion("CONF_INTERVAL_UNIT <=", value, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitLike(String value) {
            addCriterion("CONF_INTERVAL_UNIT like", value, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitNotLike(String value) {
            addCriterion("CONF_INTERVAL_UNIT not like", value, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitIn(List<String> values) {
            addCriterion("CONF_INTERVAL_UNIT in", values, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitNotIn(List<String> values) {
            addCriterion("CONF_INTERVAL_UNIT not in", values, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitBetween(String value1, String value2) {
            addCriterion("CONF_INTERVAL_UNIT between", value1, value2, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitNotBetween(String value1, String value2) {
            addCriterion("CONF_INTERVAL_UNIT not between", value1, value2, "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andConfIntervalIsNull() {
            addCriterion("CONF_INTERVAL is null");
            return (Criteria) this;
        }

        public Criteria andConfIntervalIsNotNull() {
            addCriterion("CONF_INTERVAL is not null");
            return (Criteria) this;
        }

        public Criteria andConfIntervalEqualTo(Long value) {
            addCriterion("CONF_INTERVAL =", value, "confInterval");
            return (Criteria) this;
        }

        public Criteria andConfIntervalNotEqualTo(Long value) {
            addCriterion("CONF_INTERVAL <>", value, "confInterval");
            return (Criteria) this;
        }

        public Criteria andConfIntervalGreaterThan(Long value) {
            addCriterion("CONF_INTERVAL >", value, "confInterval");
            return (Criteria) this;
        }

        public Criteria andConfIntervalGreaterThanOrEqualTo(Long value) {
            addCriterion("CONF_INTERVAL >=", value, "confInterval");
            return (Criteria) this;
        }

        public Criteria andConfIntervalLessThan(Long value) {
            addCriterion("CONF_INTERVAL <", value, "confInterval");
            return (Criteria) this;
        }

        public Criteria andConfIntervalLessThanOrEqualTo(Long value) {
            addCriterion("CONF_INTERVAL <=", value, "confInterval");
            return (Criteria) this;
        }

        public Criteria andConfIntervalIn(List<Long> values) {
            addCriterion("CONF_INTERVAL in", values, "confInterval");
            return (Criteria) this;
        }

        public Criteria andConfIntervalNotIn(List<Long> values) {
            addCriterion("CONF_INTERVAL not in", values, "confInterval");
            return (Criteria) this;
        }

        public Criteria andConfIntervalBetween(Long value1, Long value2) {
            addCriterion("CONF_INTERVAL between", value1, value2, "confInterval");
            return (Criteria) this;
        }

        public Criteria andConfIntervalNotBetween(Long value1, Long value2) {
            addCriterion("CONF_INTERVAL not between", value1, value2, "confInterval");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorIsNull() {
            addCriterion("STATUS_PROCESSOR is null");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorIsNotNull() {
            addCriterion("STATUS_PROCESSOR is not null");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorEqualTo(String value) {
            addCriterion("STATUS_PROCESSOR =", value, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorNotEqualTo(String value) {
            addCriterion("STATUS_PROCESSOR <>", value, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorGreaterThan(String value) {
            addCriterion("STATUS_PROCESSOR >", value, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_PROCESSOR >=", value, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorLessThan(String value) {
            addCriterion("STATUS_PROCESSOR <", value, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorLessThanOrEqualTo(String value) {
            addCriterion("STATUS_PROCESSOR <=", value, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorLike(String value) {
            addCriterion("STATUS_PROCESSOR like", value, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorNotLike(String value) {
            addCriterion("STATUS_PROCESSOR not like", value, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorIn(List<String> values) {
            addCriterion("STATUS_PROCESSOR in", values, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorNotIn(List<String> values) {
            addCriterion("STATUS_PROCESSOR not in", values, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorBetween(String value1, String value2) {
            addCriterion("STATUS_PROCESSOR between", value1, value2, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorNotBetween(String value1, String value2) {
            addCriterion("STATUS_PROCESSOR not between", value1, value2, "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmIsNull() {
            addCriterion("STATUS_LAST_EXE_TM is null");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmIsNotNull() {
            addCriterion("STATUS_LAST_EXE_TM is not null");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmEqualTo(Long value) {
            addCriterion("STATUS_LAST_EXE_TM =", value, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmNotEqualTo(Long value) {
            addCriterion("STATUS_LAST_EXE_TM <>", value, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmGreaterThan(Long value) {
            addCriterion("STATUS_LAST_EXE_TM >", value, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmGreaterThanOrEqualTo(Long value) {
            addCriterion("STATUS_LAST_EXE_TM >=", value, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmLessThan(Long value) {
            addCriterion("STATUS_LAST_EXE_TM <", value, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmLessThanOrEqualTo(Long value) {
            addCriterion("STATUS_LAST_EXE_TM <=", value, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmIn(List<Long> values) {
            addCriterion("STATUS_LAST_EXE_TM in", values, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmNotIn(List<Long> values) {
            addCriterion("STATUS_LAST_EXE_TM not in", values, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmBetween(Long value1, Long value2) {
            addCriterion("STATUS_LAST_EXE_TM between", value1, value2, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusLastExeTmNotBetween(Long value1, Long value2) {
            addCriterion("STATUS_LAST_EXE_TM not between", value1, value2, "statusLastExeTm");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeIsNull() {
            addCriterion("STATUS_RUNED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeIsNotNull() {
            addCriterion("STATUS_RUNED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeEqualTo(Long value) {
            addCriterion("STATUS_RUNED_TIME =", value, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeNotEqualTo(Long value) {
            addCriterion("STATUS_RUNED_TIME <>", value, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeGreaterThan(Long value) {
            addCriterion("STATUS_RUNED_TIME >", value, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("STATUS_RUNED_TIME >=", value, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeLessThan(Long value) {
            addCriterion("STATUS_RUNED_TIME <", value, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeLessThanOrEqualTo(Long value) {
            addCriterion("STATUS_RUNED_TIME <=", value, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeIn(List<Long> values) {
            addCriterion("STATUS_RUNED_TIME in", values, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeNotIn(List<Long> values) {
            addCriterion("STATUS_RUNED_TIME not in", values, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeBetween(Long value1, Long value2) {
            addCriterion("STATUS_RUNED_TIME between", value1, value2, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andStatusRunedTimeNotBetween(Long value1, Long value2) {
            addCriterion("STATUS_RUNED_TIME not between", value1, value2, "statusRunedTime");
            return (Criteria) this;
        }

        public Criteria andProcessingIsNull() {
            addCriterion("PROCESSING is null");
            return (Criteria) this;
        }

        public Criteria andProcessingIsNotNull() {
            addCriterion("PROCESSING is not null");
            return (Criteria) this;
        }

        public Criteria andProcessingEqualTo(String value) {
            addCriterion("PROCESSING =", value, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingNotEqualTo(String value) {
            addCriterion("PROCESSING <>", value, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingGreaterThan(String value) {
            addCriterion("PROCESSING >", value, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESSING >=", value, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingLessThan(String value) {
            addCriterion("PROCESSING <", value, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingLessThanOrEqualTo(String value) {
            addCriterion("PROCESSING <=", value, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingLike(String value) {
            addCriterion("PROCESSING like", value, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingNotLike(String value) {
            addCriterion("PROCESSING not like", value, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingIn(List<String> values) {
            addCriterion("PROCESSING in", values, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingNotIn(List<String> values) {
            addCriterion("PROCESSING not in", values, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingBetween(String value1, String value2) {
            addCriterion("PROCESSING between", value1, value2, "processing");
            return (Criteria) this;
        }

        public Criteria andProcessingNotBetween(String value1, String value2) {
            addCriterion("PROCESSING not between", value1, value2, "processing");
            return (Criteria) this;
        }

        public Criteria andExecutingIsNull() {
            addCriterion("EXECUTING is null");
            return (Criteria) this;
        }

        public Criteria andExecutingIsNotNull() {
            addCriterion("EXECUTING is not null");
            return (Criteria) this;
        }

        public Criteria andExecutingEqualTo(String value) {
            addCriterion("EXECUTING =", value, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingNotEqualTo(String value) {
            addCriterion("EXECUTING <>", value, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingGreaterThan(String value) {
            addCriterion("EXECUTING >", value, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingGreaterThanOrEqualTo(String value) {
            addCriterion("EXECUTING >=", value, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingLessThan(String value) {
            addCriterion("EXECUTING <", value, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingLessThanOrEqualTo(String value) {
            addCriterion("EXECUTING <=", value, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingLike(String value) {
            addCriterion("EXECUTING like", value, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingNotLike(String value) {
            addCriterion("EXECUTING not like", value, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingIn(List<String> values) {
            addCriterion("EXECUTING in", values, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingNotIn(List<String> values) {
            addCriterion("EXECUTING not in", values, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingBetween(String value1, String value2) {
            addCriterion("EXECUTING between", value1, value2, "executing");
            return (Criteria) this;
        }

        public Criteria andExecutingNotBetween(String value1, String value2) {
            addCriterion("EXECUTING not between", value1, value2, "executing");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNull() {
            addCriterion("SERVICE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNotNull() {
            addCriterion("SERVICE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andServiceNameEqualTo(String value) {
            addCriterion("SERVICE_NAME =", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotEqualTo(String value) {
            addCriterion("SERVICE_NAME <>", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThan(String value) {
            addCriterion("SERVICE_NAME >", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_NAME >=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThan(String value) {
            addCriterion("SERVICE_NAME <", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_NAME <=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLike(String value) {
            addCriterion("SERVICE_NAME like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotLike(String value) {
            addCriterion("SERVICE_NAME not like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameIn(List<String> values) {
            addCriterion("SERVICE_NAME in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotIn(List<String> values) {
            addCriterion("SERVICE_NAME not in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameBetween(String value1, String value2) {
            addCriterion("SERVICE_NAME between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotBetween(String value1, String value2) {
            addCriterion("SERVICE_NAME not between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("VALID is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("VALID is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(String value) {
            addCriterion("VALID =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(String value) {
            addCriterion("VALID <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(String value) {
            addCriterion("VALID >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(String value) {
            addCriterion("VALID >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(String value) {
            addCriterion("VALID <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(String value) {
            addCriterion("VALID <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLike(String value) {
            addCriterion("VALID like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotLike(String value) {
            addCriterion("VALID not like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<String> values) {
            addCriterion("VALID in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<String> values) {
            addCriterion("VALID not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(String value1, String value2) {
            addCriterion("VALID between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(String value1, String value2) {
            addCriterion("VALID not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andTkeyLikeInsensitive(String value) {
            addCriterion("upper(TKEY) like", value.toUpperCase(), "tkey");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(NAME) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andConfTypeLikeInsensitive(String value) {
            addCriterion("upper(CONF_TYPE) like", value.toUpperCase(), "confType");
            return (Criteria) this;
        }

        public Criteria andConfIntervalUnitLikeInsensitive(String value) {
            addCriterion("upper(CONF_INTERVAL_UNIT) like", value.toUpperCase(), "confIntervalUnit");
            return (Criteria) this;
        }

        public Criteria andStatusProcessorLikeInsensitive(String value) {
            addCriterion("upper(STATUS_PROCESSOR) like", value.toUpperCase(), "statusProcessor");
            return (Criteria) this;
        }

        public Criteria andProcessingLikeInsensitive(String value) {
            addCriterion("upper(PROCESSING) like", value.toUpperCase(), "processing");
            return (Criteria) this;
        }

        public Criteria andExecutingLikeInsensitive(String value) {
            addCriterion("upper(EXECUTING) like", value.toUpperCase(), "executing");
            return (Criteria) this;
        }

        public Criteria andServiceNameLikeInsensitive(String value) {
            addCriterion("upper(SERVICE_NAME) like", value.toUpperCase(), "serviceName");
            return (Criteria) this;
        }

        public Criteria andValidLikeInsensitive(String value) {
            addCriterion("upper(VALID) like", value.toUpperCase(), "valid");
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